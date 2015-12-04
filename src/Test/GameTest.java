package Test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.RiskGame;
import model.RiskMap;
import model.BeginnerAI;
import model.Card;
import model.Card.CardType;
import model.CardCollection;
import model.Country;
import model.HardAI;
import model.IntermediateAI;
import model.Player.PlayerType;
import model.RiskMap.CountryType;

public class GameTest {
	@Test
	public void testAI() {
		for (int i = 0; i < 10; i++) {
			RiskGame game = new RiskGame();
			game.addPlayer(new IntermediateAI("medium(1)", Color.BLUE));
			game.addPlayer(new HardAI("hard(1)",Color.BLUE));
			game.addPlayer(new BeginnerAI("beginner(1)",Color.WHITE));
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
		}
	}
	
	@Test
	public void testRun6Bot(){
		Run6Bots a=new Run6Bots();
		a.BeginnerVSIntermediate();
		a.BeginnerVSHard();
	}

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
		ALASKA.addArmys(3);
		ALASKA.removeArmys(2);
		assertEquals(ALASKA.getArmyCount(),2);
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
	
	private Country ICELAND = new Country("Iceland",CountryType.SOUTHAMERICA,100,100);
	private Country SCANDINAVIA = new Country("Scandinavia",CountryType.SOUTHAMERICA,100,100);
	private Country GREATBRITAIN = new Country("Great Britain",CountryType.SOUTHAMERICA,100,100);
	private Country NORTHERNEUROPE = new Country("Northern Europe",CountryType.SOUTHAMERICA,100,100);
	private Country SOUTHERNEUROPE = new Country("Southern Europe",CountryType.SOUTHAMERICA,100,100);
	private Country ALASKA = new Country("Alaska",CountryType.SOUTHAMERICA,100,100);
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
