package progfund;
/** 
 * <h3>Overview</h3>
 * <p>This assignment will require you to write a class to represent
 * a resizable array of characters. It should act much like a String.
 * Unlike a String however, the characters can be changed or deleted
 * directly.</p>
 * 
 * <p>To make your life easier you will be provided with an API
 * specification (just like the JavaDocs you use for your 
 * programming). Your job is to follow this API to fill out the 
 * method stubs with appropriate code. You should also ensure that 
 * this code is as uncrashable as possible. If your code detects 
 * faulty/incorrect input it should handle it as instructed in the 
 * API.</p>
 * 
 * <h3>Data Structure</h3>
 * <p>We will be storing chars into an array within the CharList 
 * class. Something to note is that arrays are immutable
 * (They cannot be changed once they are created.) A large part of
 * this assignment is to add methods to the CharList class to
 * simulate an array that is mutable. These methods should facilitate
 * adding and deleting chars from the array and changing the array's
 * size. Your array must always be exactly the size necessary to fit
 * all the characters. There must be no null chars on the end.</p>
 * 
 * <h3>Testing</h3>
 * <p>To ensure your code is uncrashable you will have to filter out 
 * incorrect input with if statements. A test driver has been
 * provided to help you test your classes. However, you should write 
 * your own main method to test your classes as well.
 * Test the expected input, test the boundary cases, test outside the
 * boundary cases and test the ridiculous cases. Test early and test
 * thoroughly.</p>
 * 
 * <h3>How To Do The Assignment</h3>
 * <p>We suggest you write your code in the order that the methods
 * appear in. The most independent methods are listed first. More
 * dependent methods are written last. You can call your earlier methods
 * to complete the later methods. For example, deleteChar() might call 
 * charAt().</p>
 * <p>You can write your own main method in this class to test only the
 * methods which you have completed. You might consider printing out the
 * contents of the array to make sure it contains the correct characters
 * and length.
 * </p>
 * 
 * <h3>Additional Requirements</h3>
 * <p>You must provide an academic integrity statement at the top of
 * each class you write. This should include your name, id number,
 * date and the statement, "This is my own work as defined by the 
 * SAIBT / EIBT Academic Misconduct policy."
 * You will need to comment your code thoroughly while you are
 * programming.</p>
 * 
 * <p>In general, you may not use methods from other classes. The only
 * exceptions are string.length() and string.charAt() may be used in
 * your constructor and Character.toUpperCase(c) may be used in the 
 * toTitleCase() method. The use of other Java methods may result in a loss
 * of marks.
 * </p>
 */


//#
//# File:      CharList.java
//# Author:    Ke Zhang
//# SAIBT Id:  26852
//# Version:   1.0  15 August 2017
//# Description: Assignment I
//#   This is my own work as defined by the University's
//#   Academic Misconduct policy.
//#
public class CharList {
	/**
	 * Array to store chars
	 */
	
	private char[] characters = null;
	

	/**
	 * Create a char array of length 0
	 */
	public CharList(){
		this.characters = new char[0];
	}

	/**
	 * Create a CharList from an argument String.
	 * Loop through the string to add each char 
	 * to the characters array.
	 * @param other The String to copy.
	 */
	public CharList(String other){
		/*
		 * to check if the other equal to null or not
		 */
		if (other == null){
			this.characters =  new char[0];
		}
		else{
			characters = new char[other.length()];
		
			/*
			 * for loop to put the string to charList
			 */
			for (int i = 0 ; i < other.length() ; i ++){
			characters[i] = other.charAt(i);
			}
			
		}
		
	}

	/**
	 * Create a CharList by deep copying an argument CharList.
	 * You will need to loop through the array and copy each char.
	 * @param other The CharList to copy.
	 */
	public CharList(CharList other){
		if (other == null){
			this.characters =  new char[0];
		}
		else{
			characters = new char[other.length()];
			
			/*
			 * for loop to copy the charlist
			 */
			for (int i = 0 ; i< other.length(); i ++){
				characters[i] = other.characters[i];
				
			}
		}
	}

	/**
	 * Calculate and return the length of the String.
	 * @return The length of the String.
	 */
	public int length() {
        int total = 0;
        for (int i = 0; i<characters.length ; i ++){
        	
        	/*
			 * check each items in array if it is not empty total ++
			 */
        	if (this.characters[i] != '\0'){
            	total ++;
        	}
        }
        return total;
	}

	/**
	 * Compares to CharLists returning true if they contain the same chars
	 * and are the same length.
	 * @param other Object presumed to be a CharList. Must typecast to
	 * CharList if it is an instance of this class.
	 * @return true if both arrays contain the same chars and
	 * are the same length.
	 */
	@Override
	public boolean equals(Object other){
		if (other == null){
			return false;
		}
		/*
		 * check if they have the same class
		 */
		if (other instanceof CharList){
			CharList newOther = (CharList) other;
			
			/*
			 * check if they have the same length
			 */
			if (newOther.length() == characters.length){
				
				/*
				 * check if each items is the same
				 */
				for (int i = 0 ; i < characters.length; i++){
					if ( characters[i] != newOther.charAt(i)){
						/*
						 * once there is a difference return false
						 */
						return false;
					}
				}
				return true;
			}
			
		}
		
		return false;
		
	}
		


	/**
	 * Return the char at the argument index from the characters array.
	 * @param index the position of the char to return.
	 * @return The char at the given index or '\0' if no char is available.
	 */
	public char charAt(int index){
		
		/*
		 * check the if the index is in the range of array
		 */
		if (characters.length > 0 && characters.length > index && index >= 0){
			return characters[index];
		}
		return '\0';
	}

	/**
	 * Return a deep copy of the characters array.
	 * Create a new array and loop through the 
	 * characters array copying them to the new array.
	 * @return A char array deep copying the contents of the characters
	 * instance variable.
	 * 
	 */
	public char[] toCharArray(){
		if ( characters == null){
			return null;
		}
		else{
			char[] newCharacters = new char[characters.length];
			for (int i = 0 ; i < characters.length ; i ++){
				newCharacters[i] = characters[i];
				
			}
			return newCharacters;
		
		}
		
	}

	/**
	 * Convert the char array back into a String and return it.
	 * @return String representation of the char array.
	 */
	@Override
	public String toString(){
		if (characters == null){
			return null;
		}
		else{
			String newCharacters = "";
			for (int i = 0 ; i < characters.length ; i ++){
				newCharacters += characters[i];
			}
		return newCharacters;
			
		}
	}

	/**
	 * Add the argument charlist to the end of this charlist.
	 * The resulting array will be the length of both charlists added together.
	 * @param other The CharList to add.
	 */
	public void append(CharList other){
		if (other == null){
			this.characters = this.characters;
		}
		else{
			/*
			 * set a new array to store the thing i want
			 */
			char[] newCharacters = new char[characters.length + other.length()];
			/*
			 * copy the charlist
			 */
			for(int i = 0 ; i < characters.length ; i ++){
				newCharacters[i] = characters[i];
			}
			/*
			 * put the other into it
			 */
			for (int i = characters.length ; i < characters.length + other.length() ; i ++  ){
				newCharacters[i] = other.charAt(i - characters.length);
			}
			characters = newCharacters;
		}
	}

	/**
	 * Add the argument char list to the start of this charlist.
	 * The resulting array will be the length of both charlists added together.
	 * @param other The CharList to add.
	 */
	public void prepend(CharList other){
		if (other == null){
			this.characters = this.characters;
		}
		else{
			char[] newCharacters = new char[characters.length + 
			                                other.length()];
			
			for(int i = 0 ; i < other.length() ; i ++){
				newCharacters[i] = other.charAt(i);
			}
			
			for (int i = other.length() ; i < characters.length + 
					other.length() ; i ++  ){
				newCharacters[i] = characters[i - other.length()];
			}
			characters = newCharacters;
		}
	}

	/**
	 * Add the argument charlist at the specified index in the array. 
	 * All characters at that index or beyond will be pushed to the end.
	 * @param other The CharList to insert.
	 * @param index The position to insert at.
	 */
	public void insert(CharList other, int index){
		if (other == null){
			this.characters = this.characters;
		}
		
		else{
			char[] newCharacters = new char[characters.length + other.length()];
			/*
			 * check the index different index match different situation
			 */
			if (index == 0){

				prepend(other);
			}
			else if (index > characters.length){
				characters = characters;
			}
			else if (index == characters.length){
				append(other);
			}
			else if (index < 0){
				characters = characters;
			}
			else{
				for(int i = 0 ; i < index ; i ++){
					newCharacters[i] = characters[i];
				}
				for(int i = index ; 
						i <index + other.length() ; i ++){
					newCharacters[i] = other.charAt(i -index);
				}
				for(int i =  index + other.length();
						i < other.length() + characters.length;i ++){
					newCharacters[i] = characters[i- other.length()];
				}
				characters = newCharacters;
			}
			
		}
	}

	/**
	 * Return the substring in the range from start up to and excluding end.
	 * @param start Index of first character.
	 * @param end Index of the character to stop before. 
	 * @return A CharList representing the chars in this range. If start
	 * is less than zero, or end is greater than length or start and end 
	 * are reversed, return null.
	 */
	public CharList getRange(int start, int end){
		if (start > end|| start < 0|| end > characters.length){
			return null;
		}
		else{
			char[] newCharacters = new char[end - start];
			for (int i = start ; i < end ; i++){
				newCharacters[i - start] = characters[i];
				
			}
			CharList result = new CharList();
			result.characters = newCharacters;
			return result;
		}
	}

	/**
	 * Searches through the characters in this CharList for the sequence
	 * matching the argument CharList.
	 * @param substring Substring to find.
	 * @return The index of the first character of the substring or -1 if not in the CharList.
	 */
	public int indexOf(CharList substring){
		int position = -1;
		if (substring == null){
			return -1;
		}
		else{
			if (characters.length >0 && substring.length() > 0 
					&& characters.length > substring.length()){
				/*
				 * to check the item has the same letter with the substring
				 */
				for (int i = 0; i < characters.length ; i ++){
					if (characters[i] == substring.charAt(0)){
						/*
						 * once you get ,get the position
						 */
						position = i;
						/*
						 * check if the letter is total the same with substring
						 */
						for (int t = 0 ; t< substring.length() ; t ++){
							/*
							 * once it is different ,return -1
							 */
							if (substring.charAt(t) != this.characters[t + i ]){
								return -1;
							}
						}
						return position;
					}
				}
				return -1;
			}
			else{
				return -1;
			}
		}
		
	}

	/**
	 * Deletes the char at the argument index.
	 * Characters will be shifted back to fill in the empty space.
	 * The char array will be resized to fit only the non null characters.
	 * Any blank space must be removed such that 
	 * "racecar".deleteChar(3) results in the CharList "raccar".
	 * @param index position of char to delete.
	 * @return true if index is within the bounds of the array.
	 */
	public boolean deleteChar(int index){
		
		if (index < 0 || index >= characters.length){
			return false;
		}
		else{
			char[] newCharacters = new char[characters.length - 1];
			if (index == 0){
				for (int i = 0 ; i < characters.length - 1;i ++){
					newCharacters[i ] = characters[i + 1];
				}
				
			}
			else if (index == characters.length - 1){
				for (int i = 0 ; i < characters.length - 1;i ++){
					newCharacters[i] = characters[i];
				}
				
			}
			else{
				for (int i = 0 ; i < index;i ++){
					newCharacters[i] = characters[i];
				}
				for (int i = 0; i < characters.length -1 - index; i++){
					newCharacters[i + index] = characters[i + 1 + index];
				}
				
			}	
			characters = newCharacters;
			return true;
			
		}
		
	}

	/**
	 * Deletes the substring from the arguments start (inclusive) to end (exclusive).
	 * Any blank space must be removed such that "abcdefg".deleteRange(1, 4)
	 * results in "aefg".
	 * @param start index of the char to start at.
	 * @param end index of the char to stop before.
	 * @return true if the range was successfully deleted.
	 */
	public boolean deleteRange(int start, int end){
		if (start > end || start < 0 ||end > characters.length ){
			return false;
		}
		else{
			char[] newCharacters = new char[characters.length - (end - start)];
			if ( start == 0){
				for (int i = 0 ; i < characters.length - (end - start) ; i ++){
					newCharacters[i] = characters[i + end];
				}
				
			}
			else if (end == characters.length){
				for (int i = 0 ; i < characters.length - (end - start); i ++){
					newCharacters[i] = characters[i];
				}	
			}
			else{
				for(int i = 0; i < start ; i ++){
					newCharacters[i] = characters[i];
				}
				for (int i = 0; i <characters.length - (end - start) - start  ; i ++){
					newCharacters[ start + i] = characters[i + end];
				}
			}
			characters = newCharacters;
			return true;
		}
	}

	/**
	 * Deletes the first instance of the argument substring
	 * if it is found in this CharList.
	 * Any blank space must be removed such that 
	 * "racecar".deleteSubstring("cec") results in the 
	 * CharList "raar".
	 * 
	 * @param substring The string to find.
	 * @return the index of the first character 
	 * in the substring that was deleted or -1 
	 * if the substring was not found.
	 */
	public int deleteSubstring(CharList substring){
		int position = -1;
		if (substring == null){
			return -1;
		}
		else{
			if (characters.length >0 && substring.length() > 0 
					&& characters.length > substring.length()){
				for (int i = 0; i < characters.length ; i ++){
					if (characters[i] == substring.charAt(0)){
						position = i;
						for (int t = 0 ; t< substring.length() ; t ++){
							if (substring.charAt(t) != this.characters[t + i ]){
								return -1;
							}
						}
						/*
						 * once you make sure the position of substring, use the delete
						 * range method
						 */
						deleteRange(i,i+substring.length());
						return i;
					}

				}
				return -1;
			}
			else{
				return -1;
			}
		}
	}

	/**
	 * Capitalizes the first letter of each word in the CharList.
	 * You may use Character.toUpperCase(chr).
	 */
	public void toTitleCase(){
		if ( characters == null){
			characters = characters;
		}
		else{
			char[] newCharacters = new char[characters.length];
			
			for ( int i = characters.length - 1; i >= 0  ;i--){
				/*
				 * once you check the space , that means the next letter should change
				 * to upper
				 */
				if (characters[i] == 32){
					newCharacters[i] = 32;
					newCharacters[i + 1] = Character.toUpperCase(characters[i + 1]);
				}
				else{
					newCharacters[i] = characters[i];
				}
			}
			/*
			 * the first letter also need to be upper
			 */
			newCharacters[0] = Character.toUpperCase(characters[0]);
			characters = newCharacters;
		}
	}
	
	/**
	 * Compares the chars of the argument CharList to the chars of
	 * the calling object. The Strings will be compared 
	 * lexicographically such that Bed < Bread < Eggs < Milk < Mouse.
	 *
	 * @param other The CharList to compare
	 * @return 1 if the calling object has a greater string than the
	 * argument. -1 if the calling object has a lesser string to the
	 * argument. 0 if the strings of the argument and calling objects
	 * are the same.
	 */
	public int compareTo(CharList other){
		/*
		 * use compare method to compare to string , 
		 * firstly you should change them into string
		 */
		int result = this.toString().compareTo(other.toString());
		if (result >0){
			return 1;
		}
		else if (result < 0){
			return -1;
		}
		else {
			return 0;
		}
		
	}
}		
		
	
	


