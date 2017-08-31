package com.example.simplestore.view;

import javax.annotation.PostConstruct;

import com.example.simplestore.entity.Product;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;

@UIScope
@SpringView(name = ProductBasicView.VIEW_NAME)
public class ProductBasicView extends AbstractView<Product> implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -753457469557047598L;
	public static final String VIEW_NAME = "product-basic";

	@PostConstruct
	void init() {
		super.init();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
	}
}