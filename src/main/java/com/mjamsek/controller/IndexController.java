package com.mjamsek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mjamsek.model.sporocilo.Sporocilo;
import com.mjamsek.model.sporocilo.SporociloService;
import com.mjamsek.model.uporabnik.Uporabnik;
import com.mjamsek.model.uporabnik.UporabnikService;
import com.mjamsek.utilities.InitUtility;

@Controller
public class IndexController {
	
	@Autowired
	private SporociloService sporServ;
	
	@Autowired
	private UporabnikService upbServ;
	
	@GetMapping("/")
	public String naloziIndexStran(Model model) {
		Uporabnik trenutniUporabnik = upbServ.dobiTrenutnegaUporabnika();
		model.addAttribute("trenutniUporabnik", trenutniUporabnik);
		long stNeprebranih = sporServ.steviloNeprebranih();
		model.addAttribute("stNeprebranih", stNeprebranih);
		
		List<Sporocilo> neprebrana = sporServ.poisciNeprebrana(trenutniUporabnik);
		
		model.addAttribute("neprebrana", neprebrana);
		model.addAttribute("trenutniUporabnik", trenutniUporabnik);
		
		return "index";
	}
	
}
