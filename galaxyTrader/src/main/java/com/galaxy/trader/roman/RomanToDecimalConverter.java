package com.galaxy.trader.roman;

/**
 * Interface to convert Roman to decimal.
 * 
 * @author Abhi
 * 
 */
public interface RomanToDecimalConverter {
	/**
	 * @param romanLetterArray
	 *            An array of valid Roman letters
	 * @return decimal value corresponding to input romanLetterArray.
	 * @throws IllegalArgumentException
	 *             in case of invalid Roman letter sequence.
	 */
	public long convertToDecimal(RomanLetter[] romanLetterArray);
}
