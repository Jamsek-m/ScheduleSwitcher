package com.mjamsek.model.uporabnik;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("uporabnik_repository")
public interface UporabnikRepository extends JpaRepository<Uporabnik, Long> {
	
	public Uporabnik findByUporabniskoIme(String uporabniskoIme);
	
	public Uporabnik findById(long id);
	
	public Uporabnik findByAktiven(int aktiven);
	
	public List<Uporabnik> findByImeContaining(String ime);
	
	@Query(value = "SELECT * FROM uporabnik u WHERE u.aktiven=1 ORDER BY u.ime LIMIT :upbperpage OFFSET :page", nativeQuery = true)
	public List<Uporabnik> findAllByPage(@Param("upbperpage") int upbPerPage, @Param("page") int page);
	
	@Query("SELECT COUNT(*) as NUMBER FROM Uporabnik u WHERE u.aktiven=1")
	public long findNumberOfUsers();
}
