package org.wpattern.rest.cdi.product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.wpattern.rest.cdi.utils.GenericDaoImpl;

public class ProductDaoImpl extends GenericDaoImpl<ProductEntity> implements ProductData {

	public ProductDaoImpl() throws Exception {
		super("tb_product");
	}

	@Override
	protected ProductEntity parser(ResultSet result) throws Exception {
		Long id = result.getLong("id");
		String name = result.getString("name");
		String description = result.getString("description");
		BigDecimal price = result.getBigDecimal("price");

		return new ProductEntity(id, name, description, price);
	}

	@Override
	protected ProductEntity insert(ProductEntity entity, Connection connection) throws Exception {
		String insertTableSQL = String.format("INSERT INTO %s (name, description, price) VALUES (?, ?, ?)", this.tableName);

		PreparedStatement statement = connection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);

		statement.setString(1, entity.getName());
		statement.setString(2, entity.getDescription());
		statement.setBigDecimal(3, entity.getPrice());

		int affectedRows = statement.executeUpdate();

		if (affectedRows == 0) {
			throw new SQLException("Creating user failed, no rows affected.");
		}

		try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				entity.setId(generatedKeys.getLong(1));
			}
			else {
				throw new SQLException("Creating user failed, no ID obtained.");
			}
		}

		return entity;
	}

	@Override
	protected void update(ProductEntity entity, Connection connection) throws Exception {
		String updateTableSQL = String.format("UPDATE %s SET id=?, name=?, description=?, price=? WHERE id = %s", this.tableName, entity.getId());

		PreparedStatement statement = connection.prepareStatement(updateTableSQL);

		statement.setLong(1, entity.getId());
		statement.setString(2, entity.getName());
		statement.setString(3, entity.getDescription());
		statement.setBigDecimal(4, entity.getPrice());

		statement.executeUpdate();
	}

}
