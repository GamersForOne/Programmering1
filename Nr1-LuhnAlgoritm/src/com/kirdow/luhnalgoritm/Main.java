package com.kirdow.luhnalgoritm;

import javax.swing.JOptionPane;

//This program is made for Java 8 (version 1.8)
public class Main {
	
	//This is the main method for the program
	public static void main(String[] args) {
		//We create a new LuhnAlgorithm object to get access to the algorithm
		LuhnAlgorithm luhn = new LuhnAlgorithm();
		//We declare a new string for the input.
		String input = null;
		//We start a loop so we don't need to run the program all the time we want to enter a new value
		do {
			//We check if input not equals null, if it is, it's because we haven't got the input box yet, we just declared it
			if (input != null) {
				//We add a try-catch statement just in case the user entered a non-number value
				try {
					//We parse the string to a long, this is where we need the try-catch statement
					long inputNumber = Long.parseLong(input);
					//Now we run the algorithm to see if it is a valid luhn number
					if (luhn.checkLuhn(inputNumber))
						//If it is, inform the user about it
						JOptionPane.showMessageDialog(null, "That's a valid Luhn Number");
					else
						//If not, inform the user about that too
						JOptionPane.showMessageDialog(null, "Invalid luhn number");
				//Did the user not enter a number?
				} catch (NumberFormatException e) {
					//If so, prompt the user saying they need to enter a number
					JOptionPane.showMessageDialog(null, "Oops, you didn't enter a number");
				}
			}
			//This is where the user enters a number
			input = JOptionPane.showInputDialog("Enter number to check with LuhnAlgorithm");
		//If the input is null, aka that the user pressed Cancel, return out of the program
		} while (input != null);
		//Prompt the user and write in console that the program has ended
		JOptionPane.showMessageDialog(null, "Program Ended");
		System.out.println("Program ended");
	}
	
}
