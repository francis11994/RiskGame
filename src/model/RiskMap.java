package model;

import java.util.ArrayList;
import java.util.List;

public class RiskMap {

	private ArrayList<Country> map;
	private Country ICELAND = new Country("Iceland");
	private Country SCANDINAVIA = new Country("Scandinavia");
	private Country GREATBRITAIN = new Country("Great Britain");
	private Country NORTHERNEUROPE = new Country("Northern Europe");
	private Country WESTERNEUROPE = new Country("Western Europe");
	private Country SOUTHERNEUROPE = new Country("Southern Europe");
	private Country ALASKA = new Country("Alaska");
	private Country NORTHWESTTERRITORY = new Country("Northwest Territory");
	private Country GREENLAND = new Country("Greenland");
	private Country ALBERTA = new Country("Alberta");
	private Country ONTARIO = new Country("Ontario");
	private Country EASTERNCANADA = new Country("Eastern Canada");
	private Country WESTERNUNITEDSTATES = new Country("Western United States");
	private Country EASTERNUNITEDSTATES = new Country("Eastern United States");
	private Country CENTRALAMERICA = new Country("Central America");
	private Country VENEZUELA = new Country("Venezuela");
	private Country BRAZIL = new Country("Brazil");
	private Country PERU = new Country("Peru");
	private Country ARGENTINA = new Country("Argentina");
	private Country EGYPT = new Country("Egypt");
	private Country NORTHAFRICA = new Country("North Africa");
	private Country EASTAFRICA = new Country("East Africa");
	private Country CENTRALAFRICA = new Country("Central Africa");
	private Country SOUTHAFRICA = new Country("South Africa");
	private Country MADAGASCAR = new Country("Madagascar");
	private Country RUSSIA = new Country("Russia");
	private Country URAL = new Country("Ural");
	private Country SIBERIA = new Country("Siberia");
	private Country YAKUTSK = new Country("Yakutsk");
	private Country KAMCHATKA = new Country("Kamchatka");
	private Country AFGHANISTAN = new Country("Afghanistan");
	private Country MIDDLEEAST = new Country("Middle East");
	private Country INDIA = new Country("India");
	private Country CHINA = new Country("China");
	private Country SIAM = new Country("Siam");
	private Country IRKUTSK = new Country("Irkutsk");
	private Country MONGOLIA = new Country("Mongolia");
	private Country JAPAN = new Country("Japan");
	private Country INDONESIA = new Country("Indonesia");
	private Country EASTERNAUSTRALIA = new Country("Eastern Australia");
	private Country WESTERNAUSTRALIA = new Country("Western Australia");
	private Country NEWGUINEA = new Country("New Guinea");
	
	private ArrayList asia;
	private ArrayList africa;
	private ArrayList australia;
	private ArrayList sorthAmerica;
	private ArrayList northAmerica;
	private ArrayList europe;

	
	public RiskMap(){

	}
	
	public void addCountry(Country c){
		map.add(c);
	}
	
	public boolean removeCountry(Country c){
		return map.remove(c);
	}
	
	public ArrayList<Country> getAllCountry() {
		// TODO Auto-generated method stub
		return map;
	}

}
