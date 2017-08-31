package com.example.simplestore.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.simplestore.view.SaleBasicView;
import com.example.simplestore.view.CategoryBasicView;
import com.example.simplestore.view.ClientBasicView;
import com.example.simplestore.view.InventoryBasicView;
import com.example.simplestore.view.ProductBasicView;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
public class RootUI extends UI {

	/**
	 * Generated serial version
	 */
	private static final long serialVersionUID = -4628478571572655803L;

	@Autowired
    private SpringViewProvider viewProvider;

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        //root.setMargin(true);
        //root.setSpacing(true);
        setContent(root);

        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        navigationBar.addComponent(createNavigationButton("Inventory", InventoryBasicView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Sale", SaleBasicView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Client", ClientBasicView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Product", ProductBasicView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Category", CategoryBasicView.VIEW_NAME));
        root.addComponent(navigationBar);

        final Panel viewContainer = new Panel();
        viewContainer.setSizeFull();
        root.addComponent(viewContainer);
        root.setExpandRatio(viewContainer, 1.0f);

        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addProvider(viewProvider);
    }

    private Button createNavigationButton(String caption, final String viewName) {
        Button button = new Button(caption);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        // If you didn't choose Java 8 when creating the project, convert this to an anonymous listener class
        button.addClickListener(event -> getUI().getNavigator().navigateTo(viewName));
        return button;
    }
}
