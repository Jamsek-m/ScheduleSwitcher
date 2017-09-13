package com.mjamsek.email;

import org.thymeleaf.context.Context;

public interface EmailService {
	
	//Contect c = new Context(); c.setVariable("ime", "value"); 
	public EmailStatus posljiEmail(String prejemnik, Context context, String zadeva, String template);

}
