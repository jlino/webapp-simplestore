package com.example.simplestore.bean;

import com.example.simplestore.entity.Client;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class ClientBean extends AbstractBean<Client> {
	
	public void initDataStore() {
		super.initDataStore();
		for( int i = 0; i <  20; i++ ) {
			add(new Client("Client " + i, "This was just another client" + i, new Long(i),new String("client@email.com"), new Long(i)));
		}
	}
}
