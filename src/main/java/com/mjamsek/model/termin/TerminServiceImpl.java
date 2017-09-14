package com.mjamsek.model.termin;

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
	public List<Termin> poisciIzbraneTermine(DanUra danUra) {
		return termRepo.findByCasAndDan(danUra.getUra(), danUra.getDan());
	}

}
