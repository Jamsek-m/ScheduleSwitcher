package com.mjamsek.model.zahteva;

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
@Table(name="zahteva")
public class Zahteva {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="zahteva_id")
	private long id;
	
	@Column(name="status")
	private int status;
	
	@ManyToOne
	@JoinColumn(name = "prosilec")
	private Uporabnik prosilec;
	
	@Column(name="datum_prosnje")
	private Date datumProsnje;
	
	@ManyToOne
	@JoinColumn(name="tip_zahteve")
	private TipZahteve tipZahteve;
	
	@Column(name="vsebina", columnDefinition="TEXT")
	private String vsebina;
	
	@Column(name="datum_odgovora")
	private Date datumOdgovora;
	
	@ManyToOne
	@JoinColumn(name="moderator")
	private Uporabnik moderator;
	
	public static final int STATUS_NERESENO = 0;
	public static final String STATUS_NERESENO_TXT = "Odprto";
	public static final int STATUS_V_RESEVANJU = 1;
	public static final String STATUS_V_RESEVANJU_TXT = "V reševanju";
	public static final int STATUS_ZAVRNJENO = 2;
	public static final String STATUS_ZAVRNJENO_TXT = "Zavrnjeno";
	public static final int STATUS_ODOBRENO = 3;
	public static final String STATUS_ODOBRENO_TXT = "Odobreno";

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

	public Uporabnik getProsilec() {
		return prosilec;
	}

	public void setProsilec(Uporabnik prosilec) {
		this.prosilec = prosilec;
	}

	public Date getDatumProsnje() {
		return datumProsnje;
	}

	public void setDatumProsnje(Date datumProsnje) {
		this.datumProsnje = datumProsnje;
	}

	public TipZahteve getTipZahteve() {
		return tipZahteve;
	}

	public void setTipZahteve(TipZahteve tipZahteve) {
		this.tipZahteve = tipZahteve;
	}

	public String getVsebina() {
		return vsebina;
	}

	public void setVsebina(String vsebina) {
		this.vsebina = vsebina;
	}

	public Date getDatumOdgovora() {
		return datumOdgovora;
	}

	public void setDatumOdgovora(Date datumOdgovora) {
		this.datumOdgovora = datumOdgovora;
	}

	public Uporabnik getModerator() {
		return moderator;
	}

	public void setModerator(Uporabnik moderator) {
		this.moderator = moderator;
	}
	
}
