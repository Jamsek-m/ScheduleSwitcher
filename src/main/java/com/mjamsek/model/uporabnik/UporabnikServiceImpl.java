package com.mjamsek.model.uporabnik;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mjamsek.model.vloga.Vloga;
import com.mjamsek.model.vloga.VlogaRepository;
import com.mjamsek.utilities.RegistrationUtility;

@Service("uporabnikService")
public class UporabnikServiceImpl implements UporabnikService {

	@Autowired
	private UporabnikRepository upRepo;
	
	@Autowired
	private VlogaRepository vlRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	@Override
	public Uporabnik poisciUporabnikaZUporabniskimImenom(String upime) {
		return upRepo.findByUporabniskoIme(upime);
	}

	@Override
	public void shraniUporabnika(Uporabnik up) {
		up.setGeslo(bCrypt.encode(up.getGeslo()));
		up.setAktiven(RegistrationUtility.vrniAktivacijskoStevilko(upRepo));
		up.setPosljiEmail(true);
		Vloga uporabnikovaVloga = vlRepo.findByVloga("USER");
		up.setVloge(new HashSet<Vloga>(Arrays.asList(uporabnikovaVloga)));
		upRepo.save(up);
	}

	@Override
	public List<Uporabnik> poisciVse() {
		return upRepo.findAll();
	}

	@Override
	public Uporabnik poisciZId(long id) {
		return upRepo.findById(id);
	}

	@Override
	public void urediUporabnika(Uporabnik up) {
		//TODO: Implement
	}

	@Override
	public void deaktivirajUporabnika(long id) {
		Uporabnik up = upRepo.findById(id);
		up.setAktiven(0);
		upRepo.save(up);
	}

	@Override
	public Uporabnik poisciPrekoAktivacijskegaKljuca(int kljuc) {
		return upRepo.findByAktiven(kljuc);
	}

	@Override
	public void aktivirajUporabnika(long id) {
		Uporabnik up = upRepo.findById(id);
		up.setAktiven(1);
		upRepo.save(up);
	}

}
