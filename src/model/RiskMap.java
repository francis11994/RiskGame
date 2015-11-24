package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//update
public class RiskMap implements Serializable {
	public enum CountryType {
		ASIA, AFRICA, AUSTRALIA, SOUTHAMERICA, NORTHAMERICA, EUROPE
	}

	private static ArrayList<Country> map;
	private Country ICELAND = new Country("Iceland", CountryType.EUROPE, 492 * 2 / 3, 140 * 2 / 3);
	private Country SCANDINAVIA = new Country("Scandinavia", CountryType.EUROPE, 580 * 2 / 3, 117 * 2 / 3);
	private Country GREATBRITAIN = new Country("Great Britain", CountryType.EUROPE, 500 * 2 / 3, 216 * 2 / 3);
	private Country NORTHERNEUROPE = new Country("Northern Europe", CountryType.EUROPE, 557 * 2 / 3, 217 * 2 / 3);
	private Country WESTERNEUROPE = new Country("Western Europe", CountryType.EUROPE, 500 * 2 / 3, 283 * 2 / 3);
	private Country SOUTHERNEUROPE = new Country("Southern Europe", CountryType.EUROPE, 580 * 2 / 3, 277 * 2 / 3);
	private Country ALASKA = new Country("Alaska", CountryType.NORTHAMERICA, 70 * 2 / 3, 120 * 2 / 3);
	private Country NORTHWESTTERRITORY = new Country("Northwest Territory", CountryType.NORTHAMERICA, 190 * 2 / 3,
			117 * 2 / 3);
	private Country GREENLAND = new Country("Greenland", CountryType.NORTHAMERICA, 465 * 2 / 3, 63 * 2 / 3);
	private Country ALBERTA = new Country("Alberta", CountryType.NORTHAMERICA, 190 * 2 / 3, 170 * 2 / 3);
	private Country ONTARIO = new Country("Ontario", CountryType.NORTHAMERICA, 260 * 2 / 3, 179 * 2 / 3);
	private Country QUEBE = new Country("Quebe", CountryType.NORTHAMERICA, 359 * 2 / 3, 181 * 2 / 3);
	private Country WESTERNUNITEDSTATES = new Country("Western United States", CountryType.NORTHAMERICA, 184 * 2 / 3,
			244 * 2 / 3);
	private Country EASTERNUNITEDSTATES = new Country("Eastern United States", CountryType.NORTHAMERICA, 282 * 2 / 3,
			262 * 2 / 3);
	private Country CENTRALAMERICA = new Country("Central America", CountryType.NORTHAMERICA, 196 * 2 / 3, 318 * 2 / 3);
	private Country VENEZUELA = new Country("Venezuela", CountryType.SOUTHAMERICA, 250 * 2 / 3, 395 * 2 / 3);
	private Country BRAZIL = new Country("Brazil", CountryType.SOUTHAMERICA, 345 * 2 / 3, 462 * 2 / 3);
	private Country PERU = new Country("Peru", CountryType.SOUTHAMERICA, 275 * 2 / 3, 495 * 2 / 3);
	private Country ARGENTINA = new Country("Argentina", CountryType.SOUTHAMERICA, 280 * 2 / 3, 585 * 2 / 3);
	private Country EGYPT = new Country("Egypt", CountryType.AFRICA, 605 * 2 / 3, 365 * 2 / 3);
	private Country NORTHAFRICA = new Country("North Africa", CountryType.AFRICA, 510 * 2 / 3, 400 * 2 / 3);
	private Country EASTAFRICA = new Country("East Africa", CountryType.AFRICA, 630 * 2 / 3, 440 * 2 / 3);
	private Country CONGO = new Country("Congo", CountryType.AFRICA, 585 * 2 / 3, 490 * 2 / 3);
	private Country SOUTHAFRICA = new Country("South Africa", CountryType.AFRICA, 580 * 2 / 3, 590 * 2 / 3);
	private Country MADAGASCAR = new Country("Madagascar", CountryType.AFRICA, 680 * 2 / 3, 586 * 2 / 3);
	private Country UKRAINE = new Country("Ukraine", CountryType.ASIA, 670 * 2 / 3, 175 * 2 / 3);
	private Country URAL = new Country("Ural", CountryType.ASIA, 785 * 2 / 3, 170 * 2 / 3);
	private Country SIBERIA = new Country("Siberia", CountryType.ASIA, 864 * 2 / 3, 129 * 2 / 3);
	private Country YAKUTSK = new Country("Yakutsk", CountryType.ASIA, 980 * 2 / 3, 115 * 2 / 3);
	private Country KAMCHATKA = new Country("Kamchatka", CountryType.ASIA, 1099 * 2 / 3, 124 * 2 / 3);
	private Country AFGHANISTAN = new Country("Afghanistan", CountryType.ASIA, 755 * 2 / 3, 260 * 2 / 3);
	private Country MIDDLEEAST = new Country("Middle East", CountryType.ASIA, 685 * 2 / 3, 335 * 2 / 3);
	private Country INDIA = new Country("India", CountryType.ASIA, 805 * 2 / 3, 360 * 2 / 3);
	private Country CHINA = new Country("China", CountryType.ASIA, 890 * 2 / 3, 295 * 2 / 3);
	private Country SIAM = new Country("Siam", CountryType.ASIA, 905 * 2 / 3, 384 * 2 / 3);
	private Country IRKUTSK = new Country("Irkutsk", CountryType.ASIA, 950 * 2 / 3, 175 * 2 / 3);
	private Country MONGOLIA = new Country("Mongolia", CountryType.ASIA, 978 * 2 / 3, 237 * 2 / 3);
	private Country JAPAN = new Country("Japan", CountryType.ASIA, 1040 * 2 / 3, 270 * 2 / 3);
	private Country INDONESIA = new Country("Indonesia", CountryType.AUSTRALIA, 900 * 2 / 3, 500 * 2 / 3);
	private Country EASTERNAUSTRALIA = new Country("Eastern Australia", CountryType.AUSTRALIA, 1040 * 2 / 3,
			567 * 2 / 3);
	private Country WESTERNAUSTRALIA = new Country("Western Australia", CountryType.AUSTRALIA, 930 * 2 / 3,
			570 * 2 / 3);
	private Country NEWGUINEA = new Country("New Guinea", CountryType.AUSTRALIA, 1055 * 2 / 3, 490 * 2 / 3);

	public RiskMap() {
		map = new ArrayList<Country>();
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

	public int getExtraUnit(List<Country> countries) {
		int unit = 0;
		int ASIA = 0, AFRICA = 0, AUSTRALIA = 0, SOUTHAMERICA = 0, NORTHAMERICA = 0, EUROPE = 0;
		for (Country a : countries) {
			if (a.equals(CountryType.AFRICA))
				AFRICA++;
			if (a.equals(CountryType.ASIA))
				ASIA++;
			if (a.equals(CountryType.AUSTRALIA))
				AUSTRALIA++;
			if (a.equals(CountryType.SOUTHAMERICA))
				SOUTHAMERICA++;
			if (a.equals(CountryType.EUROPE))
				EUROPE++;
		}
		if (AFRICA == 6)
			unit += 3;
		if (ASIA == 11)
			ASIA += 7;
		if (AUSTRALIA == 4)
			AUSTRALIA += 2;
		if (SOUTHAMERICA == 8)
			unit += 5;
		if (EUROPE == 7)
			unit += 5;
		if (NORTHAMERICA == 4)
			unit += 2;
		return unit;
	}

	public List<Country> getAllCountry() {
		return map;
	}

	private void uniteTheHood() {
		// North America
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

		// South America
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
		// ARGENTINA.addNeighbour(EASTERNAUSTRALIA);
		// Europe
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

		// Africa
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

		// Asia
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
		// Australia
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