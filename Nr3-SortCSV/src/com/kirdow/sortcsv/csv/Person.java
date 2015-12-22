package com.kirdow.sortcsv.csv;

public class Person {
	
	public static final int ORDER_FIRSTNAME = 0x01;
	public static final int ORDER_LASTNAME = 0x02;
	public static final int ORDER_BIRTHDATE = 0x03;
	public static final int ORDER_HOMETOWN = 0x04;
	
	public final Name firstName;
	public final Name lastName;
	public final Birthdate birthdate;
	public final City hometown;
	
	public Person(Name firstName, Name lastName, Birthdate birthdate, City hometown) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.hometown = hometown;
	}
	
	public boolean bubblesort(final Person other, int[] order, int[] orderbirth) {
		for (int i = 0; i < order.length; i++) {
			int state = 0;
			switch (order[i]) {
			case ORDER_FIRSTNAME:
				state = firstName.sort(other.firstName) - 2;
				break;
			case ORDER_LASTNAME:
				state = lastName.sort(other.lastName) - 2;
				break;
			case ORDER_BIRTHDATE:
				state = birthdate.sort(other.birthdate, orderbirth[0], orderbirth[1], orderbirth[2]) - 2;
				break;
			case ORDER_HOMETOWN:
				state = hometown.sort(other.hometown) - 2;
				break;
			}
			if (state < 0)
				return false;
			if (state > 0)
				return true;
		}
		return false;
	}
	
	public static Person[] order(Person[] persons, int orderFirst, int orderSecond, int orderThird, int orderFouth, int orderBirthFirst, int orderBirthSecond, int orderBirthThird) {
		int[] order = new int[]{orderFirst, orderSecond, orderThird, orderFouth};
		int[] orderb = new int[]{orderBirthFirst, orderBirthSecond, orderBirthThird};
		Person a = null;
		Person b = null;
		Person[] personsNew = new Person[persons.length];
		for (int i = 0; i < persons.length; i++) {
			personsNew[i] = persons[i];
		}
		boolean change;
		do {
			change = false;
			for (int i = 0; i < personsNew.length - 1; i++) {
				a = personsNew[i];
				b = personsNew[i + 1];
				if (a.bubblesort(b, order, orderb)) {
					personsNew[i] = b;
					personsNew[i + 1] = a;
					change = true;
				}
			}
		} while (change);
		return personsNew;
	}
	
	@Override
	public String toString() {
		return (new StringBuilder()).append(this.firstName.toString()).append(";").append(this.lastName.toString()).append(";").append(this.birthdate.toString()).append(";").append(this.hometown.toString()).toString();
	}
	
}
