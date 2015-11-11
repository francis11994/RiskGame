
package model;
//update
public class Card {
	public enum CardType {
		Soldier, Horseman, Cannon, Wild;
		
	}
	
	private CardType type;
	private String country;
	
	public Card(CardType a, String b){
		type = a;
		country = b;
	}

	public CardType getType() {
		return type;
	}

	public String getCountry() {
		return country;
	}
	
	public boolean CanTurnIn(Card card2, Card card3){
		boolean a=sameType(card2, card3);
		boolean b=oneOfEach(card2, card3);
		boolean c=oneOfWildCard(card2,card3);
		return a||b||c;
	}
	
	private boolean sameType(Card a, Card b){
		return type.equals(a.getType()) && type.equals(b.getType());
	}
	
	private boolean oneOfEach(Card a, Card b){
		return !type.equals(a.getType()) && !type.equals(b.getType()) && !a.getType().equals(b.getType());
	}
	
	private boolean oneOfWildCard(Card a, Card b){
		return type.equals(CardType.Wild) || a.getType().equals(CardType.Wild) || b.getType().equals(CardType.Wild);
	}
}