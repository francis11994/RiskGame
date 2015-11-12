package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.Card.CardType;

public class GameTest {

	@Test
	public void testMap() {
		RiskMap map = new RiskMap();
		List<Country> maps= new ArrayList<Country>();
		maps= map.getAllCountry();
		String a="Iceland";
		String b = "Great Britain";
		String c="Northern Europe";
		String d = "Western Europe";
		String e="Southern Europe";
		String f = "Alaska";
		assertEquals(maps.get(0).getname(),a);
		assertEquals(maps.get(2).getname(),b);
		assertEquals(maps.get(3).getname(),c);
		assertEquals(maps.get(4).getname(),d);
		assertEquals(maps.get(5).getname(),e);
		assertEquals(maps.get(6).getname(),f);
		maps.remove(ICELAND);
	}
	
	@Test
	public void testCountry() {
		assertEquals(ICELAND.getname(),"Iceland");
		assertEquals(SCANDINAVIA.getname(),"Scandinavia");
		assertEquals(GREATBRITAIN.getname(),"Great Britain");
		assertEquals(NORTHERNEUROPE.getname(), "Northern Europe");
		assertEquals(SOUTHERNEUROPE.getname(), "Southern Europe");
		assertEquals(ALASKA.getname(),"Alaska");
		ICELAND.getNegibors();
		ALASKA.addArmys(3);
		ALASKA.removeArmys(2);
		assertEquals(ALASKA.getArmyCount(),2);
		ALASKA.setOwner(3);
		assertEquals(3, ALASKA.getOwner());
	}
	
	
	@Test
	public void testArmies(){
		Armies a=new Armies();
		assertEquals(a.getArmies(),0);
		a.addsoldier(19);
		assertEquals(a.getArmies(),19);
		assertEquals(a.getIndividual(),9);
		assertEquals(a.getGroup(),1);
		
	}
	
	@Test
	public void testCardCollection(){
		CardCollection a=new CardCollection();
		a.shuffle();
	}
	
	@Test 
	public void testCard(){
		assertEquals(card02.getType(),CardType.Cannon );
		assertEquals(card03.getType(),CardType.Cannon );
		assertEquals(card22.getType(),CardType.Horseman );
		assertEquals(card29.getType(),CardType.Soldier );
		assertEquals(card44.getType(),CardType.Wild );
		
		assertTrue(card02.CanTurnIn(card03, card04));
		assertFalse(card02.CanTurnIn(card03, card15));
		assertTrue(card02.CanTurnIn(card17, card38));
		assertTrue(card43.CanTurnIn(card03, card04));
	}
	
	@Test
	public void testDice(){
		Dice a=new Dice();
		Dice b=new Dice();
		a.compareDiceWith(b);
		a.compareDiceWith(b);
		a.compareDiceWith(b);
		a.compareDiceWith(b);
		a.compareDiceWith(b);
		a.compareDiceWith(b);
		a.compareDiceWith(b);
	}
	
	
	@Test
	public void testPlayer(){
		Player a=new Player(1);
		Player b= new Player("Chen");
		assertTrue(a.isBot());
		assertFalse(b.isBot());
		assertEquals(a.getname(),"Bot1");
		assertEquals(b.getname(),"Chen");
		b.addCountry(ALASKA);
		b.addCountry(WESTERNEUROPE);
		b.addCountry(NORTHERNEUROPE);
		b.removeCountry(WESTERNEUROPE);
		b.addCards(card02);
		b.addCards(card03);
		b.addCards(card04);
		b.getCards();
		assertTrue(b.isPlaying());
		assertFalse(a.isPlaying());
		a.addCountry(NORTHERNEUROPE);
		b.submitCard(card02, card04, card03);
		assertEquals(a.getUnit(),3);
		assertEquals(b.getUnit(),5);
	}
	
	@Test
	public void testGame(){
		RiskGame game=new RiskGame();
		game.addPlayer("Chen");
		game.addBot(1);
		game.addBot(2);
		game.randomSetCountry();
		game.moveSoldier(ICELAND, ALASKA, 1);
	}
	private Country ICELAND = new Country("Iceland");
	private Country SCANDINAVIA = new Country("Scandinavia");
	private Country GREATBRITAIN = new Country("Great Britain");
	private Country NORTHERNEUROPE = new Country("Northern Europe");
	private Country WESTERNEUROPE = new Country("Western Europe");
	private Country SOUTHERNEUROPE = new Country("Southern Europe");
	private Country ALASKA = new Country("Alaska");
	
	private Card card02=new Card(CardType.Cannon, "Scandinavia");
	private Card card03=new Card(CardType.Cannon, "Great Britain");
	private Card card04=new Card(CardType.Cannon, "Northern Europe");
	
	private Card card15=new Card(CardType.Horseman, "Central America");
	private Card card17=new Card(CardType.Horseman, "Brazil");
	private Card card22=new Card(CardType.Horseman, "East Africa");

	private Card card29=new Card(CardType.Soldier, "Yakutsk");

	private Card card38=new Card(CardType.Soldier, "Japan");

	
	private Card card43=new Card(CardType.Wild, "Wild Card 1");
	private Card card44=new Card(CardType.Wild, "Wild Card 2");
	

}
