package com.mjamsek.model.zahteva;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mjamsek.model.uporabnik.Uporabnik;

@Repository
public interface ZahtevaRepository extends JpaRepository<Zahteva, Long> {

	public List<Zahteva> findAll();
	
	public Zahteva findById(long id);
	
	public List<Zahteva> findByProsilec(Uporabnik prosilec);
	
	public List<Zahteva> findByProsilecAndStatus(Uporabnik prosilec, int status);
	
	public List<Zahteva> findByStatus(int status);
	
	@Query("SELECT COUNT(*) as REZ FROM Zahteva z WHERE z.status=0")
	public long findNumberOfUnsolvedRequests();
	
}
