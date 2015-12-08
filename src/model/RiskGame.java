package model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import javax.swing.Timer;

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
	private String attackInformation;
	private Random random = new Random();
	private Dice dice1 = new Dice();
	private Dice dice2 = new Dice();
	private int RunTime = 0;
	private int reinforcement = 0;
	private Timer timer;
	private Card card;
	private boolean canCard;
	private boolean some=false;

	public RiskMap map = new RiskMap();

	public RiskGame() {
		canCard = false;
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
	}

	public void randomSetCountry(List<Country> countries) {
		while (countries.size() > 0) {
			int i = random.nextInt(countries.size());
			world.get(index).add(countries.remove(i));
			moveToNext();
		}
		reinforcement = (8-players.size())*5;
		index = 0;
		currentPlayer = players.get(0);
		country = world.get(0);
	}

	public Card getCard() {
		return card;
	}

	// sleep
	public void moveToNext() {
		if (canCard) {
			card = cards.getCard();
			currentPlayer.addCards(card);
		}
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
			reinforcement = currentPlayer.getUnit(country) + map.getExtraUnit(country);
		canCard = false;

	}

	public void addCardUnit() {
		reinforcement += currentPlayer.getCardUnit();
		setChanged();
		notifyObservers(this);
	}

	public void play() {
		if (RunTime != 0) {
			if (currentPlayer.getType().equals(PlayerType.Beginner))
				BeginnerMove();
			if (currentPlayer.getType().equals(PlayerType.Intermediate))
				IntermediateMove();
			if (currentPlayer.getType().equals(PlayerType.Hard))
				HardMove();
			setChanged();
			notifyObservers(this);
			CheckWinner();
		} else {
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
	}

	public String attactRoll(Country attacter, Country defender) {
		String temp = "";
		if (defender.getArmyCount() > 0 && attacter.getArmyCount() > 1) {
			dice1.roll();
			dice2.roll();
			if (dice1.isWin(dice2)) {
				defender.removeArmys(1);
				temp += "rolled " + dice1.getNumber() + "                          rolled " + dice2.getNumber()
						+ "\n     defender lost 1 army!\n";
			} else {
				attacter.removeArmys(1);
				temp += "rolled " + dice1.getNumber() + "                          rolled " + dice2.getNumber()
						+ "\n     attacker lost 1 army!\n";
			}
			if (defender.getArmyCount() == 0) {
				canCard = true;
				for (int i = 0; i < world.size(); i++)
					world.get(i).remove(defender);
				world.get(index).add(defender);
			}
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
				CheckWinner();
			}
			setChanged();
			notifyObservers(this);
		}
		return temp;
	}

	public void attact(Country attacter, Country defender) {
		some=false;
		while (attacter.getArmyCount() > 1 && defender.getArmyCount() > 0) {
			dice1.roll();
			dice2.roll();
			if (dice1.isWin(dice2)) {
				defender.removeArmys(1);
			} else {
				attacter.removeArmys(1);			}
		}
		if (defender.getArmyCount() == 0) {
			some=true;
			for (int i = 0; i < world.size(); i++)
				world.get(i).remove(defender);
			world.get(index).add(defender);
			attacter.moveSolider(defender, 1);

		} else
			setChanged();
		notifyObservers(this);

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
			CheckWinner();
		}
	}

	private void CheckWinner() {
		if (round > 40) {
			for (int i = 0; i < world.size() - 1; i++) {
				if (world.get(i).size() < world.get(i + 1).size()) {
					world.get(i + 1).addAll(world.get(i));
					world.remove(i);
					players.remove(i);
					i--;
				} else {
					world.get(i).addAll(world.get(i + 1));
					world.remove(i + 1);
					players.remove(i + 1);
				}
			}
			setChanged();
			notifyObservers(this);
		}
	}

	// Beginner AI randomly set armies, randomly attact countries, cannot submit
	// card
	public void BeginnerMove() {
		if (RunTime != 0) {
			if (round == 0) {
				currentPlayer.reinforce(country);
				setChanged();
				notifyObservers(this);
				sleep(); // sleep
				return;
			} else if (round > 0) {
				Country newCountry = country.get(0);
				if (reinforcement > 0) {
					currentPlayer.reinforce(country);
					reinforcement--;
					setChanged();
					notifyObservers(this);
					sleep(); // sleep
					return;
				}
				if(world.get(index).size()>5){
				for (int i = 0; i < country.size(); i++) {
					Country attacter = country.get(i);
					List<Country> neighbors = attacter.getEnemyNegibors(country);
					for (int n = 0; n < neighbors.size(); n++) {
						Country neighbor = neighbors.get(n);
						if (attacter.getArmyCount() > 1 && !country.contains(neighbor)) {
							attact(attacter, neighbor); 
							if (some) {
								newCountry = neighbor;
								attacter.moveSolider(neighbor, attacter.getArmyCount() / 2); // sleep
							}
							setChanged();
							notifyObservers(this);
							sleep();
							return;
						}
					}
				}
						Country LargestArmy = country.get(0);
						for (Country temp : country) {
							if (LargestArmy.getArmyCount() < temp.getArmyCount())
								LargestArmy = temp;
						}
						LargestArmy.moveSolider(newCountry, LargestArmy.getArmyCount() - 1);	
				}}
			if (players.size() > 1) {
				moveToNext();
				play();
			}
		} else {
			if (round == 0) {
				currentPlayer.reinforce(country);
			} else if (round > 0) {
				Country newCountry = country.get(0);
				while (reinforcement != 0) {
					currentPlayer.reinforce(country);
					reinforcement--;
				}
				int count = 0;
				for (int i = 0; i < country.size(); i++) {
					Country attacter = country.get(i);
					List<Country> neighbors = attacter.getEnemyNegibors(country);
					for (int n = 0; n < neighbors.size(); n++) {
						Country neighbor = neighbors.get(n);
						if (attacter.getArmyCount() > 1 && !country.contains(neighbor) && count < 2) {
							attact(attacter, neighbor);
							if (country.contains(neighbor)) {
								newCountry = neighbor;
								attacter.moveSolider(neighbor, attacter.getArmyCount() / 2);
							}
							count++;
						}
					}
				}
				if(world.get(index).size()>5){
				Country LargestArmy = country.get(0);
				for (Country temp : country) {
					if (LargestArmy.getArmyCount() < temp.getArmyCount())
						LargestArmy = temp;
				}
				LargestArmy.moveSolider(newCountry, LargestArmy.getArmyCount() - 1);
						}
			}
		}
	}

	// Intermediate AI set armies at threaten countries,randomly attact
	// contries, can submit card
	public void IntermediateMove() {
		if (RunTime != 0) {
			if (round == 0) {
				currentPlayer.reinforce(country);
				setChanged();
				notifyObservers(this);
				sleep(); // sleep
				return;
			} else if (round > 0) {
				Country newCountry = country.get(0);
				if (reinforcement > 0) {
					currentPlayer.reinforce(country);
					reinforcement--;
					setChanged();
					notifyObservers(this);
					sleep(); // sleep
					return;
				}
				if(world.get(index).size()>5){
				for (int i = 0; i < country.size(); i++) {
					Country attacter = country.get(i);
					List<Country> neighbors = attacter.getEnemyNegibors(country);
					for (Country neighbor : neighbors) {
						if (attacter.getArmyCount() > 1) {
							attact(attacter, neighbor);
							if (some) {
								newCountry = neighbor;
								attacter.moveSolider(neighbor, attacter.getArmyCount() / 2);
							}
							setChanged();
							notifyObservers(this);
							sleep(); // sleep
							return;
						}
					}
						Country LargestArmy = country.get(0);
						for (Country temp : country) 
							if (LargestArmy.getArmyCount() < temp.getArmyCount())
								LargestArmy = temp;
						LargestArmy.moveSolider(newCountry, LargestArmy.getArmyCount() - 1);				
				// reinforcement
			}
				}
				if (players.size() > 1) {
					moveToNext();
					play();
				}
			}
		} else {
			if (round == 0) {
				currentPlayer.reinforce(country);
			} else if (round > 0) {
				Country newCountry = country.get(0);
				while (reinforcement != 0) {
					currentPlayer.AIsubmitCard();
					currentPlayer.reinforce(country);
					reinforcement--;
				}
				if(world.get(index).size()>5){
				for (int i = 0; i < country.size(); i++) {
					Country attacter = country.get(i);
					List<Country> neighbors = attacter.getEnemyNegibors(country);
					for (Country neighbor : neighbors) {
						if (attacter.getArmyCount() > 1) {
							attact(attacter, neighbor);
							if (country.contains(neighbor)) {
								
								newCountry = neighbor;
								attacter.moveSolider(neighbor, attacter.getArmyCount() / 2);
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
									}
			}
			}
		}
	}

	public void HardMove() {
		if (RunTime != 0) {
			if (round == 0) {
				currentPlayer.reinforce(country);
				setChanged();
				notifyObservers(this);
				sleep(); // sleep
				return;
			} else if (round > 0) {
				Country newCountry = country.get(0);
				if (reinforcement > 0) {
					currentPlayer.reinforce(country);
					reinforcement--;
					setChanged();
					notifyObservers(this);
					sleep(); // sleep
					return;
				}
				if(world.get(index).size()>5){
				for (int i = 0; i < country.size(); i++) {
					Country attacter = country.get(i);
					List<Country> neighbors = attacter.getEnemyNegibors(country);
					for (Country neighbor : neighbors) {
						if (attacter.getArmyCount() > 1) {
							attact(attacter, neighbor);
							if (some) {
								newCountry = neighbor;
								attacter.moveSolider(neighbor, attacter.getArmyCount() / 2);
							}
							setChanged();
							notifyObservers(this);
							sleep(); // sleep
							return;
						}
					}
				}
					Country LargestArmy = country.get(0);
					for (Country temp : country) 
						if (LargestArmy.getArmyCount() < temp.getArmyCount())
							LargestArmy = temp;
					LargestArmy.moveSolider(newCountry, LargestArmy.getArmyCount() - 1);
				}
				// reinforcement
		}
				if (players.size() > 1) {
					moveToNext();
					play();
			}
		} else {
			if (round == 0) {
				currentPlayer.reinforce(country);
			} else if (round > 0) {
				Country newCountry = country.get(0);
				while (reinforcement != 0) {
					currentPlayer.AIsubmitCard();
					currentPlayer.reinforce(country);
					reinforcement--;
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
							}

						}
					}
					Country LargestArmy = country.get(0);
					for (Country temp : country) {
						if (LargestArmy.getArmyCount() < temp.getArmyCount())
							LargestArmy = temp;
					}
					LargestArmy.moveSolider(newCountry, LargestArmy.getArmyCount() - 1);
				}
				// reinforcement
				Country LargestArmy = country.get(0);
				for (Country temp : country) {
					if (LargestArmy.getArmyCount() < temp.getArmyCount())
						LargestArmy = temp;
				}
				LargestArmy.moveSolider(newCountry, LargestArmy.getArmyCount() - 1);
			}
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

	public void reinforcement(Country a) {
		a.addArmys(1);
		reinforcement--;
		setChanged();
		notifyObservers(this);

	}

	public RiskMap getMap() {
		return map;
	}

	public void sleep() {
		if (RunTime == 0) {
			if (round == 0) {
				moveToNext();
				play();
			} else if (currentPlayer.getType().equals(PlayerType.Beginner))
				BeginnerMove();
			else if (currentPlayer.getType().equals(PlayerType.Intermediate))
				IntermediateMove();
			else if (currentPlayer.getType().equals(PlayerType.Hard))
				HardMove();
		} else {
			timer = new Timer(10, new TimerListener());
			timer.start();
		}
	}

	public class TimerListener implements ActionListener {
		int count = 0;

		public TimerListener() {
			count = 0;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (10 * count < RunTime)
				count++;
			else {
				count = 0;
				timer.stop();
				if (round == 0) {
					moveToNext();
					play();
				} else if (currentPlayer.getType().equals(PlayerType.Beginner))
					BeginnerMove();
				else if (currentPlayer.getType().equals(PlayerType.Intermediate))
					IntermediateMove();
				else if (currentPlayer.getType().equals(PlayerType.Hard))
					HardMove();
			}

		}

	}

}