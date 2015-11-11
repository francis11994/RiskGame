package model;

<<<<<<< HEAD
import java.util.HashMap;
=======
import java.util.ArrayList;
>>>>>>> 6d764016166c14bc898015e5ff0fde548e2d0361
import java.util.List;
//update

public class Country {
	

	int armyCount;
	private Player owner;
	private List<Country> neighbours;
	String name;

	public Country(String c) {
		this.name = c;
<<<<<<< HEAD
	}

	public List<Country> getNegibors() {
		return null;
=======
		//Chen:
		neighbours = new ArrayList<Country>();
		armyCount = 0;
	}

	public List<Country> getNegibors() {
		//Chen: 
		return neighbours;
>>>>>>> 6d764016166c14bc898015e5ff0fde548e2d0361
	}

	public void addNeighbour(Country c) {
		this.neighbours.add(c);
	}

	public void addArmys(int n) {
		armyCount += n;
	}
<<<<<<< HEAD

	public int getArmyCount() {
		return armyCount;
	}

	public Player getOwner() {
		return owner;
	}

	// Chen: add a new method here
=======
	
	public void removeArmys(int n){
		armyCount -= n;
	}

	public int getArmyCount() {
		return armyCount;
	}

>>>>>>> 6d764016166c14bc898015e5ff0fde548e2d0361
	public void setOwner(Player player) {
		owner = player;
	}

<<<<<<< HEAD
	private void removeArmys(int amount) {
		this.armyCount -= amount;

	}

}
=======
	public Player getOwner() {
		return owner;
	}

	
}
>>>>>>> 6d764016166c14bc898015e5ff0fde548e2d0361
