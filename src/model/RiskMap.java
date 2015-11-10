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
		uniteTheHood();
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
	
    private void uniteTheHood() {
        ALASKA.addNeighbour(NORTHWESTTERRITORY);
        ALASKA.addNeighbour(KAMCHATKA);
        ALASKA.addNeighbour(ALBERTA);
        NORTHWESTTERRITORY.addNeighbour(GREENLAND);
        NORTHWESTTERRITORY.addNeighbour(ALBERTA);
        NORTHWESTTERRITORY.addNeighbour(ONTARIO);
        GREENLAND.addNeighbour(ONTARIO);
        GREENLAND.addNeighbour(EASTERNCANADA);
        GREENLAND.addNeighbour(ICELAND);
        ALBERTA.addNeighbour(ONTARIO);
        ALBERTA.addNeighbour(WESTERNUNITEDSTATES);
        ONTARIO.addNeighbour(EASTERNCANADA);
        ONTARIO.addNeighbour(WESTERNUNITEDSTATES);
        ONTARIO.addNeighbour(EASTERNUNITEDSTATES);
        EASTERNCANADA.addNeighbour(EASTERNUNITEDSTATES);
        WESTERNUNITEDSTATES.addNeighbour(EASTERNUNITEDSTATES);
        WESTERNUNITEDSTATES.addNeighbour(CENTRALAMERICA);
        EASTERNUNITEDSTATES.addNeighbour(CENTRALAMERICA);
        CENTRALAMERICA.addNeighbour(VENEZUELA);
        VENEZUELA.addNeighbour(PERU);
        VENEZUELA.addNeighbour(BRAZIL);
        PERU.addNeighbour(BRAZIL);
        PERU.addNeighbour(ARGENTINA);
        BRAZIL.addNeighbour(ARGENTINA);
        BRAZIL.addNeighbour(NORTHAFRICA);
        ICELAND.addNeighbour(SCANDINAVIA);
        ICELAND.addNeighbour(GREATBRITAIN);
        SCANDINAVIA.addNeighbour(RUSSIA);
        SCANDINAVIA.addNeighbour(GREATBRITAIN);
        SCANDINAVIA.addNeighbour(NORTHERNEUROPE);
        GREATBRITAIN.addNeighbour(NORTHERNEUROPE);
        GREATBRITAIN.addNeighbour(WESTERNEUROPE);
        NORTHERNEUROPE.addNeighbour(SOUTHERNEUROPE);
        NORTHERNEUROPE.addNeighbour(RUSSIA);
        NORTHERNEUROPE.addNeighbour(WESTERNEUROPE);
        WESTERNEUROPE.addNeighbour(SOUTHERNEUROPE);
        WESTERNEUROPE.addNeighbour(NORTHAFRICA);
        SOUTHERNEUROPE.addNeighbour(MIDDLEEAST);
        SOUTHERNEUROPE.addNeighbour(RUSSIA);
        SOUTHERNEUROPE.addNeighbour(NORTHAFRICA);
        SOUTHERNEUROPE.addNeighbour(EGYPT);
        NORTHAFRICA.addNeighbour(EGYPT);
        NORTHAFRICA.addNeighbour(EASTAFRICA);
        NORTHAFRICA.addNeighbour(CENTRALAFRICA);
        EGYPT.addNeighbour(MIDDLEEAST);
        EGYPT.addNeighbour(EASTAFRICA);
        EASTAFRICA.addNeighbour(CENTRALAFRICA);
        EASTAFRICA.addNeighbour(SOUTHAFRICA);
        EASTAFRICA.addNeighbour(MADAGASCAR);
        CENTRALAFRICA.addNeighbour(SOUTHAFRICA);
        SOUTHAFRICA.addNeighbour(MADAGASCAR);
        RUSSIA.addNeighbour(URAL);
        RUSSIA.addNeighbour(AFGHANISTAN);
        RUSSIA.addNeighbour(MIDDLEEAST);
        URAL.addNeighbour(SIBERIA);
        URAL.addNeighbour(AFGHANISTAN);
        URAL.addNeighbour(CHINA);
        SIBERIA.addNeighbour(YAKUTSK);
        SIBERIA.addNeighbour(IRKUTSK);
        SIBERIA.addNeighbour(MONGOLIA);
        SIBERIA.addNeighbour(CHINA);
        YAKUTSK.addNeighbour(KAMCHATKA);
        YAKUTSK.addNeighbour(IRKUTSK);
        KAMCHATKA.addNeighbour(JAPAN);
        KAMCHATKA.addNeighbour(MONGOLIA);
        IRKUTSK.addNeighbour(KAMCHATKA);
        IRKUTSK.addNeighbour(MONGOLIA);
        MONGOLIA.addNeighbour(JAPAN);
        MONGOLIA.addNeighbour(CHINA);
        AFGHANISTAN.addNeighbour(CHINA);
        AFGHANISTAN.addNeighbour(INDIA);
        AFGHANISTAN.addNeighbour(MIDDLEEAST);
        CHINA.addNeighbour(INDIA);
        CHINA.addNeighbour(SIAM);
        MIDDLEEAST.addNeighbour(INDIA);
        MIDDLEEAST.addNeighbour(EASTAFRICA);
        INDIA.addNeighbour(SIAM);
        SIAM.addNeighbour(INDONESIA);
        INDONESIA.addNeighbour(NEWGUINEA);
        INDONESIA.addNeighbour(WESTERNAUSTRALIA);
        NEWGUINEA.addNeighbour(EASTERNAUSTRALIA);
        WESTERNAUSTRALIA.addNeighbour(EASTERNAUSTRALIA);
        WESTERNAUSTRALIA.addNeighbour(NEWGUINEA);
    }

}
