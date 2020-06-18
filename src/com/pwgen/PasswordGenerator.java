package com.pwgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class PasswordGenerator {
	private static int randomNumberGenerator(int min, int max) {
		return (ThreadLocalRandom.current().nextInt(min, max + 1));
	}

	private static String shuffleString(String input) {
		List<Character> characters = new ArrayList<Character>();
		for (char c : input.toCharArray()) {
			characters.add(c);
		}
		StringBuilder output = new StringBuilder(input.length());
		while (characters.size() != 0) {
			int randPicker = (int) (Math.random() * characters.size());
			output.append(characters.remove(randPicker));
		}
		return (output.toString());
	}

	private static String charValueGenerator(int[] tupleArr, int tupleArrIndex, int minAscii, int maxAscii) {
		String password = "";
		for (int i = 0; i < tupleArr[tupleArrIndex]; i++) {
			String pwCharacter = String.valueOf(Character.toChars(randomNumberGenerator(minAscii, maxAscii)));
			password = password + pwCharacter;
		}
		return password;
	}

	private static int[] passwordTupleGenerator(int passwordLength, int tupleLength) {
		int[] myArr = new int[tupleLength];
		int i = 0;
		int tupleSum = 0;
		int myNewPW = 0;
		int myRange = 0;

		while (true) {
			myNewPW = passwordLength - tupleSum;
			if (tupleLength == 1) {
				myArr[i] = myNewPW;
				break;
			}
			myRange = myNewPW - tupleLength + 1;
			myArr[i] = randomNumberGenerator(1, myRange);
			tupleSum = tupleSum + myArr[i];
			i++;
			tupleLength--;
		}
		return myArr;
	}

	public static String passwordGenerator(int pwLength, String choice) {
		String password = "";
		int[] tupleArr = passwordTupleGenerator(pwLength, choice.length());
		int tupleArrIndex = 0;

		if (choice.contains("1")) {
			password = password + charValueGenerator(tupleArr, tupleArrIndex, 65, 90);
			tupleArrIndex++;
		}

		if (choice.contains("2")) {
			password = password + charValueGenerator(tupleArr, tupleArrIndex, 97, 122);
			tupleArrIndex++;
		}

		if (choice.contains("3")) {
			password = password + charValueGenerator(tupleArr, tupleArrIndex, 48, 57);
			tupleArrIndex++;
		}

		if (choice.contains("4")) {
			password = password + charValueGenerator(tupleArr, tupleArrIndex, 33, 47);
			tupleArrIndex++;
		}

		return shuffleString(password);
	}

	public static void main(String[] args) {
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("\n\n**************************************************");
			int passwordLength = 8;
			do {
				System.out.print("password length (8 or more) >> ");
				while (!sc.hasNextInt()) {
					System.out.print("length must be a number (8 or more) >> ");
					sc.next();
				}
				passwordLength = sc.nextInt();
			} while (passwordLength < 8);

			sc.nextLine();
			System.out.println("\n1 for uppercase");
			System.out.println("2 for lowercase");
			System.out.println("3 for numbers");
			System.out.println("4 for symbols");
			System.out.println("[e to end program]");
			System.out.print("\nyour choice >> ");
			String choice = sc.nextLine();
			if (choice.contains("e")) {
				System.out.println("goodbye!");
				break;
			}
			if (choice.length() <= 4) {
				System.out.print("your password: " + passwordGenerator(passwordLength, choice));
			} else {
				System.out.println("wrong input, please try again!");
			}
		}
	}
}
