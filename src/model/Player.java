package model;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private List <Country> countries;
	private List <Card> cardsHeld;
	private String username;
	private int cardSubmission;
	private boolean isBot;
	//Construct Beginner AI
	public Player(int i){
		countries = new ArrayList<Country>();
		cardsHeld = new ArrayList<Card>();
		username = "Bot" + i;
		cardSubmission = 0;
		isBot=true;
	}
	//Construct gamer
	public Player(String username) {
		countries = new ArrayList<Country>();
		cardsHeld = new ArrayList<Card>();
		this.username = username;
		cardSubmission = 0;
		isBot=false;
	}
	public boolean isBot(){
		return isBot;
	}
	public String getname(){
		return username;
	}
	
	public void removeCountry(Country a){
		countries.remove(a);
	}
	
	public List<Card> getCards(){
		return cardsHeld;
	}
	
	
	public boolean addCards(Card c){
		if(cardsHeld.size() < 5){
			cardsHeld.add(c);
			return true;
		}else{
			return false;
		}
	}

	public void addCountry(Country country){
		countries.add(country);
	}
	
	public boolean isPlaying(){
		return countries.size()!=0;
	}
	
	//Francis: remove cards when we turn in
	public void submitCard(Card card1, Card card2, Card card3){
		cardsHeld.remove(card1);
		cardsHeld.remove(card2);
		cardsHeld.remove(card3);
		cardSubmission += 1;
	}
	
	public int getUnit(){
		return CardUnit() + CountryUnit();
	}
	
	private int CardUnit(){
		return cardSubmission * 3;
		}
		
	private int CountryUnit(){
		return countries.size();
	}
}