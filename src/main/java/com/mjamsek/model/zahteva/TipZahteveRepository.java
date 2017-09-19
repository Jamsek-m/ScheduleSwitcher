package com.mjamsek.model.zahteva;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipZahteveRepository extends JpaRepository<TipZahteve, Integer> {

	public List<TipZahteve> findAll();
	
	public TipZahteve findById(int id);
	
}
