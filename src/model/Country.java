package model;

import java.util.List;

public class Country {
	int armyCount;
	Player owner;
	List <Country> negibhours; //spelling needs to be fixed
	
	public Country(){
		
	}
	
	public List<Country> getNegibors(){
		return null;
	}
	
	public void addNegibor(Country c){
		this.negibhours.add(c);
	}
	
	public void addArmys(int n){
		armyCount += n;
	}
	
	public int getArmyCount(){
		return armyCount;
	}
	
	public Player getOwner(){
		return owner;
	}
	//Chen: add a new method here
	public void setOwner(Player player){
		owner = player;
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
