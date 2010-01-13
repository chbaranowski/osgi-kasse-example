package org.osgiusers.germany.example.basar.dao;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;
import org.osgiusers.germany.example.basar.domain.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.jdbc.SimpleJdbcTestUtils;


@ContextConfiguration(locations = { "/test/spring/test-context.xml" })
public class SellerDaoTest extends DatabaseTest {

	@Autowired
	private SellerDao sellerDao;

	@Test
	public void testInsertSeller() throws Exception {
		Seller seller = new Seller();
		seller.setBasarNumber(new Long(130));
		seller.setName("Baranowski");
		sellerDao.insertSeller(seller);

		int countRowsInSellerTable = SimpleJdbcTestUtils.countRowsInTable(
				simpleJdbcTemplate, "seller");
		assertEquals(3, countRowsInSellerTable);
	}

	@Test
	public void testUpdateSeller() throws Exception {
		Seller seller = sellerDao.getSeller(100);
		seller.setName("Neuer Name");
		sellerDao.updateSeller(seller);

		seller = sellerDao.getSeller(100);
		assertEquals("Neuer Name", seller.getName());
	}

	@Test
	public void testGetSeller() throws Exception {
		Seller seller = sellerDao.getSeller(100);

		assertNotNull(seller);
		assertEquals(Long.valueOf(100), seller.getBasarNumber());
		assertEquals("Test Kunde", seller.getName());
		assertEquals(2, seller.getPositions().size());
	}

	@Test
	public void testGetSeller_NonExistingSeller() throws Exception {
		Seller seller = sellerDao.getSeller(0);
		assertNull(seller);
	}

	@Test
	public void testDeleteSeller() throws Exception {
		Seller seller = sellerDao.getSeller(100);

		sellerDao.deleteSeller(seller);

		int countRowsInSellerTable = SimpleJdbcTestUtils.countRowsInTable(
				simpleJdbcTemplate, "seller");
		assertEquals(1, countRowsInSellerTable);
		int countRowsInPositionTable = SimpleJdbcTestUtils.countRowsInTable(
				simpleJdbcTemplate, "position");
		assertEquals(0, countRowsInPositionTable);
	}

	@Test
	public void testGetSellerList() throws Exception {
		Collection<Seller> sellers = sellerDao.getSellerList();
		assertNotNull(sellers);
		assertEquals(2, sellers.size());
	}

}
