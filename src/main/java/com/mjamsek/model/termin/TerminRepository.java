package com.mjamsek.model.termin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mjamsek.model.predmet.Predmet;
import com.mjamsek.model.uporabnik.Uporabnik;

public interface TerminRepository extends JpaRepository<Termin, Long> {
	
	public Termin findById(long id);

	public List<Termin> findByLastnikAndTip(Uporabnik up, int tip);
	
	public List<Termin> findByLastnikAndTipAndStatus(Uporabnik up, int tip, int status);
	
	public List<Termin> findByCasAndDan(int cas, int dan);
	
	public List<Termin> findByPredmetAndTipAndDanAndCasAndStatusAndLastnikNot(Predmet p, int tip, int dan, int cas, int status, Uporabnik iskalec);
	
	public Termin findByPredmetAndLastnik(Predmet p, Uporabnik u);
	
	public Termin findByPredmetAndLastnikAndTipAndStatus(Predmet p, Uporabnik u, int tip, int status);
}
