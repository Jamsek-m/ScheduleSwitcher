package com.mjamsek.model.predmet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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

}
