package model;

import java.awt.Color;
import java.util.List;
import java.util.Random;

public class IntermediateAI extends Player {
	private Random random=new Random();
	private int index=0;
	public IntermediateAI(String username, Color color) {
		super(username,color);
	}
	@Override
	public PlayerType getType() {
		return PlayerType.Intermediate;
	}
	@Override
	public int getUnit(List<Country> countries) {
		int card=getCardUnit();
		int total= card + countries.size()/3;
		if(total<3)
			total=3;
		return total;
	}

	@Override
	public void reinforce(int unit, List<Country> countries) {
		for (Country a : countries) {
			while (a.isThreaten(countries) && unit > 0) {
				a.addArmys(1);
				unit--;
			}
		}
		while (unit > 0) {
			randomsetArmy(countries);
			unit--;
		}
	}

	private void randomsetArmy(List<Country> countries) {
		index = random.nextInt(countries.size());
		countries.get(index).addArmys(1);
	}
	


}
