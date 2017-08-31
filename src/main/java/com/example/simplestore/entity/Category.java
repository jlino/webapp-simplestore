package com.example.simplestore.entity;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Category extends AbstractEntity {
	
	@OneToMany(mappedBy = "category")
	protected Map<Long, Product> products;
	
	public Category() {
		super();
	}
	public Category(String name, String description) {
		super(name, description);
	}
	public Category(String name, String description, Map<Long, Product> products) {
		super(name, description);
		this.products = products;
	}
	public Map<Long, Product> getProducts() {
		return products;
	}
	public void setProducts(Map<Long, Product> products) {
		this.products = products;
	}
	
}
