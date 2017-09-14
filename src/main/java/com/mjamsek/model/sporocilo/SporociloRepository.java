package com.mjamsek.model.sporocilo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mjamsek.model.uporabnik.Uporabnik;

public interface SporociloRepository extends JpaRepository<Sporocilo, Long>{

	public List<Sporocilo> findByAvtorOrderByPoslanoDesc(Uporabnik avtor);
	
	public List<Sporocilo> findByPrejemnikOrderByStatusAscPoslanoDesc(Uporabnik prejemnik);
	
	public Sporocilo findById(long id);
	
	@Query("SELECT s FROM Sporocilo s WHERE s.status = 0 AND s.prejemnik = :target")
	public List<Sporocilo> findUnreadMessages(@Param("target") Uporabnik u);
	
}
