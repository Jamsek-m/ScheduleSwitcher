package com.mjamsek.model.enota;

import java.util.List;

public interface EnotaService {

	public List<Enota> poisciVse();
	
	public Enota poisciZId(int id);
	
	public void urediEnota(Enota enota);
	
	public void shraniEnota(Enota enota);
	
}
