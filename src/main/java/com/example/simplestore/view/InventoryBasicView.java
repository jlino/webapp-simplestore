package com.example.simplestore.view;

import javax.annotation.PostConstruct;

import com.example.simplestore.entity.Inventory;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;

@UIScope
@SpringView(name = InventoryBasicView.VIEW_NAME)
public class InventoryBasicView extends AbstractView<Inventory> implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3578484130309494395L;
	public static final String VIEW_NAME = "inventory-basic";

	@PostConstruct
	void init() {
		super.init();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
	}
}