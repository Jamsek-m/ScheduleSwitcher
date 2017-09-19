package com.mjamsek.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mjamsek.model.sporocilo.SporociloService;
import com.mjamsek.model.uporabnik.Uporabnik;
import com.mjamsek.model.uporabnik.UporabnikService;
import com.mjamsek.model.vloga.Vloga;
import com.mjamsek.model.vloga.VlogaService;
import com.mjamsek.wrappers.EditUserWrapper;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UporabnikService upbServ;
	
	@Autowired
	private SporociloService sporServ;
	
	@Autowired
	private VlogaService vlServ;
	
	@Value("${politika.seznam.velikost}")
	private int STEVILO_NA_STRAN;
	
	@GetMapping("/")
	public String loadAdminHomePage(Model model) {
		Uporabnik trenutniUporabnik = upbServ.dobiTrenutnegaUporabnika();
		model.addAttribute("trenutniUporabnik", trenutniUporabnik);
		long stNeprebranih = sporServ.steviloNeprebranih();
		model.addAttribute("stNeprebranih", stNeprebranih);
		
		List<Uporabnik> uporabniki = upbServ.vrniVsePoStrani(1);
		model.addAttribute("uporabniki", uporabniki);
		
		long stUporabnikov = upbServ.vrniSteviloUporabnikov();
		long stStrani = (long)Math.ceil((double)stUporabnikov/(double)STEVILO_NA_STRAN);
		
		model.addAttribute("stStrani", stStrani);
		model.addAttribute("trenutnaStran", 1);
		
		
		return "admin/admin-home-page";
	}
	
	@GetMapping("/stran/{page}")
	public String loadNextAdminHomePage(@PathVariable("page") int stran, Model model) {
		Uporabnik trenutniUporabnik = upbServ.dobiTrenutnegaUporabnika();
		model.addAttribute("trenutniUporabnik", trenutniUporabnik);
		long stNeprebranih = sporServ.steviloNeprebranih();
		model.addAttribute("stNeprebranih", stNeprebranih);
		
		List<Uporabnik> uporabniki = upbServ.vrniVsePoStrani(stran);
		model.addAttribute("uporabniki", uporabniki);
		
		long stUporabnikov = upbServ.vrniSteviloUporabnikov();
		long stStrani = (long)Math.ceil((double)stUporabnikov/(double)STEVILO_NA_STRAN);
		model.addAttribute("stStrani", stStrani);
		model.addAttribute("trenutnaStran", stran);
		
		return "admin/admin-home-page";
	}
	
	@GetMapping("/search-users")
	public String findUsersMatching(@RequestParam("uporabnik") String uporabnik, Model model) {
		Uporabnik trenutniUporabnik = upbServ.dobiTrenutnegaUporabnika();
		model.addAttribute("trenutniUporabnik", trenutniUporabnik);
		long stNeprebranih = sporServ.steviloNeprebranih();
		model.addAttribute("stNeprebranih", stNeprebranih);
		
		model.addAttribute("iskanje", true);
		
		List<Uporabnik> uporabniki = upbServ.poisciZImenom(uporabnik);
		model.addAttribute("uporabniki", uporabniki);
		
		return "admin/admin-home-page";
	}
	
	@GetMapping("/deaktiviraj/{id}")
	public String deactivateUser(@PathVariable("id")long id) {
		upbServ.deaktivirajUporabnika(id);
		return "redirect:/admin/";
	}
	
	@GetMapping("/uredi/{id}")
	public String loadEditUserPage(@PathVariable("id")long id, Model model) {
		Uporabnik trenutniUporabnik = upbServ.dobiTrenutnegaUporabnika();
		model.addAttribute("trenutniUporabnik", trenutniUporabnik);
		long stNeprebranih = sporServ.steviloNeprebranih();
		model.addAttribute("stNeprebranih", stNeprebranih);
		
		Uporabnik uporabnik = upbServ.poisciZId(id);
		EditUserWrapper up = new EditUserWrapper();
		up.setDisplayName(uporabnik.getIme());
		up.setId(uporabnik.getId());
		up.setUsername(uporabnik.getUporabniskoIme());
		up.setEmail(uporabnik.getEmail());
		model.addAttribute("uporabnik", up);
		
		model.addAttribute("userMeta", uporabnik);
		
		List<Vloga> vloge = vlServ.poisciVse();
		model.addAttribute("vloge", vloge);
		
		return "admin/admin-edit-user-page";
	}
	
	@PostMapping("/uredi")
	public String saveUser(@ModelAttribute("uporabnik") EditUserWrapper uporabnik, HttpServletRequest request) {
		String hostname = "http://" + request.getServerName();
		if(request.getServerName().equals("localhost")) {
			hostname += ":" + request.getServerPort();
		}
		upbServ.adminUrediUporabnika(uporabnik, hostname);
		return "redirect:/admin/";
	}
	
	@GetMapping("/ponastavi-geslo/{id}")
	public String resetPassword(@PathVariable("id") long id, HttpServletRequest request) {
		String hostname = "http://" + request.getServerName();
		if(request.getServerName().equals("localhost")) {
			hostname += ":" + request.getServerPort();
		}
		upbServ.ponastaviGeslo(hostname, id);
		return "redirect:/admin/";
	}
	
}
