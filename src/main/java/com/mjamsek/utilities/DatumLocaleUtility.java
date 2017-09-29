package com.mjamsek.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DatumLocaleUtility {
	
	private Date datum;
	
	private Locale locale;
	
	private final String[] en_dan = {"Monday", "Thursday", "Wednesday", "Tuesday", "Friday", "Saturday", "Sunday"};
 	private final String[] sl_dan = {"Ponedeljek", "Torek", "Sreda", "Četrtek", "Petek", "Sobota", "Nedelja"};
	
	public DatumLocaleUtility(Date datum, Locale locale) {
		this.datum = datum;
		this.locale = locale;
	}

	public String vrniDan() {
		DateFormat format = new SimpleDateFormat("u"); //vrne od 1(pon) do 7(ned)
		int dan = Integer.parseInt(format.format(datum));
		if(locale.getLanguage().equals(new Locale("en").getLanguage())){
			return en_dan[dan-1];
		} else {
			return sl_dan[dan-1];
		}
	}
	
	public String vrniDan(String i) {
		int index = Integer.parseInt(i); //dobim od 0(pon) do 6(ned)
		if(locale.getLanguage().equals(new Locale("en").getLanguage())){
			return en_dan[index];
		} else {
			return sl_dan[index];
		}
	}
	
}
