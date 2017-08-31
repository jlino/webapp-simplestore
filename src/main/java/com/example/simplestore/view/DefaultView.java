package com.example.simplestore.view;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = DefaultView.VIEW_NAME)
public class DefaultView extends VerticalLayout implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3453188440903879027L;
	public static final String VIEW_NAME = "";

    @PostConstruct
    void init() {
        addComponent(new Label("Hi, this is a bar. What can I get you?"));
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // the view is constructed in the init() method()
    }
}