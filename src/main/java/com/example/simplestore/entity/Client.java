package com.example.simplestore.entity;

import javax.persistence.Entity;

@Entity
public class Client extends AbstractEntity  {

	protected Long phoneNumber;
	protected String email;
	protected Long nif;
	
	public Client() {
		super();
	}
	public Client(Long phoneNumber, String email, Long nif) {
		super();
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.nif = nif;
	}
	public Client(String name, String description, Long phoneNumber, String email, Long nif) {
		super(name, description);
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.nif = nif;
	}
	public Client(String name, String description) {
		super(name, description);
	}
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getNif() {
		return nif;
	}
	public void setNif(Long nif) {
		this.nif = nif;
	}

}
