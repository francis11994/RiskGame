package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private List <Country> countries;
	private List <Card> cardsHeld;
	private String username;
	private boolean isBot;
	
	public Player(String username) {
		countries = new ArrayList<Country>();
		this.username = username;
		isBot = false;
	}
	
	public Player (){
		isBot = true;
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
	
	public boolean isBot(){
		return isBot;
	}
	//Chen: addCountry(), getCountryNumber(),
	public void addCountry(Country country){
		countries.add(country);
	}
	
	public int getCountryNumber(){
		return countries.size();
	}
}
