package org.wpattern.rest.cdi.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class GenericDaoImpl<T extends BaseEntity> implements GenericDao<T> {

	private static final Logger LOGGER = Logger.getLogger(GenericDao.class.getName());

	private static String PASSWORD = "123456";

	private static String USERNAME = "wpattern";

	private static String DATABASE = "cdi_rest";

	protected Connection connection;

	protected String tableName;

	public GenericDaoImpl(String tableName) throws Exception {
		this.tableName = tableName;

		Class.forName("com.mysql.jdbc.Driver");

		this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE + "?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
	}

	protected abstract T parser(ResultSet result) throws Exception;

	protected abstract T insert(T entity, Connection connection) throws Exception;

	protected abstract void update(T entity, Connection connection) throws Exception;

	@Override
	public List<T> findAll() {
		LOGGER.log(Level.FINE, "TESSSSSSSSSSSSSSSSSSSSSSSSTTTTTTTTTTTTTTTEEEEEEEEEEEEE");

		try {
			ArrayList<T> entities = new ArrayList<T>();

			ResultSet result = this.connection.createStatement().executeQuery("SELECT * FROM " + this.tableName);

			while (result.next()) {
				entities.add(this.parser(result));
			}

			return entities;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);

			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public T findById(Long id) {
		try {
			String query = String.format("SELECT * FROM %s AS t WHERE t.id = '%s'", this.tableName, id);

			ResultSet result = this.connection.createStatement().executeQuery(query);

			if (result.next()) {
				return this.parser(result);
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);

			throw new DaoException(e.getMessage(), e);
		}

		return null;
	}


	@Override
	public T insert(T entity) {
		try {
			return this.insert(entity, this.connection);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);

			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public void update(T entity) {
		try {
			this.update(entity, this.connection);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);

			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public void delete(T entity) {
		try {
			String query = String.format("DELETE FROM %s WHERE id = %s;", this.tableName, entity.getId());

			PreparedStatement statement = this.connection.prepareStatement(query);

			statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);

			throw new DaoException(e.getMessage(), e);
		}
	}

}
