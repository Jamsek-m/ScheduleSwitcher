package com.mjamsek.model.predmet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mjamsek.model.enota.Enota;

@Entity
@Table(name="predmet")
public class Predmet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="predmet_id")
	private int id;
	
	@Column(name="predmet")
	private String predmet;
	
	@Column(name="oznaka")
	private String oznaka;
	
	@Column(name="letnik")
	private int letnik;
	
	@Column(name="nosilec")
	private String nosilec;
	
	@ManyToOne
	@JoinColumn(name = "enota_id")
	private Enota enota;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPredmet() {
		return predmet;
	}

	public void setPredmet(String predmet) {
		this.predmet = predmet;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public String getNosilec() {
		return nosilec;
	}

	public void setNosilec(String nosilec) {
		this.nosilec = nosilec;
	}

	public int getLetnik() {
		return letnik;
	}

	public void setLetnik(int letnik) {
		this.letnik = letnik;
	}

	public Enota getEnota() {
		return enota;
	}

	public void setEnota(Enota enota) {
		this.enota = enota;
	}
	
}
