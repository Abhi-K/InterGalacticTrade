package com.galaxy.trader.line.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.galaxy.trader.line.parser.util.AnswerHelper;
import com.galaxy.trader.line.parser.util.AnswerHolder;
import com.galaxy.trader.roman.RomanNumeralUtil;
import com.galaxy.trader.roman.RomanLetter;

/**
 * @author Abhi <br>
 *         Handles line like "how many Credits is glob prok Silver ?"
 */
public class MetalCreditQuestionLineParser extends AbstractLineParser {

	private static final Pattern REGEX_VALID_NUM_METAL_TO_DECIMAL_QUESTION = Pattern
			.compile("^how many Credits is (([A-Za-z\\s])+)\\?$");

	public MetalCreditQuestionLineParser(LineParser next) {
		super(next);
	}

	@Override
	protected void handle(String line, TradersContext context) {
		// Split the line based on spaces, if splits.lemgth length is n then,
		// splits[n-2] is Metal object,
		// splits[4] to splits[n-3] is Roman number
		final String[] splits = line.trim().split("\\s+");
		final int length = splits.length;
		final int romanNumberLength = length - 6;
		final RomanLetter[] romanLetterArray = new RomanLetter[romanNumberLength];
		// validate Roman
		AnswerHolder result = 
				AnswerHelper.extractRoman(4, splits, romanLetterArray, context);
		final String messagePart = result.getMessagePart();
		if(result.isError()){
			context.collectAnswers(messagePart);
			return;
		}
		final long numberOfUnits = 
				RomanNumeralUtil.getDecimalConverter().convertToDecimal(romanLetterArray);
		if(numberOfUnits == 0){
			context.collectAnswers(AnswerHolder.NO_ANSWER_FOUND_MESSAGE);
			return;
		}
		// validate tradeObject's credit exists
		final String tradeObject = splits[length - 2];
		final Double metalCreditPerUnit = context
				.getMetalCreditPerUnit(tradeObject);
		if(metalCreditPerUnit == null){
			context.collectAnswers(AnswerHolder.NO_ANSWER_FOUND_MESSAGE);
			return;
		}
		double totalCredit = numberOfUnits * metalCreditPerUnit;
		String answer = String.format("%s%s is %.0f Credits",
				messagePart.toString(), tradeObject, totalCredit);
		context.collectAnswers(answer);
	}

	@Override
	protected boolean canParse(String line) {
		Matcher matcher = REGEX_VALID_NUM_METAL_TO_DECIMAL_QUESTION
				.matcher(line);
		return matcher.matches();
	}

}
