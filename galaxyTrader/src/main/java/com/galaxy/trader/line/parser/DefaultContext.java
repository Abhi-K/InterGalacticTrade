package com.galaxy.trader.line.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.galaxy.trader.line.parser.util.ParseErrorHolder;
import com.galaxy.trader.roman.RomanLetter;

/**
 * Default implementation that stores parse information.
 * 
 * @author Abhi
 * 
 */
public class DefaultContext implements TradersContext {

	private Map<String, RomanLetter> symbolToRomanMap = new HashMap<>();
	private Map<String, Double> tradeObjectToPerUnitCreditMap = new HashMap<>();
	private List<String> answers = new ArrayList<>();
	private List<ParseErrorHolder> lineParseErrors = new ArrayList<>();

	public DefaultContext() {
	}

	@Override
	public void addSymbolInfo(String galacticSymbol, RomanLetter letter) {
		symbolToRomanMap.put(galacticSymbol, letter);
	}

	@Override
	public void addCreditInfo(String tradeObject, double valuePerUnit) {
		tradeObjectToPerUnitCreditMap.put(tradeObject, valuePerUnit);
	}

	@Override
	public RomanLetter getRomanLetter(String galacticSymbol) {
		return symbolToRomanMap.get(galacticSymbol);
	}

	@Override
	public double getMetalCreditPerUnit(String tradeObject) {
		return tradeObjectToPerUnitCreditMap.get(tradeObject);
	}

	@Override
	public void collectAnswers(String anAnswer) {
		answers.add(anAnswer);
	}

	@Override
	public List<String> outputAnswers() {
		return Collections.unmodifiableList(this.answers);
	}

	@Override
	public void outputError(Throwable error, String line) {
		lineParseErrors.add(new ParseErrorHolder(error, line));
	}

}
