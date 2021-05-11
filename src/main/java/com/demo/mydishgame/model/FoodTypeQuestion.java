package com.demo.mydishgame.model;

public class FoodTypeQuestion extends FoodQuestion {

	private final String foodType;

	private FoodQuestion questionForAnswerNo;

	private FoodQuestion questionForAnswerYes;

	public FoodTypeQuestion(final String foodType) {
		this.foodType = foodType;
	}

	@Override
	public String getQuestion() {

		return String.format(FoodQuestion.QUESTION_TEMPLATE, this.foodType);
	}

	public FoodQuestion getQuestionForAnswerNo() {
		return this.questionForAnswerNo;
	}

	public FoodQuestion getQuestionForAnswerYes() {
		return this.questionForAnswerYes;
	}

	@Override
	public String getSubject() {
		return this.foodType;
	}

	public void setQuestionForAnswerNo(final FoodQuestion questionForAnswerNo) {
		this.questionForAnswerNo = questionForAnswerNo;
	}

	public void setQuestionForAnswerYes(final FoodQuestion questionForAnswerYes) {
		this.questionForAnswerYes = questionForAnswerYes;
	}
}
