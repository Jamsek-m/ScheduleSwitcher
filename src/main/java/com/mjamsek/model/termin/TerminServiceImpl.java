package com.mjamsek.model.termin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjamsek.model.predmet.Predmet;
import com.mjamsek.model.predmet.PredmetService;
import com.mjamsek.model.uporabnik.Uporabnik;
import com.mjamsek.model.uporabnik.UporabnikService;
import com.mjamsek.utilities.DanUra;

@Service("terminService")
public class TerminServiceImpl implements TerminService {

	@Autowired
	private TerminRepository termRepo;
	
	@Autowired
	private UporabnikService upbServ;
	
	@Autowired
	private PredmetService predServ;
	
	private static final int TIP_ISKANI_TERMIN = 1;
	
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
	
	public List<Termin> poisciPonujeneTermineZaTvojTermin(Termin termin){
		Uporabnik iskalec = termin.getLastnik();
		Predmet iskaniPredmet = termin.getPredmet();
		int iskaniCas = termin.getCas();
		int iskaniDan = termin.getDan();
		
		return termRepo.findByPredmetAndTipAndDanAndCasAndLastnikNot(iskaniPredmet, TIP_ISKANI_TERMIN, iskaniDan, iskaniCas, iskalec);
	}

}
