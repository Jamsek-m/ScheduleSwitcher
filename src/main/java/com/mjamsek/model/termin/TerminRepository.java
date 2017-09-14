package com.mjamsek.model.termin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mjamsek.model.uporabnik.Uporabnik;
import com.mjamsek.utilities.DanUra;

public interface TerminRepository extends JpaRepository<Termin, Long> {

	public List<Termin> findByLastnik(Uporabnik up);
	
	public List<Termin> findByCasAndDan(int cas, int dan);
	
}
