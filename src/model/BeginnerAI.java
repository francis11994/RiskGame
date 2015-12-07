package model;

import java.awt.Color;
import java.util.List;
import java.util.Random;

public class BeginnerAI extends Player {
	private Random random = new Random();
	private int index;

	public BeginnerAI(String username, Color color) {
		super(username, color);
	}

	@Override
	public PlayerType getType() {
		return PlayerType.Beginner;
	}

	@Override
	public int getUnit(List<Country> countries) {
		int card = getCardUnit();
		int total = card + countries.size() / 5;
		if (total < 3)
			total = 2;
		return total;
	}

	@Override
	public void reinforce(List<Country> countries) {
		index = random.nextInt(countries.size());
		Country selected = countries.get(index);
		selected.addArmys(1);
	}

}
