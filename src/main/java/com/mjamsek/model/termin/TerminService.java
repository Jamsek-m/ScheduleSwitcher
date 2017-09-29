package com.mjamsek.model.termin;

import java.util.List;

import com.mjamsek.model.uporabnik.Uporabnik;
import com.mjamsek.utilities.DanUra;

public interface TerminService {

	public List<Termin> poisciPonujeneSvojeTermine(Uporabnik up);
	
	public List<Termin> poisciIskaneSvojeTermine(Uporabnik up);
	
	//public List<Termin> poisciIzbraneTermine(List<DanUra> termini);
	
	//public List<Termin> poisciPonujeneTermineZaTvojTermin(Termin termin);
	
	public void shraniIskanTermin(Termin t, Uporabnik up);
	
	public List<Termin> poisciIskaneTermine(Uporabnik up);
	
	public void shraniPonujenTermin(Termin t, Uporabnik u);
	
	public void izbrisiTermin(long id);
	
	public void spremeniStatus(long id);
}
