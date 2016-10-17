package org.wpattern.rest.cdi.product;

import java.math.BigDecimal;

import org.wpattern.rest.cdi.utils.BaseEntity;

public class ProductEntity extends BaseEntity {

	private static final long serialVersionUID = 201610160815L;

	private String name;

	private String description;

	private BigDecimal price;

	public ProductEntity() {
	}

	public ProductEntity(String name, String description, BigDecimal price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public ProductEntity(Long id, String name, String description, BigDecimal price) {
		setId(id);
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
