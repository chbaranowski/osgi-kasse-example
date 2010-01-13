package org.osgiusers.germany.example.basar.dao;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.osgiusers.germany.example.basar.domain.Configuration;
import org.osgiusers.germany.example.basar.domain.Position;
import org.osgiusers.germany.example.basar.domain.PositionKey;
import org.osgiusers.germany.example.basar.domain.PositionType;
import org.osgiusers.germany.example.basar.domain.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.jdbc.SimpleJdbcTestUtils;


@ContextConfiguration(locations = { "/test/spring/test-context.xml" })
public class PositionDaoTest extends DatabaseTest {

	@Autowired
	private PositionDao positionDao;

	@Autowired
	private SellerDao sellerDao;

	@Autowired
	private Configuration configuration;

	@Test
	public void testGetPosition() throws Exception {

		PositionKey positionKey = new PositionKey("kasseA", 1);
		Position position = positionDao.getPosition(positionKey);

		assertNotNull(position);
		assertEquals(10, position.getPrice(), 0);
		assertEquals("Hose", position.getDescription());
		assertEquals("Test Kunde", position.getSeller().getName());
		assertEquals(PositionType.SALE, position.getType());
	}

	@Test
	public void testInsertPosition() throws Exception {

		Seller seller = sellerDao.getSeller(100);

		PositionKey positionKey = positionDao.createPositionKey();

		Position position = new Position();
		position.setPositionKey(positionKey);
		position.setPrice(21);
		position.setDescription("Kleid");
		position.setSeller(seller);
		position.setType(PositionType.SALE);
		position.setCreateTime(new Date());

		positionDao.insertPosition(position);

		int countRowsInPositionTable = SimpleJdbcTestUtils.countRowsInTable(
				simpleJdbcTemplate, "position");
		assertEquals(3, countRowsInPositionTable);
	}

	@Test
	public void testUpdatePosition() throws Exception {
		PositionKey positionKey = new PositionKey("kasseA", 1);
		Position position = positionDao.getPosition(positionKey);

		position.setDescription("Fahrrad");
		positionDao.updatePosition(position);

		position = positionDao.getPosition(positionKey);

		assertEquals("Fahrrad", position.getDescription());
	}

	@Test
	public void testDeletePosition() throws Exception {

		PositionKey positionKey = new PositionKey("kasseA", 1);
		Position position = positionDao.getPosition(positionKey);

		positionDao.deletePosition(position);

		int countRowsInPositionTable = SimpleJdbcTestUtils.countRowsInTable(
				simpleJdbcTemplate, "position");
		assertEquals(1, countRowsInPositionTable);

		int countRowsInSellerTable = SimpleJdbcTestUtils.countRowsInTable(
				simpleJdbcTemplate, "seller");
		assertEquals(1, countRowsInSellerTable);
	}

	@Test
	public void testGetPositionList() throws Exception {
		List<Position> positionList = positionDao.getPositionList();

		Position position = positionList.get(0);
		assertEquals(new PositionKey("kasseB", 1), position.getPositionKey());
		position = positionList.get(1);
		assertEquals(new PositionKey("kasseA", 1), position.getPositionKey());
	}

}
