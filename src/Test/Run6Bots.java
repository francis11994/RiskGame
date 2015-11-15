package Test;

import org.junit.Test;

import model.RiskGame;
import model.RiskMap;
import model.Player.PlayerType;

public class Run6Bots {
	@Test
	public void test1() {
		int a = 0;
		int b = 0;
		int c = 0;
		for (int i = 0; i < 500; i++) {
			RiskGame game = new RiskGame();
			game.addPlayer(PlayerType.Beginner, "beginner(1)");
			game.addPlayer(PlayerType.Intermediate, "medium(1)");
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			c += game.getRound();
			if (game.getPlayer().getType().equals(PlayerType.Intermediate))
				a++;
			else
				b++;
		}
		for (int i = 0; i < 500; i++) {
			RiskGame game = new RiskGame();
			game.addPlayer(PlayerType.Intermediate, "medium(1)");
			game.addPlayer(PlayerType.Beginner, "beginner(1)");
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			c += game.getRound();
			if (game.getPlayer().getType().equals(PlayerType.Intermediate))
				a++;
			else
				b++;
		}
		c = c / 1000;
		System.out.println("Test 1\nThere are 1000 games (1 vs 1) \t (1) Beginner wins " + b
				+ " times   (2) Medium wins " + a + " times\t\tthe average round = " + c);
	}

	@Test
	public void test2() {
		int a = 0;
		int b = 0;
		int c = 0;
		for (int i = 0; i < 500; i++) {
			RiskGame game = new RiskGame();
			game.addPlayer(PlayerType.Beginner, "beginner(1)");
			game.addPlayer(PlayerType.Hard, "hard(1)");
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			c += game.getRound();
			if (game.getPlayer().getType().equals(PlayerType.Hard))
				a++;
			else
				b++;
		}
		for (int i = 0; i < 500; i++) {
			RiskGame game = new RiskGame();
			game.addPlayer(PlayerType.Hard, "hard(1)");
			game.addPlayer(PlayerType.Beginner, "beginner(1)");
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			c += game.getRound();
			if (game.getPlayer().getType().equals(PlayerType.Hard))
				a++;
			else
				b++;
		}
		c = c / 1000;
		System.out.println("There are 1000 games (1 vs 1) \t (1) Beginner wins " + b + " times   (2) hard wins " + a
				+ " times\t\tthe average round = " + c);
	}

	@Test
	public void test3() {
		int a = 0;
		int b = 0;
		int c = 0;
		for (int i = 0; i < 500; i++) {
			RiskGame game = new RiskGame();
			game.addPlayer(PlayerType.Intermediate, "medium(1)");
			game.addPlayer(PlayerType.Hard, "hard(1)");
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			c += game.getRound();
			if (game.getPlayer().getType().equals(PlayerType.Hard))
				a++;
			else
				b++;
		}
		for (int i = 0; i < 500; i++) {
			RiskGame game = new RiskGame();
			game.addPlayer(PlayerType.Hard, "hard(1)");
			game.addPlayer(PlayerType.Intermediate, "medium(1)");
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			c += game.getRound();
			if (game.getPlayer().getType().equals(PlayerType.Hard))
				a++;
			else
				b++;
		}
		c = c / 1000;
		System.out.println("There are 1000 games (1 vs 1) \t (1) medium wins " + b + " times   (2) hard wins " + a
				+ " times\t\tthe average round = " + c);
	}

	@Test
	public void test4() {
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		for (int i = 0; i < 500; i++) {
			RiskGame game = new RiskGame();
			game.addPlayer(PlayerType.Beginner, "beginner(1)");
			game.addPlayer(PlayerType.Intermediate, "medium(1)");
			game.addPlayer(PlayerType.Hard, "hard(1)");
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			d += game.getRound();
			if (game.getPlayer().getType().equals(PlayerType.Hard))
				a++;
			else
				b++;
		}
		for (int i = 0; i < 500; i++) {
			RiskGame game = new RiskGame();
			game.addPlayer(PlayerType.Hard, "hard(1)");
			game.addPlayer(PlayerType.Intermediate, "medium(1)");
			game.addPlayer(PlayerType.Beginner, "beginner(1)");
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			d += game.getRound();
			if (game.getPlayer().getType().equals(PlayerType.Hard))
				a++;
			else if (game.getPlayer().getType().equals(PlayerType.Intermediate))
				b++;
			else
				c++;
		}
		d = d / 1000;
		System.out.println("There are 1000 games (1 vs 1 vs 1) \t (1) beginner wins " + c + " times (2) medium wins "
				+ b + " times   (3) hard wins " + a + " times\t\tthe average round = " + d);
	}

	@Test
	public void test5() {
		int a = 0;
		int b = 0;
		int c = 0;
		for (int i = 0; i < 500; i++) {
			RiskGame game = new RiskGame();
			game.addPlayer(PlayerType.Beginner, "beginner(1)");
			game.addPlayer(PlayerType.Beginner, "beginner(2)");
			game.addPlayer(PlayerType.Beginner, "beginner(3)");
			game.addPlayer(PlayerType.Intermediate, "medium(1)");
			game.addPlayer(PlayerType.Intermediate, "medium(2)");
			game.addPlayer(PlayerType.Intermediate, "medium(3)");
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			c += game.getRound();
			if (game.getPlayer().getType().equals(PlayerType.Intermediate))
				a++;
			else
				b++;
		}
		for (int i = 0; i < 500; i++) {
			RiskGame game = new RiskGame();
			game.addPlayer(PlayerType.Intermediate, "medium(1)");
			game.addPlayer(PlayerType.Intermediate, "medium(2)");
			game.addPlayer(PlayerType.Intermediate, "medium(3)");
			game.addPlayer(PlayerType.Beginner, "beginner(1)");
			game.addPlayer(PlayerType.Beginner, "beginner(2)");
			game.addPlayer(PlayerType.Beginner, "beginner(3)");
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			c += game.getRound();
			if (game.getPlayer().getType().equals(PlayerType.Intermediate))
				a++;
			else
				b++;
		}
		c = c / 1000;
		System.out.println("Test2\nThere are 1000 games (3 vs 3) \t (1) Beginner wins " + b
				+ " times   (2) Medium wins " + a + " times\t\tthe average round = " + c);
	}

	@Test
	public void test6() {
		int a = 0;
		int b = 0;
		int c = 0;
		for (int i = 0; i < 500; i++) {
			RiskGame game = new RiskGame();
			game.addPlayer(PlayerType.Beginner, "beginner(1)");
			game.addPlayer(PlayerType.Beginner, "beginner(2)");
			game.addPlayer(PlayerType.Beginner, "beginner(3)");
			game.addPlayer(PlayerType.Hard, "hard(1)");
			game.addPlayer(PlayerType.Hard, "hard(2)");
			game.addPlayer(PlayerType.Hard, "hard(3)");
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			c += game.getRound();
			if (game.getPlayer().getType().equals(PlayerType.Hard))
				a++;
			else
				b++;
		}
		for (int i = 0; i < 500; i++) {
			RiskGame game = new RiskGame();
			game.addPlayer(PlayerType.Hard, "hard(1)");
			game.addPlayer(PlayerType.Hard, "hard(2)");
			game.addPlayer(PlayerType.Hard, "hard(3)");
			game.addPlayer(PlayerType.Beginner, "beginner(1)");
			game.addPlayer(PlayerType.Beginner, "beginner(2)");
			game.addPlayer(PlayerType.Beginner, "beginner(3)");
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			c += game.getRound();
			if (game.getPlayer().getType().equals(PlayerType.Hard))
				a++;
			else
				b++;
		}
		c = c / 1000;
		System.out.println("There are 1000 games (3 vs 3) \t (1) Beginner wins " + b + " times   (2) hard wins " + a
				+ " times\t\tthe average round = " + c);
	}

	@Test
	public void test7() {
		int a = 0;
		int b = 0;
		int c = 0;
		for (int i = 0; i < 500; i++) {
			RiskGame game = new RiskGame();
			game.addPlayer(PlayerType.Intermediate, "medium(1)");
			game.addPlayer(PlayerType.Intermediate, "medium(2)");
			game.addPlayer(PlayerType.Intermediate, "medium(3)");
			game.addPlayer(PlayerType.Hard, "hard(1)");
			game.addPlayer(PlayerType.Hard, "hard(2)");
			game.addPlayer(PlayerType.Hard, "hard(3)");
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			c += game.getRound();
			if (game.getPlayer().getType().equals(PlayerType.Hard))
				a++;
			else
				b++;
		}
		for (int i = 0; i < 500; i++) {
			RiskGame game = new RiskGame();
			game.addPlayer(PlayerType.Hard, "hard(1)");
			game.addPlayer(PlayerType.Hard, "hard(2)");
			game.addPlayer(PlayerType.Hard, "hard(3)");
			game.addPlayer(PlayerType.Intermediate, "medium(1)");
			game.addPlayer(PlayerType.Intermediate, "medium(2)");
			game.addPlayer(PlayerType.Intermediate, "medium(3)");
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			c += game.getRound();
			if (game.getPlayer().getType().equals(PlayerType.Hard))
				a++;
			else
				b++;
		}
		c = c / 1000;
		System.out.println("There are 1000 games (3 vs 3) \t (1) medium wins " + b + " times   (2) hard wins " + a
				+ " times\t\tthe average round = " + c);

	}

	@Test
	public void test8() {
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		for (int i = 0; i < 500; i++) {
			RiskGame game = new RiskGame();
			game.addPlayer(PlayerType.Beginner, "beginner(1)");
			game.addPlayer(PlayerType.Intermediate, "medium(1)");
			game.addPlayer(PlayerType.Hard, "hard(1)");
			game.addPlayer(PlayerType.Beginner, "beginner(2)");
			game.addPlayer(PlayerType.Intermediate, "medium(2)");
			game.addPlayer(PlayerType.Hard, "hard(2)");
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			d += game.getRound();
			if (game.getPlayer().getType().equals(PlayerType.Hard))
				a++;
			else
				b++;
		}
		for (int i = 0; i < 500; i++) {
			RiskGame game = new RiskGame();
			game.addPlayer(PlayerType.Hard, "hard(1)");
			game.addPlayer(PlayerType.Intermediate, "medium(1)");
			game.addPlayer(PlayerType.Beginner, "beginner(1)");
			game.addPlayer(PlayerType.Hard, "hard(2)");
			game.addPlayer(PlayerType.Intermediate, "medium(2)");
			game.addPlayer(PlayerType.Beginner, "beginner(2)");
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			d += game.getRound();
			if (game.getPlayer().getType().equals(PlayerType.Hard))
				a++;
			else if (game.getPlayer().getType().equals(PlayerType.Intermediate))
				b++;
			else
				c++;
		}
		d = d / 1000;
		System.out.println("There are 1000 games (2 vs 2 vs 2) \t (1) beginner wins " + c + " times (2) medium wins "
				+ b + " times   (3) hard wins " + a + " times\t\tthe average round = " + d);
	}

}