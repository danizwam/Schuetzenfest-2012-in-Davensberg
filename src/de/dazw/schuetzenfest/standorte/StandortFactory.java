package de.dazw.schuetzenfest.standorte;

import java.util.HashMap;
import java.util.Map;

public class StandortFactory {
	
	private static StandortFactory instance = null;
	Map<String, Standort> map = new HashMap<String, Standort>();
	
	private StandortFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static StandortFactory getInstance(){
		if(instance == null){
			instance = new StandortFactory();
		}
		return instance;
	}

	public Standort getStandort(String ort){
		if(map.get(ort) == null){
			createStandort(ort);
		}
		
		return map.get(ort);
	}
	
	private Standort createStandort(String ort){
		
		Standort s = null;
		
		try {
			Class<?> forName = Class.forName("de.dazw.schuetzenfest.standorte." + ort);
			s = (Standort) forName.newInstance();
			map.put(ort, s);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return s;
	}
	
	
	
	
}
