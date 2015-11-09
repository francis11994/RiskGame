package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
	List <Country> countries;
	List <Card> cardsHeld;
	
	public Player() {
		countries = new ArrayList<Country>();
	}
	public void placeTroop(Country c, int amount){
		c.addArmys(amount);
	}
	
	public void attack(Country attacker, Country defender){
		attacker.attack(defender);
	}
	
	public void moveTroops(Country to, Country from, int amount){
		from.move(to, amount);
		
	}
	public List<Card> getCards(){
		return cardsHeld;
	}
	
	public void addCards(Card c){
		cardsHeld.add(c);
	}
	//Chen: addCountry(), getCountryNumber(),
	public void addCountry(Country country){
		countries.add(country);
	}
	
	public int getCountryNumber(){
		return countries.size();
	}
}
