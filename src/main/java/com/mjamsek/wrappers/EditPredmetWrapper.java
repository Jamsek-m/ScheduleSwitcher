package com.mjamsek.wrappers;

import com.mjamsek.model.enota.Enota;

public class EditPredmetWrapper {
	
	private int id;
	
	private String oznaka;

	private String naziv;
	
	private String nosilec;
	
	private int letnik;
	
	private Enota enota;

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
