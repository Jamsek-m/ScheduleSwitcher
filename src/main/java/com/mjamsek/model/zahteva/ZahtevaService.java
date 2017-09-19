package com.mjamsek.model.zahteva;

import java.util.List;

import com.mjamsek.model.uporabnik.Uporabnik;

public interface ZahtevaService {

	public static final int STATUS_NERESENO = 0;
	public static final int STATUS_V_RESEVANJU = 1;
	public static final int STATUS_ZAVRNJENO = 2;
	public static final int STATUS_ODOBRENO = 3;
	
	public List<Zahteva> vrniVse();
	
	public List<Zahteva> vrniSvojeZahteve(Uporabnik up);
	
	public List<Zahteva> vrniNereseneZahteve(int status);
	
	public List<Zahteva> vrniSvojeNereseneZahteve(Uporabnik up, int status);
	
	public long vrniSteviloNeresenihZahtev();
	
	public List<TipZahteve> vrniTipeZahtev();
	
	public void spremeniStatusZahteve(long id, int status);
	
	public void posljiZahtevo(Zahteva zahteva);
	
}
