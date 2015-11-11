package model;

<<<<<<< HEAD
import java.util.Random;

public class Dice {
	
	private Random random;
	private int number;
	public Dice(){
		random = new Random();
	}
	
	public int getRandomNumber(){
		number = random.nextInt(6);
		return number+1;
	}
	
	public boolean compareDiceWith(Dice defenser){
		if(this.getRandomNumber() > defenser.getRandomNumber()){
			return true; //win offenser
		}else{
			return false; //win defenser
		}
	}
	
=======
public class Dice {
	public Dice(){
		
	}
>>>>>>> 6d764016166c14bc898015e5ff0fde548e2d0361
}
