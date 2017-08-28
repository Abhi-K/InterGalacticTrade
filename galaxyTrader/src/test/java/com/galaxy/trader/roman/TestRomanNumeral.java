package com.galaxy.trader.roman;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Test class for RomanNumeral#convertToDecimal(RomanNumeral.RomanLetter[])
 * 
 * @author Abhi <br>
 */
public class TestRomanNumeral {

	@Test
	public final void testConvertToDecimalSuccess() {
		assertEquals(convertToDecimal("I"), 1);
		assertEquals(convertToDecimal("II"), 2);
		assertEquals(convertToDecimal("III"), 3);
		assertEquals(convertToDecimal("IV"), 4);
		assertEquals(convertToDecimal("V"), 5);
		assertEquals(convertToDecimal("VI"), 6);
		assertEquals(convertToDecimal("VII"), 7);
		assertEquals(convertToDecimal("VIII"), 8);
		assertEquals(convertToDecimal("IX"), 9);
		assertEquals(convertToDecimal("X"), 10);
		assertEquals(convertToDecimal("XI"), 11);
		assertEquals(convertToDecimal("XII"), 12);
		assertEquals(convertToDecimal("XIII"), 13);
		assertEquals(convertToDecimal("XIV"), 14);
		assertEquals(convertToDecimal("XV"), 15);
		assertEquals(convertToDecimal("XVI"), 16);
		assertEquals(convertToDecimal("XVII"), 17);
		assertEquals(convertToDecimal("XVIII"), 18);
		assertEquals(convertToDecimal("XIX"), 19);
		assertEquals(convertToDecimal("XX"), 20);
		assertEquals(convertToDecimal("XXI"), 21);
		assertEquals(convertToDecimal("XXII"), 22);
		assertEquals(convertToDecimal("XXIII"), 23);
		assertEquals(convertToDecimal("XXIV"), 24);
		assertEquals(convertToDecimal("XXV"), 25);
		assertEquals(convertToDecimal("XXVI"), 26);
		assertEquals(convertToDecimal("XXVII"), 27);
		assertEquals(convertToDecimal("XXVIII"), 28);
		assertEquals(convertToDecimal("XXIX"), 29);
		assertEquals(convertToDecimal("XXX"), 30);
		assertEquals(convertToDecimal("XXXI"), 31);
		assertEquals(convertToDecimal("XXXII"), 32);
		assertEquals(convertToDecimal("XXXIII"), 33);
		assertEquals(convertToDecimal("XXXIV"), 34);
		assertEquals(convertToDecimal("XXXV"), 35);
		assertEquals(convertToDecimal("XXXVI"), 36);
		assertEquals(convertToDecimal("XXXVII"), 37);
		assertEquals(convertToDecimal("XXXVIII"), 38);
		assertEquals(convertToDecimal("XXXIX"), 39);
		assertEquals(convertToDecimal("XL"), 40);
		assertEquals(convertToDecimal("XLI"), 41);
		assertEquals(convertToDecimal("XLII"), 42);
		assertEquals(convertToDecimal("XLIII"), 43);
		assertEquals(convertToDecimal("XLIV"), 44);
		assertEquals(convertToDecimal("XLV"), 45);
		assertEquals(convertToDecimal("XLVI"), 46);
		assertEquals(convertToDecimal("XLVII"), 47);
		assertEquals(convertToDecimal("XLVIII"), 48);
		assertEquals(convertToDecimal("XLIX"), 49);
		assertEquals(convertToDecimal("L"), 50);
		assertEquals(convertToDecimal("LI"), 51);
		assertEquals(convertToDecimal("LII"), 52);
		assertEquals(convertToDecimal("LIII"), 53);
		assertEquals(convertToDecimal("LIV"), 54);
		assertEquals(convertToDecimal("LV"), 55);
		assertEquals(convertToDecimal("LVI"), 56);
		assertEquals(convertToDecimal("LVII"), 57);
		assertEquals(convertToDecimal("LVIII"), 58);
		assertEquals(convertToDecimal("LIX"), 59);
		assertEquals(convertToDecimal("LX"), 60);
		assertEquals(convertToDecimal("LXI"), 61);
		assertEquals(convertToDecimal("LXII"), 62);
		assertEquals(convertToDecimal("LXIII"), 63);
		assertEquals(convertToDecimal("LXIV"), 64);
		assertEquals(convertToDecimal("LXV"), 65);
		assertEquals(convertToDecimal("LXVI"), 66);
		assertEquals(convertToDecimal("LXVII"), 67);
		assertEquals(convertToDecimal("LXVIII"), 68);
		assertEquals(convertToDecimal("LXIX"), 69);
		assertEquals(convertToDecimal("LXX"), 70);
		assertEquals(convertToDecimal("LXXI"), 71);
		assertEquals(convertToDecimal("LXXII"), 72);
		assertEquals(convertToDecimal("LXXIII"), 73);
		assertEquals(convertToDecimal("LXXIV"), 74);
		assertEquals(convertToDecimal("LXXV"), 75);
		assertEquals(convertToDecimal("LXXVI"), 76);
		assertEquals(convertToDecimal("LXXVII"), 77);
		assertEquals(convertToDecimal("LXXVIII"), 78);
		assertEquals(convertToDecimal("LXXIX"), 79);
		assertEquals(convertToDecimal("LXXX"), 80);
		assertEquals(convertToDecimal("LXXXI"), 81);
		assertEquals(convertToDecimal("LXXXII"), 82);
		assertEquals(convertToDecimal("LXXXIII"), 83);
		assertEquals(convertToDecimal("LXXXIV"), 84);
		assertEquals(convertToDecimal("LXXXV"), 85);
		assertEquals(convertToDecimal("LXXXVI"), 86);
		assertEquals(convertToDecimal("LXXXVII"), 87);
		assertEquals(convertToDecimal("LXXXVIII"), 88);
		assertEquals(convertToDecimal("LXXXIX"), 89);
		assertEquals(convertToDecimal("XC"), 90);
		assertEquals(convertToDecimal("XCI"), 91);
		assertEquals(convertToDecimal("XCII"), 92);
		assertEquals(convertToDecimal("XCIII"), 93);
		assertEquals(convertToDecimal("XCIV"), 94);
		assertEquals(convertToDecimal("XCV"), 95);
		assertEquals(convertToDecimal("XCVI"), 96);
		assertEquals(convertToDecimal("XCVII"), 97);
		assertEquals(convertToDecimal("XCVIII"), 98);
		assertEquals(convertToDecimal("XCIX"), 99);
		assertEquals(convertToDecimal("C"), 100);
		assertEquals(convertToDecimal("DI"), 501);
		assertEquals(convertToDecimal("DL"), 550);
		assertEquals(convertToDecimal("DXXX"), 530);
		assertEquals(convertToDecimal("DCCVII"), 707);
		assertEquals(convertToDecimal("DCCCXC"), 890);
		assertEquals(convertToDecimal("MD"), 1500);
		assertEquals(convertToDecimal("MDCCC"), 1800);
		assertEquals(convertToDecimal("CM"), 900);
	}

	@Test
	public final void testConvertFailureByRepeatationRuleForNonRepeatable() {
		try {
			convertToDecimal("MMDD");
			fail("Conversion should fail");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testConvertFailureByRepeatationRuleForRepeatableAtEnd() {
		try {
			convertToDecimal("MCMIIII");
			fail("Conversion should fail");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testConvertFailureByRepeatationRuleForRepeatableAtMiddle() {
		try {
			convertToDecimal("MCMIXXXXIII");
			fail("Conversion should fail ");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testConvertFailureBySubtractionRule() {
		try {
			convertToDecimal("XIL");
			fail("Conversion should fail ");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	public final void testConvertFailureByDecreasingValueRule() {
		try {
			convertToDecimal("IVX");
			fail("Conversion should fail");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	private RomanLetter[] convertToValidRomanLetterArray(String romanDigitToTest) {
		final char[] charArray = romanDigitToTest.toCharArray();
		final List<RomanLetter> listOfChar = new ArrayList<>(charArray.length);
		for (Character charSymbol : charArray) {
			final RomanLetter letter = RomanLetter.fromCharacter(charSymbol);
			if (letter == null) {
				throw new IllegalArgumentException("Invalid Roman Letter "
						+ charSymbol);
			}
			listOfChar.add(letter);
		}
		return listOfChar.toArray(new RomanLetter[charArray.length]);
	}

	private long convertToDecimal(String romanDigitToTest) {
		return RomanNumeralUtil.getDecimalConverter().convertToDecimal(
				convertToValidRomanLetterArray(romanDigitToTest));
	}
}
