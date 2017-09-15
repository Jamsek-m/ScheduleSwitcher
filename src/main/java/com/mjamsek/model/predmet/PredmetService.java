package com.mjamsek.model.predmet;

import java.util.List;

import com.mjamsek.wrappers.EditPredmetWrapper;

public interface PredmetService {

	public List<Predmet> poisciVse();
	
	public List<Predmet> poisciVseVLetniku(int letnik);
	
	public Predmet poisciPoOznaki(String oznaka);
	
	public Predmet poisciPoId(int id);
	
	public void urediPredmet(EditPredmetWrapper p);
	
}
