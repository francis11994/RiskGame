package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Player.PlayerType;

public class RiskGame {
	private static List<List<Country>> AllCountry;
	private static List<Player> players;
	private List<Country> country;
	private CardCollection cards;
	private int index;
	private int round;
	private Random random = new Random();
	private Player currentPlayer;
	private Dice dice1=new Dice();
	private Dice dice2=new Dice();

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

	public void moveToNext(){
		index++;
		if (index >= players.size()){
			index = 0;
			round++;
		}
	}
	
	public int getUnit(){
		int unit=0;
		if(round==0)
			unit= (10-players.size())*5;
		else unit=currentPlayer.getUnit(AllCountry.get(index));
		return unit;		
	}
	
	public void attact(Country attacter, Country defender){
		while(attacter.getArmyCount()>1&&defender.getArmyCount()>0){
			dice1.roll();
			dice2.roll();
			if(dice1.isWin(dice2))
				defender.removeArmys(1);
			else attacter.removeArmys(1);
		}
		if(defender.getArmyCount()==0){
			ownerCountry(defender);
			moveSolider(attacter, defender,1);
		}
	}
	
	private void ownerCountry(Country a){
		for(int i=0;i<AllCountry.size();i++)
			AllCountry.get(i).remove(a);
		AllCountry.get(index).add(a);
	}
	
	public void moveSolider(Country here, Country toThere, int number ){
		here.removeArmys(number);
		toThere.addArmys(number);
	}

	public void play(){
		currentPlayer=players.get(index);
		country=AllCountry.get(index);
		if(currentPlayer.getType().equals(PlayerType.Beginner))
			BeginnerMove();
		if(currentPlayer.getType().equals(PlayerType.Intermediate))
			IntermediateMove();
		if(currentPlayer.getType().equals(PlayerType.Hard))
			HardMove();
		
	}

	public void BeginnerMove(){	
			currentPlayer.reinforce(getUnit(), country);
			System.out.println("Beginner Unit:" + getUnit());
		if(round>0){
			for(int i=0;i<10;i++){
				int t=random.nextInt(country.size());
				Country state=country.get(t);
				if(state.getArmyCount()>2){
				List<Country> neighbors=state.getNegibors();
				int a=random.nextInt(neighbors.size());
					if(!country.contains(neighbors.get(a)))
						attact(state,neighbors.get(a));
				}
			}
		}
		moveToNext();
		play();
	}
	
	public void IntermediateMove(){
		currentPlayer.reinforce(getUnit(), country);
		if(round>0){
			//attact
			//moveArmy
		}
		moveToNext();
		play();
	}
	
	public void HardMove(){
		currentPlayer.reinforce(getUnit(), country);
		if(round>0){
			//attact
			//moveArmy
		}
		moveToNext();
		play();
	}
	
	
	public void restart() {
		players = new ArrayList<Player>();
		cards.shuffle();
	}
	
	public String toString(){
		String a="Round "+round+"\n";
		
		for(int i=0;i<players.size();i++){
			a+="Player name: "+ players.get(i).getname()+"\t"+"Country ("+AllCountry.get(i).size()+")\t";
			country= AllCountry.get(i);
			for(int n=0;n<country.size();n++)
				a+=country.get(n).getArmyCount()+country.get(n).getname()+"\t\t";
			a+="\n";
		}
		return a;
	}
	public Player getPlayer(){
		return currentPlayer;
	}
	public List<Country> getCountry(){
		return country;
	}
}