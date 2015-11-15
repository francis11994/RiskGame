package model;

import java.util.List;
import java.util.Random;

public class BeginnerAI extends Player {
	private Random random=new Random();
	private int index;
	public BeginnerAI(String username) {
		super(username);
	}
	
	@Override
	public PlayerType getType() {
		return PlayerType.Beginner;
	}

	@Override
	public int getUnit(List<Country> countries) {
		int card=getCardUnit();
		int total= card + countries.size()*2/7;
		if(total<3)
			total=3;
		return total;
	}

	@Override
	public void reinforce(int unit, List<Country> countries) {
		while(unit>0){
			index=random.nextInt(countries.size());
			countries.get(index).addArmys(1);
			unit--;	
		}
		
	}


}
