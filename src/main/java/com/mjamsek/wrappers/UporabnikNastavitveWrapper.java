package com.mjamsek.wrappers;

public class UporabnikNastavitveWrapper {

	private long id;
	
	private boolean posljiEmail;
	
	private String geslo;
	
	private String email;
	
	private int letnik;
	
	private int enota_id;
	
	public UporabnikNastavitveWrapper() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isPosljiEmail() {
		return posljiEmail;
	}

	public void setPosljiEmail(boolean posljiEmail) {
		this.posljiEmail = posljiEmail;
	}

	public String getGeslo() {
		return geslo;
	}

	public void setGeslo(String geslo) {
		this.geslo = geslo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getLetnik() {
		return letnik;
	}

	public void setLetnik(int letnik) {
		this.letnik = letnik;
	}

	public int getEnota_id() {
		return enota_id;
	}

	public void setEnota_id(int enota_id) {
		this.enota_id = enota_id;
	}
	
}
