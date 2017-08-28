package com.galaxy.trader.line.parser;

/**
 * Interface to parse individual lines
 * 
 * @author Abhi
 */
public interface LineParser {

	/**
	 * @param line
	 *            an input line as String
	 * @param context
	 *            TradersContext to be used for add/get line for info or answer
	 * @return true if parsed by this parser itself, false otherwise
	 */
	boolean parse(String line, TradersContext context);
}
