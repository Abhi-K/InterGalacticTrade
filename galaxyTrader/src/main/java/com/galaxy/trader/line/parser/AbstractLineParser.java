package com.galaxy.trader.line.parser;

/**
 * Base class to be used for each line parser<br>
 * Sub classes should prefer extending this class over direct implementation of
 * LineParser.<br>Any exceptions thrown by handle method is captured and processing
 * continues.
 * 
 * @author Abhi
 * 
 */
public abstract class AbstractLineParser implements LineParser {

	/**
	 * A parser that always returns false, to be used at end of parser chain.
	 */
	public static final LineParser NULL_PARSER = new LineParser() {

		@Override
		public boolean parse(String line, TradersContext inputHandler) {
			return false;
		}

	};

	// next parser in chain of parsers
	private LineParser nextLineParser;

	/**
	 * if next parser in chain is passed s null then
	 * 
	 * @param next
	 *            parser in chain
	 */
	public AbstractLineParser(LineParser next) {
		if (next == null) {
			this.nextLineParser = NULL_PARSER;
		} else {
			this.nextLineParser = next;
		}
	}

	@Override
	public boolean parse(String line, TradersContext context) {
		if (this.canParse(line)) {
			try {
				handle(line, context);
			} catch (Throwable e) {
				handleError(e, line, context);
			}
			return true;
		}
		return this.nextLineParser.parse(line, context);
	}

	/**
	 * Handles error occurred while line parsing.<br>
	 * Default implementation passes error error to TradersContext.
	 * 
	 * @param error
	 * @param line
	 * @param context
	 */
	protected void handleError(Throwable error, String line,
			TradersContext context) {
		context.outputError(error, line);
	}

	/**
	 * Handles the actual parsing logic to be used by subclasses.
	 * 
	 * @param line
	 *            to be parsed
	 * @param context
	 *            TradersContext
	 */
	protected abstract void handle(String line, TradersContext context);

	/**
	 * @param line
	 *            line to be checked for parsing
	 * @return true if this parser can handle this given line, false otherwise
	 */
	protected abstract boolean canParse(String line);
}
