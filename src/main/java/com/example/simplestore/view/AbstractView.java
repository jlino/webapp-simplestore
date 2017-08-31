package com.example.simplestore.view;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.simplestore.bean.AbstractBean;
import com.example.simplestore.filter.AbstractEntityFilter;
import com.vaadin.data.Binder;
import com.vaadin.data.Binder.Binding;
import com.vaadin.data.converter.StringToLongConverter;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;

@SpringView(name = AbstractView.VIEW_NAME)
public abstract class AbstractView<T> extends VerticalLayout implements View {
	private static final long serialVersionUID = 6942705882903885298L;
	public static final String VIEW_NAME = "basic";

	@Autowired
	AbstractBean<T> dataBean;

	@PostConstruct
	void init() {
		//setMargin(true);
		//setSpacing(true);
		//setSizeFull();

		Field fields[] = returnedClass().getFields();

		HorizontalLayout hlId = new HorizontalLayout();

		// Create filterable data source
		ConfigurableFilterDataProvider<T, Void, Map<String, Object>> everythingConfigurable = getFilteredDataProvider();
		Map<String, Object> parameters = new HashMap<String, Object>();
		everythingConfigurable.setFilter(parameters);

		// Display filtered data
		Grid<T> grid = new Grid<>(returnedClass());
		grid.setDataProvider(everythingConfigurable);

		//int sizeOfWindow = Page.getCurrent().getBrowserWindowWidth();
		Binder<T> binder = grid.getEditor().getBinder();

		// Populate view elements that depend on what fields  the T type has.
		HashMap<String, TextField> searchTextFields = new HashMap<String, TextField>();
		for (Field field : fields) {
			String fieldName = field.getName();

			// Make field searchable
			TextField searchField = new TextField(fieldName, "");
			searchField.addValueChangeListener(event -> {
				String recoveredFieldName = event.getComponent().getCaption();
				try {
					Field recoveredField = returnedClass().getField(recoveredFieldName);

					String filterValue = event.getValue();
					String getterName = "get" + recoveredFieldName.substring(0, 1).toUpperCase() + recoveredFieldName.substring(1);

					// Filter Numbers
					try {
						if(Number.class.isAssignableFrom(recoveredField.getType())) {
							if(filterValue.length() > 0 && filterValue.matches("^[0-9]+$")) {
								parameters.put(getterName, recoveredField.getType().getDeclaredConstructor(String.class).newInstance(filterValue));	
							}
							else {
								parameters.remove(getterName);
							}
						}

						// Filter character sequences
						else if(CharSequence.class.isAssignableFrom(recoveredField.getType())) {
							if(filterValue.length() > 0) {
								parameters.put(getterName, recoveredField.getType().getDeclaredConstructor(String.class).newInstance(filterValue));
							}
							else {
								parameters.remove(getterName);
							}
						}
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
						e.printStackTrace();
					}
				} catch (NoSuchFieldException | SecurityException e1) {
					e1.printStackTrace();
				}
				
				everythingConfigurable.setFilter(parameters);
			});
			searchTextFields.put(fieldName, searchField);
			hlId.addComponent(searchField);
			
			// Don't make IDs editable
			if(fieldName == "id") {
				continue;
			}
			
			// Make number fields editable
			if(Long.class.isAssignableFrom(field.getType())) {
				Binding<T, Long> binding = binder.forField(new TextField()).withConverter(new StringToLongConverter("ERROR: This is not a Long.")).bind(fieldName);
				Column<T, ?> column = grid.getColumn(fieldName);
				column.setEditorBinding(binding);
			}

			// Make character fields editable
			else if(CharSequence.class.isAssignableFrom(field.getType())) {
				Binding<T, String> binding = binder.bind(new TextField(), fieldName);
				Column<T, ?> column = grid.getColumn(fieldName);
				column.setEditorBinding(binding);
			}

			/* Make an AbstractEntity fields editable
			else if(AbstractEntity.class.isAssignableFrom(field.getType())) {
				Binding<T, Long> binding = binder.forField(new TextField()).withConverter(new StringToLongConverter("ERROR: This is not a Long.")).bind(fieldName);
				Column<T, ?> column = grid.getColumn(fieldName);
				column.setEditorBinding(binding);
			}

			// Make an Map fields editable
			else if(Map.class.isAssignableFrom(field.getType())) {
				Binding<T, Long> binding = binder.forField(new TextField()).withConverter(new StringToLongConverter("ERROR: This is not a Long.")).bind(fieldName);
				Column<T, ?> column = grid.getColumn(fieldName);
				column.setEditorBinding(binding);
			}*/
		}
		
		// Render a button that adds the data row (item)
		Column<T, String> addColumn = grid.addColumn(entity -> "Add",
				new ButtonRenderer<T>(clickEvent -> {
					try {
						T addedEntity = returnedClass().newInstance();
						Long entityId = dataBean.add(addedEntity);
						searchTextFields.get("id").setValue(entityId.toString());
						grid.setDataProvider(everythingConfigurable);
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}));
		addColumn.setMaximumWidth(90);
		
		// Render a button that deletes the data row (item)
		Column<T, String> deleteColumn = grid.addColumn(entity -> "Delete",
				new ButtonRenderer<T>(clickEvent -> {
					dataBean.remove((T) clickEvent.getItem());
					grid.setDataProvider(everythingConfigurable);
				}));
		deleteColumn.setMaximumWidth(100);
		
		// Setup grid
		grid.setWidth("100%");
		grid.getEditor().setEnabled(true);

		// Construct Abstract view
		addComponent(hlId);
		addComponent(grid);
	}

	public Class<T> returnedClass() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();

		@SuppressWarnings("unchecked")
		Class<T> ret = (Class<T>) parameterizedType.getActualTypeArguments()[0];

		return ret;
	}

	/**
	 * @return
	 */
	protected ConfigurableFilterDataProvider<T, Void, Map<String, Object>> getFilteredDataProvider() {
		DataProvider<T, AbstractEntityFilter> dataProvider =
				DataProvider.fromFilteringCallbacks(
						query -> {
							AbstractEntityFilter filter = query.getFilter().orElse(null);
							return  (Stream<T>) dataBean.fetchFilteredEntities(
									query.getOffset(),
									query.getLimit(),
									filter != null ? filter.parametersFilter : null
									).stream();
						},
						query -> {
							AbstractEntityFilter filter = query.getFilter().orElse(null);
							return dataBean.getCount(
									filter != null ? filter.parametersFilter : null
									);
						}
						);

		ConfigurableFilterDataProvider<T, Void, Map<String, Object>> configurableFilterDataProvider =
				dataProvider.withConfigurableFilter(
						// Can be shortened as PersonFilter::new
						(filterText, parameters) -> {
							return new AbstractEntityFilter(parameters);
						}
						);

		return configurableFilterDataProvider;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// the view is constructed in the init() method()
	}
}