package com.example.simplestore.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-08-12T01:02:20.501+0100")
@StaticMetamodel(Product.class)
public class Product_ extends AbstractEntity_ {
	public static volatile SingularAttribute<Product, Double> tax;
	public static volatile MapAttribute<Product, Long, Inventory> inventories;
	public static volatile SingularAttribute<Product, Category> category;
}
