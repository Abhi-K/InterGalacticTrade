package com.galaxy.trader;

import java.util.List;

/**
 * Interface to be used by client for processing and getting answers
 * 
 * @author Abhi
 */
public interface TradersGuide {

	/**
	 * @return a list of processed accumulated answerss
	 */
	List<String> outputAnswers();

	/**
	 * process the input
	 * 
	 * @throws InputProcessingException
	 *             if any error happens while processing
	 */
	void process() throws InputProcessingException;
}
