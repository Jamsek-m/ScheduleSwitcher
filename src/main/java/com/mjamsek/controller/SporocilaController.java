package com.mjamsek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mjamsek.model.sporocilo.Sporocilo;
import com.mjamsek.model.sporocilo.SporociloService;
import com.mjamsek.model.uporabnik.Uporabnik;
import com.mjamsek.model.uporabnik.UporabnikService;
import com.mjamsek.wrappers.SendSporociloWrapper;

@Controller
@RequestMapping("/sporocila")
public class SporocilaController {

	@Autowired
	private SporociloService sporServ;
	
	@Autowired
	private UporabnikService upbServ;
	
	@GetMapping("/read/{id}")
	public String loadMessage(@PathVariable("id") long id, Model model) {
		Uporabnik trenutniUporabnik = upbServ.dobiTrenutnegaUporabnika();
		Sporocilo sporocilo = sporServ.poisciSporocilo(id);
		if(sporocilo.getStatus() == 0) {
			sporServ.oznaciKotPrebrano(sporocilo);
		}
		model.addAttribute("sporocilo", sporocilo);
		model.addAttribute("trenutniUporabnik", trenutniUporabnik);
		return "sporocila/read-sporocilo-page";
	}
	
	@GetMapping("/prejeto")
	public String loadPrejetaSporocilaPage(Model model) {
		Uporabnik trenutniUporabnik = upbServ.dobiTrenutnegaUporabnika();
		model.addAttribute("trenutniUporabnik", trenutniUporabnik);
		List<Sporocilo> prejetaSporocila = sporServ.poisciVsaPrejetaSporocila(upbServ.dobiTrenutnegaUporabnika());
		model.addAttribute("prejeta", prejetaSporocila);
		return "sporocila/prejeta-sporocila-page";
	}
	
	@GetMapping("/poslano")
	public String loadPoslanaSporocilaPage(Model model) {
		Uporabnik trenutniUporabnik = upbServ.dobiTrenutnegaUporabnika();
		model.addAttribute("trenutniUporabnik", trenutniUporabnik);
		List<Sporocilo> poslanaSporocila = sporServ.poisciVsaPoslanaSporocila(upbServ.dobiTrenutnegaUporabnika());
		model.addAttribute("poslana", poslanaSporocila);
		return "sporocila/poslana-sporocila-page";
	}
	
	@GetMapping("/novo")
	public String loadNovoSporociloPage(Model model) {
		Uporabnik trenutniUporabnik = upbServ.dobiTrenutnegaUporabnika();
		model.addAttribute("trenutniUporabnik", trenutniUporabnik);
		SendSporociloWrapper sp = new SendSporociloWrapper();
		model.addAttribute("spor", sp);
		return "sporocila/novo-sporocilo-page";
	}
	
	@PostMapping("/novo")
	public String saveNovoSporocilo(@ModelAttribute(value="spor") SendSporociloWrapper sporocilo) {
		sporServ.posljiSporocilo(sporocilo);
		return "redirect:/sporocila/poslano";
	}
	
	@GetMapping("/get-users")
	public @ResponseBody List<Uporabnik> getUsers(@RequestParam(value="query") String query){
		return upbServ.poisciZImenom(query);
	}
	
}




