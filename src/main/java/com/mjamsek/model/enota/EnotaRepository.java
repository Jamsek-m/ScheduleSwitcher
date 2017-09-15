package com.mjamsek.model.enota;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnotaRepository extends JpaRepository<Enota, Integer> {

	public List<Enota> findAll();
	
	public Enota findById(int id);
	
}
