package model;

public class Card {
	public enum CardType {
		Solder, Horseman, Cannon, Wild;
		
	}
	
	private CardType type;
	private String country;
	
	public Card(CardType type, String country){
		this.setType(type);
		this.setCountry(country);
	}

	public CardType getType() {
		return type;
	}

	public void setType(CardType type) {
		this.type = type;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
}