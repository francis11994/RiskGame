package model;

import java.util.HashMap;
import java.util.List;

public class Country {
	

	int armyCount;
	private Player owner;
	private List<Country> neighbours;
	String name;

	public Country(String c) {
		this.name = c;
	}

	public List<Country> getNegibors() {
		return null;
	}

	public void addNeighbour(Country c) {
		this.neighbours.add(c);
	}

	public void addArmys(int n) {
		armyCount += n;
	}

	public int getArmyCount() {
		return armyCount;
	}

	public Player getOwner() {
		return owner;
	}

	// Chen: add a new method here
	public void setOwner(Player player) {
		owner = player;
	}

	public void attack(Country defender) {
		if(!neighbours.contains(defender)){
			return;
		}
		
		//dice roll
	}

	public void move(Country to, int amount) {
		to.addArmys(amount);
		this.removeArmys(amount);

	}

	private void removeArmys(int amount) {
		this.armyCount -= amount;

	}

}
