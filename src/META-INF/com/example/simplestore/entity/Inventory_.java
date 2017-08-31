package com.example.simplestore.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-08-12T00:48:46.032+0100")
@StaticMetamodel(Inventory.class)
public class Inventory_ extends AbstractEntity_ {
	public static volatile SingularAttribute<Inventory, Long> availableQuantity;
	public static volatile SingularAttribute<Inventory, Sale> sale;
	public static volatile SingularAttribute<Inventory, Product> product;
}
