package com.mjamsek.model.uporabnik;

import java.util.List;

import com.mjamsek.wrappers.UporabnikNastavitveWrapper;

public interface UporabnikService {

	public Uporabnik poisciUporabnikaZUporabniskimImenom(String upime);
	
	public Uporabnik poisciPrekoAktivacijskegaKljuca(int kljuc);
	
	public List<Uporabnik> poisciZImenom(String ime);
	
	public void shraniUporabnika(Uporabnik up);
	
	public List<Uporabnik> poisciVse();
	
	public Uporabnik poisciZId(long id);
	
	public void urediUporabnika(UporabnikNastavitveWrapper up, String hostname);
	
	public void deaktivirajUporabnika(long id);
	
	public void aktivirajUporabnika(long id);
	
	public Uporabnik dobiTrenutnegaUporabnika();
	
}
