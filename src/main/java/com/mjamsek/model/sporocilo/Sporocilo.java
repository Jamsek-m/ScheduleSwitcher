package com.mjamsek.model.sporocilo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mjamsek.model.uporabnik.Uporabnik;

@Entity
@Table(name="sporocila")
public class Sporocilo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="sporocilo_id")
	private long id;
	
	@Column(name="zadeva")
	private String zadeva;
	
	@Column(name="vsebina", columnDefinition = "TEXT")
	private String vsebina;
	
	@Column(name="poslano")
	private Date poslano;
	
	@Column(name="status")
	private int status;
	
	@ManyToOne
	@JoinColumn(name = "avtor_id")
	private Uporabnik avtor;
	
	@ManyToOne
	@JoinColumn(name = "prejemnik_id")
	private Uporabnik prejemnik;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getZadeva() {
		return zadeva;
	}

	public void setZadeva(String zadeva) {
		this.zadeva = zadeva;
	}

	public String getVsebina() {
		return vsebina;
	}

	public void setVsebina(String vsebina) {
		this.vsebina = vsebina;
	}

	public Date getPoslano() {
		return poslano;
	}

	public void setPoslano(Date poslano) {
		this.poslano = poslano;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Uporabnik getAvtor() {
		return avtor;
	}

	public void setAvtor(Uporabnik avtor) {
		this.avtor = avtor;
	}

	public Uporabnik getPrejemnik() {
		return prejemnik;
	}

	public void setPrejemnik(Uporabnik prejemnik) {
		this.prejemnik = prejemnik;
	}
	
}
