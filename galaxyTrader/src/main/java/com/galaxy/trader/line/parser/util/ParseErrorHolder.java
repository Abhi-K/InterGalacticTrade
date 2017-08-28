package com.galaxy.trader.line.parser.util;

/**
 * Holds error messages occurred for line while parsing. <br>
 * <b>Note:</b> it does not override equals, hashcode.
 * 
 * @author Abhi
 * 
 */
public class ParseErrorHolder {

	private final String line;
	private final Throwable error;

	public ParseErrorHolder(Throwable error, String line) {
		this.error = error;
		this.line = line;
	}

	/**
	 * @return the line
	 */
	public String getLine() {
		return line;
	}

	/**
	 * @return the error
	 */
	public Throwable getError() {
		return error;
	}

	@Override
	public String toString() {
		return "ParseErrorHolder [line=" + line + ", error=" + error + "]";
	}

}
