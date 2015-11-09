package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RiskGame {
	private static Player currentPlayer;
	private static List<Player> players;
	private RiskMap maps;
	private Country currentCountry;

	
public RiskGame(){
	currentPlayer=null;
	currentCountry=null;
	players=new ArrayList<Player>();
	maps=new RiskMap();
	
}

public void addPlayer(String username){
	currentPlayer=new Player(username);
	players.add(currentPlayer);
}

//Player class should add a new constructor Player() to add bot into Players
//also a boolean method IsBot() to check whether this player is bot
public void addBot(){
	currentPlayer=new Player();
	players.add(currentPlayer);
}


//Maps class need a method to get all of the countries ( getAllCountry())
//Player class need a method to return the number of the countries it owns. (getNumberOfCountries()
// and setNumberOfCountry())
public void randomSetCountry(){
	ArrayList<Country> allCountries=RiskMap.getAllCountry();
	int index=0;
	Random random=new Random();
	while(allCountries!=null){
		currentCountry=allCountries.remove(random.nextInt(allCountries.size()));
		 players.get(index).addCountry(currentCountry);
		index++;
		if(index>=players.size())
			index=0;
	}
}

public void reinforcement(Country country){
	country.addArmys(1);
}

public void atactCountry(Country attecter, Country defenser){
	
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
	currentPlayer=null;
	currentCountry=null;
	players=new ArrayList<Player>();
	maps=new RiskMap();
}
public void AIPlayer(){
	
}

}
