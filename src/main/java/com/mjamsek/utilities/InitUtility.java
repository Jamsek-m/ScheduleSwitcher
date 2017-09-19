package com.mjamsek.utilities;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mjamsek.model.enota.Enota;
import com.mjamsek.model.enota.EnotaRepository;
import com.mjamsek.model.uporabnik.Uporabnik;
import com.mjamsek.model.uporabnik.UporabnikRepository;
import com.mjamsek.model.vloga.Vloga;
import com.mjamsek.model.vloga.VlogaRepository;
import com.mjamsek.model.zahteva.TipZahteve;
import com.mjamsek.model.zahteva.TipZahteveRepository;

@Service
public class InitUtility {

	@Autowired
	private UporabnikRepository upbRepo;
	
	@Autowired
	private VlogaRepository vlRepo;
	
	@Autowired
	private TipZahteveRepository tipRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	@Autowired
	private EnotaRepository enRepo;
	
	public void initPodatkovneBaze() {
		System.err.println("================= INIT BAZE ====================");
		
		//init vloge
		int stVlog = vlRepo.findAll().size();
		Vloga MOD = new Vloga();
		MOD.setVloga("MOD");
		Vloga ADMIN = new Vloga();
		ADMIN.setVloga("ADMIN");
		Vloga USER = new Vloga();
		USER.setVloga("USER");
		if(stVlog == 0) {
			vlRepo.save(MOD);
			vlRepo.save(ADMIN);
			vlRepo.save(USER);
			System.err.println("======= init vloge =======");
		}
		
		//init enot
		Enota UNI = new Enota();
		UNI.setNaziv("Univerzitetni");
		UNI.setOznaka("UNI");
		Enota VSS = new Enota();
		VSS.setNaziv("Visokošolski");
		VSS.setOznaka("VSŠ");
		int stEnot = enRepo.findAll().size();
		if(stEnot == 0) {
			enRepo.save(UNI);
			enRepo.save(VSS);
			System.err.println("======= init enote =======");
		}
		
		//init admin racuna
		int stUporabnikov = upbRepo.findAll().size();
		Uporabnik admin = new Uporabnik();
		admin.setAktiven(1);
		admin.setEmail("miha_jamsek@windowslive.com");
		admin.setGeslo(bCrypt.encode("geslo123"));
		admin.setIme("Miha");
		admin.setLetnik(3);
		admin.setPosljiEmail(true);
		admin.setUporabniskoIme("miha");
		HashSet<Vloga> vloge = new HashSet<Vloga>();
		vloge.add(USER);
		vloge.add(ADMIN);
		vloge.add(MOD);
		admin.setVloge(vloge);
		admin.setEnota(UNI);
		if(stUporabnikov == 0) {
			upbRepo.save(admin);
			System.err.println("======= init admina =======");
		}
		
		int stTipov = tipRepo.findAll().size();
		TipZahteve zaMod = new TipZahteve();
		zaMod.setNaziv("Prošnja za moderatorstvo");
		zaMod.setSkrbnik(MOD);
		if(stTipov == 0) {
			tipRepo.save(zaMod);
			System.err.println("======= init tipa zahtev =======");
		}
		System.err.println("\n\n");
	}
	
}
