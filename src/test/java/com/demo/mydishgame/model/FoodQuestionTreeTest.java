package com.demo.mydishgame.model;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FoodQuestionTreeTest {

	@Test
	void testIfGetFirstSubjectAfterReset() {
		final FoodTypeQuestion junkFoodQuestion = new FoodTypeQuestion("Junk food");
		final DishQuestion burguerQuestion = new DishQuestion("Burguer");
		final DishQuestion riceQuestion = new DishQuestion("Rice");
		junkFoodQuestion.setQuestionForAnswerYes(burguerQuestion);
		junkFoodQuestion.setQuestionForAnswerNo(riceQuestion);

		final FoodQuestionTree foodQuestionTree = new FoodQuestionTree(junkFoodQuestion);

		Assertions.assertEquals("Junk food", foodQuestionTree.getActualQuestionSubject());
	}

	@Test
	void checkFirstQuestionText() {
		final FoodTypeQuestion junkFoodQuestion = new FoodTypeQuestion("Junk food");
		final DishQuestion burguerQuestion = new DishQuestion("Burguer");
		final DishQuestion riceQuestion = new DishQuestion("Rice");
		junkFoodQuestion.setQuestionForAnswerYes(burguerQuestion);
		junkFoodQuestion.setQuestionForAnswerNo(riceQuestion);

		final FoodQuestionTree foodQuestionTree = new FoodQuestionTree(junkFoodQuestion);

		Assertions.assertEquals("The dish that are you thinking is Junk food?",
				foodQuestionTree.getActualQuestionText());
	}

	@Test
	void testActualQuestion() {
		final FoodTypeQuestion junkFoodQuestion = new FoodTypeQuestion("Junk food");
		final DishQuestion burguerQuestion = new DishQuestion("Burguer");
		final DishQuestion riceQuestion = new DishQuestion("Rice");
		junkFoodQuestion.setQuestionForAnswerYes(burguerQuestion);
		junkFoodQuestion.setQuestionForAnswerNo(riceQuestion);

		final FoodQuestionTree foodQuestionTree = new FoodQuestionTree(junkFoodQuestion);
		foodQuestionTree.moveToNextFor(false);

		Assertions.assertEquals(riceQuestion, foodQuestionTree.getActualQuestion());
	}

	@Test
	void checkWinnerMessage() {
		final FoodTypeQuestion junkFoodQuestion = new FoodTypeQuestion("Junk food");
		final DishQuestion burguerQuestion = new DishQuestion("Burguer");
		final DishQuestion riceQuestion = new DishQuestion("Rice");
		junkFoodQuestion.setQuestionForAnswerYes(burguerQuestion);
		junkFoodQuestion.setQuestionForAnswerNo(riceQuestion);

		final FoodQuestionTree foodQuestionTree = new FoodQuestionTree(junkFoodQuestion);
		foodQuestionTree.moveToNextFor(true);
		final Optional<String> winnerMessage = foodQuestionTree.getWinnerMessage();

		Assertions.assertEquals("I won again!", winnerMessage.get());
	}

	@Test
	void shouldntHaveMoreQuestions() {
		final FoodTypeQuestion junkFoodQuestion = new FoodTypeQuestion("Junk food");
		final DishQuestion burguerQuestion = new DishQuestion("Burguer");
		final DishQuestion riceQuestion = new DishQuestion("Rice");
		junkFoodQuestion.setQuestionForAnswerYes(burguerQuestion);
		junkFoodQuestion.setQuestionForAnswerNo(riceQuestion);

		final FoodQuestionTree foodQuestionTree = new FoodQuestionTree(junkFoodQuestion);
		foodQuestionTree.moveToNextFor(true);

		Assertions.assertFalse(foodQuestionTree.hasNextQuestion());
	}

	@Test
	void tesNextQestion() {
		final FoodTypeQuestion junkFoodQuestion = new FoodTypeQuestion("Junk food");
		final DishQuestion burguerQuestion = new DishQuestion("Burguer");
		final DishQuestion riceQuestion = new DishQuestion("Rice");
		junkFoodQuestion.setQuestionForAnswerYes(burguerQuestion);
		junkFoodQuestion.setQuestionForAnswerNo(riceQuestion);

		final FoodQuestionTree foodQuestionTree = new FoodQuestionTree(junkFoodQuestion);
		foodQuestionTree.moveToNextFor(false);

		Assertions.assertEquals(riceQuestion, foodQuestionTree.getActualQuestion());
	}

	@Test
	void testActualQuestionAfterReset() {
		final FoodTypeQuestion junkFoodQuestion = new FoodTypeQuestion("Junk food");
		final DishQuestion burguerQuestion = new DishQuestion("Burguer");
		final DishQuestion riceQuestion = new DishQuestion("Rice");
		junkFoodQuestion.setQuestionForAnswerYes(burguerQuestion);
		junkFoodQuestion.setQuestionForAnswerNo(riceQuestion);

		final FoodQuestionTree foodQuestionTree = new FoodQuestionTree(junkFoodQuestion);
		foodQuestionTree.moveToNextFor(false);
		foodQuestionTree.reset();

		Assertions.assertEquals(junkFoodQuestion, foodQuestionTree.getActualQuestion());
	}

	@Test
	void checkExtensionFeature() throws Exception {
		final FoodTypeQuestion junkFoodQuestion = new FoodTypeQuestion("Junk food");
		final DishQuestion burguerQuestion = new DishQuestion("Burguer");
		final DishQuestion riceQuestion = new DishQuestion("Rice");
		junkFoodQuestion.setQuestionForAnswerYes(burguerQuestion);
		junkFoodQuestion.setQuestionForAnswerNo(riceQuestion);

		final FoodQuestionTree foodQuestionTree = new FoodQuestionTree(junkFoodQuestion);
		foodQuestionTree.moveToNextFor(false);

		final DishQuestion pizzaQuestion = new DishQuestion("Pizza");
		foodQuestionTree.changeActualQuestion(pizzaQuestion);
		foodQuestionTree.reset();
		foodQuestionTree.moveToNextFor(false);

		Assertions.assertEquals(pizzaQuestion, foodQuestionTree.getActualQuestion());
	}
}
