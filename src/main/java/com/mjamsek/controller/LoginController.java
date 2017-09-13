package com.mjamsek.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.context.Context;

import com.mjamsek.email.EmailService;
import com.mjamsek.model.uporabnik.Uporabnik;
import com.mjamsek.model.uporabnik.UporabnikService;
import com.mjamsek.utilities.EmailUtility;

@Controller
public class LoginController {

	@Autowired
	private UporabnikService upbServ;
	
	@Autowired
	private EmailService emailServ;
	
	@GetMapping("/login")
	public String loadLoginPage(Model model) {
		model.addAttribute("uporabnik", new Uporabnik());
		return "login/login-page";
	}
	
	@GetMapping("/register")
	public String loadRegistrationPage(Model model) {
		model.addAttribute("uporabnik", new Uporabnik());
		return "login/register-page";
	}
	
	@PostMapping("/register")
	public String registerNewUser(@Valid Uporabnik uporabnik, BindingResult bindingResult, Model model, HttpServletRequest request) {
		Uporabnik obstojeciUporabnik = upbServ.poisciUporabnikaZUporabniskimImenom(uporabnik.getUporabniskoIme());
		if(obstojeciUporabnik != null) {
			bindingResult.rejectValue("uporabniskoIme", "error.uporabnik", "uporabniško ime je zasedeno!");
		}
		
		if(uporabnik.getGeslo().length() < 6) {
			bindingResult.rejectValue("geslo", "error.uporabnik", "Password is too short! Min 6 characters needed.");
		}
		if(uporabnik.getIme().length() < 4) {
			bindingResult.rejectValue("ime", "error.uporabnik", "Nickname is too short! Min 4 characters needed.");
		}
		if(uporabnik.getUporabniskoIme().length() < 4) {
			bindingResult.rejectValue("uporabniskoIme", "error.uporabnik", "Username is too short! Min 4 characters needed.");
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
			model.addAttribute("uporabnik", new Uporabnik());
			return "redirect:/login";
		}
	}
	
	@GetMapping("/access-denied")
	public String loadAccessDeniedPage() {
		return "login/access-denied-page";
	}
	
	@GetMapping("/activate-user/{activation_key}")
	public String activateUser(@PathVariable("activation_key") int key, Model model) {
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
