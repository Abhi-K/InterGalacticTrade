package com.galaxy.trader.line.parser.util;

import com.galaxy.trader.line.parser.TradersContext;
import com.galaxy.trader.roman.RomanLetter;

public class AnswerHelper {
	private AnswerHelper() {
	}
	
	public static AnswerHolder extractRoman(final int start, final String[] splits,
			final RomanLetter[] romanLetterArray, final TradersContext context) {
		final StringBuilder galacticSymbols = new StringBuilder();
		final int end = romanLetterArray.length + start;
		for (int i = start; i < end; i++) {
			final String galacticSymbol = splits[i];
			final RomanLetter letter = context.getRomanLetter(galacticSymbol);
			if (letter == null) {
				// letter not found error
				return new AnswerHolder(true);
			}
			romanLetterArray[i - start] = letter;
			galacticSymbols.append(galacticSymbol + " ");
		}
		return new AnswerHolder(false, galacticSymbols.toString());
	}
}
