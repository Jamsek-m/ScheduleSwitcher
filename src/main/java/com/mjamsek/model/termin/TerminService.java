package com.mjamsek.model.termin;

import java.util.List;

import com.mjamsek.model.uporabnik.Uporabnik;
import com.mjamsek.utilities.DanUra;

public interface TerminService {

	public List<Termin> poisciSvojeTermine(Uporabnik up);
	
	public List<Termin> poisciIzbraneTermine(List<DanUra> termini);
	
}
