package com.mjamsek.model.sporocilo;

import java.util.List;

import com.mjamsek.model.uporabnik.Uporabnik;

public interface SporociloService {

	public List<Sporocilo> poisciVsaPoslanaSporocila(Uporabnik up);
	
	public List<Sporocilo> poisciVsaPrejetaSporocila(Uporabnik up);
	
	public Sporocilo poisciSporocilo(long id);
	
}
