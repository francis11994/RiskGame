
package model;

import java.awt.Color;

import model.Player.PlayerType;

public class Run6Bots {
	private RiskGame game = new RiskGame();
	
	public static void main(String[] args) {
		Run6Bots a=new Run6Bots();
		a.BeginnerVSIntermediate();
		a.BeginnerVSHard();
		a.IntermediateVSHard();
		a.BeginnerVSIntermediateVSHard();
	}
	
	public Run6Bots(){
	}
	public void BeginnerVSIntermediate(){
		int a = 0;
		int b = 0;
		int c = 0;
		for (int i = 0; i < 500; i++) {
			game.restart();
			game.addPlayer(new BeginnerAI("beginner(1)",Color.WHITE));
			game.addPlayer(new IntermediateAI("medium(1)", Color.BLUE));
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			c += game.getRound();
			if (game.getPlayer().getType().equals(PlayerType.Intermediate))
				a++;
			else
				b++;
		}
		for (int i = 0; i < 500; i++) {
			game.restart();
			game.addPlayer(new IntermediateAI("medium(1)", Color.BLUE));
			game.addPlayer(new BeginnerAI("beginner(1)",Color.WHITE));
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			c += game.getRound();
			if (game.getPlayer().getType().equals(PlayerType.Intermediate))
				a++;
			else
				b++;
		}
		c = c / 1000;
		System.out.println("Run(1): There are 1000 games (1 vs 1) \t\t(1) Beginner wins " + b
				+ " times\t(2) Medium wins " + a + " times\t\t\t\tthe average round = " + c);
	}

	public void BeginnerVSHard(){
			int a = 0;
			int b = 0;
			int c = 0;
			for (int i = 0; i < 500; i++) {
				game.restart();
				game.addPlayer(new BeginnerAI("beginner(1)",Color.WHITE));
				game.addPlayer(new HardAI("hard(1)",Color.BLUE));
				game.randomSetCountry(new RiskMap().getAllCountry());
				game.play();
				c += game.getRound();
				if (game.getPlayer().getType().equals(PlayerType.Hard))
					a++;
				else
					b++;
			}
			for (int i = 0; i < 500; i++) {
				game.restart();
				game.addPlayer(new HardAI("hard(1)",Color.BLUE));
				game.addPlayer(new BeginnerAI("beginner(1)",Color.WHITE));
				game.randomSetCountry(new RiskMap().getAllCountry());
				game.play();
				c += game.getRound();
				if (game.getPlayer().getType().equals(PlayerType.Hard))
					a++;
				else
					b++;
			}
			c = c / 1000;
			System.out.println("Run(2): There are 1000 games (1 vs 1) \t\t(1) Beginner wins " + b + " times\t(2) hard wins " + a
					+ " times\t\t\t\tthe average round = " + c);
		}
	
	public void IntermediateVSHard(){
		int a = 0;
		int b = 0;
		int c = 0;
		for (int i = 0; i < 500; i++) {
			game.restart();
			game.addPlayer(new IntermediateAI("medium(1)", Color.BLUE));
			game.addPlayer(new HardAI("hard(1)",Color.BLUE));
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			c += game.getRound();
			if (game.getPlayer().getType().equals(PlayerType.Hard))
				a++;
			else
				b++;
		}
		for (int i = 0; i < 500; i++) {
			game.restart();
			game.addPlayer(new HardAI("hard(1)",Color.BLUE));
			game.addPlayer(new IntermediateAI("medium(1)", Color.BLUE));
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			c += game.getRound();
			if (game.getPlayer().getType().equals(PlayerType.Hard))
				a++;
			else
				b++;
		}
		c = c / 1000;
		System.out.println("Run(3): There are 1000 games (1 vs 1) \t\t(1) medium wins " + b + " times\t(2) hard wins " + a
				+ " times\t\t\t\t\tthe average round = " + c);
	}

	public void BeginnerVSIntermediateVSHard(){
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		for (int i = 0; i < 500; i++) {
			game.restart();
			game.addPlayer(new BeginnerAI("beginner(1)",Color.WHITE));
			game.addPlayer(new IntermediateAI("medium(1)", Color.BLUE));
			game.addPlayer(new HardAI("hard(1)",Color.BLUE));
			game.randomSetCountry(new RiskMap().getAllCountry());
			game.play();
			d += game.getRound();
			if (game.getPlayer().getType().equals(PlayerType.Hard))
				a++;
			else
				b++;
		}
		for (int i = 0; i < 500; i++) {
			game.restart();
			game.addPlayer(new IntermediateAI("medium(1)", Color.BLUE));
			game.addPlayer(new HardAI("hard(1)",Color.BLUE));
			game.addPlayer(new BeginnerAI("beginner(1)",Color.WHITE));
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
		System.out.println("Run(4): There are 1000 games (1 vs 1 vs 1) \t(1) beginner wins " + c + " times  (2) medium wins "
				+ b + " times   (3) hard wins " + a + " times\t\tthe average round = " + d);
	}
	
}
