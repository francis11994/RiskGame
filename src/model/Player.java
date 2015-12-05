package model;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Player implements Serializable {
	public enum PlayerType {
		Human, Beginner, Intermediate, Hard
	}

	private List<Card> cardsHeld;
	private String username;
	private int cardTimes;
	private Color color;
	private boolean submitedCard = false;

	public Player(String username, Color c) {
		cardsHeld = new ArrayList<Card>();
		this.username = username;
		cardTimes = 0;
		color = c;
	}

	public void addCards(Card c) {
		if (cardsHeld.size() < 5)
			cardsHeld.add(c);
	}

	public void AIsubmitCard() {
		if (cardsHeld.size() == 3)
			if (cardsHeld.get(0).CanTurnIn(cardsHeld.get(1), cardsHeld.get(2)))
				submitCard(cardsHeld.get(0), cardsHeld.get(1), cardsHeld.get(2));
		if (cardsHeld.size() == 4) {
			if (cardsHeld.get(0).CanTurnIn(cardsHeld.get(1), cardsHeld.get(3)))
				submitCard(cardsHeld.get(0), cardsHeld.get(1), cardsHeld.get(3));
			else if (cardsHeld.get(0).CanTurnIn(cardsHeld.get(2), cardsHeld.get(3)))
				submitCard(cardsHeld.get(0), cardsHeld.get(2), cardsHeld.get(3));
			else if (cardsHeld.get(1).CanTurnIn(cardsHeld.get(2), cardsHeld.get(3)))
				submitCard(cardsHeld.get(1), cardsHeld.get(2), cardsHeld.get(3));
		}
		if (cardsHeld.size() == 5) {
			if (cardsHeld.get(0).CanTurnIn(cardsHeld.get(1), cardsHeld.get(4)))
				submitCard(cardsHeld.get(0), cardsHeld.get(1), cardsHeld.get(4));
			else if (cardsHeld.get(0).CanTurnIn(cardsHeld.get(2), cardsHeld.get(4)))
				submitCard(cardsHeld.get(0), cardsHeld.get(2), cardsHeld.get(4));
			else if (cardsHeld.get(1).CanTurnIn(cardsHeld.get(2), cardsHeld.get(4)))
				submitCard(cardsHeld.get(1), cardsHeld.get(2), cardsHeld.get(4));
			else if (cardsHeld.get(0).CanTurnIn(cardsHeld.get(3), cardsHeld.get(4)))
				submitCard(cardsHeld.get(0), cardsHeld.get(3), cardsHeld.get(4));
			else if (cardsHeld.get(1).CanTurnIn(cardsHeld.get(3), cardsHeld.get(4)))
				submitCard(cardsHeld.get(1), cardsHeld.get(3), cardsHeld.get(4));
			else if (cardsHeld.get(2).CanTurnIn(cardsHeld.get(3), cardsHeld.get(4)))
				submitCard(cardsHeld.get(2), cardsHeld.get(3), cardsHeld.get(4));
		}

	}

	public String getName() {
		return username;
	}

	public Color getColor() {
		return color;
	}
	
	public void submitCard(Card card1, Card card2, Card card3) {
		cardsHeld.remove(card1);
		cardsHeld.remove(card2);
		cardsHeld.remove(card3);
		cardTimes += 1;
		submitedCard = true;
	}

	public int getCardUnit2(){
		int unit = 0;
		if(cardTimes == 1)
			unit = 4;
		else if(cardTimes == 2)
			unit = 6;
		else if(cardTimes == 3)
			unit = 8;
		else if(cardTimes == 4)
			unit = 10;
		else if(cardTimes == 5)
			unit = 12;
		else if(cardTimes > 5)
			unit = cardTimes * 5 - 15;
		return unit;
	}
	public int getCardUnit() {
		if (submitedCard) {
			submitedCard = false;
			return getCardUnit2();
		}
		return 0;
	}
	
	public List<Card> getCardsHeld(){
		return cardsHeld;
	}
	public abstract PlayerType getType();

	public abstract int getUnit(List<Country> countries);

	public abstract void reinforce(List<Country> countries);

}