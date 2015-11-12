package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Card.CardType;


public class RiskGame {
	private static Player currentPlayer;
	private static List<Player> players;
	private CardCollection cards;
	private RiskMap maps;
	
public RiskGame(){
	currentPlayer = null;
	players = new ArrayList<Player>();
	maps = new RiskMap();
	cards=new CardCollection();
}

public void addPlayer(String username){
	currentPlayer = new Player(username);
	players.add(currentPlayer);
}

public void addBot(int i){
	currentPlayer = new Player(i);
	players.add(currentPlayer);
}

public void randomSetCountry(){
	ArrayList<Country> allCountries = maps.getAllCountry();
	int index = 0;
	Random random = new Random();
	while(allCountries != null){
		Country country=allCountries.remove(random.nextInt(allCountries.size()));
		 players.get(index).addCountry(country);
		index++;
		if(index>=players.size())
			index=0;
	}
}

public void SetArmy(){
	
}

public void moveSoldier(Country a, Country b, int c){
	a.removeArmys(c);
	b.addArmys(c);
}
public void atactCountry(Player Defenser, Country attacter, Country defenser){
	Dice a=new Dice();
	Dice b = new Dice();
	while(attacter.getArmyCount()>=1 &&defenser.getArmyCount()>=0){
		if(a.compareDiceWith(b))
			defenser.removeArmys(1);
		else attacter.removeArmys(1);
	}
	if(defenser.getArmyCount()==0){
		Defenser.removeCountry(defenser);
		currentPlayer.addCountry(defenser);
	}
}
public void turnInCard(Card card1, Card card2,Card card3){
	currentPlayer.submitCard(card1, card2, card3);
}

public int getUnit(){
	return currentPlayer.getUnit();
}
public boolean IsWin(){
	return players.size()<=1;
}

public void restart(){
	currentPlayer = null;
	players = new ArrayList<Player>();
	maps = new RiskMap();
	cards.shuffle();
}


}