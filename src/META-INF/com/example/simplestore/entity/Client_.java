package com.example.simplestore.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-08-12T00:23:27.557+0100")
@StaticMetamodel(Client.class)
public class Client_ extends AbstractEntity_ {
	public static volatile SingularAttribute<Client, Long> phoneNumber;
	public static volatile SingularAttribute<Client, Long> nif;
	public static volatile SingularAttribute<Client, String> email;
}
