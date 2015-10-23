package com.kirdow.fibonacci;

import javax.swing.JOptionPane;

public class Main {
	
	//We don't need the constructor, so we leave it empty
	private Main() { }
	
	//This is where we run the program and calls Fibonacci
	private void run() {
		//We add a try-catch in case incase the user enters a non-number value
		try {
			//Then we ask the user to enter a number
			String input = JOptionPane.showInputDialog(null, "Enter number");
			
			//If the input string is null, then the user pressed cancel, so we get out of the run method
			if (input == null)
				return;
			
			//Here we parse the string to an integer
			//If it fails, the try-catch catches the error
			int number = Integer.parseInt(input);
			
			//Now we have have the number, now run it through the fibonacci sequence
			int fibonacci = Fibonacci.get(number);
			//We finally print the number to the user using a stringbuilder
			JOptionPane.showMessageDialog(null, (new StringBuilder()).append("Fibonacci: ").append(fibonacci).toString());
		} catch (NumberFormatException e) {
			//If it fails, prompt the user and try again
			JOptionPane.showMessageDialog(null, "Not a Number");
			run();
		}
	}
	
	//This is the main/start method of the program
	public static void main(String[] args) {
		//We run the program, by creating an object and calling an object
		//We don't need the object in main method, so we don't care about storing it as a variable
		(new Main()).run();
		//We prompt the user that the program has ended and also print it in console
		JOptionPane.showMessageDialog(null, "Program ended");
		System.out.println("Program ended");
	}
	
}
