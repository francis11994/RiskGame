package model;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Country implements Serializable{
	

	int armyCount;
	private List<Country> neighbours;
	String name;
	int X,Y;
	public Country(String c, int pixelX, int pixelY) {
		X=pixelX;
		Y=pixelY;
		this.name = c;
		neighbours = new ArrayList<Country>();
		armyCount = 1;
	}

	public List<Country> getNegibors() {
		return neighbours;
	}

	public void addNeighbour(Country c) {
		this.neighbours.add(c);
	}

	public void addArmys(int n) {
		armyCount += n;
	}

	public void removeArmys(int n){
		armyCount -= n;
	}
	public int getArmyCount() {
		return armyCount;
	}

	public String getname(){
		return name;
	}
	
	public boolean isThreaten(List<Country> ownCountry){
		int enemy=0;
		for(Country neighbor:neighbours){
			for(Country NextNeighbor:neighbor.getNegibors())
			if(!ownCountry.contains(NextNeighbor))
			enemy+=NextNeighbor.getArmyCount();
		}
		return enemy>=armyCount;
	}
	public int getX(){
		return X;
	}
	
	public int getY(){
		return Y;
	}
	public boolean isLocateAt(Point point){
		if(Math.abs(point.getX()-X)<40&&Math.abs(point.getY()-Y)<40)
			return true;
		return false;
	}
}