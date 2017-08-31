package com.example.simplestore.entity;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Sale extends AbstractEntity {

	protected Client client;

	@OneToMany(mappedBy = "sale")
	protected Map<Long, Inventory> items;
	
	public Sale() {
		super();
	}
	public Sale(String name, String description) {
		super(name, description);
	}
	public Sale(String name, String description, Client client, Map<Long, Inventory> items) {
		super(name, description);
		this.client = client;
		this.items = items;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Map<Long, Inventory> getItems() {
		return items;
	}
	public void setItems(Map<Long, Inventory> items) {
		this.items = items;
	}

}
