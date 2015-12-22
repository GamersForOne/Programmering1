package com.kirdow.sortcsv.csv;

public class City {
	
	public final Name name;
	
	public City(final Name name) {
		this.name = name;
	}
	
	public City(final City city) {
		this.name = city.name.clone();
	}
	
	public City(final String name) {
		this.name = new Name(name);
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof City) {
			City o = (City) other;
			return name.equals(o.name);
		}
		return name.equals(other);
	}
	
	@Override
	public City clone() {
		return new City(this);
	}
	
	public int sort(final City other) {
		return name.sort(other.name);
	}
	
	public boolean before(final City other) {
		return name.before(other.name);
	}
	
	public boolean after(final City other) {
		return name.after(other.name);
	}
	
	@Override
	public String toString() {
		return name.toString();
	}
	
}
