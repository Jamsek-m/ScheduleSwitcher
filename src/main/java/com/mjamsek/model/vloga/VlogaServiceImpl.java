package com.mjamsek.model.vloga;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("vlogaService")
public class VlogaServiceImpl implements VlogaService {

	@Autowired
	private VlogaRepository vlRepo;
	
	@Override
	public List<Vloga> poisciVse() {
		return vlRepo.findAll();
	}

}
