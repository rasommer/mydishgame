package com.demo.mydishgame.model;

import java.util.Optional;

public class FoodQuestionTree implements QuestionTree<FoodQuestion> {

	private FoodQuestion actualFoodQuestion;
	private final FoodTypeQuestion firstFoodQuestion;
	private FoodTypeQuestion previousFoodQuestion;

	public FoodQuestionTree(final FoodTypeQuestion firstFoodQuestion) {
		this.actualFoodQuestion = firstFoodQuestion;
		this.firstFoodQuestion = firstFoodQuestion;
	}

	@Override
	public void changeActualQuestion(final FoodQuestion foodQuestion) {
		if (this.previousFoodQuestion.getQuestionForAnswerNo().equals(this.actualFoodQuestion)) {
			this.previousFoodQuestion.setQuestionForAnswerNo(foodQuestion);
		} else {
			this.previousFoodQuestion.setQuestionForAnswerYes(foodQuestion);
		}
	}

	@Override
	public Question getActualQuestion() {
		return this.actualFoodQuestion;
	}

	@Override
	public String getActualQuestionSubject() {
		return this.actualFoodQuestion.getSubject();
	}

	@Override
	public String getActualQuestionText() {
		return this.actualFoodQuestion.getQuestion();
	}

	@Override
	public Optional<String> getWinnerMessage() {
		final Optional<String> message;

		if (this.actualFoodQuestion instanceof DishQuestion) {
			final DishQuestion actualDishQuestion = (DishQuestion) this.actualFoodQuestion;
			message = Optional.of(actualDishQuestion.getWinnerMessage());
		} else {
			message = Optional.empty();
		}

		return message;
	}

	@Override
	public boolean hasNextQuestion() {
		return this.actualFoodQuestion instanceof FoodTypeQuestion;
	}

	@Override
	public void moveToNextFor(final boolean answer) {
		if (this.hasNextQuestion()) {
			final FoodTypeQuestion actualFoodTypeQuestion = (FoodTypeQuestion) this.actualFoodQuestion;
			this.previousFoodQuestion = actualFoodTypeQuestion;
			this.actualFoodQuestion = answer ? actualFoodTypeQuestion.getQuestionForAnswerYes()
					: actualFoodTypeQuestion.getQuestionForAnswerNo();
		} else {
			throw new IllegalStateException("The actual Question doesn't have a next Question Node.");
		}

	}

	@Override
	public void reset() {
		this.actualFoodQuestion = this.firstFoodQuestion;
	}

}
