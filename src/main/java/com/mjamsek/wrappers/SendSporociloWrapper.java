package com.mjamsek.wrappers;

public class SendSporociloWrapper {
	
	private String prejemnik;
	private String zadeva;
	private String vsebina;
	
	public SendSporociloWrapper(String p, String z, String v) {
		this.prejemnik = p;
		this.zadeva = z;
		this.vsebina = v;
	}
	
	public SendSporociloWrapper() {
		
	}

	public String getPrejemnik() {
		return prejemnik;
	}

	public void setPrejemnik(String prejemnik) {
		this.prejemnik = prejemnik;
	}

	public String getZadeva() {
		return zadeva;
	}

	public void setZadeva(String zadeva) {
		this.zadeva = zadeva;
	}

	public String getVsebina() {
		return vsebina;
	}

	public void setVsebina(String vsebina) {
		this.vsebina = vsebina;
	}
	
}
