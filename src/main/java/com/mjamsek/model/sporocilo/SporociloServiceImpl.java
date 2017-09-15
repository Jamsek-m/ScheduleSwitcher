package com.mjamsek.model.sporocilo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.mjamsek.email.EmailService;
import com.mjamsek.model.uporabnik.Uporabnik;
import com.mjamsek.model.uporabnik.UporabnikService;
import com.mjamsek.wrappers.SendSporociloWrapper;

@Service("sporociloService")
public class SporociloServiceImpl implements SporociloService {

	@Autowired
	private SporociloRepository sporRepo;
	
	@Autowired
	private UporabnikService upbServ;
	
	@Autowired
	private EmailService emailServ;
	
	@Override
	public List<Sporocilo> poisciVsaPoslanaSporocila(Uporabnik up) {
		return sporRepo.findByAvtorOrderByPoslanoDesc(up);
	}

	@Override
	public List<Sporocilo> poisciVsaPrejetaSporocila(Uporabnik up) {
		return sporRepo.findByPrejemnikOrderByStatusAscPoslanoDesc(up);
	}

	@Override
	public Sporocilo poisciSporocilo(long id) {
		return sporRepo.findById(id);
	}

	@Override
	public List<Sporocilo> poisciNeprebrana(Uporabnik up) {
		return sporRepo.findUnreadMessages(up);
	}

	@Override
	public void oznaciKotPrebrano(Sporocilo sp) {
		sp.setStatus(1);
		sporRepo.save(sp);
	}

	@Override
	public void posljiSporocilo(SendSporociloWrapper sp) {
		Uporabnik avtor = upbServ.dobiTrenutnegaUporabnika();
		Date trenutniCas = new Date();
		List<Uporabnik> prejemniki = upbServ.poisciZImenom(sp.getPrejemnik());
		Uporabnik prejemnik = prejemniki.get(0);
		
		Sporocilo sporocilo = new Sporocilo();
		sporocilo.setAvtor(avtor);
		sporocilo.setPoslano(trenutniCas);
		sporocilo.setPrejemnik(prejemnik);
		sporocilo.setStatus(0);
		sporocilo.setZadeva(sp.getZadeva());
		sporocilo.setVsebina(sp.getVsebina());
		
		sporRepo.save(sporocilo);
		
		if(prejemnik.isPosljiEmail() && prejemnik.getAktiven() == 1) {
			Context emailContext = new Context();
			emailContext.setVariable("zadeva", sp.getZadeva());
			emailContext.setVariable("prejemnik", sp.getPrejemnik());
			emailContext.setVariable("vsebina", sp.getVsebina());
			emailContext.setVariable("poslano", trenutniCas);
			emailServ.posljiEmail(prejemnik.getEmail(), emailContext, "Novo Sporočilo", "email/sporocilo-email");
			
		}
		
		
	}

}
