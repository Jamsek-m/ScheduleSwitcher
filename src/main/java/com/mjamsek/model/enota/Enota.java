package com.mjamsek.model.enota;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="enota")
public class Enota {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="enota_id")
	private int id;
	
	@Column(name="oznaka")
	private String oznaka;
	
	@Column(name="naziv")
	private String naziv;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
}
