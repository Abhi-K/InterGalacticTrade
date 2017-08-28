package com.galaxy.trader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.galaxy.trader.line.parser.FileBasedTradersGuideImpl;

/**
 * @author Abhishek Kumar
 *         <p>
 *         This class is the entry point of the application.<br>
 *         </p>
 * 
 */
public class Launcher {

	/**
	 * @param args
	 *            absolute path of input file
	 * @throws InputIntializationException
	 * @throws InputProcessingException
	 */
	public static void main(String[] args) throws InputProcessingException {
		if (args.length == 0) {
			System.out
					.println("Please provide the missing input file path argument.");
			System.exit(-1);
		}
		String inputFilePath = args[0];
		if (inputFilePath == null || "".equals(inputFilePath.trim())) {
			System.out
					.println("Please provide a valid input file path.");
			System.exit(-1);
		}
		inputFilePath = inputFilePath.trim();
		Path inputFile = Paths.get(inputFilePath);
		if (Files.notExists(inputFile)) {
			System.out.println("Given input file does not exist.");
			System.exit(-1);
		}
		TradersGuide tradersGuide = new FileBasedTradersGuideImpl(inputFile);
		tradersGuide.process();
		List<String> answersList = tradersGuide.outputAnswers();
		for (String answer : answersList) {
			System.out.println(answer);
		}
	}

}
