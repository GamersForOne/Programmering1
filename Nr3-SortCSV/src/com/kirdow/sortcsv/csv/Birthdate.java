package com.kirdow.sortcsv.csv;

public class Birthdate {
	
	//I could use enum for this but in this case I feel this is easier
	public static final int LESS = 0x01;
	public static final int EQUALS = 0x02;
	public static final int GREATER = 0x03;
	
	public static final int ORDER_YEAR = 0x04;
	public static final int ORDER_MONTH = 0x05;
	public static final int ORDER_DAY = 0x06;
	
	public final String date;
	private final int year;
	private final int month;
	private final int day;
	
	public Birthdate(final Birthdate date) {
		StringBuilder sb = new StringBuilder();
		final String old = date.date;
		for (int i = 0; i < old.length(); i++)
			sb.append(old.charAt(i));
		this.date = sb.toString();
		this.year = date.year;
		this.month = date.month;
		this.day = date.day;
	}
	
	private static final int[] upperMonths = new int[]{1, 3, 5, 7, 8, 10, 12};
	
	private static final boolean isUppermonth(int month) {
		for (int i = 0; i < upperMonths.length; i++)
			if (upperMonths[i] == month) return true;
		return false;
	}
	
	public Birthdate(int year, int month, int day) throws InvalidDateException {
		if (year < 0)
			throw new InvalidDateException("year", year);
		if (month < 1 || month > 12)
			throw new InvalidDateException("month", month);
		if (day < 1)
			throw new InvalidDateException("day", day);
		if (month == 2) {
			if (day > 29)
				throw new InvalidDateException("day", day);
			if (day > 28 && year % 4 == 0)
				throw new InvalidDateException("day", day);
		} else if (isUppermonth(month)) {
			if (day > 31)
				throw new InvalidDateException("day", day);
		} else if (day > 30) {
			throw new InvalidDateException("day", day);
		}
		this.date = (new StringBuilder()).append(year).append("-").append(month < 10 ? "0" : month).append(month < 10 ? month : "").append("-").append(day < 10 ? "0" : day).append(day < 10 ? day : "").toString();
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	public static Birthdate getFromString(String date) throws InvalidDateStringException, InvalidDateException {
		String[] parts = date.split("-");
		if (parts.length != 3) throw new InvalidDateStringException(date);
		int y = 0;
		int m = 0;
		int d = 0;
		try {
			y = Integer.parseInt(parts[0]);
			m = Integer.parseInt(parts[1]);
			d = Integer.parseInt(parts[2]);
		} catch (NumberFormatException e) {
			throw new InvalidDateStringException(date);
		}
		return new Birthdate(y, m, d);
		
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof String) {
			String o = (String)other;
			if (o.equals(this.date))
				return true;
		} else if (other instanceof Birthdate) {
			Birthdate o = (Birthdate)other;
			return sort(o) == EQUALS;
		}
		return false;
	}
	
	@Override
	public Birthdate clone() {
		return new Birthdate(this);
	}
	
	public int sort(final Birthdate other, int...order) {
		for (int i = 0; i < (order.length > 3 ? 3 : order.length); i++) {
			switch (order[i]) {
			case ORDER_YEAR:
				if (year < other.year)
					return LESS;
				if (year > other.year)
					return GREATER;
				break;
			case ORDER_MONTH:
				if (month < other.month)
					return LESS;
				if (month > other.month)
					return GREATER;
				break;
			case ORDER_DAY:
				if (day < other.day)
					return LESS;
				if (day > other.day)
					return GREATER;
				break;
			}
		}
		return EQUALS;
	}
	
	public boolean less(final Birthdate other) {
		return sort(other) == LESS;
	}
	
	public boolean greater(final Birthdate other) {
		return sort(other) == GREATER;
	}
	
	@Override
	public String toString() {
		return this.date;
	}
	
}
