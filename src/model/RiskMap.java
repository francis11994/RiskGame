package model;

import java.util.ArrayList;
import java.util.List;

public class RiskMap {

	ArrayList<Country> map;
	
	public RiskMap(){
		
	}
	
	public void addCountry(Country c){
		map.add(c);
	}
	
	public boolean removeCountry(Country c){
		return map.remove(c);
	}
	
	public ArrayList<Country> getAllCountry() {
		// TODO Auto-generated method stub
		return map;
	}

}
