package com.mjamsek.model.uporabnik;

import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mjamsek.model.enota.Enota;
import com.mjamsek.model.predmet.Predmet;
import com.mjamsek.model.vloga.Vloga;

@Entity
@Table(name="uporabnik")
public class Uporabnik {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="upb_id")
	private long id;
	
	@Column(name="upb_ime")
	private String uporabniskoIme;
	
	@Column(name="ime")
	private String ime;
	
	@Column(name="geslo")
	private String geslo;
	
	@Column(name="aktiven")
	private int aktiven;
	
	@Column(name="email")
	private String email;
	
	@Column(name="letnik")
	private int letnik;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "upb_predmeti", joinColumns = @JoinColumn(name = "upb_id"), inverseJoinColumns = @JoinColumn(name = "predmet_id"))
	private Set<Predmet> predmeti;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "upb_vloge", joinColumns = @JoinColumn(name = "upb_id"), inverseJoinColumns = @JoinColumn(name = "vloga_id"))
	private Set<Vloga> vloge;
	
	@Column(name = "posljiEmail")
	private boolean posljiEmail;
	
	@ManyToOne
	@JoinColumn(name = "enota_id")
	private Enota enota;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUporabniskoIme() {
		return uporabniskoIme;
	}

	public void setUporabniskoIme(String uporabniskoIme) {
		this.uporabniskoIme = uporabniskoIme;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getGeslo() {
		return geslo;
	}

	public void setGeslo(String geslo) {
		this.geslo = geslo;
	}

	public int getAktiven() {
		return aktiven;
	}

	public void setAktiven(int aktiven) {
		this.aktiven = aktiven;
	}

	public Enota getEnota() {
		return enota;
	}

	public void setEnota(Enota enota) {
		this.enota = enota;
	}

	public Set<Vloga> getVloge() {
		return vloge;
	}

	public void setVloge(Set<Vloga> vloge) {
		this.vloge = vloge;
	}
	
	public int getLetnik() {
		return letnik;
	}

	public void setLetnik(int letnik) {
		this.letnik = letnik;
	}

	public Set<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(Set<Predmet> predmeti) {
		this.predmeti = predmeti;
	}

	public boolean isPosljiEmail() {
		return posljiEmail;
	}

	public void setPosljiEmail(boolean posljiEmail) {
		this.posljiEmail = posljiEmail;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		if(this.vloge != null) {
			boolean prvi = true;
			Iterator<Vloga> it = this.vloge.iterator();
			while(it.hasNext()) {
				Vloga role = it.next();
				if(prvi) {
					sb.append(role.getVloga());
					prvi = false;
				} else {
					sb.append(", ");
					sb.append(role.getVloga());
				}
			}
		}
		sb.append("]");
		String roles = sb.toString();
		return String.format(
				"Uporabnik = { id : %s, uporabniskoIme : %s, ime : %s, geslo : %s, aktiven : %s, email : %s, letnik : %s, posljiEmail : %s, vloge : %s }",
				id, uporabniskoIme, ime, geslo, aktiven, email, letnik, posljiEmail, roles);
	}

	public boolean imaVlogo(String search_role) {
		Iterator<Vloga> it = this.vloge.iterator();
		while(it.hasNext()) {
			Vloga role = it.next();
			if(role.getVloga().equals(search_role)) {
				return true;
			}
		}
		return false;
	}
	
}
