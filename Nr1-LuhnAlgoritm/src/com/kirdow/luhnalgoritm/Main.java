package com.kirdow.luhnalgoritm;

import javax.swing.JOptionPane;

//This program is made for Java 8 (version 1.8)
public class Main {
	
	//This is a toggle variable which decides if we work with 64-bit numbers, or string-based numbers
	//OldWay == true means we're working with 64-bit numbers
	private static boolean OldWay = false;
	
	private static void oldWay() {
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
					
					//We check if the number is positive.
					//If it is, we run the algorithm
					if (inputNumber >= 0) {
						//Now we run the algorithm to see if it is a valid luhn number
						if (luhn.checkLuhn(inputNumber))
							//If it is, inform the user about it
							JOptionPane.showMessageDialog(null, "That's a valid Luhn Number");
						else
							//If not, inform the user about that too
							JOptionPane.showMessageDialog(null, "Invalid luhn number");
					} else {
						//If it's less than 0, prompt the user
						JOptionPane.showMessageDialog(null, "Please enter a positive number");
						//The minus character is not supported so we test if it's positive
						//just incase the user enters it
					}
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
	}
	
	private static void newWay() {
		LuhnAlgorithm luhn = new LuhnAlgorithm();
		
		String input = null;
		
		do {
			if (input != null) {
				try {
					boolean isLuhn = luhn.checkLuhnString(input);
					
					if (isLuhn)
						JOptionPane.showMessageDialog(null, "That's a valid Luhn Number");
					else
						JOptionPane.showMessageDialog(null, "Invalid luhn number");
				} catch (NotNumberInputException e) {
					JOptionPane.showMessageDialog(null, "Please enter a positive number");
				}
			}
			
			input = JOptionPane.showInputDialog("Enter number to check with LuhnAlgorithm");
		} while (input != null);
	}
	
	//This is the main method for the program
	public static void main(String[] args) {
		//We first check if we should do the old or new way
		Main.OldWay = calculateOldWay(args);
		//We check if we work with the old way or the new way
		if (OldWay) {
			//If it's the old way, we call oldWay()
			oldWay();
		} else {
			//Else if it's the new way, we call newWay()
			newWay();
		}
		//Prompt the user and write in console that the program has ended
		JOptionPane.showMessageDialog(null, "Program Ended");
		System.out.println("Program ended");
	}
	
	//This function determine if we should use the old way or new way to calculate if a number is luhn
	private static boolean calculateOldWay(String[] args) {
		
		//First we check if args length is greater than or equal to 1
		if (args.length >= 1) {
			//If it is, the following 8 lines, turns the first argument, which always is the path to
			//the file which this program is executed as, like in this case "Nr1-LuhnAlgoritm.jar", by default,
			//to a rawFile, with just the filename without filepath and extension
			String path = args[0];
			String filename = "";
			if (path.contains("\\")) {
				filename = path.substring(path.lastIndexOf("\\"));
			} else {
				filename = path.substring(path.lastIndexOf("/"));
			}
			String rawFile = filename.substring(0, filename.lastIndexOf(".")-1);
			
			//Then we check if it's equal to oldway, or newway, to see what way to use
			//We also doesn't care about upper- or lowercase
			if (rawFile.equalsIgnoreCase("oldway"))
				return true;
			else if (rawFile.equalsIgnoreCase("newway"))
				return false;
			
			//If both these fail, we instead check if there's more than 1 args
			if (args.length >= 2) {
				//If there is, we run thru them to check for "-old" or "-o" to see if we should use the old way
				for (int i = 1; i < args.length; i++) {
					if (args[i].equalsIgnoreCase("-old") || args[i].equalsIgnoreCase("-o"))
						return true;
				}
			}
		}
		
		//If both these fail, we return the current value, which is false or the new way
		return Main.OldWay;
	}
	
}
