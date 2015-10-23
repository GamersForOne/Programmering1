package com.kirdow.luhnalgoritm;

public class LuhnAlgorithm {
	
	//We need no constructor, so we leave it empty
	public LuhnAlgorithm() { }
	
	//This is the public method, it takes number as input and returns true if it's a valid luhn number
	//We also work with long (or 64-bit) numbers, because the personnumber seem to not fit in a 32-bit number.
	public boolean checkLuhn(long number) {
		//First we generate a pre-luhn number, which is the 2 digit number that
		//should be evenly divided by 10 (where preluhn % 10 == 0 should return true)
		long genPreLuhn = this.generatePreLuhn(number);
		//Then we return true if it's an evenly dividable by 10.
		return genPreLuhn % 10 == 0;
	}
	
	//this will generate the pre-luhn number, it takes in a long variable as input
	private long generatePreLuhn(long sequence) {
		//We set the doubler bool to false, which means we will start multiplying by 1
		boolean doubler = false;
		//After each iteration, we divide by 10, so we make a backup just in case
		long numb = sequence;
		//This is the value that we actually multiply, we set it to -1 to help the loop determining
		//when the first round is
		long nMod = -1;
		//Then we use a stringbuilder, which is better programming practice, because adding to a normal string
		//Can cause too much garbage
		StringBuilder sb = new StringBuilder();
		//This is the loop
		do {
			//First it checks if it's not the first time looping, remember that we set the nMod variable to -1
			//to tell the loop that it's the first time
			//If it isn't the first time, we jump to the next digit by dividing by 10
			if (nMod != -1) numb = numb / 10;
			//This is the one getting the value to multiply, we get the lowest current digit, by dividing the whole by 10
			//and retrieving the rest (modulus)
			nMod = numb % 10;
			//Then we append the value to the StringBuilder, remember that we are alternating doubling and not doubling.
			//That's what the doubler variable is for
			sb.append(doubler ? (nMod * 2) : (nMod));
			//Then we toggle the doubler (so if it's false, it becomes true, and if it's true, it becomes false)
			doubler = !doubler;
		//We do this loop when the whole number is greater than 9, the reason we do 9, is because when it's between 10 and 19,
		//and we divide by 10, it becomes between 0 and 9, and that's the last time we are supposed to loop
		} while (numb > 9);
		//Then we create a char array, with the length of the stringbuilder
		//and then copying over the characters
		//We could also have just done "char[] preAdd = sb.toString().toCharArray(); but that would just creating an extra
		//String object which we don't need, so this should save us memory
		char[] preAdd = new char[sb.length()];
		//This copies over the chars from the stringbuilder to the array
		sb.getChars(0, sb.length(), preAdd, 0);
		//Then we initialize the preluhn value, which will add all those values's digits we just created together to get one value
		long preLuhn = 0;
		//We loop through all characters
		for (char c : preAdd)
			//Here we add the values to the preLuhn value
			//But hey, where does the 48 come from? Well, in ASCII, the 0 character has the ASCII value, 48, and 1 character has 49 etc
			//So we subtract 48 to get the actual value and not the character. We could also just parse it, but parsing
			//Does cause abit of extra memory, so I prefer doing this
			preLuhn += c - 48;
		//Now we return the preLuhn value again
		return preLuhn;
		
	}
	
	//This is the public method like, like the old one, but we take a string input instead so we can get an endless
	//amount of digits input
	//It also throws NotNumberInputException which can also be found in this project source
	public boolean checkLuhnString(String number) throws NotNumberInputException {
		//We call the private function to generate a preLuhnNumber, but instead of the standard
		//one we call the String version
		long genPreLuhn = this.generatePreLuhnString(number);
		
		//When we get it we return true if it's evenly dividable by 10
		return genPreLuhn % 10 == 0;
	}
	
	//This is the String version of the pre-luhn generation, this is where the NotNumberInputException actually is thrown
	private long generatePreLuhnString(String sequence) throws NotNumberInputException {
		//We have a doubler which is the bool which tells if we multiply by 2 or 1.
		//It's false so we start with 1
		boolean doubler = false;
		
		//We also make a backup of the sequence just in case
		String numb = sequence;
		
		//We then take the chars from the sequence...
		char[] sequChars = sequence.toCharArray();
		
		//... and loop thru them to see if it's a positive number.
		//If it's not, we throw a new NotNumberInputException
		for (char c : sequChars) {
			if (c < '0' || c > '9' || c == '-' || c == '+')
				throw new NotNumberInputException();
		}
		
		//We then make the startpoint
		//We want to start at the end, which is the lowest digit
		int index = numb.length() - 1;
		
		//Then we create a stringbuilder which will contain all the numbers calculated
		StringBuilder sb = new StringBuilder();
		
		//We loop thru all chars from the end
		for (; index >= 0; index--) {
			//Here we get the char at the current position
			char c = sequChars[index];
			
			//we make it a number by removing the ascii value for '0' which is 48
			int i = c - 48;
			
			//then we append the number to the StringBuilder, but multiply by 2 if doubler == true
			sb.append(doubler ? (i * 2) : (i));
			
			//Then we toggle the doubler, so true turns false and false turns true
			doubler = !doubler;
		}
		
		//Then, after the loop, we setup the preluhn number
		//This part is the same as before
		int preluhn = 0;
		
		//We then declares the chars and copies them over
		char[] chars = new char[sb.length()];
		sb.getChars(0, sb.length(), chars, 0);
		
		//Then we just loop through the chars, and add the number to the preluhn.
		//Remember that we have the ascii value but we want the actual digit, so we subtract 48 each time
		for (char c : chars) {
			preluhn += c - 48;
		}
		
		//And lastly we return the preluhn value
		return preluhn;
	}
	
}
