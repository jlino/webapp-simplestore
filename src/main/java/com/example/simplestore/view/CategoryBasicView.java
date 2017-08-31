package com.example.simplestore.view;

import javax.annotation.PostConstruct;

import com.example.simplestore.entity.Category;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;


@UIScope
@SpringView(name = CategoryBasicView.VIEW_NAME)
public class CategoryBasicView extends AbstractView<Category> implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 998786475717399335L;
	public static final String VIEW_NAME = "category-basic";

	@PostConstruct
	void init() {
		super.init();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
	}
}