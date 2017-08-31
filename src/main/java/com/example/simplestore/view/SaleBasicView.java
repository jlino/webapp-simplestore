package com.example.simplestore.view;

import javax.annotation.PostConstruct;

import com.example.simplestore.entity.Sale;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;


@UIScope
@SpringView(name = SaleBasicView.VIEW_NAME)
public class SaleBasicView extends AbstractView<Sale> implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5233197589640364749L;
	public static final String VIEW_NAME = "sale-basic";

	@PostConstruct
	void init() {
		super.init();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
	}
}