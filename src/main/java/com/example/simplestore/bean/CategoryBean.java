package com.example.simplestore.bean;

import com.example.simplestore.entity.Category;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class CategoryBean extends AbstractBean<Category> {
	
	public void initDataStore() {
		super.initDataStore();
		for( int i = 0; i <  20; i++ ) {
			add(new Category("Category " + i, "This was just another category" + i));
		}
	}
}
