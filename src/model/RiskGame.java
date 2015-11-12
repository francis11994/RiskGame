package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RiskGame {
	private static List<Player> players;
	private CardCollection cards;
	private RiskMap maps;
	private int index;
	private int round;
	private boolean isplaying;
	public RiskGame() {
		players = new ArrayList<Player>();
		maps = new RiskMap();
		cards = new CardCollection();
		index = 0;
		round = 0;
		isplaying=false;
	}

	public void addPlayer(String username) {
		players.add(new Player(username));
	}

	public void addBot(int i) {
		players.add(new Player(i));
	}

	public void randomSetCountry() {
		ArrayList<Country> allCountries = maps.getAllCountry();
		Random random = new Random();
		while (allCountries != null) {
			Country country = allCountries.remove(random.nextInt(allCountries.size()));
			players.get(index).addCountry(country);
			index++;
			if (index >= players.size())
				index = 0;
		}
		index=0;
	}

	public void moveSoldier(Country a, Country b, int c) {
		a.removeArmys(c);
		b.addArmys(c);
		nextPlayer();
	}

	public void atactCountry(Player Defenser, Country attacter, Country defenser) {
		Dice a = new Dice();
		Dice b = new Dice();
		while (attacter.getArmyCount() >= 1 && defenser.getArmyCount() >= 0) {
			if (a.compareDiceWith(b))
				defenser.removeArmys(1);
			else
				attacter.removeArmys(1);
		}
		if (defenser.getArmyCount() == 0) {
			Defenser.removeCountry(defenser);
			players.get(index).addCountry(defenser);
		}
	}

	public void turnInCard(Card card1, Card card2, Card card3) {
		players.get(index).submitCard(card1, card2, card3);
	}
	
	public void nextPlayer(){
		if(index==players.size()){
			round++;
			index=0;
		}
		else index++;	
	}

	public int getUnit() {
		if(round>0)
		return players.get(index).getUnit();
		else return (10-players.size())*5;
	}

	public boolean IsWin() {
		return players.size() <= 1;
	}

	public void restart() {
		players = new ArrayList<Player>();
		maps = new RiskMap();
		cards.shuffle();
	}

	public void BeginnerAI(){
		
	}
}