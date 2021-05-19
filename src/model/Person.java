package model;

import java.sql.Date;

public class Person {
	
	private String name;
	private int id;
	private Date birthDate;
	
	
	
	public Person(String name, int id, Date birthDate) {
		super();
		this.name = name;
		this.id = id;
		this.birthDate = birthDate;
	}

	public String getName() {
		return name;
	}

}
