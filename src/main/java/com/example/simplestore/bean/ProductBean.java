package com.example.simplestore.bean;

import com.example.simplestore.entity.Category;
import com.example.simplestore.entity.Product;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class ProductBean extends AbstractBean<Product> {
	
	public void initDataStore() {
		super.initDataStore();
		for( int i = 0; i <  10; i++ ) {
			add(new Product("Product " + i, "This was just another product" + i, new Double(i), new Category()));
		}
	}
}
