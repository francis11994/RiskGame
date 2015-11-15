



package Test;

import static org.junit.Assert.*;


import org.junit.Test;

import model.RiskMap;

import model.RiskGame;
import model.Player.PlayerType;

public class AllTest {

	@Test
	public void test1() {
		int a=0;
		int b=0;
		int c=0;
		for(int i=0;i<500;i++){
		RiskGame game=new RiskGame();
		game.addPlayer(PlayerType.Beginner, "beginner(1)");
		game.addPlayer(PlayerType.Intermediate, "medium(1)");
		game.randomSetCountry(new RiskMap().getAllCountry());
		game.play();
		c+=game.getRound();
		if(game.getPlayer().getType().equals(PlayerType.Intermediate))
			a++;
		else b++;
		}
		for(int i=0;i<500;i++){
			RiskGame game=new RiskGame();
			game.addPlayer(PlayerType.Intermediate, "medium(1)");
			game.addPlayer(PlayerType.Beginner, "beginner(1)");
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			c+=game.getRound();
			if(game.getPlayer().getType().equals(PlayerType.Intermediate))
				a++;
			else b++;
			}
		c=c/1000;
		System.out.println("There are 1000 games \t (1) Beginner wins "+b+" times   (2) Medium wins "+a+" times\t\tthe average round = "+c);	
	}
		
	@Test
	public void test2() {
		int a=0;
		int b=0;
		int c=0;
		for(int i=0;i<500;i++){
		RiskGame game=new RiskGame();
		game.addPlayer(PlayerType.Beginner, "beginner(1)");
		game.addPlayer(PlayerType.Hard, "hard(1)");
		game.randomSetCountry(new RiskMap().getAllCountry());
		game.play();
		c+=game.getRound();
		if(game.getPlayer().getType().equals(PlayerType.Hard))
			a++;
		else b++;
		}
		for(int i=0;i<500;i++){
			RiskGame game=new RiskGame();
			game.addPlayer(PlayerType.Hard, "hard(1)");
			game.addPlayer(PlayerType.Beginner, "beginner(1)");
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			c+=game.getRound();
			if(game.getPlayer().getType().equals(PlayerType.Hard))
				a++;
			else b++;
			}
		c=c/1000;
		System.out.println("There are 1000 games \t (1) Beginner wins "+b+" times   (2) hard wins "+a+" times\t\tthe average round = "+c);
	}
	
	@Test
	public void test3() {
		int a=0;
		int b=0;
		int c=0;
		for(int i=0;i<500;i++){
		RiskGame game=new RiskGame();
		game.addPlayer(PlayerType.Intermediate, "medium(1)");
		game.addPlayer(PlayerType.Hard, "hard(1)");
		game.randomSetCountry(new RiskMap().getAllCountry());
		game.play();
		c+=game.getRound();
		if(game.getPlayer().getType().equals(PlayerType.Hard))
			a++;
		else b++;
		}
		for(int i=0;i<500;i++){
			RiskGame game=new RiskGame();
			game.addPlayer(PlayerType.Hard, "hard(1)");
			game.addPlayer(PlayerType.Intermediate, "medium(1)");
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			c+=game.getRound();
			if(game.getPlayer().getType().equals(PlayerType.Hard))
				a++;
			else b++;
			}
		c=c/1000;
		System.out.println("There are 1000 games \t (1) medium wins "+b+" times   (2) hard wins "+a+" times\t\tthe average round = "+c);
	}
	
	@Test
	public void test4() {
		int a=0;
		int b=0;
		int c=0;
		for(int i=0;i<500;i++){
		RiskGame game=new RiskGame();
		game.addPlayer(PlayerType.Beginner, "beginner(1)");
		game.addPlayer(PlayerType.Intermediate, "medium(1)");
		game.addPlayer(PlayerType.Beginner, "beginner(2)");
		game.addPlayer(PlayerType.Intermediate, "medium(2)");
		game.randomSetCountry(new RiskMap().getAllCountry());
		game.play();
		c+=game.getRound();
		if(game.getPlayer().getType().equals(PlayerType.Intermediate))
			a++;
		else b++;
		}
		for(int i=0;i<500;i++){
			RiskGame game=new RiskGame();
			game.addPlayer(PlayerType.Intermediate, "medium(1)");
			game.addPlayer(PlayerType.Beginner, "beginner(1)");
			game.addPlayer(PlayerType.Intermediate, "medium(2)");
			game.addPlayer(PlayerType.Beginner, "beginner(2)");
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			c+=game.getRound();
			if(game.getPlayer().getType().equals(PlayerType.Intermediate))
				a++;
			else b++;
			}
		c=c/1000;
		System.out.println("There are 1000 games \t (1) Beginner wins "+b+" times   (2) Medium wins "+a+" times\tthe average round = "+c);	
	}
	
	@Test
	public void test5() {
		int a=0;
		int b=0;
		int c=0;
		for(int i=0;i<500;i++){
		RiskGame game=new RiskGame();
		game.addPlayer(PlayerType.Beginner, "beginner(1)");
		game.addPlayer(PlayerType.Hard, "hard(1)");
		game.addPlayer(PlayerType.Beginner, "beginner(2)");
		game.addPlayer(PlayerType.Hard, "hard(2)");
		game.randomSetCountry(new RiskMap().getAllCountry());
		game.play();
		c+=game.getRound();
		if(game.getPlayer().getType().equals(PlayerType.Hard))
			a++;
		else b++;
		}
		for(int i=0;i<500;i++){
			RiskGame game=new RiskGame();
			game.addPlayer(PlayerType.Hard, "hard(1)");
			game.addPlayer(PlayerType.Beginner, "beginner(1)");
			game.addPlayer(PlayerType.Hard, "hard(2)");
			game.addPlayer(PlayerType.Beginner, "beginner(2)");
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			c+=game.getRound();
			if(game.getPlayer().getType().equals(PlayerType.Hard))
				a++;
			else b++;
			}
		c=c/1000;
		System.out.println("There are 1000 games \t (1) Beginner wins "+b+" times   (2) hard wins "+a+" times\t\tthe average round = "+c);
	}

	@Test
	public void test6() {
		int a=0;
		int b=0;
		int c=0;
		for(int i=0;i<500;i++){
		RiskGame game=new RiskGame();
		game.addPlayer(PlayerType.Intermediate, "medium(1)");
		game.addPlayer(PlayerType.Hard, "hard(1)");
		game.addPlayer(PlayerType.Intermediate, "medium(2)");
		game.addPlayer(PlayerType.Hard, "hard(2)");
		game.randomSetCountry(new RiskMap().getAllCountry());
		game.play();
		c+=game.getRound();
		if(game.getPlayer().getType().equals(PlayerType.Hard))
			a++;
		else b++;
		}
		for(int i=0;i<500;i++){
			RiskGame game=new RiskGame();
			game.addPlayer(PlayerType.Hard, "hard(1)");
			game.addPlayer(PlayerType.Intermediate, "medium(1)");
			game.addPlayer(PlayerType.Hard, "hard(2)");
			game.addPlayer(PlayerType.Intermediate, "medium(2)");
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			c+=game.getRound();
			if(game.getPlayer().getType().equals(PlayerType.Hard))
				a++;
			else b++;
			}
		c=c/1000;
		System.out.println("There are 1000 games \t (1) medium wins "+b+" times   (2) hard wins "+a+" times\t\tthe average round = "+c);
	}

	
}
