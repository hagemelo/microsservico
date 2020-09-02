package br.com.jhage.core.constante;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Alexsander Melo
 * @since 01/12/2018
 *
 */

public enum Hora {

	H0700("07:00", "07H"), H0715("07:15", "07H"), H0730("07:30", "07H"), H0745("07:45", "07H"),

	H0800("08:00", "08H"), H0815("08:15", "08H"), H0830("08:30", "08H"), H0845("08:45", "08H"),

	H0900("09:00", "09H"), H0915("09:15", "09H"), H0930("09:30", "09H"), H0945("09:45", "09H"),

	H1000("10:00", "10H"), H1015("10:15", "10H"), H1030("10:30", "10H"), H1045("10:45", "10H"),

	H1100("11:00", "11H"), H1115("11:15", "11H"), H1130("11:30", "11H"), H1145("11:45", "11H"),

	H1200("12:00", "12H"), H1215("12:15", "12H"), H1230("12:30", "12H"), H1245("12:45", "12H"),

	H1300("13:00", "13H"), H1315("13:15", "13H"), H1330("13:30", "13H"), H1345("13:45", "13H"),

	H1400("14:00", "14H"), H1415("14:15", "14H"), H1430("14:30", "14H"), H1445("14:45", "14H"),

	H1500("15:00", "15H"), H1515("15:15", "15H"), H1530("15:30", "15H"), H1545("15:45", "15H"),

	H1600("16:00", "16H"), H1615("16:15", "16H"), H1630("16:30", "16H"), H1645("16:45", "16H"),

	H1700("17:00", "17H"), H1715("17:15", "17H"), H1730("17:30", "17H"), H1745("17:45", "17H"),

	H1800("18:00", "18H"), H1815("18:15", "18H"), H1830("18:30", "18H"), H1845("18:45", "18H"),

	H1900("19:00", "19H"), H1915("19:15", "19H"), H1930("19:30", "19H"), H1945("19:45", "19H"),

	H2000("20:00", "20H"), H2015("20:15", "20H"), H2030("20:30", "20H"), H2045("20:45", "20H"),

	TODOS("TODOS", "");

	private final String value;

	private final String desc;

	Hora(String value, String desc) {

		this.value = value;
		this.desc = desc;
	}

	@Override
	public String toString() {

		return this.value;
	}

	public String desc() {

		return this.desc;
	}

	private static final Map<String, Hora> valuetMap = new HashMap<String, Hora>();

	static {
		for (final Hora e : Hora.values()) {
			valuetMap.put(e.toString(), e);
		}
	}

	public static Hora fromValue(final String v) {

		return valuetMap.get(v);
	}

}
