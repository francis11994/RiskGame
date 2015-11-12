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
	private Random random = new Random();
	private List<Country> countries;
	public RiskGame() {
		players = new ArrayList<Player>();
		maps = new RiskMap();
		cards = new CardCollection();
		index = 0;
		round = 0;
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
		while (allCountries.size() >=1) {
			Country country = allCountries.remove(random.nextInt(allCountries.size()));
			players.get(index).addCountry(country);
			country.setOwner(index);
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

	public void atactCountry(Country attacter, Country defenser) {
		Dice a = new Dice();
		Dice b = new Dice();
		while (attacter.getArmyCount() >= 2 && defenser.getArmyCount() >= 0) {
			if (a.compareDiceWith(b))
				defenser.removeArmys(1);
			else
				attacter.removeArmys(1);
		}
		if (defenser.getArmyCount() == 0) {
			players.get(defenser.getOwner()).removeCountry(defenser);
			defenser.setOwner(index);
			players.get(index).addCountry(defenser);
			defenser.addArmys(1);
			attacter.removeArmys(1);
		}
		attacter.addArmys(1);
		defenser.removeArmys(1);
	}

	public void turnInCard(Card card1, Card card2, Card card3) {
		players.get(index).submitCard(card1, card2, card3);
	}
	
	public void nextPlayer(){
		checkLostPlayer();
		if(index==players.size()-1){
			round++;
			index=0;
		}
		else index++;
		if(!IsWin()){
			if(players.get(index).isBot())
				BeginnerAI();
		}
	}

	public int getUnit() {
		if(round>0)
		return players.get(index).getUnit();
		else return (10-players.size())*5;
	}

	public boolean IsWin() {
		return players.size() <= 1;
	}

	public void checkLostPlayer(){
		for(Player a: players)
			if(!a.isPlaying())
				players.remove(a);
	}
	public void restart() {
		players = new ArrayList<Player>();
		maps = new RiskMap();
		cards.shuffle();
	}

	//Beginner AI is here
	//Beginner AI is here
	//Beginner AI is here
	public void BeginnerAI(){
		int unit=getUnit();
		randomSetArmy(unit);
		if(round>0){
			randomAttact();
		}
		nextPlayer();	
	}
	private void randomAttact(){
		for(Country a: countries){
			if(a.getArmyCount()>=3)
			if(a.getOwner()!= a.getNegibors().get(0).getOwner())
			atactCountry(a, a.getNegibors().get(0));
			}
			
		}
	
	
	private void randomSetArmy(int a){
		countries= players.get(index).getCountryList();
		while(a>0){
			countries.get(random.nextInt(countries.size())).addArmys(1);
			a--;
		}
	}
}