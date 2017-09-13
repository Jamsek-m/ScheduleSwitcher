package com.mjamsek.utilities;

import com.mjamsek.model.uporabnik.Uporabnik;
import com.mjamsek.model.uporabnik.UporabnikRepository;

public class RegistrationUtility {
	
	public static int vrniAktivacijskoStevilko(UporabnikRepository upRepo) {
		int aktivacijskaStevilka = 0;
		while(aktivacijskaStevilka == 0) {
			int nakljucnoStevilo = (int)((Math.random() * (999999+1-100000)) + 100000);
			Uporabnik preveriAktivacijskoSt = upRepo.findByAktiven(nakljucnoStevilo);
			if(preveriAktivacijskoSt == null) {
				aktivacijskaStevilka = nakljucnoStevilo;
			}
		}
		return aktivacijskaStevilka;
	}

}
