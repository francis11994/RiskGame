package model;

import java.util.ArrayList;
import java.util.List;
//update

public class Country {
	

	int armyCount;
	private Player owner;
	private List<Country> neighbours;
	String name;

	public Country(String c) {
		this.name = c;
		//Chen:
		neighbours = new ArrayList<Country>();
		armyCount = 0;
	}

	public List<Country> getNegibors() {
		//Chen: 
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

	public void setOwner(Player player) {
		owner = player;
	}

	public Player getOwner() {
		return owner;
	}

	
}
