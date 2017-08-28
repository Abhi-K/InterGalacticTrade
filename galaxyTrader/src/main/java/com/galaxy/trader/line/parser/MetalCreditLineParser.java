package com.galaxy.trader.line.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.galaxy.trader.line.parser.util.AnswerHelper;
import com.galaxy.trader.line.parser.util.AnswerHolder;
import com.galaxy.trader.roman.RomanLetter;
import com.galaxy.trader.roman.RomanNumeralUtil;

/**
 * Handles line like "glob glob Silver is 34 Credits" <br>
 * All parsing is based on exact format as per sample and is case-sensitive
 * 
 * @author Abhi
 */
public class MetalCreditLineParser extends AbstractLineParser {

	private static final Pattern REGEX_VALID_METAL_CREDIT = Pattern
			.compile("^([A-Za-z]+)([A-Za-z\\s]*) is (\\d+) (Credits)$");

	public MetalCreditLineParser(LineParser next) {
		super(next);
	}

	@Override
	protected void handle(String line, TradersContext context) {
		// Split the line based on spaces, if splits.lemgth length is n then,
		// splits[n-2] is credit value,
		// splits[n-4] is Metal Object,
		// splits[0] to splits[n-3] is Roman number
		final String[] splits = line.trim().split("\\s+");
		final int length = splits.length;
		final int romanNumberLength = length - 4;
		final RomanLetter[] romanLetterArray = new RomanLetter[romanNumberLength];
		// validate Roman
		final AnswerHolder result = AnswerHelper.extractRoman(0, splits,
				romanLetterArray, context);
		if (result.isError()) {
			throw new IllegalArgumentException("Illegal Roman letter found");
		}
		final long numberOfUnits = RomanNumeralUtil.getDecimalConverter()
				.convertToDecimal(romanLetterArray);
		if (numberOfUnits == 0) {
			throw new IllegalArgumentException("Illegal Roman value specified");
		}
		// add credit info
		double credits = Double.parseDouble(splits[length - 2]);
		double valuePerUnit = credits / numberOfUnits;
		context.addCreditInfo(splits[length - 4], valuePerUnit);
	}

	@Override
	protected boolean canParse(String line) {
		Matcher matcher = REGEX_VALID_METAL_CREDIT.matcher(line);
		return matcher.matches();
	}

}
