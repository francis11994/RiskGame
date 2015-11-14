
package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.RiskMap;
import model.Country;
import model.Player;
import model.RiskGame;
import model.Player.PlayerType;

public class AllTest {

	@Test
	public void test() {
		RiskGame game=new RiskGame();
		game.addPlayer(PlayerType.Beginner, "Player 1");
		game.addPlayer(PlayerType.Beginner, "Player 2");
		game.addPlayer(PlayerType.Human, "Player 3");
		game.randomSetCountry(new RiskMap().getAllCountry());
		game.play();
		System.out.println(game.toString());
		game.moveToNext();
		game.play();
		System.out.println(game.toString());
		game.moveToNext();
		game.play();
		System.out.println(game.toString());
		game.moveToNext();
		game.play();
		System.out.println(game.toString());
		game.moveToNext();
		game.play();
		System.out.println(game.toString());
		game.moveToNext();
		game.play();
		System.out.println(game.toString());
		game.moveToNext();
		game.play();
		System.out.println(game.toString());
		game.moveToNext();
		game.play();
		System.out.println(game.toString());
		game.moveToNext();
		game.play();
		System.out.println(game.toString());
		game.moveToNext();
		game.play();
		System.out.println(game.toString());
		game.moveToNext();
		game.play();
		System.out.println(game.toString());
		game.moveToNext();
		game.play();
		System.out.println(game.toString());
		game.moveToNext();
		game.play();
		System.out.println(game.toString());
		game.moveToNext();
		game.play();
		System.out.println(game.toString());
		game.moveToNext();
		game.play();
		System.out.println(game.toString());
		game.moveToNext();
		game.play();
		System.out.println(game.toString());
		game.moveToNext();
		game.play();
		System.out.println(game.toString());
		game.moveToNext();
		game.play();
		System.out.println(game.toString());
		game.moveToNext();
		game.play();
		System.out.println(game.toString());
		game.moveToNext();
		game.play();
		System.out.println(game.toString());
		game.moveToNext();
		game.play();
		System.out.println(game.toString());
		game.moveToNext();
		game.play();
		System.out.println(game.toString());
		game.moveToNext();
		game.play();
		System.out.println(game.toString());
		game.moveToNext();
		game.play();
		System.out.println(game.toString());
		game.moveToNext();
		game.play();
		System.out.println(game.toString());
		game.moveToNext();
		game.play();
		System.out.println(game.toString());
		
		
		
		
	}

}
