package com.demo.mydishgame.controller.game;

import java.util.Optional;

import com.demo.mydishgame.controller.food.ExtensibleFoodQuizController;
import com.demo.mydishgame.controller.food.FoodQuizController;
import com.demo.mydishgame.model.FoodTypeQuestion;
import com.demo.mydishgame.model.FoodQuestionTree;
import com.demo.mydishgame.model.DishQuestion;
import com.demo.mydishgame.view.QuizView;

public class GameController {

	private boolean active;
	private final ExtensibleFoodQuizController foodQuizz;
	private GameExitHandler gameExitHandler;
	private final QuizView quizView;

	public GameController(final String foodType, final String dish, final String secondDish) {
		final FoodTypeQuestion firstFoodQuestion = new FoodTypeQuestion(foodType);
		final DishQuestion dishQuestionForYes = new DishQuestion(dish);
		firstFoodQuestion.setQuestionForAnswerYes(dishQuestionForYes);
		final DishQuestion dishQuestionForNo = new DishQuestion(secondDish);
		firstFoodQuestion.setQuestionForAnswerNo(dishQuestionForNo);
		this.foodQuizz = new FoodQuizController(new FoodQuestionTree(firstFoodQuestion));
		this.quizView = new QuizView();
		this.active = false;
	}

	private void addNewQuestions(final String newDish, final String difference) {
		final FoodTypeQuestion foodTypeQuestion = new FoodTypeQuestion(difference);
		final DishQuestion DishQuestion = new DishQuestion(newDish);
		this.foodQuizz.extendQuiz(foodTypeQuestion, DishQuestion);
		this.foodQuizz.reset();
	}

	public void extendQuiz() {
		final String newDish = this.quizView.askForNewDish();
		final String difference = this.quizView.askForDifference(newDish, this.foodQuizz.getActualQuestionSubject());
		this.addNewQuestions(newDish, difference);
	}

	public boolean isActive() {
		return this.active;
	}

	public boolean isFinished() {
		return this.foodQuizz.isFinished();
	}

	public void provideQuestion() {
		final int answerValue = this.quizView.showNextQuestion(this.foodQuizz.getActualQuestionText());
		if (answerValue == -1) {
			this.gameExitHandler.onGameExit();
		} else {
			final Boolean answer = answerValue == 0;
			this.foodQuizz.updateForAnswer(answer);
		}
	}

	public void reset() {
		this.foodQuizz.reset();
	}

	public void setGameExitHandler(final GameExitHandler gameExitHandler) {
		this.gameExitHandler = gameExitHandler;
	}

	public void shouldContinue() {
		final int shouldContinueValue = this.quizView.shouldContinue();
		final boolean shouldContinue = shouldContinueValue == 0;
		if (!shouldContinue) {
			this.gameExitHandler.onGameExit();
		}
	}

	public boolean shouldExtendQuiz() {
		final Optional<Boolean> correct = this.foodQuizz.isCorrect();
		return !correct.get();
	}

	public void showFirstQuestion() {
		if (this.active) {
			this.quizView.showFirstDialog();
		}
	}

	public void showOff() {
		this.quizView.showOff(this.foodQuizz.getWinnerMessage().get());
	}

	public void showPresentation() {
		this.quizView.showGamePresentation();
	}

	public void startGame() {
		this.active = true;
	}
}
