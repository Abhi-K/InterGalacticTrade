package com.galaxy.trader.line.parser;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.galaxy.trader.InputProcessingException;
import com.galaxy.trader.TradersGuide;
import com.galaxy.trader.line.parser.util.AnswerHolder;

/**
 * Base class to be used by subclasses for implementing TradersGuide<br>
 * Sub classes should prefer extending this class over direct implementation of
 * TradersGuide.
 * 
 * @author Abhi
 */
public abstract class AbstractTradersGuide implements TradersGuide {

	protected boolean isProcessed;
	protected TradersContext inputContext;
	protected LineParser parser;

	protected AbstractTradersGuide() {
		super();
	}

	@Override
	public List<String> outputAnswers() {
		if (!isProcessed) {
			return Collections.emptyList();
		}
		return this.inputContext.outputAnswers();
	}

	/**
	 * Initialize parser chain and context
	 */
	protected void initialize() {
		this.parser = buildParser();
		this.inputContext = buildContext();
	}

	/**
	 * @return Parser to be used for parsing lines, default parser prepares
	 *         chain of parsers to be used for a line.
	 */
	protected LineParser buildParser() {
		LineParser metalCreditQuestionLineParser = 
				new MetalCreditQuestionLineParser(null);
		LineParser romanSymbolToDecimalQuestionLineParser = 
				new RomanSymbolToDecimalQuestionLineParser(metalCreditQuestionLineParser);
		LineParser metalCreditLineParser = 
				new MetalCreditLineParser(romanSymbolToDecimalQuestionLineParser);
		return new GalacticSymbolToRomanLetterLineParser(metalCreditLineParser);
	}

	/**
	 * @return TradersContext
	 */
	protected TradersContext buildContext() {
		return new DefaultContext();
	}

	@Override
	public final void process() throws InputProcessingException {
		try {
			initialize();
			final Iterator<String> lineIterator = buildInputIterator();
			while (lineIterator.hasNext()) {
				doProcessing(lineIterator.next());
			}
		} catch (Exception e) {
			throw new InputProcessingException(e);
		}
		isProcessed = true;
	}

	/**
	 * @param line
	 *            a line to be processed
	 * @throws InputProcessingException
	 *             in case of any error while parsing.
	 */
	protected void doProcessing(final String line) throws InputProcessingException {
		if (!this.parser.parse(line, inputContext)) {
			inputContext.collectAnswers(AnswerHolder.NO_ANSWER_FOUND_MESSAGE);
		}
	}

	/**
	 * @return line iterator to be processed by parsers for this TraderGuide
	 * @throws InputProcessingException
	 *             if any error while building iterator
	 */
	protected abstract Iterator<String> buildInputIterator()
			throws InputProcessingException;

}