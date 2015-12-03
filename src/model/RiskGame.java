
package model;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import model.Player.PlayerType;
import model.RiskMap.CountryType;

public class RiskGame extends Observable implements Serializable {
	private List<List<Country>> world;
	private List<Country> country;
	private Country currentCountry;
	private List<Player> players;
	private Player currentPlayer;
	private CardCollection cards;
	private int round;
	private int index;
	private Random random = new Random();
	private Dice dice1 = new Dice();
	private Dice dice2 = new Dice();
	private int RunTime = 0;
	private int reinforcement = 0;

	public RiskMap map = new RiskMap();

	public RiskGame() {
		currentCountry = null;
		world = new ArrayList<List<Country>>();
		players = new ArrayList<Player>();
		cards = new CardCollection();
		index = 0;
		round = 0;
		reinforcement = 0;
	}

	public void addPlayer(Player a) {
		currentPlayer = a;
		players.add(currentPlayer);
		country = new ArrayList<Country>();
		world.add(country);
		setChanged();
		notifyObservers(this);
	}

	public void randomSetCountry(List<Country> countries) {
		while (countries.size() > 0) {
			int i = random.nextInt(countries.size());
			world.get(index).add(countries.remove(i));
			moveToNext();
		}
		reinforcement = 2; ////////////////////// (10 - players.size()) * 5;
		index = 0;
		currentPlayer = players.get(0);
		country = world.get(0);
		setChanged();
		notifyObservers(this);
	}

	// sleep
	public void moveToNext() {
		index++;
		if (index >= players.size()) {
			index = 0;
			if (round > 0)
				round++;
			else {
				if (reinforcement == 1)
					round = 1;
				if (reinforcement > 0)
					reinforcement--;
			}
		}
		currentPlayer = players.get(index);
		country = world.get(index);
		if (round > 0 && players.size() > 1) 
			reinforcement = currentPlayer.getUnit(country)+map.getExtraUnit(country);
	
		setChanged();
		notifyObservers(this);
	}

	public void play() {
		while (!currentPlayer.getType().equals(PlayerType.Human) && players.size() > 1) {

			sleep();
			if (currentPlayer.getType().equals(PlayerType.Beginner))
				BeginnerMove();
			if (currentPlayer.getType().equals(PlayerType.Intermediate)) {
				IntermediateMove();
			}
			if (currentPlayer.getType().equals(PlayerType.Hard))
				HardMove();
			CheckWinner();
			moveToNext();
		}
	}

	// sleep
	public void attact(Country attacter, Country defender) {
		while (attacter.getArmyCount() > 1 && defender.getArmyCount() > 0) {
			dice1.roll();
			dice2.roll();
			if (dice1.isWin(dice2)) {
				defender.removeArmys(1);
				System.out.println(
						"rolled " + dice1.getNumber() + " \trolled " + dice2.getNumber() + "\tdefender lost 1 solider");
			} else {
				attacter.removeArmys(1);
				System.out.println(
						"rolled " + dice1.getNumber() + " \trolled " + dice2.getNumber() + "\tattacker lost 1 solider");
			}
		}
		if (defender.getArmyCount() == 0) {
			for (int i = 0; i < world.size(); i++)
				world.get(i).remove(defender);
			world.get(index).add(defender);
			attacter.moveSolider(defender, 1);
			System.out.println("Finally, attacer win and " + attacter.getArmyCount() + " remainning");

		} else
			System.out.println("Finally, defender win and " + defender.getArmyCount() + " remainning");
		setChanged();
		notifyObservers(this);
		sleep();

		// removeLostPlayer
		int number = -1;
		for (int i = 0; i < players.size(); i++)
			if (world.get(i).size() == 0)
				number = i;
		if (number != -1) {
			world.remove(number);
			players.remove(number);
			if (index > number)
				index--;
			setChanged();
			notifyObservers(this);
			sleep();
			CheckWinner();
		}
	}

	private void CheckWinner() {
		if (round > 150) {
			for (int i = 0; i < world.size() - 1; i++) {
				if (world.get(i).size() < world.get(i + 1).size()) {
					world.remove(i);
					players.remove(i);
					i--;
				} else {
					world.remove(i + 1);
					players.remove(i + 1);
				}
			}
		}
	}

	// Beginner AI randomly set armies, randomly attact countries, cannot submit
	// card
	public void BeginnerMove() {
		if (round == 0) {
			currentPlayer.reinforce(country);
			setChanged();
			notifyObservers(this);
			sleep();
		} else if (round > 0) {
			while (reinforcement != 0) {
				currentPlayer.reinforce(country);
				reinforcement--;
				sleep();
			}
			int count = 0;
			for (int i = 0; i < country.size(); i++) {
				Country attacter = country.get(i);
				List<Country> neighbors = attacter.getEnemyNegibors(country);
				for (int n = 0; n < neighbors.size(); n++) {
					Country neighbor = neighbors.get(n);
					if (attacter.getArmyCount() > 1 && !country.contains(neighbor) && count < 3) {
						sleep();
						attact(attacter, neighbor);
						if (!country.contains(neighbor))
							attacter.moveSolider(neighbor, attacter.getArmyCount() / 2);
						count++;
					}
				}
			}
		}
	}

	// Intermediate AI set armies at threaten countries,randomly attact
	// contries, can submit card
	public void IntermediateMove() {
		if (round == 0) {
			currentPlayer.reinforce(country);
			setChanged();
			notifyObservers(this);
			sleep();
		} else if (round > 0) {
			Country newCountry = country.get(0);
			currentPlayer.addCards(cards.getCard());
			while (reinforcement != 0) {
				currentPlayer.AIsubmitCard();
				currentPlayer.reinforce(country);
				reinforcement--;
				sleep();
			}
			for (int i = 0; i < country.size(); i++) {
				Country attacter = country.get(i);
				List<Country> neighbors = attacter.getEnemyNegibors(country);
				for (Country neighbor : neighbors) {
					if (attacter.getArmyCount() > 1) {
						attact(attacter, neighbor);
						if (country.contains(neighbor)) {
							newCountry = neighbor;
							attacter.moveSolider(neighbor, attacter.getArmyCount() / 2);
							setChanged();
							notifyObservers(this);
							sleep();
						}

					}
				}
			}
			// reinforcement

			setChanged();
			notifyObservers(this);
			sleep();
		}
	}

	public void HardMove() {
		if (round == 0) {
			currentPlayer.reinforce(country);
			setChanged();
			notifyObservers(this);
			sleep();
		} else if (round > 0) {
			Country newCountry = country.get(0);
			currentPlayer.addCards(cards.getCard());
			while (reinforcement != 0) {
				currentPlayer.AIsubmitCard();
				currentPlayer.reinforce(country);
				reinforcement--;
				sleep();
			}
			for (int i = 0; i < country.size(); i++) {
				Country attacter = country.get(i);
				List<Country> neighbors = attacter.getEnemyNegibors(country);
				for (Country neighbor : neighbors) {
					if (attacter.getArmyCount() > 1) {
						attact(attacter, neighbor);
						if (country.contains(neighbor)) {
							newCountry = neighbor;
							attacter.moveSolider(neighbor, attacter.getArmyCount() / 2);
							setChanged();
							notifyObservers(this);
							sleep();
						}

					}
				}
			}
			// reinforcement
			Country LargestArmy = country.get(0);
			for (Country temp : country) {
				if (LargestArmy.getArmyCount() < temp.getArmyCount())
					LargestArmy = temp;
			}
			LargestArmy.moveSolider(newCountry, LargestArmy.getArmyCount() - 1);
			setChanged();
			notifyObservers(this);
			sleep();
		}
	}

	public int getReinforcement() {
		return reinforcement;
	}

	public void setRuntime(int time) {
		RunTime = time;
	}

	public void restart() {
		players.clear();
		world.clear();
		cards.shuffle();
		index = 0;
		round = 0;
		currentCountry = null;
		currentPlayer = null;
		reinforcement = 0;
		setChanged();
		notifyObservers(this);
	}

	public Player getPlayer() {
		return currentPlayer;
	}

	public List<Player> getAllPlayer() {
		return players;
	}

	public int getRound() {
		return round;
	}

	public List<List<Country>> getAllCountry() {
		return world;
	}

	public int getIndex() {
		return index;
	}

	private void sleep() {
		if (RunTime != 0)
			try {
				Thread.sleep(RunTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	public void reinforcement(Country a) {
		a.addArmys(1);
		reinforcement--;
		setChanged();
		notifyObservers(this);

	}
	
	public RiskMap getMap(){
		return map;
	}

}