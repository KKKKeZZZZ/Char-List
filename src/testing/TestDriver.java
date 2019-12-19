package testing;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

import progfund.CharList;

/**
 * <p>This class is the test driver for the CharList class.
 * In this class you will find tests for each of the methods. 
 * This test driver is used to check that normal situation 
 * works, then also checks shoulder or unusual situations
 * for example deleting null from the CharList. </p>
 * 
 * <p>This TestDriver uses reflection to access your private
 * variables. Make sure that you do not change the names of
 * any variables or methods in the CharList class. Otherwise
 * this TestDriver will not work.
 * </p>
 * 
 * @author Michael Ulpen
 * @see progfund.CharList
 */

//#
//# File:      TestDriver.java
//# Author:    Ke Zhang
//# SAIBT Id:  26852
//# Version:   1.0  15 August 2017
//# Description: Assignment I
//#   This is my own work as defined by the University's
//#   Academic Misconduct policy.
//#
public class TestDriver {

	public static int marks = 0;
	public static int total = 100;

	public static void main(String[] args){				
		Scanner keyboard = new Scanner(System.in);

		//Comment out any test names that you do not want to execute
		String[] methodNames = {
				"test Constructors", 
				"test Length", 
				"test Equals", 
				"test CharAt", 
				"test ToCharArray", 
				"test ToString", 
				"test Append", 
				"test Prepend", 
				"test Insert", 
				"test GetRange", 
				"test IndexOf", 
				"test DeleteChar", 
				"test DeleteRange", 
				"test DeleteSubstring", 
				"test ToTitleCase",
				"test CompareTo"};
		
		
		for(String name : methodNames){	
			System.out.println("*********  " + name.toUpperCase() + "  *********");
			
			String methodName = name.replace(" ", "");
			try {
				Method method = TestDriver.class.getDeclaredMethod(methodName, new Class<?>[]{});
				method.invoke(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("Press enter to continue...");
			keyboard.nextLine();
		}
		keyboard.close();
		
		System.out.println("\n**************************************\n");
		System.out.println("Total: " + marks + " / "+ total + "\n");
		
		if(marks < 25){
			System.out.println("Looks like you have just started.");
			System.out.println("You can use this Test Driver to check your progress.");
			System.out.println("Make sure to write your own main method as well.");
			System.out.println("There may be problems that this Test Driver cannot help you with.");
			System.out.println("Good luck programmer.");
		}
		else if(marks < 50){
			System.out.println("Looks like you're making progress. Well done.");
			System.out.println("Make sure to write your methods in the order that they are tested.");
			System.out.println("This Test Driver isn't as smart as you might think ;)");
			System.out.println("Get your constructors and equals method working first.");
		}
		else if(marks < 75){
			System.out.println("Great work. Keep it up!");
			System.out.println("Make sure you're checking for nulls.");
			System.out.println("Check your arguments are valid and handle them appropriately.");
		}
		else if(marks < 90){
			System.out.println("Almost there!");
			System.out.println("It's the little things that cause the biggest problems isn't it?");
			System.out.println("If you're struggling with one method, sometimes its best to take\n"
					+ "a step back and think about the algorithm.");
			System.out.println("Maybe writing pseudocode or drawing a diagram would help?");
			System.out.println("But how would I know, I'm just a computer :D");
		}
		else if(marks < 100){
			System.out.println("So close!");
			System.out.println("I know you can do this.");
			System.out.println("Sometimes the problem isn't where you expect it to be.");
			System.out.println("Make sure you have your own main method to test specific bugs.");
			System.out.println("Print things out, do some debugging!");
		}
		else if(marks == 100){
			System.out.println("Great job! 100%");
			System.out.println("Your code has passed all the tests executed by this test driver.");
			System.out.println("Remember, this is not a guarantee of your final marks.");
			System.out.println("Your supervisor may test for more cases than are outlined here.");
			System.out.println("You may also be marked on code style.");
			System.out.println("Make sure you have meaningful variable names and that you have left\n"
					+ "a comment to describe any complicated code.");
			System.out.println("Once you've done that, take a break.");
			System.out.println("You've earned it! :D");
		}
		else{
			System.out.println("Looks like you got more that 100%");
			System.out.println("That's incredible!");
			System.out.println("Maybe you should be teaching programming.");
			System.out.println("...instead of cheating.");
		}
	}

	private static void testConstructors() {
		try{
			System.out.println("Testing new CharList()");
			CharList c1 = new CharList();
			testArray(2, c1, "");
		}
		catch(NullPointerException e){
			System.err.println("Something is null and it shouldn't be");
			e.printStackTrace();
		}
		try{
			System.out.println("Testing new CharList('Hello')");
			CharList c2 = new CharList("Hello");
			testArray(2, c2, "Hello");
		}
		catch(NullPointerException e){
			System.err.println("Something is null and it shouldn't be");
			e.printStackTrace();
		}
		try{
			System.out.println("Testing new CharList((String)null)");
			CharList c2 = new CharList((String)null);
			testArray(1, c2, "");
		}
		catch(NullPointerException e){
			System.err.println("Something is null and it shouldn't be");
			e.printStackTrace();
		}
		try{
			System.out.println("Testing new CharList( new CharList('Hello') )");
			CharList original = new CharList("Hello");
			CharList c3 = new CharList(original);
			testArray(2, c3, "Hello");

			System.out.println("Testing Deep Copy");
			try{
				Class<? extends CharList> charListReflect = original.getClass();
				Field charsReflect = charListReflect.getDeclaredField("characters");
				charsReflect.setAccessible(true);
				char[] chars = (char[])charsReflect.get(original);
				chars[2] = 'g';
				System.out.println("Copied Array changed to Heglo");
				testArray(0, original, "Heglo");

				System.out.println("New Array should not change");
				if(!testArray(2, c3, "Hello")){
					System.out.println("Make sure you loop through characters and copy them one by one");
				}

			} catch (NoSuchFieldException e) {
				System.err.println("char array must be named \"characters\" and must be private");
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		catch(NullPointerException e){
			System.err.println("Something is null and it shouldn't be");
			e.printStackTrace();
		}
		try{
			System.out.println("Testing new CharList((CharList)null)");
			CharList c2 = new CharList((CharList)null);
			testArray(1, c2, "");
		}
		catch(NullPointerException e){
			System.err.println("Something is null and it shouldn't be");
			e.printStackTrace();
		}

	}

	private static void testLength() {
		try{
			System.out.println("Testing 'Hello'.length()");
			CharList c1 = new CharList("Hello");
			testMethod(1, c1, "length", 5, new Class<?>[]{}, new Object[]{});

			System.out.println("Testing ''.length()");
			CharList c2 = new CharList();
			testMethod(1, c2, "length", 0, new Class<?>[]{}, new Object[]{});

			System.out.println("Testing ['y','o','\\0','\\0', '\\0'].length()");
			CharList c3 = new CharList("hello");

			Class<? extends CharList> charListReflect = c3.getClass();
			Field charsReflect = charListReflect.getDeclaredField("characters");
			charsReflect.setAccessible(true);
			char[] chars = (char[])charsReflect.get(c3);
			chars[2] = '\0';
			chars[3] = '\0';
			chars[4] = '\0';
			testMethod(1, c3, "length", 2, new Class<?>[]{}, new Object[]{});
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	private static void testEquals() {
		System.out.println("Testing 'Hello' and 'Hello'");
		CharList c1 = new CharList("Hello");
		CharList c2 = new CharList("Hello");
		testMethod(2, c1, "equals", true, new Class<?>[]{Object.class}, new Object[]{c2});

		System.out.println("Testing 'Hello' and 'Goodbye'");
		c1 = new CharList("Hello");
		c2 = new CharList("Goodbye");
		testMethod(2, c1, "equals", false, new Class<?>[]{Object.class}, new Object[]{c2});

		System.out.println("Testing 'Hello' and 15");
		c1 = new CharList("Hello");
		testMethod(1, c1, "equals", false, new Class<?>[]{Object.class}, new Object[]{15});

		System.out.println("Testing 'Hello' and null");
		c1 = new CharList("Hello");
		testMethod(1, c1, "equals", false, new Class<?>[]{Object.class}, new Object[]{null});


	}

	private static void testCharAt() {
		System.out.println("Testing 'Hamburger'.charAt(0)");
		CharList c1 = new CharList("Hamburger");
		testMethod(1, c1, "charAt", 'H', new Class<?>[]{int.class}, new Object[]{0});

		System.out.println("Testing 'Hamburger'.charAt(8)");
		c1 = new CharList("Hamburger");
		testMethod(1, c1, "charAt", 'r', new Class<?>[]{int.class}, new Object[]{5});


		System.out.println("Testing 'Hamburger'.charAt(-1)");
		c1 = new CharList("Hamburger");
		testMethod(1, c1, "charAt", '\0', new Class<?>[]{int.class}, new Object[]{-1});


		System.out.println("Testing 'Hamburger'.charAt(10)");
		c1 = new CharList("Hamburger");
		testMethod(1, c1, "charAt", '\0', new Class<?>[]{int.class}, new Object[]{10});
	}

	private static void testToCharArray() {
		System.out.println("Testing 'Apple'.toCharArray()");
		CharList c1 = new CharList("Apple");
		testMethod(2, c1, "toCharArray", new char[]{'A','p','p','l','e'}, 
				new Class<?>[]{}, new Object[]{});

		System.out.println("Testing ''.toCharArray()");
		c1 = new CharList();
		testMethod(1, c1, "toCharArray", new char[]{}, 
				new Class<?>[]{}, new Object[]{});
	}

	private static void testToString() {
		System.out.println("Testing 'Banana'.toString()");
		CharList c1 = new CharList("Banana");
		testMethod(2, c1, "toString", "Banana", 
				new Class<?>[]{}, new Object[]{});

		System.out.println("Testing ''.toString()");
		c1 = new CharList();
		testMethod(2, c1, "toString", "", 
				new Class<?>[]{}, new Object[]{});
	}

	private static void testAppend() {
		try{
			System.out.println("Testing ''.append('Hello')'");
			CharList c1 = new CharList();
			c1.append(new CharList("Hello"));
			testArray(2, c1, "Hello");

			System.out.println("Testing 'Hello'.append('Bob')");
			CharList c2 = new CharList("Hello");
			c2.append(new CharList("Bob"));
			testArray(2, c2, "HelloBob");

			System.out.println("Testing 'HelloBob'.append('')");
			CharList c3 = new CharList("HelloBob");
			c3.append(new CharList(""));
			testArray(1, c3, "HelloBob");
			try{
				System.out.println("Testing 'HelloBob'.append(null)");
				CharList c4 = new CharList("HelloBob");
				c4.append(null);
				testArray(1, c4, "HelloBob");
			}
			catch(NullPointerException e){
				System.err.println("Make sure you are testing for null arguments");
				e.printStackTrace();
			}
		}catch(Exception e){
			System.err.println("Something weird happened");
			e.printStackTrace();
		}
	}

	private static void testPrepend() {
		try{
			System.out.println("Testing ''.prepend('Hello')'");
			CharList c1 = new CharList();
			c1.prepend(new CharList("Hello"));
			testArray(2, c1, "Hello");

			System.out.println("Testing 'Hello'.prepend('Bob')");
			CharList c2 = new CharList("Hello");
			c2.prepend(new CharList("Bob"));
			testArray(2, c2, "BobHello");

			System.out.println("Testing 'HelloBob'.prepend('')");
			CharList c3 = new CharList("BobHello");
			c3.prepend(new CharList(""));
			testArray(1, c3, "BobHello");
			try{
				System.out.println("Testing 'HelloBob'.prepend(null)");
				CharList c4 = new CharList("BobHello");
				c4.prepend(null);
				testArray(1, c4, "BobHello");
			}
			catch(NullPointerException e){
				System.err.println("Make sure you are testing for null arguments");
				e.printStackTrace();
			}
		}catch(Exception e){
			System.err.println("Something weird happened");
			e.printStackTrace();
		}
	}

	private static void testInsert() {
		try{
			System.out.println("Testing ''.insert('Skate', 0)'");
			CharList c1 = new CharList();
			c1.insert(new CharList("Skate"), 0);
			testArray(1, c1, "Skate");

			System.out.println("Testing 'Skate'.insert('board', 5)'");
			CharList c2 = new CharList("Skate");
			c2.insert(new CharList("board"), 5);
			testArray(1, c2, "Skateboard");

			System.out.println("Testing 'Skateboard'.insert('at', 4)'");
			CharList c3 = new CharList("Skateboard");
			c3.insert(new CharList("at"), 4);
			testArray(2, c3, "Skatateboard");

			try{
				System.out.println("Testing 'Skateboard'.insert('at', 42)'");
				CharList c4 = new CharList("Skateboard");
				c4.insert(new CharList("at"), 42);
				testArray(1, c4, "Skateboard");
			}
			catch(Exception e){
				e.printStackTrace();
			}
			try{
				System.out.println("Testing 'Skateboard'.insert('at', -1)'");
				CharList c5 = new CharList("Skateboard");
				c5.insert(new CharList("at"), -1);
				testArray(1, c5, "Skateboard");
			}
			catch(Exception e){
				e.printStackTrace();
			}
			try{
				System.out.println("Testing 'Skate'.insert(null, 3)'");
				CharList c6 = new CharList("Skate");
				c6.insert(null, 3);
				testArray(1, c6, "Skate");
			}
			catch(NullPointerException e){
				System.err.println("Make sure you are testing for null arguments");
				e.printStackTrace();
			}
		}catch(Exception e){
			System.err.println("Something weird happened");
			e.printStackTrace();
		}
	}

	private static void testGetRange() {
		System.out.println("** getRange() depends on equals() working... **\n");

		System.out.println("Testing 'Stalwart'.getRange(0, 8)");
		CharList c1 = new CharList("Stalwart");
		testMethod(1, c1, "getRange", new CharList("Stalwart"), 
				new Class<?>[]{int.class, int.class}, new Object[]{0, 8});

		System.out.println("Testing 'Stalwart'.getRange(2, 6)");
		CharList c2 = new CharList("Stalwart");
		testMethod(2, c2, "getRange", new CharList("alwa"), 
				new Class<?>[]{int.class, int.class}, new Object[]{2, 6});

		System.out.println("Testing 'Stalwart'.getRange(6, 2)");
		CharList c3 = new CharList("Stalwart");
		testMethod(1, c3, "getRange", null, 
				new Class<?>[]{int.class, int.class}, new Object[]{6, 2});

		System.out.println("Testing 'Stalwart'.getRange(-1, 2)");
		CharList c4 = new CharList("Stalwart");
		testMethod(1, c4, "getRange", null, 
				new Class<?>[]{int.class, int.class}, new Object[]{-1, 2});

		System.out.println("Testing 'Stalwart'.getRange(1, 20)");
		CharList c5 = new CharList("Stalwart");
		testMethod(1, c5, "getRange", null, 
				new Class<?>[]{int.class, int.class}, new Object[]{1, 20});

	}

	private static void testIndexOf() {
		System.out.println("Testing 'Slaughter'.indexOf('laugh')");
		CharList c1 = new CharList("Slaughter");
		testMethod(2, c1, "indexOf", 1, 
				new Class<?>[]{CharList.class}, new Object[]{new CharList("laugh")});

		System.out.println("Testing 'Slaughter'.indexOf('sadness')");
		CharList c2 = new CharList("Slaughter");
		testMethod(1, c2, "indexOf", -1, 
				new Class<?>[]{CharList.class}, new Object[]{new CharList("sadness")});


		System.out.println("Testing 'Slaughter'.indexOf('Sla')");
		CharList c3 = new CharList("Slaughter");
		testMethod(1, c3, "indexOf", 0, 
				new Class<?>[]{CharList.class}, new Object[]{new CharList("Sla")});

		System.out.println("Testing 'Slaughter'.indexOf('ter')");
		CharList c4 = new CharList("Slaughter");
		testMethod(1, c4, "indexOf", 6, 
				new Class<?>[]{CharList.class}, new Object[]{new CharList("ter")});

		System.out.println("Testing 'Slaughter'.indexOf(null)");
		CharList c5 = new CharList("Slaughter");
		testMethod(1, c5, "indexOf", -1, 
				new Class<?>[]{CharList.class}, new Object[]{null});
	}

	private static void testDeleteChar() {
		System.out.println("Testing 'Polar Bear'.deleteChar(5)");
		CharList c1 = new CharList("Polar Bear");
		testMethod(1, c1, "deleteChar", true, 
				new Class<?>[]{int.class}, new Object[]{5});
		testArray(1, c1, "PolarBear");

		System.out.println("Testing 'Polar Bear'.deleteChar(0)");
		CharList c2 = new CharList("Polar Bear");
		testMethod(1, c2, "deleteChar", true, 
				new Class<?>[]{int.class}, new Object[]{0});
		testArray(1, c2, "olar Bear");

		System.out.println("Testing 'Polar Bear'.deleteChar(9)");
		CharList c3 = new CharList("Polar Bear");
		testMethod(1, c3, "deleteChar", true, 
				new Class<?>[]{int.class}, new Object[]{9});
		testArray(1, c3, "Polar Bea");

		System.out.println("Testing 'Polar Bear'.deleteChar(-9)");
		CharList c4 = new CharList("Polar Bear");
		testMethod(1, c4, "deleteChar", false, 
				new Class<?>[]{int.class}, new Object[]{-9});
		testArray(1, c4, "Polar Bear");

		System.out.println("Testing 'Polar Bear'.deleteChar(19)");
		CharList c5 = new CharList("Polar Bear");
		testMethod(1, c5, "deleteChar", false, 
				new Class<?>[]{int.class}, new Object[]{19});
		testArray(1, c5, "Polar Bear");
	}

	private static void testDeleteRange() {
		System.out.println("Testing 'Emporer Penguin'.deleteRange(5, 11)");
		CharList c1 = new CharList("Emporer Penguin");
		testMethod(1, c1, "deleteRange", true, 
				new Class<?>[]{int.class, int.class}, new Object[]{5, 11});
		testArray(1, c1, "Emporguin");

		System.out.println("Testing 'Emporer Penguin'.deleteRange(0, 5)");
		CharList c2 = new CharList("Emporer Penguin");
		testMethod(1, c2, "deleteRange", true, 
				new Class<?>[]{int.class, int.class}, new Object[]{0, 5});
		testArray(1, c2, "er Penguin");

		System.out.println("Testing 'Emporer Penguin'.deleteRange(11, 15)");
		CharList c3 = new CharList("Emporer Penguin");
		testMethod(1, c3, "deleteRange", true, 
				new Class<?>[]{int.class, int.class}, new Object[]{11, 15});
		testArray(1, c3, "Emporer Pen");

		System.out.println("Testing 'Emporer Penguin'.deleteRange(10, 5)");
		CharList c4 = new CharList("Emporer Penguin");
		testMethod(1, c4, "deleteRange", false, 
				new Class<?>[]{int.class, int.class}, new Object[]{10, 5});
		testArray(1, c4, "Emporer Penguin");

		System.out.println("Testing 'Emporer Penguin'.deleteRange(-1, 5)");
		CharList c5 = new CharList("Emporer Penguin");
		testMethod(1, c5, "deleteRange", false, 
				new Class<?>[]{int.class, int.class}, new Object[]{-1, 5});
		testArray(1, c5, "Emporer Penguin");

		System.out.println("Testing 'Emporer Penguin'.deleteRange(5, 50)");
		CharList c6 = new CharList("Emporer Penguin");
		testMethod(1, c6, "deleteRange", false, 
				new Class<?>[]{int.class, int.class}, new Object[]{5, 50});
		testArray(1, c6, "Emporer Penguin");
	}

	private static void testDeleteSubstring() {
		System.out.println("Testing 'Toucan doing the cancan'.deleteSubstring('can')");
		CharList c1 = new CharList("Toucan doing the cancan");
		testMethod(1, c1, "deleteSubstring", 3, 
				new Class<?>[]{CharList.class}, new Object[]{new CharList("can")});
		testArray(1, c1, "Tou doing the cancan");

		System.out.println("Testing 'Toucan doing the cancan'.deleteSubstring('polka')");
		CharList c2 = new CharList("Toucan doing the cancan");
		testMethod(1, c2, "deleteSubstring", -1, 
				new Class<?>[]{CharList.class}, new Object[]{new CharList("polka")});
		testArray(1, c2, "Toucan doing the cancan");

		System.out.println("Testing 'Toucan doing the cancan'.deleteSubstring('')");
		CharList c3 = new CharList("Toucan doing the cancan");
		testMethod(1, c3, "deleteSubstring", -1, 
				new Class<?>[]{CharList.class}, new Object[]{new CharList("")});
		testArray(1, c3, "Toucan doing the cancan");

		System.out.println("Testing 'Toucan doing the cancan'.deleteSubstring(null)");
		CharList c4 = new CharList("Toucan doing the cancan");
		testMethod(1, c4, "deleteSubstring", -1, 
				new Class<?>[]{CharList.class}, new Object[]{null});
		testArray(1, c4, "Toucan doing the cancan");

	}


	private static void testToTitleCase() {
		System.out.println("Testing 'toucan doing the can can.'.toTitleCase()");
		CharList c1 = new CharList("toucan doing the can can.");
		c1.toTitleCase();
		testArray(2, c1, "Toucan Doing The Can Can.");

		System.out.println("Testing ''.toTitleCase()");
		CharList c2 = new CharList("");
		c1.toTitleCase();
		testArray(2, c2, "");
	}
	
	public static void testCompareTo(){
		System.out.println("Testing 'Milk'.compareTo('Mouse')");
		CharList c1 = new CharList("Milk");
		CharList c2 = new CharList("Mouse");
		testMethod(2, c1, "compareTo", -1, new Class<?>[]{CharList.class}, new Object[]{c2});
		
		System.out.println("Testing 'Bread'.compareTo('Boredom')");
		CharList c3 = new CharList("Bread");
		CharList c4 = new CharList("Boredom");
		testMethod(2, c3, "compareTo", 1, new Class<?>[]{CharList.class}, new Object[]{c4});
		
		System.out.println("Testing 'Ditto'.compareTo('Ditto')");
		CharList c5 = new CharList("Ditto");
		CharList c6 = new CharList("Ditto");
		testMethod(1, c5, "compareTo", 0, new Class<?>[]{CharList.class}, new Object[]{c6});
		
	}
	


	public static String toString(char[] chars){
		String result = "[";
		int i;
		for(i=0; i<chars.length-1; i++){
			result += chars[i] + ", ";
		}
		if(i<chars.length){
			result += chars[i];
		}
		result += "]";
		return result;
	}

	public static boolean testArray(int worth, CharList object, String expected){
		Class<? extends CharList> charListReflect = object.getClass();
		try {
			boolean correctArray = true;
			Field charsReflect = charListReflect.getDeclaredField("characters");
			charsReflect.setAccessible(true);

			char[] charsGot = (char[]) charsReflect.get(object);
			char[] charsExpected = expected.toCharArray();

			if(charsGot.length == charsExpected.length){
				for(int i=0; i<charsExpected.length; i++){
					if(charsGot[i] != charsExpected[i]){
						correctArray = false;
					}
				}
			}
			else{
				correctArray = false;
			}

			System.out.println("Expected array\t" + toString(charsExpected)
			+"\nGot array\t" + toString(charsGot));

			if(correctArray){
				TestDriver.marks += worth;
				System.out.println("Passed: " + TestDriver.marks + " / "+TestDriver.total+"\n");				
				return true;
			}
			else{
				System.out.println("Failed\n");
			}

		} catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}


	public static boolean testMethod(int worth, CharList object, String methodName, 
			Object expected, Class<?>[] argTypes, Object[] args){
		Class<? extends CharList> charListReflect = object.getClass();
		try {

			Method method = charListReflect.getDeclaredMethod(methodName, argTypes);
			Object result = method.invoke(object, args);

			if(result == null && expected == null){
				System.out.println("Expected return " + expected + 
						" got " + result);
				TestDriver.marks += worth;
				System.out.println("Passed: " + TestDriver.marks + " / "+TestDriver.total+"\n");				
				return true;
			}
			else if(result != null && result.getClass().isArray()){
				if(result.getClass() == char[].class && expected.getClass() == char[].class){
					char[] expectedChars = (char[])expected;
					char[] resultChars = (char[])result;
					System.out.println("Expected return " + Arrays.toString(expectedChars) + 
							" got " + Arrays.toString(resultChars));
					if(expectedChars.length == resultChars.length){
						boolean passed = true;
						for(int i=0; i<resultChars.length; i++){
							if(resultChars[i] != expectedChars[i]){
								passed = false;
							}
						}
						if(passed){
							TestDriver.marks += worth;
							System.out.println("Passed: " + TestDriver.marks + " / "+TestDriver.total+"\n");				
							return true;
						}
						else{
							System.out.println("Failed\n");	
						}
					}
					else{
						System.out.println("Expected length " + expectedChars.length + " got " + resultChars.length);
						System.out.println("Failed\n");
					}
				}
				else{
					System.out.println("Unrecognized type returned - skipping test");
				}
			}
			else if(result != null && result.equals(expected)){
				if(result.getClass() == String.class){
					System.out.println("Expected return \"" + expected + 
							"\" got \"" + result + "\"");
				}
				else{
					System.out.println("Expected return " + expected + 
							" got " + result);
				}
				TestDriver.marks += worth;
				System.out.println("Passed: " + TestDriver.marks + " / "+TestDriver.total+"\n");				
				return true;
			}
			else{
				System.out.println("Expected return " + expected + 
						" got " + result);
				System.out.println("Failed\n");	
			}

		} catch(IndexOutOfBoundsException e){
			System.out.println("Make sure you are checking indexes are not less than zero or greater than or equal to length.");
			e.printStackTrace();
		} catch(NullPointerException e){
			System.out.println("Make sure you are checking that the arguments or your characters array are not null.");
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}

		return false;
	}

}
