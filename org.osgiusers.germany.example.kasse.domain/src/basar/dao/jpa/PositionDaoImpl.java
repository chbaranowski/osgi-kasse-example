package basar.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import basar.dao.PositionDao;
import basar.domain.Configuration;
import basar.domain.Position;
import basar.domain.PositionKey;

@Repository("positionDAO")
public class PositionDaoImpl implements PositionDao {

	private Configuration configuration;
	
	protected EntityManager entityManager;
	
	private volatile int number = -1;
	
	@Autowired
	public void setConfiguration(Configuration configuration){
		this.configuration = configuration;
	}
	
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional(readOnly=true)
	public Position getPosition(PositionKey positionKey) {
		return entityManager.find(Position.class, positionKey);
	}

	@Transactional
	public void insertPosition(Position position) {
		entityManager.persist(position);
	}

	@Transactional
	public void updatePosition(Position position) {
		Position merge = entityManager.merge(position);
	}
	
	@Transactional
	public void deletePosition(Position position) {
		Position merge = entityManager.merge(position);
		entityManager.remove(merge);
	}

	@Transactional
	public PositionKey createPositionKey() {
		PositionKey positionKey = new PositionKey();
		positionKey.setKasse(configuration.getKasseName());
		positionKey.setNumber(getNextNumber());
		return positionKey;
	}
	
	@Transactional(readOnly=true)
	public List<Position> getPositionList() {
		Query query = entityManager.createQuery("SELECT p FROM Position p ORDER BY p.createTime DESC");
		return query.getResultList();
	}
	
	private synchronized int getNextNumber(){
		if(number == -1){
			Query query = entityManager.createNativeQuery(
					"Select number FROM position where kasse='"+configuration.getKasseName()+"' ORDER BY number DESC");
			if(query.getResultList() != null && query.getResultList().size() > 0){
				Number number = (Number) query.getResultList().get(0);
				this.number = number.intValue();
			}
		}
		this.number++;
		return this.number;
	}
	
}
