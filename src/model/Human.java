package model;

import java.awt.Color;
import java.util.List;

public class Human extends Player {

	public Human(String username, Color color) {
		super(username, color);
	}

	@Override
	public PlayerType getType() {
		return PlayerType.Human;
	}

	@Override
	public int getUnit(List<Country> countries) {
		int total = countries.size() / 3;
		if (total < 3)
			total = 3;
		return total;
	}

	@Override
	public void reinforce(List<Country> countries) {

	}

}
