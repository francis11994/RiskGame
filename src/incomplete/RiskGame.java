package incomplete;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.BeginnerAI;
import model.Card;
import model.CardCollection;
import model.Country;
import model.Dice;
import model.HardAI;
import model.Human;
import model.IntermediateAI;
import model.Player;
import model.Player.PlayerType;
import model.RiskMap;

public class RiskGame {
	private List<List<Country>> AllCountry;
	private static List<Player> players;
	private List<Country> country;
	private CardCollection cards;
	private int index;
	private int round;
	private Random random = new Random();
	private Player currentPlayer;

	public RiskGame() {
		AllCountry = new ArrayList<List<Country>>();
		players = new ArrayList<Player>();
		cards = new CardCollection();
		index = 0;
		round = 0;
	}

	public void addPlayer(PlayerType type, String username) {
		creatPlayer(type, username);
		players.add(currentPlayer);
		country = new ArrayList<Country>();
		AllCountry.add(country);
	}

	private void creatPlayer(PlayerType type, String username) {
		if (type.equals(PlayerType.Beginner)) {
			currentPlayer = new BeginnerAI(username);
		} else if (type.equals(PlayerType.Intermediate)) {
			currentPlayer = new IntermediateAI(username);
		} else if (type.equals(PlayerType.Hard)) {
			currentPlayer = new HardAI(username);
		} else
			currentPlayer = new Human(username);
	}

	public void randomSetCountry(List<Country> world) {
		while (world.size() > 0) {
			int i=random.nextInt(world.size());
			AllCountry.get(index).add(world.remove(i));
			moveToNext();
		}
		round = 0;
		index=0;
	}

	private void moveToNext(){
		index++;
		if (index >= players.size()){
			index = 0;
			round++;
		}
	}
//incomplete here
	public void play(){
		currentPlayer=players.get(index);
		country=AllCountry.get(index);
		if(currentPlayer.getType().equals(PlayerType.Human))
			return;
		if(currentPlayer.getType().equals(PlayerType.Beginner))
			BeginnerMove();
		if(currentPlayer.getType().equals(PlayerType.Intermediate))
			IntermediateMove();
		if(currentPlayer.getType().equals(PlayerType.Hard))
			HardMove();
		moveToNext();
		play();
	}
	//incomplete here
	public void BeginnerMove(){	
		if(round==0)
			currentPlayer.reinforce((10-players.size())*5, country);
		else currentPlayer.reinforce(currentPlayer.getCardUnit(), country);
	}
	
	public void IntermediateMove(){
		if(round==0)
			currentPlayer.reinforce((10-players.size())*5, country);
		else currentPlayer.reinforce(currentPlayer.getCardUnit(), country);
	}
	
	public void HardMove(){
		if(round==0)
			currentPlayer.reinforce((10-players.size())*5, country);
		else currentPlayer.reinforce(currentPlayer.getCardUnit(), country);
	}
	
	
	public void restart() {
		players = new ArrayList<Player>();
		cards.shuffle();
	}
	
	public Player getPlayer(){
		return players.get(index);
	}
	
	public List<Country> getCountry(){
		return AllCountry.get(index);
	}
	
	public String toString(){
		String a="";
		for(int i=0;i<players.size();i++){
			a+="Player name: "+ players.get(i).getname()+".   Country List: ";
			country= AllCountry.get(i);
			for(int n=0;n<country.size();n++)
				a+= n+1+country.get(n).getname()+" "+ country.get(n).getArmyCount()+"  ";
			a+="\n";
		}
		return a;
	}
}