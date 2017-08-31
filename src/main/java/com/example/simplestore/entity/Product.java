package com.example.simplestore.entity;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Product extends AbstractEntity  {

	protected Double tax;
	
	@OneToMany(mappedBy = "product")
	protected Map<Long, Inventory> inventories;

	@ManyToOne
	protected Category category;
	
	public Product() {
		super();
	}
	public Product(String name, String description) {
		super(name, description);
	}

	public Product(String name, String description, Double tax, Category category) {
		super(name, description);
		this.tax = tax;
		this.category = category;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Category getCategories() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
