package com.mjamsek.model.zahteva;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjamsek.model.uporabnik.Uporabnik;
import com.mjamsek.model.uporabnik.UporabnikService;

@Service("zahtevaService")
public class ZahtevaServiceImpl implements ZahtevaService {
	
	@Autowired
	private ZahtevaRepository zahRepo;
	
	@Autowired
	private TipZahteveRepository tipRepo;
	
	@Autowired
	private UporabnikService upbServ;
	
	@Override
	public List<Zahteva> vrniVse() {
		return zahRepo.findAll();
	}

	@Override
	public List<Zahteva> vrniSvojeZahteve(Uporabnik up) {
		return zahRepo.findByProsilec(up);
	}

	@Override
	public List<Zahteva> vrniNereseneZahteve(int status) {
		return zahRepo.findByStatus(STATUS_NERESENO);
	}

	@Override
	public List<Zahteva> vrniSvojeNereseneZahteve(Uporabnik up, int status) {
		return zahRepo.findByProsilecAndStatusOrderByDatumProsnjeDesc(up, STATUS_NERESENO);
	}

	@Override
	public long vrniSteviloNeresenihZahtev() {
		return zahRepo.findNumberOfUnsolvedRequests();
	}

	@Override
	public List<TipZahteve> vrniTipeZahtev() {
		return tipRepo.findAll();
	}

	@Override
	public void spremeniStatusZahteve(long id, int status) {
		Zahteva zahteva = zahRepo.findById(id);
		zahteva.setStatus(status);
		zahRepo.save(zahteva);
	}

	@Override
	public void posljiZahtevo(Zahteva zahteva) {
		zahteva.setStatus(STATUS_NERESENO);
		zahteva.setDatumProsnje(new Date());
		zahteva.setProsilec(upbServ.dobiTrenutnegaUporabnika());
		
		zahRepo.save(zahteva);
	}

	@Override
	public Zahteva vrniZahtevoZId(long id) {
		return zahRepo.findById(id);
	}

	@Override
	public long vrniSteviloNeresenihZaMod() {
		return zahRepo.findNumberOfUnsolvedRequestsForMods();
	}

}
