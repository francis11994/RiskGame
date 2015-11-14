package model;

import java.util.ArrayList;
//update
public class RiskMap {

	private static ArrayList<Country> map;
	
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
	private Country QUEBE = new Country("Quebe");
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
	//CONGI
	private Country CENTRALAFRICA = new Country("Central Africa");
	private Country SOUTHAFRICA = new Country("South Africa");
	private Country MADAGASCAR = new Country("Madagascar");
	//UKRAIN
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
	
	private ArrayList<Country> asia;
	private ArrayList<Country> africa;
	private ArrayList<Country> australia;
	private ArrayList<Country> sorthAmerica;
	private ArrayList<Country> northAmerica;
	private ArrayList<Country> europe;

	
	public RiskMap(){
		map=new ArrayList<Country>();
		map.add(ICELAND);
		map.add(SCANDINAVIA);
		map.add(GREATBRITAIN);
		map.add(NORTHERNEUROPE);
		map.add(WESTERNEUROPE);
		map.add(SOUTHERNEUROPE);
		map.add(ALASKA);
		map.add(NORTHWESTTERRITORY);
		map.add(GREENLAND);
		map.add(ALBERTA);
		map.add(ONTARIO);
		map.add(EASTERNUNITEDSTATES);
		map.add(WESTERNUNITEDSTATES);
		map.add(QUEBE);
		map.add(CENTRALAMERICA);
		map.add(VENEZUELA);
		map.add(BRAZIL);
		map.add(PERU);
		map.add(ARGENTINA);
		map.add(EGYPT);
		map.add(NORTHAFRICA);
		map.add(EASTAFRICA);
		map.add(CENTRALAFRICA);
		map.add(SOUTHAFRICA);
		map.add(MADAGASCAR);
		map.add(RUSSIA);
		map.add(URAL);
		map.add(SIBERIA);
		map.add(YAKUTSK);
		map.add(KAMCHATKA);
		map.add(AFGHANISTAN);
		map.add(MIDDLEEAST);
		map.add(INDIA);
		map.add(CHINA);
		map.add(SIAM);
		map.add(IRKUTSK);
		map.add(MONGOLIA);
		map.add(JAPAN);
		map.add(INDONESIA);
		map.add(EASTERNAUSTRALIA);
		map.add(WESTERNAUSTRALIA);
		map.add(NEWGUINEA);
		
		uniteTheHood();
	}
	
	public void addCountry(Country c){
		map.add(c);
	}
	
	public void removeCountry(Country c){
		 map.remove(c);
	}
	
	public ArrayList<Country> getAllCountry() {
		// TODO Auto-generated method stub
		return map;
	}
	
    private void uniteTheHood() {
    	//North America
        ALASKA.addNeighbour(NORTHWESTTERRITORY);
        ALASKA.addNeighbour(KAMCHATKA);
        ALASKA.addNeighbour(ALBERTA);
        NORTHWESTTERRITORY.addNeighbour(ALASKA);
        NORTHWESTTERRITORY.addNeighbour(GREENLAND);
        NORTHWESTTERRITORY.addNeighbour(ALBERTA);
        NORTHWESTTERRITORY.addNeighbour(ONTARIO);
        GREENLAND.addNeighbour(NORTHWESTTERRITORY);
        GREENLAND.addNeighbour(ONTARIO);
        GREENLAND.addNeighbour(QUEBE);
        GREENLAND.addNeighbour(ICELAND);
        ALBERTA.addNeighbour(ALASKA);
        ALBERTA.addNeighbour(NORTHWESTTERRITORY);
        ALBERTA.addNeighbour(ONTARIO);
        ALBERTA.addNeighbour(WESTERNUNITEDSTATES);
        ONTARIO.addNeighbour(NORTHWESTTERRITORY);
        ONTARIO.addNeighbour(GREENLAND);
        ONTARIO.addNeighbour(QUEBE);
        ONTARIO.addNeighbour(EASTERNUNITEDSTATES);
        ONTARIO.addNeighbour(WESTERNUNITEDSTATES);
        ONTARIO.addNeighbour(ALBERTA);
        QUEBE.addNeighbour(GREENLAND);
        QUEBE.addNeighbour(EASTERNUNITEDSTATES);
        QUEBE.addNeighbour(ONTARIO);
        WESTERNUNITEDSTATES.addNeighbour(ALBERTA);
        WESTERNUNITEDSTATES.addNeighbour(ONTARIO);
        WESTERNUNITEDSTATES.addNeighbour(EASTERNUNITEDSTATES);
        WESTERNUNITEDSTATES.addNeighbour(CENTRALAMERICA);
        EASTERNUNITEDSTATES.addNeighbour(CENTRALAMERICA);
        EASTERNUNITEDSTATES.addNeighbour(WESTERNUNITEDSTATES);
        EASTERNUNITEDSTATES.addNeighbour(ONTARIO);
        EASTERNUNITEDSTATES.addNeighbour(QUEBE);
        CENTRALAMERICA.addNeighbour(VENEZUELA);
        CENTRALAMERICA.addNeighbour(WESTERNUNITEDSTATES);
        
        //South America
        VENEZUELA.addNeighbour(CENTRALAMERICA);
        VENEZUELA.addNeighbour(PERU);
        VENEZUELA.addNeighbour(BRAZIL);
        PERU.addNeighbour(BRAZIL);
        PERU.addNeighbour(ARGENTINA);
        PERU.addNeighbour(VENEZUELA);
        BRAZIL.addNeighbour(VENEZUELA);
        BRAZIL.addNeighbour(PERU);
        BRAZIL.addNeighbour(ARGENTINA);
        BRAZIL.addNeighbour(NORTHAFRICA);
        ARGENTINA.addNeighbour(BRAZIL);
        ARGENTINA.addNeighbour(PERU);
        //Europe
        ICELAND.addNeighbour(GREENLAND);
        ICELAND.addNeighbour(SCANDINAVIA);
        ICELAND.addNeighbour(GREATBRITAIN);        
        SCANDINAVIA.addNeighbour(ICELAND);
        SCANDINAVIA.addNeighbour(GREATBRITAIN);
        SCANDINAVIA.addNeighbour(RUSSIA);
        SCANDINAVIA.addNeighbour(NORTHERNEUROPE);
        GREATBRITAIN.addNeighbour(NORTHERNEUROPE);
        GREATBRITAIN.addNeighbour(WESTERNEUROPE);
        NORTHERNEUROPE.addNeighbour(SOUTHERNEUROPE);
        NORTHERNEUROPE.addNeighbour(RUSSIA);
        NORTHERNEUROPE.addNeighbour(WESTERNEUROPE);
        WESTERNEUROPE.addNeighbour(SOUTHERNEUROPE);
        WESTERNEUROPE.addNeighbour(NORTHAFRICA);
        WESTERNEUROPE.addNeighbour(GREATBRITAIN);
        WESTERNEUROPE.addNeighbour(NORTHERNEUROPE);
        SOUTHERNEUROPE.addNeighbour(MIDDLEEAST);
        SOUTHERNEUROPE.addNeighbour(RUSSIA);
        SOUTHERNEUROPE.addNeighbour(NORTHERNEUROPE);
        SOUTHERNEUROPE.addNeighbour(WESTERNEUROPE);
        SOUTHERNEUROPE.addNeighbour(NORTHAFRICA);
        SOUTHERNEUROPE.addNeighbour(EGYPT);
        RUSSIA.addNeighbour(URAL);
        RUSSIA.addNeighbour(AFGHANISTAN);
        RUSSIA.addNeighbour(MIDDLEEAST);
        RUSSIA.addNeighbour(SOUTHERNEUROPE);
        RUSSIA.addNeighbour(NORTHERNEUROPE);
        RUSSIA.addNeighbour(SCANDINAVIA);
        
        //Africa
        NORTHAFRICA.addNeighbour(WESTERNEUROPE);
        NORTHAFRICA.addNeighbour(SOUTHERNEUROPE);
        NORTHAFRICA.addNeighbour(EGYPT);
        NORTHAFRICA.addNeighbour(EASTAFRICA);
        NORTHAFRICA.addNeighbour(CENTRALAFRICA);
        EGYPT.addNeighbour(MIDDLEEAST);
        EGYPT.addNeighbour(EASTAFRICA);
        EGYPT.addNeighbour(NORTHAFRICA);
        EGYPT.addNeighbour(SCANDINAVIA);
        EASTAFRICA.addNeighbour(EGYPT);
        EASTAFRICA.addNeighbour(MIDDLEEAST);
        EASTAFRICA.addNeighbour(CENTRALAFRICA);
        EASTAFRICA.addNeighbour(SOUTHAFRICA);
        EASTAFRICA.addNeighbour(MADAGASCAR);
        EASTAFRICA.addNeighbour(NORTHAFRICA);
        CENTRALAFRICA.addNeighbour(SOUTHAFRICA);
        CENTRALAFRICA.addNeighbour(NORTHAFRICA);
        CENTRALAFRICA.addNeighbour(EASTAFRICA);
        SOUTHAFRICA.addNeighbour(MADAGASCAR);
        SOUTHAFRICA.addNeighbour(EASTAFRICA);
        SOUTHAFRICA.addNeighbour(CENTRALAFRICA);
        MADAGASCAR.addNeighbour(EASTAFRICA);
        MADAGASCAR.addNeighbour(SOUTHAFRICA);
        
        //Asia
        URAL.addNeighbour(RUSSIA);
        URAL.addNeighbour(SIBERIA);
        URAL.addNeighbour(AFGHANISTAN);
        URAL.addNeighbour(CHINA);
        SIBERIA.addNeighbour(URAL);        
        SIBERIA.addNeighbour(YAKUTSK);
        SIBERIA.addNeighbour(IRKUTSK);
        SIBERIA.addNeighbour(MONGOLIA);
        SIBERIA.addNeighbour(CHINA);
        YAKUTSK.addNeighbour(KAMCHATKA);
        YAKUTSK.addNeighbour(IRKUTSK);
        YAKUTSK.addNeighbour(SIBERIA);
        KAMCHATKA.addNeighbour(YAKUTSK);
        KAMCHATKA.addNeighbour(JAPAN);
        KAMCHATKA.addNeighbour(IRKUTSK);
        KAMCHATKA.addNeighbour(MONGOLIA);
        IRKUTSK.addNeighbour(KAMCHATKA);
        IRKUTSK.addNeighbour(MONGOLIA);
        IRKUTSK.addNeighbour(SIBERIA);
        IRKUTSK.addNeighbour(YAKUTSK); 
        MONGOLIA.addNeighbour(KAMCHATKA);
        MONGOLIA.addNeighbour(JAPAN);
        MONGOLIA.addNeighbour(CHINA);
        MONGOLIA.addNeighbour(SIBERIA);
        MONGOLIA.addNeighbour(IRKUTSK);
        JAPAN.addNeighbour(KAMCHATKA);
        JAPAN.addNeighbour(MONGOLIA);
        AFGHANISTAN.addNeighbour(RUSSIA);
        AFGHANISTAN.addNeighbour(URAL);
        AFGHANISTAN.addNeighbour(CHINA);
        AFGHANISTAN.addNeighbour(INDIA);
        AFGHANISTAN.addNeighbour(MIDDLEEAST);
        CHINA.addNeighbour(MONGOLIA);
        CHINA.addNeighbour(SIAM);
        CHINA.addNeighbour(INDIA);
        CHINA.addNeighbour(AFGHANISTAN);
        CHINA.addNeighbour(URAL);
        CHINA.addNeighbour(SIBERIA);
        MIDDLEEAST.addNeighbour(EASTAFRICA);
        MIDDLEEAST.addNeighbour(EGYPT);
        MIDDLEEAST.addNeighbour(SOUTHERNEUROPE);
        MIDDLEEAST.addNeighbour(RUSSIA);
        MIDDLEEAST.addNeighbour(AFGHANISTAN);
        MIDDLEEAST.addNeighbour(INDIA);
        INDIA.addNeighbour(MIDDLEEAST);
        INDIA.addNeighbour(AFGHANISTAN);
        INDIA.addNeighbour(CHINA);
        INDIA.addNeighbour(SIAM);
        SIAM.addNeighbour(INDONESIA);
        SIAM.addNeighbour(CHINA);
        SIAM.addNeighbour(INDIA);
        //Australia
        INDONESIA.addNeighbour(SIAM);
        INDONESIA.addNeighbour(WESTERNAUSTRALIA);
        INDONESIA.addNeighbour(NEWGUINEA);
        NEWGUINEA.addNeighbour(INDONESIA);
        NEWGUINEA.addNeighbour(WESTERNAUSTRALIA);
        NEWGUINEA.addNeighbour(EASTERNAUSTRALIA);
        WESTERNAUSTRALIA.addNeighbour(EASTERNAUSTRALIA);
        WESTERNAUSTRALIA.addNeighbour(INDONESIA);
        WESTERNAUSTRALIA.addNeighbour(NEWGUINEA);
        EASTERNAUSTRALIA.addNeighbour(WESTERNAUSTRALIA);
        EASTERNAUSTRALIA.addNeighbour(NEWGUINEA);
    }

}