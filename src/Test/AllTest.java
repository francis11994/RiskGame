package Test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import incomplete.RiskGame;
import model.RiskMap;
import model.Player.PlayerType;

public class AllTest {

	@Test
	public void test() {
		RiskGame game=new RiskGame();
		game.addPlayer(PlayerType.Beginner, "Player1");
		game.addPlayer(PlayerType.Beginner, "Player2");
		game.addPlayer(PlayerType.Beginner, "Player3");
		game.addPlayer(PlayerType.Human, "Player4");
		game.addPlayer(PlayerType.Human, "Player5");
		game.randomSetCountry(new RiskMap().getAllCountry());
		game.play();
		System.out.println(game.toString());
	}

}
