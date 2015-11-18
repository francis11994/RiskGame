package model;

import java.util.ArrayList;
//update
public class RiskMap {

	private static ArrayList<Country> map;
	
	private Country ICELAND = new Country("Iceland",492,140);
	private Country SCANDINAVIA = new Country("Scandinavia",580,117);
	private Country GREATBRITAIN = new Country("Great Britain",500,216);
	private Country NORTHERNEUROPE = new Country("Northern Europe",557,217);
	private Country WESTERNEUROPE = new Country("Western Europe",500,283);
	private Country SOUTHERNEUROPE = new Country("Southern Europe",580,277);
	private Country ALASKA = new Country("Alaska",70,120);
	private Country NORTHWESTTERRITORY = new Country("Northwest Territory",190,117);
	private Country GREENLAND = new Country("Greenland",465,63);
	private Country ALBERTA = new Country("Alberta",190,170);
	private Country ONTARIO = new Country("Ontario",260,179);
	private Country QUEBE = new Country("Quebe",359,181);
	private Country WESTERNUNITEDSTATES = new Country("Western United States",184,244);
	private Country EASTERNUNITEDSTATES = new Country("Eastern United States",282,262);
	private Country CENTRALAMERICA = new Country("Central America",196,318);
	private Country VENEZUELA = new Country("Venezuela",250,395);
	private Country BRAZIL = new Country("Brazil",345,462);
	private Country PERU = new Country("Peru",275,495);
	private Country ARGENTINA = new Country("Argentina",280,585);
	private Country EGYPT = new Country("Egypt",605,365);
	private Country NORTHAFRICA = new Country("North Africa",510,400);
	private Country EASTAFRICA = new Country("East Africa",630,440);
	//CONGI
	private Country CONGO = new Country("Congo",585,490);
	private Country SOUTHAFRICA = new Country("South Africa",580,590);
	private Country MADAGASCAR = new Country("Madagascar",680,586);
	//UKRAIN
	private Country UKRAINE = new Country("Ukraine",670,175);
	private Country URAL = new Country("Ural",785,170);
	private Country SIBERIA = new Country("Siberia",864,129);
	private Country YAKUTSK = new Country("Yakutsk",980,115);
	private Country KAMCHATKA = new Country("Kamchatka",1099,124);
	private Country AFGHANISTAN = new Country("Afghanistan",755,260);
	private Country MIDDLEEAST = new Country("Middle East",685,335);
	private Country INDIA = new Country("India",805,360);
	private Country CHINA = new Country("China",890,295);
	private Country SIAM = new Country("Siam",905,384);
	private Country IRKUTSK = new Country("Irkutsk",950,175);
	private Country MONGOLIA = new Country("Mongolia",978,237);
	private Country JAPAN = new Country("Japan",1040,270);
	private Country INDONESIA = new Country("Indonesia",900,500);
	private Country EASTERNAUSTRALIA = new Country("Eastern Australia",1040,567);
	private Country WESTERNAUSTRALIA = new Country("Western Australia",930,570);
	private Country NEWGUINEA = new Country("New Guinea",1055,490);
	
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
		map.add(CONGO);
		map.add(SOUTHAFRICA);
		map.add(MADAGASCAR);
		map.add(UKRAINE);
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
	
	
	public ArrayList<Country> getAllCountry() {
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
   //     ARGENTINA.addNeighbour(EASTERNAUSTRALIA);
        //Europe
        ICELAND.addNeighbour(GREENLAND);
        ICELAND.addNeighbour(SCANDINAVIA);
        ICELAND.addNeighbour(GREATBRITAIN);        
        SCANDINAVIA.addNeighbour(ICELAND);
        SCANDINAVIA.addNeighbour(GREATBRITAIN);
        SCANDINAVIA.addNeighbour(UKRAINE);
        SCANDINAVIA.addNeighbour(NORTHERNEUROPE);
        GREATBRITAIN.addNeighbour(NORTHERNEUROPE);
        GREATBRITAIN.addNeighbour(WESTERNEUROPE);
        NORTHERNEUROPE.addNeighbour(SOUTHERNEUROPE);
        NORTHERNEUROPE.addNeighbour(UKRAINE);
        NORTHERNEUROPE.addNeighbour(WESTERNEUROPE);
        WESTERNEUROPE.addNeighbour(SOUTHERNEUROPE);
        WESTERNEUROPE.addNeighbour(NORTHAFRICA);
        WESTERNEUROPE.addNeighbour(GREATBRITAIN);
        WESTERNEUROPE.addNeighbour(NORTHERNEUROPE);
        SOUTHERNEUROPE.addNeighbour(MIDDLEEAST);
        SOUTHERNEUROPE.addNeighbour(UKRAINE);
        SOUTHERNEUROPE.addNeighbour(NORTHERNEUROPE);
        SOUTHERNEUROPE.addNeighbour(WESTERNEUROPE);
        SOUTHERNEUROPE.addNeighbour(NORTHAFRICA);
        SOUTHERNEUROPE.addNeighbour(EGYPT);
        UKRAINE.addNeighbour(URAL);
        UKRAINE.addNeighbour(AFGHANISTAN);
        UKRAINE.addNeighbour(MIDDLEEAST);
        UKRAINE.addNeighbour(SOUTHERNEUROPE);
        UKRAINE.addNeighbour(NORTHERNEUROPE);
        UKRAINE.addNeighbour(SCANDINAVIA);
        
        //Africa
        NORTHAFRICA.addNeighbour(WESTERNEUROPE);
        NORTHAFRICA.addNeighbour(SOUTHERNEUROPE);
        NORTHAFRICA.addNeighbour(EGYPT);
        NORTHAFRICA.addNeighbour(EASTAFRICA);
        NORTHAFRICA.addNeighbour(CONGO);
        EGYPT.addNeighbour(MIDDLEEAST);
        EGYPT.addNeighbour(EASTAFRICA);
        EGYPT.addNeighbour(NORTHAFRICA);
        EGYPT.addNeighbour(SCANDINAVIA);
        EASTAFRICA.addNeighbour(EGYPT);
        EASTAFRICA.addNeighbour(MIDDLEEAST);
        EASTAFRICA.addNeighbour(CONGO);
        EASTAFRICA.addNeighbour(SOUTHAFRICA);
        EASTAFRICA.addNeighbour(MADAGASCAR);
        EASTAFRICA.addNeighbour(NORTHAFRICA);
        CONGO.addNeighbour(SOUTHAFRICA);
        CONGO.addNeighbour(NORTHAFRICA);
        CONGO.addNeighbour(EASTAFRICA);
        SOUTHAFRICA.addNeighbour(MADAGASCAR);
        SOUTHAFRICA.addNeighbour(EASTAFRICA);
        SOUTHAFRICA.addNeighbour(CONGO);
        MADAGASCAR.addNeighbour(EASTAFRICA);
        MADAGASCAR.addNeighbour(SOUTHAFRICA);
        
        //Asia
        URAL.addNeighbour(UKRAINE);
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
        AFGHANISTAN.addNeighbour(UKRAINE);
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
        MIDDLEEAST.addNeighbour(UKRAINE);
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