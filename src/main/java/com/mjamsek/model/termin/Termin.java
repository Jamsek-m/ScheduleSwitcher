package com.mjamsek.model.termin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mjamsek.model.predmet.Predmet;
import com.mjamsek.model.uporabnik.Uporabnik;

@Entity
@Table(name="termini")
public class Termin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="termin_id")
	private long id;
	
	//status: 0-ni na voljo, 1-je na voljo
	@Column(name="status")
	private int status;
	
	//status 0-ponujen termin, 1-iskani termin
	@Column(name="tip")
	private int tip;
	
	@ManyToOne
	@JoinColumn(name = "upb_id")
	private Uporabnik lastnik;
	
	@ManyToOne
	@JoinColumn(name="predmet_id")
	private Predmet predmet;
	
	@Column(name="zacetek")
	private int cas;
	
	@Column(name="dan")
	private int dan;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Uporabnik getLastnik() {
		return lastnik;
	}

	public void setLastnik(Uporabnik lastnik) {
		this.lastnik = lastnik;
	}

	public Predmet getPredmet() {
		return predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}

	public int getCas() {
		return cas;
	}

	public void setCas(int cas) {
		this.cas = cas;
	}

	public int getDan() {
		return dan;
	}

	public void setDan(int dan) {
		this.dan = dan;
	}

	public int getTip() {
		return tip;
	}

	public void setTip(int tip) {
		this.tip = tip;
	}

}
