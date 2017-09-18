package com.mjamsek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mjamsek.model.predmet.Predmet;
import com.mjamsek.model.predmet.PredmetService;
import com.mjamsek.model.sporocilo.SporociloService;
import com.mjamsek.model.termin.Termin;
import com.mjamsek.model.termin.TerminService;
import com.mjamsek.model.uporabnik.Uporabnik;
import com.mjamsek.model.uporabnik.UporabnikService;

@Controller
@RequestMapping("/predmeti")
public class PredmetiController {

	@Autowired
	private UporabnikService upbServ;
	
	@Autowired
	private PredmetService predServ;
	
	@Autowired
	private TerminService terServ;
	
	@Autowired
	private SporociloService sporServ;
	
	@GetMapping("/moji-predmeti")
	public String loadMojiPredmetPage(Model model) {
		Uporabnik trenutniUporabnik = upbServ.dobiTrenutnegaUporabnika();
		model.addAttribute("trenutniUporabnik", trenutniUporabnik);
		long stNeprebranih = sporServ.steviloNeprebranih();
		model.addAttribute("stNeprebranih", stNeprebranih);
		
		List<Termin> mojiTermini = terServ.poisciSvojeTermine(trenutniUporabnik);
		model.addAttribute("mojiTermini", mojiTermini);
		
		List<Predmet> vsiPredmeti = predServ.poisciVse();
		model.addAttribute("vsiPredmeti", vsiPredmeti);
		
		Termin noviTermin = new Termin();
		model.addAttribute("noviTermin", noviTermin);
		
		return "predmeti/moji-predmeti-page";
	}
	
	
}
