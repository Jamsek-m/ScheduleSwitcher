package com.mjamsek.model.sporocilo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjamsek.model.uporabnik.Uporabnik;

@Service("sporociloService")
public class SporociloServiceImpl implements SporociloService {

	@Autowired
	private SporociloRepository sporRepo;
	
	@Override
	public List<Sporocilo> poisciVsaPoslanaSporocila(Uporabnik up) {
		return sporRepo.findByAvtor(up);
	}

	@Override
	public List<Sporocilo> poisciVsaPrejetaSporocila(Uporabnik up) {
		return sporRepo.findByPrejemnik(up);
	}

	@Override
	public Sporocilo poisciSporocilo(long id) {
		return sporRepo.findById(id);
	}

}
