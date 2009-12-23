package basar.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import basar.dao.SellerDao;
import basar.domain.Seller;

public class SellerDaoImpl implements SellerDao {

	protected EntityManager entityManager;
	
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Transactional(readOnly=true)
	public Seller getSeller(long basarNumber) {
		
		Seller seller = entityManager.find(Seller.class, basarNumber);
		return seller;
	}
	
	
	@Transactional(readOnly=true)
	public List<Seller> getSellerList() {
		Query query = entityManager.createQuery("SELECT e FROM Seller e ORDER BY e.basarNumber ASC");
		List<Seller> result = (List<Seller>) query.getResultList();
		return result;
	}

	@Transactional
	public void deleteSeller(Seller seller) {
		Seller merge = entityManager.merge(seller);
		entityManager.remove(merge);
	}

	@Transactional
	public void insertSeller(Seller seller) {
		entityManager.persist(seller);
	}
	
	@Transactional
	public void updateSeller(Seller seller) {
		Seller merge = entityManager.merge(seller);
	}

}
