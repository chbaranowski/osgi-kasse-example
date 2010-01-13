package org.osgiusers.germany.example.basar.domain.logic;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.osgiusers.germany.example.basar.dao.DatabaseTest;
import org.osgiusers.germany.example.basar.domain.Position;
import org.osgiusers.germany.example.basar.domain.Sale;
import org.osgiusers.germany.example.basar.domain.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = { "/test/spring/test-context.xml" })
public class BasarKasseFacadeTest extends DatabaseTest {

	@Autowired
	private BasarKasseFacade kasse;

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() {
		super.tearDown();
	}

	@Test
	public void testPurchase() {
		assertNotNull(kasse);

		Seller sellerOne = kasse.getSeller(100);
		Seller sellerTwo = kasse.getSeller(101);

		Position hose = new Position();
		hose.setDescription("Hose");
		hose.setPrice(2150);
		hose.setSeller(sellerOne);

		Position kleid = new Position();
		kleid.setDescription("Kleid");
		kleid.setPrice(2150);
		kleid.setSeller(sellerTwo);

		Sale sale = new Sale();
		sale.addPosition(hose);
		sale.addPosition(kleid);

		kasse.purchase(sale);
	}


}