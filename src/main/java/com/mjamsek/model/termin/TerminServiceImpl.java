package com.mjamsek.model.termin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjamsek.model.uporabnik.Uporabnik;
import com.mjamsek.utilities.DanUra;

@Service("terminService")
public class TerminServiceImpl implements TerminService {

	@Autowired
	private TerminRepository termRepo;
	
	@Override
	public List<Termin> poisciSvojeTermine(Uporabnik up) {
		return termRepo.findByLastnik(up);
	}

	@Override
	public List<Termin> poisciIzbraneTermine(List<DanUra> termini) {
		List<Termin> rezultat = new ArrayList<Termin>();
		
		for(DanUra du : termini) {
			List<Termin> vmesniRezultat = termRepo.findByCasAndDan(du.getUra(), du.getDan());
			if(!vmesniRezultat.isEmpty()) {
				rezultat.addAll(vmesniRezultat);
			}
		}
		return rezultat;
	}

}
