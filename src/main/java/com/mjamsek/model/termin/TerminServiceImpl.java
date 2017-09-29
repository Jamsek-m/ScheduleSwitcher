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
	
	@Override
	public List<Termin> poisciPonujeneSvojeTermine(Uporabnik up) {
		return termRepo.findByLastnikAndTip(up, Termin.TIP_PONUJEN_TERMIN);
	}

	/*@Override
	public List<Termin> poisciIzbraneTermine(List<DanUra> termini) {
		List<Termin> rezultat = new ArrayList<Termin>();
		
		for(DanUra du : termini) {
			List<Termin> vmesniRezultat = termRepo.findByCasAndDan(du.getUra(), du.getDan());
			if(!vmesniRezultat.isEmpty()) {
				rezultat.addAll(vmesniRezultat);
			}
		}
		return rezultat;
	}*/
	
	/*public List<Termin> poisciPonujeneTermineZaTvojTermin(Termin termin){
		Uporabnik iskalec = termin.getLastnik();
		Predmet iskaniPredmet = termin.getPredmet();
		int iskaniCas = termin.getCas();
		int iskaniDan = termin.getDan();
		
		return termRepo.findByPredmetAndTipAndDanAndCasAndStatusAndLastnikNot(iskaniPredmet, Termin.TIP_ISKAN_TERMIN, iskaniDan, iskaniCas, Termin.STATUS_PROST, iskalec);
	}*/

	@Override
	public void shraniIskanTermin(Termin t, Uporabnik up) {
		t.setLastnik(up);
		t.setStatus(Termin.STATUS_PROST);
		t.setTip(Termin.TIP_ISKAN_TERMIN);
		termRepo.save(t);
	}

	@Override
	public List<Termin> poisciIskaneSvojeTermine(Uporabnik up) {
		return termRepo.findByLastnikAndTip(up, Termin.TIP_ISKAN_TERMIN);
	}

	@Override
	public List<Termin> poisciIskaneTermine(Uporabnik up) {
		/* Pri velikem stevilu iskanih in ponujenih terminov zna ta funkcija delovati pocasi.
		 * Nadomestiti bi se jo dalo z sql poizvedbo, ki bi rezultate sortirala in grupirala 
		 * na bazi, ter vrnila samo prave izpise (na bazi se bi to izvedlo relativno hitro).
		 * */
		List<Termin> vsiMozniTermini = new ArrayList<Termin>();
		
		List<Termin> mojiPonujeniTermini = termRepo.findByLastnikAndTipAndStatus(up, Termin.TIP_PONUJEN_TERMIN, Termin.STATUS_PROST);
		List<Termin> mojiIskaniTermini = termRepo.findByLastnikAndTipAndStatus(up, Termin.TIP_ISKAN_TERMIN, Termin.STATUS_PROST);
		for(Termin item : mojiPonujeniTermini) {
			//seznam tistih ki iscejo ta termin
			List<Termin> elemsIz2 = termRepo.findByPredmetAndTipAndDanAndCasAndStatusAndLastnikNot(item.getPredmet(), Termin.TIP_ISKAN_TERMIN, item.getDan(), item.getCas(), Termin.STATUS_PROST, up);
			//izmed iskalcev tega termina izberi tiste, ki ponujajo kar jaz iscem
			for(Termin iskani : elemsIz2) {
				//kar ponuja trenutni iskalec
				Termin ponuja = termRepo.findByPredmetAndLastnikAndTipAndStatus(iskani.getPredmet(), iskani.getLastnik(), Termin.TIP_PONUJEN_TERMIN, Termin.STATUS_PROST);
				
				if(ponuja != null) {
					//ce je njegova ponudba v mojih iskanih terminih dodaj v rezultat
					for(Termin item3 : mojiIskaniTermini) {
						if(ponuja.primerjaj(item3)) {
							vsiMozniTermini.add(ponuja);
						}
					}
				}
			}
		}
		
		return vsiMozniTermini;
	}

	@Override
	public void shraniPonujenTermin(Termin t, Uporabnik up) {
		Termin obstojeci = termRepo.findByPredmetAndLastnik(t.getPredmet(), up);
		if(obstojeci != null) return;
		t.setTip(Termin.TIP_PONUJEN_TERMIN);
		t.setStatus(Termin.STATUS_PROST);
		t.setLastnik(up);
		termRepo.save(t);
	}

	
	@Override
	public void izbrisiTermin(long id) {
		termRepo.delete(id);
	}

	
	@Override
	public void spremeniStatus(long id) {
		Termin termin = termRepo.findById(id);
		if(termin.getStatus() == Termin.STATUS_PROST) {
			termin.setStatus(Termin.STATUS_ZASEDEN);
		} else {
			termin.setStatus(Termin.STATUS_PROST);
		}
		termRepo.save(termin);
	}

}
