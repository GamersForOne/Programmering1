package com.kirdow.sortcsv.csv;

public class Name {
	
	public static final int BEFORE = 0x01;
	public static final int EQUALS = 0x02;
	public static final int AFTER = 0x03;
	
	public final String name;
	
	public Name(final Name name) {
		StringBuilder sb = new StringBuilder();
		final String old = name.name;
		for (int i = 0; i < old.length(); i++) {
			sb.append(old);
		}
		this.name = sb.toString();
	}
	
	public Name(final String name) {
		this.name = name;
	}
	
	public int sort(final Name other) {
		final String s0 = this.name;
		final String s1 = other.name;
		int len = s0.length();
		int l = 0;
		if (s0.length() < s1.length())
			l = -1;
		else if (s0.length() > s1.length()) {
			l = 1;
			len = s1.length();
		}
		for (int i = 0; i < len; i++) {
			char c0 = s0.charAt(i);
			char c1 = s1.charAt(i);
			if (c0 == c1) continue;
			if (c0 < c1) return BEFORE;
			if (c1 < c0) return AFTER;
		}
		if (l < 0) return BEFORE;
		if (l > 0) return AFTER;
		else return EQUALS;
	}
	
	@Override
	public Name clone() {
		return new Name(this);
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof String) {
			String o = (String) other;
			Name tmpName = new Name(o);
			return sort(tmpName) == EQUALS;
		} else if (other instanceof Name) {
			Name o = (Name) other;
			return sort(o) == EQUALS;
		}
		return false;
	}
	
	public boolean before(final Name other) {
		return sort(other) == BEFORE;
	}
	
	public boolean after(final Name other) {
		return sort(other) == AFTER;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
}
