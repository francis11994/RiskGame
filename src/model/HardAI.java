package model;

import java.awt.Color;
import java.util.List;
import java.util.Random;

public class HardAI extends Player{
	private Random random=new Random();
	private int index;
	public HardAI(String username, Color color) {
		super(username,color);
	}

	@Override
	public PlayerType getType() {
		return PlayerType.Hard;
	}

	@Override
	public int getUnit(List<Country> countries) {
		int card=getCardUnit();
		int total= card + countries.size()*2/5;
		if(total<4)
			total=4;
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
