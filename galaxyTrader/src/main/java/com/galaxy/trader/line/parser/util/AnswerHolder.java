package com.galaxy.trader.line.parser.util;

public class AnswerHolder {
	public static final String NO_ANSWER_FOUND_MESSAGE = 
			"I have no idea what you are talking about";
	
	private boolean error;
	private String messagePart;
	
	/**
	 * @param error
	 * @param messagePart
	 */
	AnswerHolder(boolean hasError, String messagePart) {
		this.error = hasError;
		this.messagePart = messagePart;
	}

	/**
	 * @param hasError
	 */
	public AnswerHolder(boolean hasError) {
		this(hasError, NO_ANSWER_FOUND_MESSAGE);
	}

	/**
	 * @return the error
	 */
	public boolean isError() {
		return error;
	}

	/**
	 * @return the messagePart
	 */
	public String getMessagePart() {
		return messagePart;
	}
	
}
