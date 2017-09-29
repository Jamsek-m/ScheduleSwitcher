package com.mjamsek.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mjamsek.model.enota.Enota;
import com.mjamsek.model.enota.EnotaService;
import com.mjamsek.model.sporocilo.SporociloService;
import com.mjamsek.model.uporabnik.Uporabnik;
import com.mjamsek.model.uporabnik.UporabnikService;
import com.mjamsek.model.zahteva.TipZahteve;
import com.mjamsek.model.zahteva.Zahteva;
import com.mjamsek.model.zahteva.ZahtevaService;
import com.mjamsek.wrappers.UporabnikNastavitveWrapper;

@Controller
@RequestMapping("/uporabnik")
public class UporabnikController {

	@Autowired
	private UporabnikService upbServ;
	
	@Autowired
	private EnotaService enServ;
	
	@Autowired
	private SporociloService sporServ;
	
	@Autowired
	private ZahtevaService zahServ;
	
	@GetMapping("/nastavitve")
	public String loadNastavitvePage(Model model) {
		Uporabnik trenutniUporabnik = upbServ.dobiTrenutnegaUporabnika();
		model.addAttribute("trenutniUporabnik", trenutniUporabnik);
		long stNeprebranih = sporServ.steviloNeprebranih();
		model.addAttribute("stNeprebranih", stNeprebranih);
		
		UporabnikNastavitveWrapper wrapper = new UporabnikNastavitveWrapper();
		wrapper.setEmail(trenutniUporabnik.getEmail());
		wrapper.setGeslo("");
		wrapper.setId(trenutniUporabnik.getId());
		wrapper.setLetnik(trenutniUporabnik.getLetnik());
		wrapper.setPosljiEmail(trenutniUporabnik.isPosljiEmail());
		wrapper.setEnota_id(trenutniUporabnik.getEnota().getId());
		
		
		model.addAttribute("user", wrapper);
		
		List<Enota> enote = enServ.poisciVse();
		model.addAttribute("enote", enote);
		
		return "uporabnik/nastavitve-page";
	}
	
	@PostMapping("/nastavitve")
	public String shraniNastavitve(@ModelAttribute(name="user") UporabnikNastavitveWrapper up, HttpServletRequest request) {		
		String hostname = "http://" + request.getServerName();
		if(request.getServerName().equals("localhost")) {
			hostname += ":" + request.getServerPort();
		}
		upbServ.urediUporabnika(up, hostname);
		return "redirect:/uporabnik/nastavitve";
	}
	
	@GetMapping("/nova-zahteva")
	public String loadNovaZahtevaPage(Model model) {
		Uporabnik trenutniUporabnik = upbServ.dobiTrenutnegaUporabnika();
		model.addAttribute("trenutniUporabnik", trenutniUporabnik);
		long stNeprebranih = sporServ.steviloNeprebranih();
		model.addAttribute("stNeprebranih", stNeprebranih);
		
		List<TipZahteve> vrsteZahtev = zahServ.vrniTipeZahtev();
		model.addAttribute("vrste", vrsteZahtev);
		Zahteva zahteva = new Zahteva();
		model.addAttribute("zahteva", zahteva);
		
		return "uporabnik/nova-zahteva-page";
	}
	
	@PostMapping("/nova-zahteva")
	public String sendZahteva(@ModelAttribute("zahteva") Zahteva zahteva) {
		zahServ.posljiZahtevo(zahteva);
		return "redirect:/";
	}
	
	@GetMapping("/moje-zahteve")
	public String loadMojeZahtevePage(Model model) {
		Uporabnik trenutniUporabnik = upbServ.dobiTrenutnegaUporabnika();
		model.addAttribute("trenutniUporabnik", trenutniUporabnik);
		long stNeprebranih = sporServ.steviloNeprebranih();
		model.addAttribute("stNeprebranih", stNeprebranih);
		
		List<Zahteva> zahteve = zahServ.vrniSvojeZahteve(trenutniUporabnik);
		model.addAttribute("zahteve", zahteve);
		return "uporabnik/moje-zahteve-page";
	}
	
	@GetMapping("/zahteva/{id}")
	public String loadZahtevaPage(@PathVariable("id") long id, Model model) {
		Uporabnik trenutniUporabnik = upbServ.dobiTrenutnegaUporabnika();
		model.addAttribute("trenutniUporabnik", trenutniUporabnik);
		long stNeprebranih = sporServ.steviloNeprebranih();
		model.addAttribute("stNeprebranih", stNeprebranih);
		
		Zahteva zahteva = zahServ.vrniZahtevoZId(id);
		model.addAttribute("zahteva", zahteva);
		
		return "uporabnik/zahteva-page";
	}
	
}
