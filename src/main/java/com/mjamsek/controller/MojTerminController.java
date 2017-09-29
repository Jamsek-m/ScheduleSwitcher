package com.mjamsek.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mjamsek.model.predmet.Predmet;
import com.mjamsek.model.predmet.PredmetService;
import com.mjamsek.model.sporocilo.SporociloService;
import com.mjamsek.model.termin.Termin;
import com.mjamsek.model.termin.TerminService;
import com.mjamsek.model.uporabnik.Uporabnik;
import com.mjamsek.model.uporabnik.UporabnikService;
import com.mjamsek.responseTypes.BasicResponse;
import com.mjamsek.utilities.DatumLocaleUtility;

@Controller
@RequestMapping("/termin")
public class MojTerminController {
	
	@Autowired
	private UporabnikService upbServ;
	
	@Autowired 
	private SporociloService sporServ;
	
	@Autowired
	private TerminService terServ;
	
	@Autowired
	private PredmetService predServ;

	@GetMapping({"/", ""})
	public String loadTerminPage(Model model, Locale locale) {
		Uporabnik trenutniUporabnik = upbServ.dobiTrenutnegaUporabnika();
		model.addAttribute("trenutniUporabnik", trenutniUporabnik);
		long stNeprebranih = sporServ.steviloNeprebranih();
		model.addAttribute("stNeprebranih", stNeprebranih);
	
		List<Termin> mojiTermini = terServ.poisciPonujeneSvojeTermine(trenutniUporabnik);
		model.addAttribute("mojiTermini", mojiTermini);
		
		List<Termin> iskaniTermini = terServ.poisciIskaneSvojeTermine(trenutniUporabnik);
		model.addAttribute("iskaniTermini", iskaniTermini);
		
		List<Predmet> vsiPredmeti = predServ.poisciVse();
		model.addAttribute("vsiPredmeti", vsiPredmeti);
		
		model.addAttribute("termin", new Termin());
		model.addAttribute("terminIskan", new Termin());
		
		model.addAttribute("datumUtil", new DatumLocaleUtility(null, locale));
		
		return "termin/moji-termini-page";
	}
	
	@PostMapping("/nov")
	public String saveTermin(@ModelAttribute("termin") Termin termin) {
		Uporabnik trenutniUporabnik = upbServ.dobiTrenutnegaUporabnika();
		terServ.shraniPonujenTermin(termin, trenutniUporabnik);
		
		return "redirect:/termin/";
	}
	
	@PostMapping("/nov-iskan")
	public String saveTerminIskan(@ModelAttribute("terminIskan") Termin termin) {
		Uporabnik trenutniUporabnik = upbServ.dobiTrenutnegaUporabnika();
		terServ.shraniIskanTermin(termin, trenutniUporabnik);
		
		return "redirect:/termin/";
	}
	
	@PostMapping("/delete/{id}")
	public @ResponseBody BasicResponse deleteTermin(@PathVariable("id") long id) {
		terServ.izbrisiTermin(id);
		return new BasicResponse(200, "All is ok!");
	}
	
	@PostMapping("/status/{id}")
	public @ResponseBody BasicResponse spremeniStatus(@PathVariable("id") long id) {
		terServ.spremeniStatus(id);
		return new BasicResponse(200, "All is ok!");
	}
}
