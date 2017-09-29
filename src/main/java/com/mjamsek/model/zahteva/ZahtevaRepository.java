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
	
	public List<Zahteva> findByProsilecAndStatusOrderByDatumProsnjeDesc(Uporabnik prosilec, int status);
	
	public List<Zahteva> findByStatus(int status);
	
	@Query("SELECT COUNT(*) as REZ FROM Zahteva z WHERE z.status=0")
	public long findNumberOfUnsolvedRequests();
	
	@Query(value="select count(*) as rez from zahteva z inner join sif_zahteva s on s.id_tip_zahteve=z.tip_zahteve where z.status=0 and s.skrbnik = (select vloga_id from vloga where vloga='MOD')", nativeQuery=true)
	public long findNumberOfUnsolvedRequestsForMods();
	
}
