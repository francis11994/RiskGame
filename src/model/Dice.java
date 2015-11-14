
package model;

import java.util.Random;

public class Dice {
	
	private int number;
	public Dice(){
		number=0;
	}
	
	public void roll(){
		Random random=new Random();
		number=random.nextInt(6)+1;
	}
	
	public int getNumber(){
		return number;
	}
	public boolean isWin(Dice defenser){
		if(number > defenser.getNumber()){
			return true; 
		}else{
			return false; 
		}
	}
}