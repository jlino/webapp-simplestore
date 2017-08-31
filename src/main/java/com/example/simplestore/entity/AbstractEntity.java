package com.example.simplestore.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.google.gson.Gson;

@MappedSuperclass
public abstract class AbstractEntity implements IEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	protected String name;
	protected String description;

	public AbstractEntity() {
		super();
	}
	public AbstractEntity(String name, String description) {
		super();
		this.id = 0L;
		this.name = name;
		this.description = description;
	}
	public AbstractEntity(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	public Object toJSON() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	public String toString() {
		return toJSON().toString();
	}
}
