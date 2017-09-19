package com.mjamsek.model.uporabnik;

import java.util.List;

import com.mjamsek.wrappers.EditUserWrapper;
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
	
	public void makeMod(Uporabnik uporabnik);
	
	public List<Uporabnik> vrniVsePoStrani(int stran);
	
	public long vrniSteviloUporabnikov();
	
	public void adminUrediUporabnika(EditUserWrapper up, String hostname);
	
	public void zamenjajEmail(Uporabnik up, String email, String hostname);
	
	public void ponastaviGeslo(String hostname, long id);
	
}
