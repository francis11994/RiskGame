package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
	public enum PlayerType {
		Human, Beginner, Intermediate, Hard
	}

	private List<Card> cardsHeld;
	private String username;
	private int cardTimes;
	private Card a;
	private Card b;
	private Card c;
	private boolean submitedCard=false;
	public Player(String username) {
		cardsHeld = new ArrayList<Card>();
		this.username = username;
		cardTimes = 0;
	}

	public String getname() {
		return username;
	}

	public int getCardUnit() {
		int unit = 0;
		if(submitedCard){
			unit =  cardTimes * 3;
			submitedCard=false;
		}
		return unit;
	}

	public boolean addCards(Card c) {
		if (cardsHeld.size() < 5) {
			cardsHeld.add(c);
			return true;
		} else {
			return false;
		}
	}

	public List<Card> getCards() {
		return cardsHeld;
	}

	public void submitCard(Card card1, Card card2, Card card3) {
		cardsHeld.remove(card1);
		cardsHeld.remove(card2);
		cardsHeld.remove(card3);
		cardTimes += 1;
		submitedCard=true;
	}

	public void AIsubmitCard(){
		if(cardsHeld.size()>=3)
			submitCard(cardsHeld.get(0),cardsHeld.get(1),cardsHeld.get(2));
	}
	
	
	public abstract PlayerType getType();

	public abstract int getUnit(List<Country> countries);
	
	public abstract void reinforce(int unit, List<Country> countries);

}