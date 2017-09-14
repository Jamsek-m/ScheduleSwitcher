package com.mjamsek.model.sporocilo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mjamsek.model.uporabnik.Uporabnik;

public interface SporociloRepository extends JpaRepository<Sporocilo, Long>{

	public List<Sporocilo> findByAvtor(Uporabnik avtor);
	
	public List<Sporocilo> findByPrejemnik(Uporabnik prejemnik);
	
	public Sporocilo findById(long id);
	
}
