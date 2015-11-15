package model;

import java.util.List;

public class Human extends Player{

	public Human(String username) {
		super(username);
	}

	@Override
	public PlayerType getType() {
	return PlayerType.Human;
	}

	@Override
	public int getUnit(List<Country> countries) {
		int card=getCardUnit();
		int total= card + countries.size();
		if(total<3)
			total=3;
		return total;
	}

	@Override
	public void reinforce(int unit,List<Country> countries) {
		return;
		
	}


}
