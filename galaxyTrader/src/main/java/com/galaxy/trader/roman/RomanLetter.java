package com.galaxy.trader.roman;

import java.util.HashMap;
import java.util.Map;

public enum RomanLetter {

	I(1), V(5), X(10), L(50), C(100), D(500), M(1000);

	private static final Map<Character, RomanLetter> charToEnum = new HashMap<Character, RomanLetter>();

	// Initialize map from constant name to enum constant
	static {
		for (RomanLetter letter : values())
			charToEnum.put(letter.toString().charAt(0), letter);
	}
	private int value;

	RomanLetter(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	/**
	 * @param charSymbol
	 * @return RomanLetter for char if valid, null otherwise
	 */
	public static RomanLetter fromCharacter(Character charSymbol) {
		return charToEnum.get(charSymbol);
	}
}
