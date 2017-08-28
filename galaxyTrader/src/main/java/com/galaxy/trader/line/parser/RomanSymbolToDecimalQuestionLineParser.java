package com.galaxy.trader.line.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.galaxy.trader.line.parser.util.AnswerHelper;
import com.galaxy.trader.line.parser.util.AnswerHolder;
import com.galaxy.trader.roman.RomanLetter;
import com.galaxy.trader.roman.RomanNumeralUtil;

/**
 * @author Abhi <br>
 *         Handles line like "how much is pish tegj glob glob ?"
 */
public class RomanSymbolToDecimalQuestionLineParser extends AbstractLineParser {

	private static final Pattern REGEX_VALID_ROMAN_TO_DECIMAL_QUESTION = Pattern
			.compile("^how much is (([A-Za-z\\s])+)\\?$");

	public RomanSymbolToDecimalQuestionLineParser(LineParser next) {
		super(next);
	}

	@Override
	protected void handle(String line, TradersContext context) {
		// Split the line based on spaces, if splits.lemgth length is n then,
		// splits[3] to splits[n-2] is Roman number
		String[] splits = line.trim().split("\\s+");
		final int length = splits.length;
		final RomanLetter[] romanLetterArray = new RomanLetter[length - 4];
		// validate Roman
		AnswerHolder result = AnswerHelper.extractRoman(3, splits,
				romanLetterArray, context);
		final String messagePart = result.getMessagePart();
		if (result.isError()) {
			context.collectAnswers(messagePart);
			return;
		}
		final long numberOfUnits = RomanNumeralUtil.getDecimalConverter()
				.convertToDecimal(romanLetterArray);
		if (numberOfUnits == 0) {
			context.collectAnswers(AnswerHolder.NO_ANSWER_FOUND_MESSAGE);
			return;
		}
		final String answer = String.format("%sis %d", messagePart,
				numberOfUnits);
		context.collectAnswers(answer);
	}

	@Override
	protected void handleError(Throwable error, String line,
			TradersContext context) {
		super.handleError(error, line, context);
		context.collectAnswers(AnswerHolder.NO_ANSWER_FOUND_MESSAGE);
	}

	@Override
	protected boolean canParse(String line) {
		Matcher matcher = REGEX_VALID_ROMAN_TO_DECIMAL_QUESTION.matcher(line);
		return matcher.matches();
	}

}
