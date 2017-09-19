package com.mjamsek.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.context.Context;

import com.mjamsek.email.EmailService;
import com.mjamsek.model.enota.Enota;
import com.mjamsek.model.enota.EnotaService;
import com.mjamsek.model.sporocilo.SporociloService;
import com.mjamsek.model.uporabnik.Uporabnik;
import com.mjamsek.model.uporabnik.UporabnikService;
import com.mjamsek.utilities.EmailUtility;
import com.mjamsek.utilities.InitUtility;

@Controller
public class LoginController {

	@Autowired
	private InitUtility init;
	
	@Autowired
	private UporabnikService upbServ;
	
	@Autowired
	private EmailService emailServ;
	
	@Autowired
	private EnotaService enServ;
	
	@Autowired
	private SporociloService sporServ;
	
	@Value("${politika.geslo.dolzina}")
	private int DOLZINA_GESLA;
	
	@GetMapping("/login")
	public String loadLoginPage(Model model) {
		init.initPodatkovneBaze();
		Uporabnik trenutniUporabnik = upbServ.dobiTrenutnegaUporabnika();
		model.addAttribute("trenutniUporabnik", trenutniUporabnik);
		long stNeprebranih = sporServ.steviloNeprebranih();
		model.addAttribute("stNeprebranih", stNeprebranih);
		
		model.addAttribute("uporabnik", new Uporabnik());
		return "login/login-page";
	}
	
	@GetMapping("/register")
	public String loadRegistrationPage(Model model) {
		Uporabnik trenutniUporabnik = upbServ.dobiTrenutnegaUporabnika();
		model.addAttribute("trenutniUporabnik", trenutniUporabnik);
		long stNeprebranih = sporServ.steviloNeprebranih();
		model.addAttribute("stNeprebranih", stNeprebranih);
		
		model.addAttribute("uporabnik", new Uporabnik());
		
		List<Enota> enote = enServ.poisciVse();
		model.addAttribute("enote", enote);
		return "login/register-page";
	}
	
	@PostMapping("/register")
	public String registerNewUser(@Valid Uporabnik uporabnik, BindingResult bindingResult, Model model, HttpServletRequest request) {
		Uporabnik obstojeciUporabnik = upbServ.poisciUporabnikaZUporabniskimImenom(uporabnik.getUporabniskoIme());
		if(obstojeciUporabnik != null) {
			bindingResult.rejectValue("uporabniskoIme", "error.uporabnik", "uporabniško ime je zasedeno!");
		}
		
		if(uporabnik.getGeslo().length() < DOLZINA_GESLA) {
			bindingResult.rejectValue("geslo", "error.uporabnik", "Password is too short! Min 6 characters needed.");
		}
		if(uporabnik.getIme().length() < 4) {
			bindingResult.rejectValue("ime", "error.uporabnik", "Nickname is too short! Min 4 characters needed.");
		}
		if(uporabnik.getUporabniskoIme().length() < 4) {
			bindingResult.rejectValue("uporabniskoIme", "error.uporabnik", "Username is too short! Min 4 characters needed.");
		}
		if(uporabnik.getEnota().getId() == -1) {
			bindingResult.rejectValue("enota", "error.uporabnik", "Izberi enoto!");
		}
		if(!EmailUtility.jeVeljavenEmail(uporabnik.getEmail())) {
			bindingResult.rejectValue("email", "error.uporabnik", "Not a valid email!");
		}
		if(bindingResult.hasErrors()) {
			return "login/register-page";
		} else {
			upbServ.shraniUporabnika(uporabnik);
			
			String hostname = "http://" + request.getServerName();
			if(request.getServerName().equals("localhost")) {
				hostname += ":" + request.getServerPort();
			}
		
			Context emailContext = new Context();
			emailContext.setVariable("title", "aktivacija racuna");
			emailContext.setVariable("sporocilo", "pozdrav!");
			emailContext.setVariable("kljuc", uporabnik.getAktiven());
			emailContext.setVariable("hostname", hostname);
			emailServ.posljiEmail(uporabnik.getEmail(), emailContext, "Aktivacija računa", "email/activation-email");
			
			model.addAttribute("successMessage", "User has been successfully registered!");
			//model.addAttribute("uporabnik", new Uporabnik());
			return "login/registration-success-page";
		}
	}
	
	@GetMapping("/access-denied")
	public String loadAccessDeniedPage(Model model) {
		Uporabnik trenutniUporabnik = upbServ.dobiTrenutnegaUporabnika();
		model.addAttribute("trenutniUporabnik", trenutniUporabnik);
		long stNeprebranih = sporServ.steviloNeprebranih();
		model.addAttribute("stNeprebranih", stNeprebranih);
		return "login/access-denied-page";
	}
	
	@GetMapping("/activate-user/{activation_key}")
	public String activateUser(@PathVariable("activation_key") int key, Model model) {
		Uporabnik trenutniUporabnik = upbServ.dobiTrenutnegaUporabnika();
		model.addAttribute("trenutniUporabnik", trenutniUporabnik);
		long stNeprebranih = sporServ.steviloNeprebranih();
		model.addAttribute("stNeprebranih", stNeprebranih);
		
		Uporabnik uporabnik = upbServ.poisciPrekoAktivacijskegaKljuca(key);
		if(uporabnik != null) {
			uporabnik.setAktiven(1);
			upbServ.aktivirajUporabnika(uporabnik.getId());
			model.addAttribute("message", "You have successfully activated your account!");
			return "login/activate-user-page";
		} else {
			model.addAttribute("message", "Error: Your activation link is broken!");
			return "login/activate-user-page";
		}
	}
}
