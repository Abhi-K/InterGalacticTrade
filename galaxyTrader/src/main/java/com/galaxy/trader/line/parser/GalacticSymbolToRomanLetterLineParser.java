package com.galaxy.trader.line.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.galaxy.trader.roman.RomanLetter;

/**
 * <br>
 * Handles line like "glob is I", Roman letters are assumed to be case sensitive
 * 
 * @author Abhi
 */
public class GalacticSymbolToRomanLetterLineParser extends AbstractLineParser {

	private static final Pattern REGEX_VALID_SYMBOLE_TO_ROMAN = Pattern
			.compile("^([A-Za-z]+) is ([I|V|X|L|C|D|M])$");

	public GalacticSymbolToRomanLetterLineParser(LineParser next) {
		super(next);
	}

	@Override
	protected void handle(String line, TradersContext context) {
		// Split the line based on spaces
		// The first part splits[0] is galactic symbol and second part
		// splits[2] is the corresponding Roman value
		final String[] splits = line.trim().split("\\s+");
		context.addSymbolInfo(splits[0],
				RomanLetter.fromCharacter(splits[2].charAt(0)));
	}

	@Override
	protected boolean canParse(String line) {
		Matcher matcher = REGEX_VALID_SYMBOLE_TO_ROMAN.matcher(line);
		return matcher.matches();
	}

}
