package com.galaxy.trader;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.galaxy.trader.line.parser.AbstractTradersGuide;
import com.galaxy.trader.line.parser.FileBasedTradersGuideImpl;
import com.galaxy.trader.line.parser.util.AnswerHolder;

public class TestTradersGuide {

	private static List<String> expectedSampleAnswers = null;

	@BeforeClass
	public static void setupBeforeClass() {
		// make expected answers
		expectedSampleAnswers = new ArrayList<>();
		expectedSampleAnswers.add("pish tegj glob glob is 42");
		expectedSampleAnswers.add("glob prok Silver is 68 Credits");
		expectedSampleAnswers.add("glob prok Gold is 57800 Credits");
		expectedSampleAnswers.add("glob prok Iron is 782 Credits");
		expectedSampleAnswers.add("I have no idea what you are talking about");
	}

	@Test
	public final void testSampleInMemoryInput() {
		// make sample info/question lists
		final List<String> input = new ArrayList<>();
		input.add("glob is I");
		input.add("prok is V");
		input.add("pish is X");
		input.add("tegj is L");
		input.add("glob glob Silver is 34 Credits");
		input.add("glob prok Gold is 57800 Credits");
		input.add("pish pish Iron is 3910 Credits");
		input.add("how much is pish tegj glob glob ?");
		input.add("how many Credits is glob prok Silver ?");
		input.add("how many Credits is glob prok Gold ?");
		input.add("how many Credits is glob prok Iron ?");
		input.add("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");

		// make a traders guide and verify answers
		TradersGuide traderGuide = new AbstractTradersGuide() {

			@Override
			protected Iterator<String> buildInputIterator()
					throws InputProcessingException {
				return input.iterator();
			}
		};
		try {
			traderGuide.process();
			assertTrue("Answers not matching",
					expectedSampleAnswers.equals(traderGuide.outputAnswers()));
		} catch (InputProcessingException e) {
			fail("Unexpected failure while processing input");
		}
	}

	@Test
	public final void testSampleFileInput() throws URISyntaxException {
		// make a traders guide and verify answers
		TradersGuide traderGuide = new FileBasedTradersGuideImpl(Paths.get(this
				.getClass().getResource("/sample.txt").toURI()));
		try {
			traderGuide.process();
			assertTrue("Answers not matching",
					expectedSampleAnswers.equals(traderGuide.outputAnswers()));
		} catch (InputProcessingException e) {
			fail("Unexpected failure while processing input");
		}
	}

	@Test
	public final void testFileInputMixedOerdered() throws URISyntaxException {
		// make a traders guide and verify answers
		TradersGuide traderGuide = new FileBasedTradersGuideImpl(Paths.get(this
				.getClass().getResource("/mixOrdered.txt").toURI()));
		List<String> expectedAnswers = new ArrayList<>();
		expectedAnswers.add("pish prok glob is 16");
		expectedAnswers.add("pish tegj glob glob is 42");
		expectedAnswers.add("glob prok Gold is 57800 Credits");
		try {
			traderGuide.process();
			assertTrue("Answers not matching",
					expectedAnswers.equals(traderGuide.outputAnswers()));
		} catch (InputProcessingException e) {
			fail("Unexpected failure while processing input");
		}
	}

	@Test
	public final void testFileInputUnordered() throws URISyntaxException {
		// make a traders guide and verify answers
		TradersGuide traderGuide = new FileBasedTradersGuideImpl(Paths.get(this
				.getClass().getResource("/unordered.txt").toURI()));
		List<String> expectedAnswers = new ArrayList<>();
		expectedAnswers.add(AnswerHolder.NO_ANSWER_FOUND_MESSAGE);
		expectedAnswers.add("prok is 1");
		expectedAnswers.add("glob is 5");
		try {
			traderGuide.process();
			assertTrue("Answers not matching",
					expectedAnswers.equals(traderGuide.outputAnswers()));
		} catch (InputProcessingException e) {
			fail("Unexpected failure while processing input");
		}
	}

	@Test
	public final void testFileInputWrongRomanLetterSequence()
			throws URISyntaxException {
		// make a traders guide and verify answers
		TradersGuide traderGuide = new FileBasedTradersGuideImpl(Paths.get(this
				.getClass().getResource("/incorrectRomanFormat.txt").toURI()));
		List<String> expectedAnswers = new ArrayList<>();
		expectedAnswers.add(AnswerHolder.NO_ANSWER_FOUND_MESSAGE);
		expectedAnswers.add("pish glob prok is 16");
		expectedAnswers.add(AnswerHolder.NO_ANSWER_FOUND_MESSAGE);

		try {
			traderGuide.process();
			assertTrue("Answers not matching",
					expectedAnswers.equals(traderGuide.outputAnswers()));
		} catch (InputProcessingException e) {
			fail("Unexpected failure while processing input");
		}
	}

	@Test
	public final void testFileInputWrongRomanLetterSymbol()
			throws URISyntaxException {
		// make a traders guide and verify answers
		TradersGuide traderGuide = new FileBasedTradersGuideImpl(Paths.get(this
				.getClass().getResource("/wrongRomanLetter.txt").toURI()));
		List<String> expectedAnswers = new ArrayList<>();
		expectedAnswers.add(AnswerHolder.NO_ANSWER_FOUND_MESSAGE);
		expectedAnswers.add("glob prok is 6");
		try {
			traderGuide.process();
			assertTrue("Answers not matching",
					expectedAnswers.equals(traderGuide.outputAnswers()));
		} catch (InputProcessingException e) {
			fail("Unexpected failure while processing input");
		}
	}

	@Test
	public final void testFileInputWrongCreditQuestion()
			throws URISyntaxException {
		// make a traders guide and verify answers
		TradersGuide traderGuide = new FileBasedTradersGuideImpl(Paths.get(this
				.getClass().getResource("/wrongCreditQuestion.txt").toURI()));
		List<String> expectedAnswers = new ArrayList<>();
		expectedAnswers.add(AnswerHolder.NO_ANSWER_FOUND_MESSAGE);
		try {
			traderGuide.process();
			assertTrue("Answers not matching",
					expectedAnswers.equals(traderGuide.outputAnswers()));
		} catch (InputProcessingException e) {
			fail("Unexpected failure while processing input");
		}
	}

}
