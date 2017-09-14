package com.mjamsek.model.uporabnik;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("uporabnik_repository")
public interface UporabnikRepository extends JpaRepository<Uporabnik, Long> {
	
	public Uporabnik findByUporabniskoIme(String uporabniskoIme);
	
	public Uporabnik findById(long id);
	
	public Uporabnik findByAktiven(int aktiven);
	
	public List<Uporabnik> findByImeContaining(String ime);
	
}
