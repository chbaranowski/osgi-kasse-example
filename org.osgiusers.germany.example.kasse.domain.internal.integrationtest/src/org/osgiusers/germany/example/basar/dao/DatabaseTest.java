package org.osgiusers.germany.example.basar.dao;

import java.io.InputStream;
import java.sql.Connection;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false)
public abstract class DatabaseTest {

	/**
	 * The SimpleJdbcTemplate that this base class manages, available to
	 * subclasses.
	 */
	protected SimpleJdbcTemplate simpleJdbcTemplate;

	private DataSource dataSource;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	@Before
	public void setUp() throws Exception {
		DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());
	}

	@After
	public void tearDown() {

	}

	protected IDatabaseConnection getConnection() throws Exception {
		Connection connection = DataSourceUtils.getConnection(dataSource);
		return new DatabaseConnection(connection);
	}

	public IDataSet getDataSet() throws Exception {
		String name = "/" + getClass().getName().replace('.', '/')
				+ "DataSet.xml";
		InputStream resourceAsStream = getClass().getResourceAsStream(name);
		return new XmlDataSet(resourceAsStream);
	}

}
