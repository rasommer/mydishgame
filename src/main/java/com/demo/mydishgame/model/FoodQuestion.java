package com.demo.mydishgame.model;

public abstract class FoodQuestion implements Question {

	protected static final String QUESTION_TEMPLATE = "The dish that are you thinking is %s?";

	public abstract String getSubject();

}
