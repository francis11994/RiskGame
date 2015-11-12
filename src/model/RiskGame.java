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

//sdafafafawefwefwfrwgargwragrwawrwrwe
public void SetArmy(){
	int i=0;
	currentPlayer=players.get(0);
	
}

public void atactCountry(Player Attacter, Player Defenser, Country attacter, Country defenser){
	Dice a=new Dice();
	Dice b = new Dice();
	while(attacter.getArmyCount()!=0 &&defenser.getArmyCount()!=0){
		if(a.compareDiceWith(b))
			attacter.removeArmys(1);
		else defenser.removeArmys(1);
	}
}
public void turnInCard(Card card1, Card card2,Card card3){
	currentPlayer.removeCard(card1);
	currentPlayer.removeCard(card2);
	currentPlayer.removeCard(card3);
}
public int getUnit(){
	return currentPlayer.getCountryNumber();
}
public boolean IsWin(){
	return players.size()<=1;
}
public boolean IsLost(){
	return currentPlayer.getCountryNumber()==0;
}
public void restart(){
	currentPlayer = null;
	currentCountry = null;
	players = new ArrayList<Player>();
	maps = new RiskMap();
}
public void AIPlayer(){
	
}

}