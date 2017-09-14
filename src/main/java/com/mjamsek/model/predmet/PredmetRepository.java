package com.mjamsek.model.predmet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("predmetRepository")
public interface PredmetRepository extends JpaRepository<Predmet, Integer> {

	public Predmet findById(int id);
	
	public List<Predmet> findAll();
	
	public List<Predmet> findByLetnik(int letnik);
	
	public Predmet findByOznaka(String oznaka);

}
