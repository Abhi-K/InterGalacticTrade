package com.galaxy.trader.line.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

import com.galaxy.trader.InputProcessingException;

/**
 * @author Abhi <br>
 *         File based Traders guide which reads input from a file.
 */
public class FileBasedTradersGuideImpl extends AbstractTradersGuide {

	private Path inputPath;

	public FileBasedTradersGuideImpl(Path inputFilePath) {
		this.inputPath = inputFilePath;
	}

	@Override
	protected Iterator<String> buildInputIterator()
			throws InputProcessingException {
		return new TextFileIterator(inputPath);
	}

	/**
	 * A simple text file iterator
	 */
	static class TextFileIterator implements Iterator<String> {

		String nextLine = null;
		BufferedReader reader;

		public TextFileIterator(Path inputPath) throws InputProcessingException {
			try {
				reader = Files.newBufferedReader(inputPath,
						StandardCharsets.UTF_8);
				nextLine = reader.readLine();
			} catch (IOException e) {
				throw new InputProcessingException(e);
			}
		}

		@Override
		public boolean hasNext() {
			return nextLine != null;
		}

		@Override
		public String next() {
			String result = nextLine;
			try {
				if (nextLine != null) {
					nextLine = reader.readLine();
					if (nextLine == null) {
						reader.close();
					}
				}
				return result;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException(
					"Remove is not supported for TextFileIterator type.");
		}
	}
}
