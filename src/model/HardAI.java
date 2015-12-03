package model;

import java.awt.Color;
import java.util.List;
import java.util.Random;

public class HardAI extends Player {

	public HardAI(String username, Color color) {
		super(username, color);
	}

	@Override
	public PlayerType getType() {
		return PlayerType.Hard;
	}

	@Override
	public int getUnit(List<Country> countries) {
		int total = getCardUnit() + countries.size() * 2 / 5;
		if (total < 4)
			total = 4;
		return total;
	}

	@Override
	public void reinforce(List<Country> countries) {
		for (Country a : countries)
			if (a.isThreaten(countries)) {
				a.addArmys(1);
				return;
			}
		Random random = new Random();
		int index = random.nextInt(countries.size());
		countries.get(index).addArmys(1);
	}
}
