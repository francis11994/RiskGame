package model;

import java.util.List;
import java.util.Random;

public class HardAI extends Player{
	private Random random=new Random();
	private int index;
	public HardAI(String username) {
		super(username);
	}

	@Override
	public PlayerType getType() {
		return PlayerType.Hard;
	}

	@Override
	public int getUnit(List<Country> countries) {
		int card=getCardUnit();
		int total= card + countries.size();
		if(total<5)
			total=5;
		return total;
	}

	@Override
	public void reinforce(int unit,List<Country> countries) {
		while(unit>0){
			index=random.nextInt(countries.size());
			countries.get(index).addArmys(1);
			unit--;	
		}
	}


}
