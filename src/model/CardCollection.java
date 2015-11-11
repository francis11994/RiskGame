package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Card.CardType;

public class CardCollection {
	private List<Card>   cards;
	private Card card01=new Card(CardType.Cannon, "Iceland");
	private Card card02=new Card(CardType.Cannon, "Scandinavia");
	private Card card03=new Card(CardType.Cannon, "Great Britain");
	private Card card04=new Card(CardType.Cannon, "Northern Europe");
	private Card card05=new Card(CardType.Cannon, "Western Europe");
	private Card card06=new Card(CardType.Cannon, "Southern Europe");
	private Card card07=new Card(CardType.Cannon, "Alaska");
	private Card card08=new Card(CardType.Cannon, "Northwest Territory");
	private Card card09=new Card(CardType.Cannon, "Greenland");
	private Card card10=new Card(CardType.Cannon, "Alberta");
	private Card card11=new Card(CardType.Cannon, "Ontario");
	private Card card12=new Card(CardType.Cannon, "Eastern Canada");
	private Card card13=new Card(CardType.Cannon, "Western United States");
	private Card card14=new Card(CardType.Cannon, "Eastern United States");
	
	private Card card15=new Card(CardType.Horseman, "Central America");
	private Card card16=new Card(CardType.Horseman, "Venezuela");
	private Card card17=new Card(CardType.Horseman, "Brazil");
	private Card card18=new Card(CardType.Horseman, "Peru");
	private Card card19=new Card(CardType.Horseman, "Argentina");
	private Card card20=new Card(CardType.Horseman, "Egypt");
	private Card card21=new Card(CardType.Horseman, "North Africa");
	private Card card22=new Card(CardType.Horseman, "East Africa");
	private Card card23=new Card(CardType.Horseman, "Central Africa");
	private Card card24=new Card(CardType.Horseman, "South Africa");
	private Card card25=new Card(CardType.Horseman, "Madagascar");
	private Card card26=new Card(CardType.Horseman, "Russia");
	private Card card27=new Card(CardType.Horseman, "Ural");
	private Card card28=new Card(CardType.Horseman, "Siberia");
	
	private Card card29=new Card(CardType.Soldier, "Yakutsk");
	private Card card30=new Card(CardType.Soldier, "Kamchatka");
	private Card card31=new Card(CardType.Soldier, "Afghanistan");
	private Card card32=new Card(CardType.Soldier, "Middle East");
	private Card card33=new Card(CardType.Soldier, "India");
	private Card card34=new Card(CardType.Soldier, "China");
	private Card card35=new Card(CardType.Soldier, "Siam");
	private Card card36=new Card(CardType.Soldier, "Irkutsk");
	private Card card37=new Card(CardType.Soldier, "Mongolia");
	private Card card38=new Card(CardType.Soldier, "Japan");
	private Card card39=new Card(CardType.Soldier, "Indonesia");
	private Card card40=new Card(CardType.Soldier, "Eastern Australia");
	private Card card41=new Card(CardType.Soldier, "Western Australia");
	private Card card42=new Card(CardType.Soldier, "New Guinea");
	
	private Card card43=new Card(CardType.Wild, "Wild Card 1");
	private Card card44=new Card(CardType.Wild, "Wild Card 2");
	
	
	public CardCollection(){
			cards=new ArrayList<Card>();
			addAll();
			shuffle();
		}
		
		private void addAll(){
			cards.add(card01);
			cards.add(card02);
			cards.add(card03);
			cards.add(card04);
			cards.add(card05);
			cards.add(card06);
			cards.add(card07);
			cards.add(card08);
			cards.add(card09);
			cards.add(card10);
			cards.add(card11);
			cards.add(card12);
			cards.add(card13);
			cards.add(card14);
			cards.add(card15);
			cards.add(card16);
			cards.add(card17);
			cards.add(card18);
			cards.add(card19);
			cards.add(card20);
			cards.add(card21);
			cards.add(card22);
			cards.add(card23);
			cards.add(card24);
			cards.add(card25);
			cards.add(card26);
			cards.add(card27);
			cards.add(card28);
			cards.add(card29);
			cards.add(card30);
			cards.add(card31);
			cards.add(card32);
			cards.add(card33);
			cards.add(card34);
			cards.add(card35);
			cards.add(card36);
			cards.add(card37);
			cards.add(card38);
			cards.add(card39);
			cards.add(card40);
			cards.add(card41);
			cards.add(card42);
			cards.add(card43);
			cards.add(card44);
		}
		
		public void shuffle(){
			Random random=new Random();
			int a=0;
			for(int i=100;i>0;i--){
				Card card=cards.remove(random.nextInt(44));
				cards.add(random.nextInt(44), card);
			}
		}
		
		public List<Card> getCardCollection(){
			return cards;
		}
		
		
}
