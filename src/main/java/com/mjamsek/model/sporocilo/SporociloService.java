package com.mjamsek.model.sporocilo;

import java.util.List;

import com.mjamsek.model.uporabnik.Uporabnik;
import com.mjamsek.wrappers.SendSporociloWrapper;

public interface SporociloService {

	public List<Sporocilo> poisciVsaPoslanaSporocila(Uporabnik up);
	
	public List<Sporocilo> poisciVsaPrejetaSporocila(Uporabnik up);
	
	public Sporocilo poisciSporocilo(long id);
	
	public List<Sporocilo> poisciNeprebrana(Uporabnik up);
	
	public void oznaciKotPrebrano(Sporocilo sp);
	
	public void posljiSporocilo(SendSporociloWrapper sp);
	
	public long steviloNeprebranih();
	
}
