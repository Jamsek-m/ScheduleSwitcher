package com.mjamsek.model.predmet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjamsek.wrappers.EditPredmetWrapper;

@Service("predmetService")
public class PredmetServiceImpl implements PredmetService {
	
	@Autowired
	private PredmetRepository predmetRepo;

	@Override
	public List<Predmet> poisciVse() {
		return predmetRepo.findAll();
	}

	@Override
	public List<Predmet> poisciVseVLetniku(int letnik) {
		return predmetRepo.findByLetnik(letnik);
	}

	@Override
	public Predmet poisciPoOznaki(String oznaka) {
		return predmetRepo.findByOznaka(oznaka);
	}

	@Override
	public Predmet poisciPoId(int id) {
		return predmetRepo.findById(id);
	}

	@Override
	public void urediPredmet(EditPredmetWrapper p) {
		Predmet predmet = predmetRepo.findById(p.getId());
		predmet.setLetnik(p.getLetnik());
		predmet.setNosilec(p.getNosilec());
		predmet.setOznaka(p.getOznaka());
		predmet.setPredmet(p.getNaziv());
		predmet.setEnota(p.getEnota());
		
		predmetRepo.save(predmet);
	}

}
