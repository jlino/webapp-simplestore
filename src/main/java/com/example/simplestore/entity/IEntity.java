package com.example.simplestore.entity;

public interface IEntity {
	
	public Long getId();
	public void setId(Long id);
	public String getName();
	public void setName(String name);
	public String getDescription();
	public void setDescription(String description);
	public Object toJSON();

}
