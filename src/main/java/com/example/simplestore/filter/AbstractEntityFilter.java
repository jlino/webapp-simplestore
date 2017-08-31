package com.example.simplestore.filter;

import java.util.Map;

public class AbstractEntityFilter {
	public final Map<String, Object> parametersFilter;

	public AbstractEntityFilter( Map<String, Object> parameters ) {
		parametersFilter = parameters;
	}
}