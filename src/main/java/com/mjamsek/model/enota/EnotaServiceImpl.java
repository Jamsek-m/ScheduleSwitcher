package com.mjamsek.model.enota;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("enotaService")
public class EnotaServiceImpl implements EnotaService {

	@Autowired
	private EnotaRepository enRepo;
	
	@Override
	public List<Enota> poisciVse() {
		return enRepo.findAll();
	}

	@Override
	public Enota poisciZId(int id) {
		return enRepo.findById(id);
	}

	@Override
	public void urediEnota(Enota enota) {
		enRepo.save(enota);
	}

}
