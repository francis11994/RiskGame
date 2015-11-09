package model;

import java.util.List;

public class Country {
	int armyCount;
	Player owner;
	
	public Country(){
		
	}
	
	public List<Country> getNegibors(){
		return null;
	}
	
	public void addArmys(int n){
		armyCount += n;
	}
	
	public int getArmyCount(){
		return getArmyCount();
	}
	
	public Player getOwner(){
		return owner;
	}

	public void attack(Country defender) {
		
	}

	public void move(Country to, int amount) {
		to.addArmys(amount);
		this.removeArmys(amount);
		
		
	}

	private void removeArmys(int amount) {
		this.armyCount -= amount;
		
	}
	
}
