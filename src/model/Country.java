package model;

import java.util.ArrayList;
import java.util.List;

public class Country {
	

	int armyCount;
	private List<Country> neighbours;
	String name;

	public Country(String c) {
		this.name = c;
		neighbours = new ArrayList<Country>();
		armyCount = 1;
	}

	public List<Country> getNegibors() {
		return neighbours;
	}

	public void addNeighbour(Country c) {
		this.neighbours.add(c);
	}

	public void addArmys(int n) {
		armyCount += n;
	}

	public void removeArmys(int n){
		armyCount -= n;
	}
	public int getArmyCount() {
		return armyCount;
	}

	public String getname(){
		return name;
	}
	
	public boolean isThreaten(List<Country> ownCountry){
		int enemy=0;
		for(Country neighbor:neighbours){
			for(Country NextNeighbor:neighbor.getNegibors())
			if(!ownCountry.contains(NextNeighbor))
			enemy+=NextNeighbor.getArmyCount();
		}
		return enemy>=armyCount;
	}

 
}