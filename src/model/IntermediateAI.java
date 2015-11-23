package model;

import java.awt.Color;
import java.util.List;
import java.util.Random;

public class IntermediateAI extends Player {

	public IntermediateAI(String username, Color color) {
		super(username, color);
	}

	@Override
	public PlayerType getType() {
		return PlayerType.Intermediate;
	}

	@Override
	public int getUnit(List<Country> countries) {
		int card = getCardUnit();
		int total = card + countries.size() / 3;
		if (total < 3)
			total = 3;
		return total;
	}

	@Override
	public void reinforce( List<Country> countries) {
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
