package com.mjamsek.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mjamsek.model.sporocilo.SporociloService;
import com.mjamsek.model.termin.Termin;
import com.mjamsek.model.termin.TerminService;
import com.mjamsek.model.uporabnik.Uporabnik;
import com.mjamsek.model.uporabnik.UporabnikService;
import com.mjamsek.utilities.DatumLocaleUtility;

@Controller
@RequestMapping("/isci-termine")
public class IsciTerminController {

	@Autowired
	private UporabnikService upbServ;
	
	@Autowired
	private SporociloService sporServ;
	
	@Autowired
	private TerminService terServ;
	
	@GetMapping({"/", ""})
	public String loadIsciTerminePage(Model model, Locale loc) {
		Uporabnik trenutniUporabnik = upbServ.dobiTrenutnegaUporabnika();
		model.addAttribute("trenutniUporabnik", trenutniUporabnik);
		long stNeprebranih = sporServ.steviloNeprebranih();
		model.addAttribute("stNeprebranih", stNeprebranih);
		
		List<Termin> vsiIskaniTermini = terServ.poisciIskaneTermine(trenutniUporabnik);	
		model.addAttribute("ustrezniTermini", vsiIskaniTermini);
		
		model.addAttribute("datumUtil", new DatumLocaleUtility(null, loc));
		
		return "termin/isci-termine-page";
	}
	
}
