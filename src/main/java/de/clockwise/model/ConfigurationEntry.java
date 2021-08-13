package de.clockwise.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name = "franz")
public class ConfigurationEntry  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String key;

	private String value;

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}

	public void setKey(final String key) {
		this.key = key;
	}

}
