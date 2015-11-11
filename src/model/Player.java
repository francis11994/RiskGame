package model;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private List <Country> countries;
	private List <Card> cardsHeld;
	private String username;
	
	public Player(String username) {
		countries = new ArrayList<Country>();
		this.username = username;
	}
	
<<<<<<< HEAD
	public Player (){
		isBot = true;
	}
	public void placeTroop(Country c, int amount){
		c.addArmys(amount);
	}
	
	public void moveTroops(Country to, Country from, int amount){
		
		
	}
=======
	//Chen:
	public void removeCountry(Country a){
		countries.remove(a);
	}
	
>>>>>>> 6d764016166c14bc898015e5ff0fde548e2d0361
	public List<Card> getCards(){
		return cardsHeld;
	}
	
	//Fixing from Francis
	//we only can hold less than 5 cards in each players deck
	//if number of cards are less than 5 in player, we can add card (true)
	//However, if we have 5 cards, then automatically turn in 3 cards showing player cards (false)
	//TODO
	//Dan: the player needs to select what cards they want to remove when cards > 5 
<<<<<<< HEAD
	//
=======
	
>>>>>>> 6d764016166c14bc898015e5ff0fde548e2d0361
	public boolean addCards(Card c){
		if(cardsHeld.size() < 5){
			cardsHeld.add(c);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isBot(){
		return isBot;
	}

	public void addCountry(Country country){
		countries.add(country);
	}
	
	public int getCountryNumber(){
		return countries.size();
	}
	
	//Francis: remove cards when we turn in
	public void removeCard(Card cards){
		
	}
}
