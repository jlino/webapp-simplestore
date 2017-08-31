package com.example.simplestore.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Inventory extends AbstractEntity  {

	protected Long	availableQuantity;
	
	@ManyToOne
	protected Product product;
	
	@ManyToOne
	protected Sale sale;

	public Inventory() {
		super();
	}
	public Inventory(String name, String description) {
		super(name, description);
	}
	public Inventory(String name, String description, Long availableQuantity, Product product, Sale sale) {
		super(name, description);
		this.availableQuantity = availableQuantity;
		this.product = product;
		this.sale = sale;
	}

	public Long getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Long availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	public Sale getSale() {
		return sale;
	}
	public void setSale(Sale sale) {
		this.sale = sale;
	}

}
