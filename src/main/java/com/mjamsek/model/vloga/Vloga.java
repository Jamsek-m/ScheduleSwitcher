package com.mjamsek.model.vloga;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vloga")
public class Vloga {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "vloga_id")
	private int id;

	@Column(name = "vloga")
	private String vloga;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVloga() {
		return vloga;
	}

	public void setVloga(String vloga) {
		this.vloga = vloga;
	}

}
