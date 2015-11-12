package model;

import java.util.ArrayList;
import java.util.List;
//update

public class Country {
	

	int armyCount;
	private List<Country> neighbours;
	String name;
	int ownerIndex;

	public Country(String c) {
		this.name = c;
		neighbours = new ArrayList<Country>();
		armyCount = 1;
		ownerIndex=0;
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
	public void setOwner(int a){
		ownerIndex=a;
	}
	public int getOwner(){
		return ownerIndex;
	}
	public String getname(){
		return name;
	}
}