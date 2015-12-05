
package model;

import java.io.Serializable;
import java.util.Random;

public class Dice implements Serializable{
	private Random random=new Random();
	private int number = 0;
	
	public int getNumber(){
		return number;
	}
	
	public void roll(){
		number=random.nextInt(6)+1;
	}
	
	public boolean isWin(Dice defenser){
		if(number > defenser.getNumber()){
			return true; 
		}else{
			return false; 
		}
	}
}