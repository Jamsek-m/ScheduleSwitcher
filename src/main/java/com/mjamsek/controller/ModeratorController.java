package com.mjamsek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mjamsek.model.enota.Enota;
import com.mjamsek.model.enota.EnotaService;
import com.mjamsek.model.predmet.Predmet;
import com.mjamsek.model.predmet.PredmetService;
import com.mjamsek.model.uporabnik.Uporabnik;
import com.mjamsek.model.uporabnik.UporabnikService;
import com.mjamsek.wrappers.EditPredmetWrapper;

@Controller
@RequestMapping("/moderator")
public class ModeratorController {

	@Autowired
	private UporabnikService upbServ;
	
	@Autowired
	private PredmetService predServ;
	
	@Autowired
	private EnotaService enServ;
	
	@GetMapping("/")
	public String loadModeratorPage(Model model) {
		Uporabnik trenutniUporabnik = upbServ.dobiTrenutnegaUporabnika();
		model.addAttribute("trenutniUporabnik", trenutniUporabnik);
		
		List<Predmet> vsiPredmeti = predServ.poisciVse();
		model.addAttribute("vsiPredmeti", vsiPredmeti);
		
		List<Enota> vseEnote = enServ.poisciVse();
		model.addAttribute("vseEnote", vseEnote);
		
		return "mod/moderator-home-page";
	}
	
	@GetMapping("/edit/predmet")
	public String loadEditPredmetPage(@RequestParam("predmet") int predmet_id,  Model model) {
		Uporabnik trenutniUporabnik = upbServ.dobiTrenutnegaUporabnika();
		model.addAttribute("trenutniUporabnik", trenutniUporabnik);
		
		Predmet predmet = predServ.poisciPoId(predmet_id);
		EditPredmetWrapper wrapper = new EditPredmetWrapper();
		wrapper.setEnota(predmet.getEnota());
		wrapper.setId(predmet.getId());
		wrapper.setLetnik(predmet.getLetnik());
		wrapper.setNaziv(predmet.getPredmet());
		wrapper.setNosilec(predmet.getNosilec());
		wrapper.setOznaka(predmet.getOznaka());
		model.addAttribute("predmet", wrapper);
		
		List<Enota> vseEnote = enServ.poisciVse();
		model.addAttribute("vseEnote", vseEnote);
		
		return "mod/edit-predmet-page";
	}
	
	@PostMapping("/edit/predmet")
	public String savePredmet(@ModelAttribute("predmet") EditPredmetWrapper predmet) {
		predServ.urediPredmet(predmet);
		return "redirect:/moderator/";
	}
	
	@GetMapping("/edit/enota")
	public String loadEditEnotaPage(@RequestParam("enota") int enota_id,  Model model) {
		Enota enota = enServ.poisciZId(enota_id);
		model.addAttribute("enota", enota);
		
		Uporabnik trenutniUporabnik = upbServ.dobiTrenutnegaUporabnika();
		model.addAttribute("trenutniUporabnik", trenutniUporabnik);
		
		return "mod/edit-enota-page";
	}
	
	@PostMapping("/edit/enota")
	public String saveEnota(@ModelAttribute("enota") Enota enota) {
		enServ.urediEnota(enota);
		return "redirect:/moderator/";
	}
	
	@PostMapping("/edit/uporabnik")
	public @ResponseBody String makeUserMod(@RequestBody String uporabnik) {
		System.err.println("Uporabnik: " + uporabnik);
		uporabnik = uporabnik.split("=")[0];
		
		List<Uporabnik> uporabniki = upbServ.poisciZImenom(uporabnik);
		Uporabnik noviMod;
		if(uporabniki.size() > 1) {
			for(Uporabnik up : uporabniki) {
				if(up.getIme().equals(uporabnik)) {
					noviMod = up;
					break;
				}
			}
			noviMod = null;
		} else {
			noviMod = uporabniki.get(0);
		}
		upbServ.makeMod(noviMod);
		
		return "uspeh";
	}
	
}