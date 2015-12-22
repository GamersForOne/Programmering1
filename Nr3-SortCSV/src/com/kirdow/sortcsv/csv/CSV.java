package com.kirdow.sortcsv.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class CSV {
	
	private Person[] persons;
	
	public CSV(Person[] persons) {
		this.persons = persons;
	}
	
	public void sort(int...order) {
		int bindex = 0;
		for (int o : order) {
			if (o == Person.ORDER_BIRTHDATE) {
				break;
			}
			bindex++;
		}
		int[] border = new int[7];
		int offset = 0;
		int msize = order.length - 3;
		for (int i = 0; i < msize; i++) {
			if (i == bindex) {
				border[i] = order[i];
				for (int j = 0; j < 3; j++) {
					border[4+j] = order[i+j+1];
				}
				offset += 3;
			} else {
				border[i] = order[i+offset];
			}
		}
		this.persons = Person.order(persons, border[0], border[1], border[2], border[3], border[4], border[5], border[6]);
	}
	
	public String[] getLines() {
		String[] lines = new String[persons.length];
		for (int i = 0; i < lines.length; i++) {
			lines[i] = persons[i].toString();
		}
		return lines;
	}
	
	public void saveTo(File path) throws IOException {
		FileWriter writer = new FileWriter(path);
		
		String layout = "Firstname;Lastname;Birthdate;Hometown";
		writer.write(layout);
		writer.write("\r\n");
		String[] lines = getLines();
		for (int i = 0; i < lines.length; i++) {
			if (i > 0) writer.write("\r\n");
			writer.write(lines[i]);
		}
		writer.flush();
		writer.close();
	}
	
}
