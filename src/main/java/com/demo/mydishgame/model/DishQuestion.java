package com.demo.mydishgame.model;

public class DishQuestion extends FoodQuestion {

	private static final String RIGHT_ANSWER_MESSAGE = "I won again!";
	private final String dish;

	public DishQuestion(final String dish) {
		this.dish = dish;
	}

	public String getDish() {
		return this.dish;
	}

	@Override
	public String getQuestion() {
		return String.format(FoodQuestion.QUESTION_TEMPLATE, this.dish);
	}

	@Override
	public String getSubject() {
		return this.dish;
	}

	public String getWinnerMessage() {
		return DishQuestion.RIGHT_ANSWER_MESSAGE;
	}

}
