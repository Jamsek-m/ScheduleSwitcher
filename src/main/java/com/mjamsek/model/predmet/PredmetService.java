package com.mjamsek.model.predmet;

import java.util.List;

public interface PredmetService {

	public List<Predmet> poisciVse();
	
	public List<Predmet> poisciVseVLetniku(int letnik);
	
	public Predmet poisciPoOznaki(String oznaka);
	
	public Predmet poisciPoId(int id);
	
}
