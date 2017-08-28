package com.galaxy.trader.line.parser;

import java.util.List;

import com.galaxy.trader.roman.RomanLetter;

/**
 * <p>
 * Provides interface to add/get trading information to be used for answering
 * questions
 * 
 * @author Abhi
 */
public interface TradersContext {

	/**
	 * @param symbol
	 *            galactic symbol
	 * @param digit
	 *            roman digit
	 */
	void addSymbolInfo(String symbol, RomanLetter digit);

	/**
	 * @param tradeObject
	 *            Object to be traded
	 * @param valuePerUnit
	 *            credit per unit
	 */
	void addCreditInfo(String tradeObject, double valuePerUnit);

	/**
	 * @param galacticSymbol
	 * @return Romanletter for given galacticSymbol
	 */
	RomanLetter getRomanLetter(String galacticSymbol);

	/**
	 * @param tradeObject
	 * @return tradeObject's Credit per unit
	 */
	double getMetalCreditPerUnit(String tradeObject);

	/**
	 * Stores questions's answer
	 * 
	 * @param answer
	 *            to be stored for later retrieval
	 */
	void collectAnswers(String answer);

	/**
	 * @return all answers collected.
	 */
	List<String> outputAnswers();

	/**
	 * Capture error while line parsing
	 * 
	 * @param error
	 * @param line
	 */
	void outputError(Throwable error, String line);
}
