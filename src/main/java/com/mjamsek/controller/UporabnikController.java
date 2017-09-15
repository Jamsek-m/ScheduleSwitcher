package com.mjamsek.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mjamsek.model.enota.Enota;
import com.mjamsek.model.enota.EnotaService;
import com.mjamsek.model.uporabnik.Uporabnik;
import com.mjamsek.model.uporabnik.UporabnikService;
import com.mjamsek.wrappers.UporabnikNastavitveWrapper;

@Controller
@RequestMapping("/uporabnik")
public class UporabnikController {

	@Autowired
	private UporabnikService upbServ;
	
	@Autowired
	private EnotaService enServ;
	
	@GetMapping("/nastavitve")
	public String loadNastavitvePage(Model model) {
		Uporabnik trenutniUporabnik = upbServ.dobiTrenutnegaUporabnika();
		
		UporabnikNastavitveWrapper wrapper = new UporabnikNastavitveWrapper();
		wrapper.setEmail(trenutniUporabnik.getEmail());
		wrapper.setGeslo("");
		wrapper.setId(trenutniUporabnik.getId());
		wrapper.setLetnik(trenutniUporabnik.getLetnik());
		wrapper.setPosljiEmail(trenutniUporabnik.isPosljiEmail());
		wrapper.setEnota_id(trenutniUporabnik.getEnota().getId());
		
		model.addAttribute("trenutniUporabnik", trenutniUporabnik);
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
	
}
