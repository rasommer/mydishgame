package com.demo.mydishgame.controller;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.demo.mydishgame.controller.food.FoodQuizController;
import com.demo.mydishgame.model.DishQuestion;
import com.demo.mydishgame.model.FoodQuestionTree;
import com.demo.mydishgame.model.FoodTypeQuestion;

class FoodQuizControllerTest {

	@Test
	void checkIfGetFirstSubjectAfterReset() {
		final FoodTypeQuestion chineseFoodQuestion = new FoodTypeQuestion("Chinese food");
		final DishQuestion springRollQuestion = new DishQuestion("Spring Rolls");
		final DishQuestion appleQuestion = new DishQuestion("Apple");
		chineseFoodQuestion.setQuestionForAnswerYes(springRollQuestion);
		chineseFoodQuestion.setQuestionForAnswerNo(appleQuestion);

		final FoodQuestionTree foodQuestionTree = new FoodQuestionTree(chineseFoodQuestion);

		final FoodQuizController foodQuizController = new FoodQuizController(foodQuestionTree);
		foodQuizController.updateForAnswer(true);
		foodQuizController.updateForAnswer(false);
		foodQuizController.reset();

		Assertions.assertFalse(foodQuizController.isFinished());
	}

	@Test
	void checkIfUnfinishedAfterReset() {
		final FoodTypeQuestion chineseFoodQuestion = new FoodTypeQuestion("Chinese food");
		final DishQuestion springRollQuestion = new DishQuestion("Spring Rolls");
		final DishQuestion appleQuestion = new DishQuestion("Apple");
		chineseFoodQuestion.setQuestionForAnswerYes(springRollQuestion);
		chineseFoodQuestion.setQuestionForAnswerNo(appleQuestion);

		final FoodQuestionTree foodQuestionTree = new FoodQuestionTree(chineseFoodQuestion);

		final FoodQuizController foodQuizController = new FoodQuizController(foodQuestionTree);
		foodQuizController.updateForAnswer(true);
		foodQuizController.updateForAnswer(false);
		foodQuizController.reset();

		Assertions.assertEquals("Chinese food", foodQuizController.getActualQuestionSubject());
	}

	@Test
	void checkIfUpdateQuestionTreeAfterExtending() {
		final FoodTypeQuestion chineseFoodQuestion = new FoodTypeQuestion("Chinese food");
		final DishQuestion springRollQuestion = new DishQuestion("Spring Rolls");
		final DishQuestion appleQuestion = new DishQuestion("Apple");
		chineseFoodQuestion.setQuestionForAnswerYes(springRollQuestion);
		chineseFoodQuestion.setQuestionForAnswerNo(appleQuestion);

		final FoodQuestionTree foodQuestionTree = new FoodQuestionTree(chineseFoodQuestion);

		final FoodQuizController foodQuizController = new FoodQuizController(foodQuestionTree);
		foodQuizController.updateForAnswer(true);
		foodQuizController.updateForAnswer(false);

		final FoodTypeQuestion friedQuestion = new FoodTypeQuestion("Fried");
		final DishQuestion friedShrimpQuestion = new DishQuestion("Fried Shrimp");

		foodQuizController.extendQuiz(friedQuestion, friedShrimpQuestion);
		foodQuizController.reset();
		foodQuizController.updateForAnswer(true);
		foodQuizController.updateForAnswer(true);

		Assertions.assertEquals("Fried Shrimp", foodQuizController.getActualQuestionSubject());
	}

	@Test
	void getWinnerMessageAfterTheMatch() {
		final FoodTypeQuestion chineseFoodQuestion = new FoodTypeQuestion("Chinese food");
		final DishQuestion springRollQuestion = new DishQuestion("Spring Rolls");
		final DishQuestion appleQuestion = new DishQuestion("Apple");
		chineseFoodQuestion.setQuestionForAnswerYes(springRollQuestion);
		chineseFoodQuestion.setQuestionForAnswerNo(appleQuestion);

		final FoodQuestionTree foodQuestionTree = new FoodQuestionTree(chineseFoodQuestion);

		final FoodQuizController foodQuizController = new FoodQuizController(foodQuestionTree);
		foodQuizController.updateForAnswer(true);
		foodQuizController.updateForAnswer(true);
		final Optional<String> winnerMessage = foodQuizController.getWinnerMessage();

		Assertions.assertEquals("I won again!", winnerMessage.get());
	}

	@Test
	void shouldBeCorrectWhenWin() {
		final FoodTypeQuestion chineseFoodQuestion = new FoodTypeQuestion("Chinese food");
		final DishQuestion springRollQuestion = new DishQuestion("Spring Rolls");
		final DishQuestion appleQuestion = new DishQuestion("Apple");
		chineseFoodQuestion.setQuestionForAnswerYes(springRollQuestion);
		chineseFoodQuestion.setQuestionForAnswerNo(appleQuestion);

		final FoodQuestionTree foodQuestionTree = new FoodQuestionTree(chineseFoodQuestion);

		final FoodQuizController foodQuizController = new FoodQuizController(foodQuestionTree);
		foodQuizController.updateForAnswer(true);
		foodQuizController.updateForAnswer(true);

		final Optional<Boolean> correct = foodQuizController.isCorrect();

		Assertions.assertTrue(correct.get());
	}

	@Test
	void shouldBeFinishedtAfterAllQuestions() {
		final FoodTypeQuestion chineseFoodQuestion = new FoodTypeQuestion("Chinese food");
		final DishQuestion springRollQuestion = new DishQuestion("Spring Rolls");
		final DishQuestion appleQuestion = new DishQuestion("Apple");
		chineseFoodQuestion.setQuestionForAnswerYes(springRollQuestion);
		chineseFoodQuestion.setQuestionForAnswerNo(appleQuestion);

		final FoodQuestionTree foodQuestionTree = new FoodQuestionTree(chineseFoodQuestion);

		final FoodQuizController foodQuizController = new FoodQuizController(foodQuestionTree);
		foodQuizController.updateForAnswer(true);
		foodQuizController.updateForAnswer(false);

		Assertions.assertTrue(foodQuizController.isFinished());

	}

	@Test
	void shouldBeIncorrectWhenLose() {
		final FoodTypeQuestion chineseFoodQuestion = new FoodTypeQuestion("Chinese food");
		final DishQuestion springRollQuestion = new DishQuestion("Spring Rolls");
		final DishQuestion appleQuestion = new DishQuestion("Apple");
		chineseFoodQuestion.setQuestionForAnswerYes(springRollQuestion);
		chineseFoodQuestion.setQuestionForAnswerNo(appleQuestion);

		final FoodQuestionTree foodQuestionTree = new FoodQuestionTree(chineseFoodQuestion);

		final FoodQuizController foodQuizController = new FoodQuizController(foodQuestionTree);
		foodQuizController.updateForAnswer(true);
		foodQuizController.updateForAnswer(false);

		final Optional<Boolean> correct = foodQuizController.isCorrect();

		Assertions.assertFalse(correct.get());
	}

	@Test
	void shouldBeUnfinishedtWhenThereAreMoreQuestions() {
		final FoodTypeQuestion chineseFoodQuestion = new FoodTypeQuestion("Chinese food");
		final DishQuestion springRollQuestion = new DishQuestion("Spring Rolls");
		final DishQuestion appleQuestion = new DishQuestion("Apple");
		chineseFoodQuestion.setQuestionForAnswerYes(springRollQuestion);
		chineseFoodQuestion.setQuestionForAnswerNo(appleQuestion);

		final FoodQuestionTree foodQuestionTree = new FoodQuestionTree(chineseFoodQuestion);

		final FoodQuizController foodQuizController = new FoodQuizController(foodQuestionTree);
		foodQuizController.updateForAnswer(true);

		Assertions.assertFalse(foodQuizController.isFinished());
	}

	@Test
	void shouldReturnActualQuestionSubject() {
		final FoodTypeQuestion chineseFoodQuestion = new FoodTypeQuestion("Chinese food");

		final FoodQuestionTree foodQuestionTree = new FoodQuestionTree(chineseFoodQuestion);

		final FoodQuizController foodQuizController = new FoodQuizController(foodQuestionTree);

		Assertions.assertEquals("Chinese food", foodQuizController.getActualQuestionSubject());
	}

	@Test
	void testReturnActualQuestionSubjectAfterUpdate() {
		final FoodTypeQuestion chineseFoodQuestion = new FoodTypeQuestion("Chinese food");
		final DishQuestion springRollQuestion = new DishQuestion("Spring Rolls");
		final DishQuestion appleQuestion = new DishQuestion("Apple");
		chineseFoodQuestion.setQuestionForAnswerYes(springRollQuestion);
		chineseFoodQuestion.setQuestionForAnswerNo(appleQuestion);

		final FoodQuestionTree foodQuestionTree = new FoodQuestionTree(chineseFoodQuestion);

		final FoodQuizController foodQuizController = new FoodQuizController(foodQuestionTree);
		foodQuizController.updateForAnswer(true);

		Assertions.assertEquals("Spring Rolls", foodQuizController.getActualQuestionSubject());
	}

	@Test
	void shouldReturnActualQuestionText() {
		final FoodTypeQuestion chineseFoodQuestion = new FoodTypeQuestion("Chinese food");

		final FoodQuestionTree foodQuestionTree = new FoodQuestionTree(chineseFoodQuestion);

		final FoodQuizController foodQuizController = new FoodQuizController(foodQuestionTree);

		Assertions.assertEquals("The dish that are you thinking is Chinese food?",
				foodQuizController.getActualQuestionText());
	}

	@Test
	void testReturnActualQuestionTextAfterUpdate() {
		final FoodTypeQuestion chineseFoodQuestion = new FoodTypeQuestion("Chinese food");
		final DishQuestion springRollQuestion = new DishQuestion("Spring Rolls");
		final DishQuestion appleQuestion = new DishQuestion("Apple");
		chineseFoodQuestion.setQuestionForAnswerYes(springRollQuestion);
		chineseFoodQuestion.setQuestionForAnswerNo(appleQuestion);

		final FoodQuestionTree foodQuestionTree = new FoodQuestionTree(chineseFoodQuestion);

		final FoodQuizController foodQuizController = new FoodQuizController(foodQuestionTree);
		foodQuizController.updateForAnswer(false);

		Assertions.assertEquals("The dish that are you thinking is Apple?", foodQuizController.getActualQuestionText());
	}
}
