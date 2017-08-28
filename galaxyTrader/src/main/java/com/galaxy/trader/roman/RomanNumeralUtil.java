package com.galaxy.trader.roman;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class provides Simple implementation of RomanToDecimalConverter to
 * convert Roman to Decimal numerals
 * </p>
 * 
 * @author Abhishek Kumar
 * 
 */
public class RomanNumeralUtil {

	private static final RomanToDecimalConverter INSTANCE = new SimpleConverter();

	private RomanNumeralUtil() {
	}

	/**
	 * @return A RomanToDecimalConverter
	 */
	public static RomanToDecimalConverter getDecimalConverter() {
		return INSTANCE;
	}

	/**
	 * A simple Roman to decimal converter
	 * 
	 * @author Abhi
	 * 
	 */
	static class SimpleConverter implements RomanToDecimalConverter {

		/**
		 * @param romanLetterArray
		 *            Array of valid Roman letters
		 * @return decimal integer
		 * 
		 * @throws IllegalArgumentException
		 *             If romanLetterArray is empty or null or is Roman letters
		 *             are malformed
		 */
		public long convertToDecimal(final RomanLetter[] romanLetterArray) {
			if (romanLetterArray == null || romanLetterArray.length == 0) {
				throw new IllegalArgumentException(
						"Input RomanLetterArray is null or empty "
								+ romanLetterArray);
			}
			long total = 0;
			int currLetterCount = 1;
			int lastCalculatedValue = Integer.MAX_VALUE;
			final Map<RomanLetter, Integer> countMapForNonRepeatableLetter = new HashMap<>(
					3);
			final int length = romanLetterArray.length;
			for (int i = 0; i < length; i++) {
				int currentLetterVal = romanLetterArray[i].getValue();
				if (i + 1 < length) {
					int nextLetterVal = romanLetterArray[i + 1].getValue();
					if (nextLetterVal <= currentLetterVal) {
						if (romanLetterArray[i] == romanLetterArray[i + 1]) {
							currLetterCount++;
						} else {
							currLetterCount = 1;
						}
						validateLetterRepeatation(
								countMapForNonRepeatableLetter,
								romanLetterArray[i], currLetterCount);
						validateDecreasingSequence(lastCalculatedValue,
								currentLetterVal);
						total = total + currentLetterVal;
						lastCalculatedValue = currentLetterVal;
					} else {
						currLetterCount = 1;
						validateSubtraction(romanLetterArray[i],
								romanLetterArray[i + 1]);
						validateLetterRepeatation(
								countMapForNonRepeatableLetter,
								romanLetterArray[i], currLetterCount);
						final int currVal = nextLetterVal - currentLetterVal;
						validateDecreasingSequence(lastCalculatedValue, currVal);
						total = total + currVal;
						lastCalculatedValue = currVal;
						i++;
						// jumping should take care of previous letter count
						if (i + 1 < length
								&& romanLetterArray[i] == romanLetterArray[i + 1]) {
							currLetterCount++;
						}
					}
				} else {
					validateLetterRepeatation(countMapForNonRepeatableLetter,
							romanLetterArray[i], currLetterCount);
					validateDecreasingSequence(lastCalculatedValue,
							currentLetterVal);
					total = total + currentLetterVal;
					lastCalculatedValue = currentLetterVal;
				}
			}
			return total;
		}

		private void validateDecreasingSequence(int lastCalculatedValue,
				int currentLetterVal) {
			if (lastCalculatedValue < currentLetterVal) {
				throw new IllegalArgumentException(
						"Incorrect increasing roman letter value sequence");
			}
		}

		private void validateSubtraction(RomanLetter previousLetter,
				RomanLetter currRomanLetter) {
			switch (previousLetter) {
				case I :
					if (currRomanLetter != RomanLetter.V
							&& currRomanLetter != RomanLetter.X) {
						throw new IllegalArgumentException(
								"Illegal occurence of " + previousLetter
										+ " before " + currRomanLetter);
					}
					break;
				case X :
					if (currRomanLetter != RomanLetter.L
							&& currRomanLetter != RomanLetter.C) {
						throw new IllegalArgumentException(
								"Illegal occurence of " + previousLetter
										+ " before " + currRomanLetter);
					}
					break;
				case C :
					if (currRomanLetter != RomanLetter.D
							&& currRomanLetter != RomanLetter.M) {
						throw new IllegalArgumentException(
								"Illegal occurence of " + previousLetter
										+ " before " + currRomanLetter);
					}
					break;
				case V :
				case L :
				case D :
					throw new IllegalArgumentException(previousLetter
							+ " cannot occur before " + currRomanLetter);
				default :
					break;
			}

		}

		private void validateLetterRepeatation(
				Map<RomanLetter, Integer> countMapForNonRepeatableLetter,
				RomanLetter romanLetter, int currLetterCount) {
			switch (romanLetter) {
				case I :
				case X :
				case C :
				case M :
					if (currLetterCount > 3) {
						throw new IllegalArgumentException(romanLetter
								+ " exceeding maximum allowed repeatation of 3");
					}
					break;
				case V :
				case L :
				case D :
					if (currLetterCount > 1) {
						throw new IllegalArgumentException(romanLetter
								+ " exceeding maximum allowed repeatation of 1");
					}
					if (countMapForNonRepeatableLetter.get(romanLetter) == null) {
						countMapForNonRepeatableLetter.put(romanLetter, 1);
					} else {
						throw new IllegalArgumentException(romanLetter
								+ " exceeding maximum allowed repeatation of 1");
					}
					break;
				default :
					break;
			}
		}
	}
}
