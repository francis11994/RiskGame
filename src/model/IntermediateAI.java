package model;

import java.util.List;
import java.util.Random;

public class IntermediateAI extends Player {
	
	private Random random=new Random();
	private int index=0;
	public IntermediateAI(String username) {
		super(username);
	}
	@Override
	public PlayerType getType() {
		return PlayerType.Intermediate;
	}
	@Override
	public int getUnit(List<Country> countries) {
		int card=getCardUnit();
		int total= card + countries.size()/3;
		if(total<4)
			total=4;
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
