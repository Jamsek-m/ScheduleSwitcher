package com.mjamsek.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service("emailService")
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private EmailHtmlSender emailSender;
	
	@Override
	@Async
	public EmailStatus posljiEmail(String prejemnik, Context context, String zadeva, String template) {
		return emailSender.send(prejemnik, zadeva, template, context);
	}

}
