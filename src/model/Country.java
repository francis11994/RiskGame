package model;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.RiskMap.CountryType;

public class Country implements Serializable {

	CountryType type;
	int armyCount;
	private List<Country> neighbours;
	String name;
	int X, Y;

	public Country(String c, CountryType t, int pixelX, int pixelY) {
		type = t;
		X = pixelX;
		Y = pixelY;
		this.name = c;
		neighbours = new ArrayList<Country>();
		armyCount = 1;
	}

	public void moveSolider(Country to, int unit) {
		removeArmys(unit);
		to.addArmys(unit);
	}

	public boolean isThreaten(List<Country> ownCountry) {
		int enemy = 0;
		for (Country a : neighbours)
			if (!ownCountry.contains(a))
				enemy += a.getArmyCount();
		return enemy >= armyCount;
	}

	public boolean isLocateAt(Point point) {
		if (Math.abs(point.getX() - X) < 40 * 2 / 3 && Math.abs(point.getY() - Y) < 40 * 2 / 3)
			return true;
		return false;
	}

	public List<Country> getNegibors(List<Country> ownCountry) {
		List<Country> enemy = new ArrayList<Country>();
		for (Country a : neighbours)
			if (!ownCountry.contains(a))
				enemy.add(a);
		return enemy;
	}

	public void addNeighbour(Country c) {
		this.neighbours.add(c);
	}

	public void addArmys(int n) {
		armyCount += n;
	}

	public void removeArmys(int n) {
		armyCount -= n;
	}

	public int getArmyCount() {
		return armyCount;
	}

	public String getname() {
		return name;
	}

	public int getX() {
		return X;
	}

	public CountryType getType() {
		return type;
	}

	public int getY() {
		return Y;
	}
}