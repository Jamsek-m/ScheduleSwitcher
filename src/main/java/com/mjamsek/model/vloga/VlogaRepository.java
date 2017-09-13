package com.mjamsek.model.vloga;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("vlogaRepository")
public interface VlogaRepository extends JpaRepository<Vloga, Integer> {

	public Vloga findByVloga(String vloga);
	
	public List<Vloga> findAll();
	
	public Vloga findById(int id);
	
}
