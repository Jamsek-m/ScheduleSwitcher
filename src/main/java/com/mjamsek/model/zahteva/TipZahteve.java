package com.mjamsek.model.zahteva;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mjamsek.model.vloga.Vloga;

@Entity
@Table(name="sif_zahteva")
public class TipZahteve {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_tip_zahteve")
	private int id;
	
	@Column(name="naziv_zahteve")
	private String naziv;
	
	@ManyToOne
	@JoinColumn(name="skrbnik")
	private Vloga skrbnik;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Vloga getSkrbnik() {
		return skrbnik;
	}

	public void setSkrbnik(Vloga skrbnik) {
		this.skrbnik = skrbnik;
	}
	
}
