package com.example.simplestore.bean;

import java.util.HashMap;

import com.example.simplestore.entity.Client;
import com.example.simplestore.entity.Inventory;
import com.example.simplestore.entity.Sale;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class SaleBean extends AbstractBean<Sale> {

	public void initDataStore() {
		super.initDataStore();
		for( int i = 0; i <  10; i++ ) {
			add(new Sale("Sale " + i, "This was just another sale" + i, new Client(),new HashMap<Long,Inventory>()));
		}
	}
}
