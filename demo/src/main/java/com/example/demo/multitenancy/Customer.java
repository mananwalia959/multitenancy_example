package com.example.demo.multitenancy;



import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("customer")
class Customer {

	@Id Integer id;
	String firstname;
	String lastname;

	boolean hasId() {
		return id != null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}
