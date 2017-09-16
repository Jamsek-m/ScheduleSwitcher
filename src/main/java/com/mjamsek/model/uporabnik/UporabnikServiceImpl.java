package com.mjamsek.model.uporabnik;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.mjamsek.email.EmailService;
import com.mjamsek.model.enota.EnotaService;
import com.mjamsek.model.vloga.Vloga;
import com.mjamsek.model.vloga.VlogaRepository;
import com.mjamsek.utilities.RegistrationUtility;
import com.mjamsek.wrappers.UporabnikNastavitveWrapper;

@Service("uporabnikService")
public class UporabnikServiceImpl implements UporabnikService {

	@Autowired
	private UporabnikRepository upRepo;
	
	@Autowired
	private VlogaRepository vlRepo;
	
	@Autowired
	private EnotaService enServ;
	
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	@Autowired
	private EmailService emailServ;
	
	@Value("${politika.geslo.dolzina}")
	private int DOLZINA_GESLA;
	
	private static final int STATUS_AKTIVEN = 1;
	private static final int STATUS_NEAKTIVEN = 0;
	
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
	public void urediUporabnika(UporabnikNastavitveWrapper up, String hostname) {
		Uporabnik trenutniUporabnik = upRepo.findById(up.getId());
		if(!up.getEmail().equals(trenutniUporabnik.getEmail())) {
			trenutniUporabnik.setEmail(up.getEmail());
			trenutniUporabnik.setAktiven(RegistrationUtility.vrniAktivacijskoStevilko(upRepo));
			//poslji potrditveni email
			Context emailContext = new Context();
			emailContext.setVariable("kljuc", trenutniUporabnik.getAktiven());
			emailContext.setVariable("hostname", hostname);
			emailServ.posljiEmail(trenutniUporabnik.getEmail(), emailContext, "Potrditev emaila", "email/change-mail-email");
		}
		if(up.getGeslo().length() >= DOLZINA_GESLA) {
			trenutniUporabnik.setGeslo(bCrypt.encode(up.getGeslo()));
		}
		trenutniUporabnik.setEnota(enServ.poisciZId(up.getEnota_id()));
		trenutniUporabnik.setPosljiEmail(up.isPosljiEmail());
		trenutniUporabnik.setLetnik(up.getLetnik());
		upRepo.save(trenutniUporabnik);
	}

	@Override
	public void deaktivirajUporabnika(long id) {
		Uporabnik up = upRepo.findById(id);
		up.setAktiven(STATUS_NEAKTIVEN);
		upRepo.save(up);
	}

	@Override
	public Uporabnik poisciPrekoAktivacijskegaKljuca(int kljuc) {
		return upRepo.findByAktiven(kljuc);
	}

	@Override
	public void aktivirajUporabnika(long id) {
		Uporabnik up = upRepo.findById(id);
		up.setAktiven(STATUS_AKTIVEN);
		upRepo.save(up);
	}

	@Override
	public Uporabnik dobiTrenutnegaUporabnika() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String imeTrenutnegaUporabnika = auth.getName();
		return upRepo.findByUporabniskoIme(imeTrenutnegaUporabnika);
	}

	@Override
	public List<Uporabnik> poisciZImenom(String ime) {
		return upRepo.findByImeContaining(ime);
	}

	@Override
	public void makeMod(Uporabnik uporabnik) {
		Vloga moderator = vlRepo.findByVloga("MOD");
		uporabnik.getVloge().add(moderator);
		upRepo.save(uporabnik);
	}

}
