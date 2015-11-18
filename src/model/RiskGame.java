package model;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import model.Player.PlayerType;

public class RiskGame extends Observable implements Serializable{
	private  List<List<Country>> AllCountry;
	private  List<Player> players;
	private List<Country> country;
	private CardCollection cards;
	private int index;
	private int round;
	private Random random = new Random();
	private Player currentPlayer;
	private Dice dice1 = new Dice();
	private Dice dice2 = new Dice();
	public  int RunTime = 0;
	
	public RiskGame() {
		AllCountry = new ArrayList<List<Country>>();
		players = new ArrayList<Player>();
		cards = new CardCollection();
		index = 0;
		round = 0;
	}

	public void addPlayer(PlayerType type, String username,Color color) {
		creatPlayer(type, username,color);
		players.add(currentPlayer);
		country = new ArrayList<Country>();
		AllCountry.add(country);
		setChanged();
		notifyObservers(this);
	}

	private void creatPlayer(PlayerType type, String username, Color color) {
		if (type.equals(PlayerType.Beginner)) {
			currentPlayer = new BeginnerAI(username,color );
		} else if (type.equals(PlayerType.Intermediate)) {
			currentPlayer = new IntermediateAI(username,color);
		} else if (type.equals(PlayerType.Hard)) {
			currentPlayer = new HardAI(username, color);
		} else
			currentPlayer = new Human(username,color);
	}

	public void randomSetCountry(List<Country> world) {
		while (world.size() > 0) {
			int i = random.nextInt(world.size());
			AllCountry.get(index).add(world.remove(i));
			moveToNext();
		}
		round = (players.size() - 10) * 5;
		index = 0;
		currentPlayer = players.get(0);
		country = AllCountry.get(0);
		setChanged();
		notifyObservers(this);
	}

	public void moveToNext() {
		index++;
		if (index >= players.size()) {
			index = 0;
			round++;
		}
	}

	public boolean isPlaying() {
		boolean isPlaying = players.size() > 1;
		if (isPlaying && round > 150) {
			isPlaying = false;
			for (int i = 0; i < AllCountry.size() - 1; i++) {
				if (AllCountry.get(i).size() < AllCountry.get(i + 1).size()) {
					AllCountry.remove(i);
					players.remove(i);
					i--;
				} else {
					AllCountry.remove(i + 1);
					players.remove(i + 1);
				}
			}
		}
		return isPlaying;
	}

	public void attact(Country attacter, Country defender) {
		while (attacter.getArmyCount() > 1 && defender.getArmyCount() > 0) {
			dice1.roll();
			dice2.roll();
			if (dice1.isWin(dice2))
				defender.removeArmys(1);
			else
				attacter.removeArmys(1);
		}
		if (defender.getArmyCount() == 0) {
			ownerCountry(defender);
			moveSolider(attacter, defender, 1);
			removeLostPlayer();
		}
		setChanged();
		notifyObservers(this);
	}

	public void removeLostPlayer() {
		int number = -1;
		for (int i = 0; i < players.size(); i++)
			if (AllCountry.get(i).size() == 0)
				number = i;
		if (number != -1) {
			AllCountry.remove(number);
			players.remove(number);
			if(index>number)
				index--;
		}
		setChanged();
		notifyObservers(this);
	}

	private void ownerCountry(Country a) {
		for (int i = 0; i < AllCountry.size(); i++)
			AllCountry.get(i).remove(a);
		AllCountry.get(index).add(a);
		
	}

	public void moveSolider(Country here, Country toThere, int number) {
		here.removeArmys(number);
		toThere.addArmys(number);
		setChanged();
		notifyObservers(this);
	}

	public void play() {
		while (!currentPlayer.getType().equals(PlayerType.Human) && isPlaying()) {
			if (currentPlayer.getType().equals(PlayerType.Beginner))
				BeginnerMove();
			if (currentPlayer.getType().equals(PlayerType.Intermediate))
				IntermediateMove();
			if (currentPlayer.getType().equals(PlayerType.Hard))
				HardMove();
			moveToNext();
			currentPlayer = players.get(index);
			country = AllCountry.get(index);
			setChanged();
			notifyObservers(this);
		}
	}

	// Beginner AI randomly set armies, randomly attact countries, cannot submit
	// card
	public void BeginnerMove() {
		if (round <= 0){
			sleep();
			currentPlayer.reinforce(1, country);
		}
		if (round > 0) {
			int unit = currentPlayer.getUnit(country);
			while(unit!=0){
			sleep();
			currentPlayer.reinforce(unit, country);
			unit--;
			}
			int count = 0;
			for (int i = 0; i < country.size(); i++) {
				Country attacter = country.get(i);
				List<Country> neighbors = attacter.getNegibors();
				for (int n = 0; n < neighbors.size(); n++) {
					Country neighbor = neighbors.get(n);
					if (attacter.getArmyCount() > 1 && !country.contains(neighbor) && count < 3) {
						sleep();
						attact(attacter, neighbor);
						if (!country.contains(neighbor))
							moveSolider(attacter, neighbor, attacter.getArmyCount() / 2);
							count++;
					}
				}
			}
		}
	}

	// Intermediate AI set armies at threaten countries,randomly attact
	// contries, can submit card
	public void IntermediateMove() {
		if (round <= 0){
			sleep();
			currentPlayer.reinforce(1, country);
		}
		if (round > 0) {
			Country newCountry=country.get(0);
			currentPlayer.addCards(cards.getCard());
			currentPlayer.AIsubmitCard();
			int unit = currentPlayer.getUnit(country);
			while(unit!=0){
				sleep();
				currentPlayer.reinforce(unit, country);
				unit--;
				}
			for (int i = 0; i < country.size(); i++) {
				Country attacter = country.get(i);
				List<Country> neighbors = attacter.getNegibors();
				for (int n = 0; n < neighbors.size(); n++) {
					Country neighbor = neighbors.get(n);
					if (attacter.getArmyCount() > 1 && !country.contains(neighbor) ) {
						sleep();
						attact(attacter, neighbor);
						if (country.contains(neighbor))
							moveSolider(attacter, neighbor, attacter.getArmyCount() /2);
					}
				}
			}
			Country center=getLargestCountry();
			sleep();
			moveSolider(center, newCountry,center.getArmyCount()-1);	
		}
	}

	public void HardMove() {
		if (round <= 0){
			sleep();
			currentPlayer.reinforce(1, country);
		}
		if (round > 0) {
			Country newCountry=country.get(0);
			currentPlayer.addCards(cards.getCard());
			currentPlayer.AIsubmitCard();
			int unit = currentPlayer.getUnit(country);
			while(unit!=0){
				sleep();
				currentPlayer.reinforce(unit, country);
				unit--;
				}
			for (int i = 0; i < country.size(); i++) {
				Country attacter = country.get(i);
				List<Country> neighbors = attacter.getNegibors();
				for (int n = 0; n < neighbors.size(); n++) {
					Country neighbor = neighbors.get(n);
					if (attacter.getArmyCount() > 1 && !country.contains(neighbor)) {
						sleep();
						attact(attacter, neighbor);
						if (country.contains(neighbor))
							newCountry=neighbor;
							moveSolider(attacter, neighbor, attacter.getArmyCount() /2);
					}
				}
			}
			Country center=getLargestCountry();
			sleep();
			moveSolider(center, newCountry,center.getArmyCount()-1);			
		}
	}

	private Country getLargestCountry(){
		Country a=country.get(0);
		for(Country temp:country){
			if(a.getArmyCount() < temp.getArmyCount())
				a=temp;
		}
			return a;
	}
	public void setRuntime(int time){
		RunTime=time;
	}
	private void sleep(){
		if(RunTime!=0)
		try {
			Thread.sleep(RunTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void restart() {
		players.clear();
		AllCountry.clear();
		cards.shuffle();
		index=0;
		round=0;
		currentPlayer=null;
		setChanged();
		notifyObservers(this);
	}

	public Player getPlayer() {
		return currentPlayer;
	}

	public List<Player> getAllPlayer(){
		return players;
	}
	public int getRound() {
		return round;
	}
	
	public List<List<Country>> getAllCountry(){
		return AllCountry;
	}
	
	public int getIndex(){
		return index;
	}
}