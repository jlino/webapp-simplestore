package com.example.simplestore.bean;

import com.example.simplestore.entity.Inventory;
import com.example.simplestore.entity.Product;
import com.example.simplestore.entity.Sale;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class InventoryBean extends AbstractBean<Inventory> {
	
	public void initDataStore() {
		super.initDataStore();
		for( int i = 0; i <  10; i++ ) {
			add(new Inventory("Inventory " + i, "This was just another inventory item" + i, new Long(i), new Product(), new Sale()));
		}
	}
}
