package com.example.simplestore.view;

import javax.annotation.PostConstruct;

import com.example.simplestore.entity.Client;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;

@UIScope
@SpringView(name = ClientBasicView.VIEW_NAME)
public class ClientBasicView extends AbstractView<Client> implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7373849509507663564L;
	public static final String VIEW_NAME = "client-basic";

	@PostConstruct
	void init() {
		super.init();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
	}
}