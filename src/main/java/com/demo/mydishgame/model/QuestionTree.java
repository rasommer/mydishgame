package com.demo.mydishgame.model;

import java.util.Optional;

public interface QuestionTree<T extends Question> {

	void changeActualQuestion(T question);

	Question getActualQuestion();

	String getActualQuestionSubject();

	String getActualQuestionText();

	Optional<String> getWinnerMessage();

	boolean hasNextQuestion();

	void moveToNextFor(boolean answer);

	void reset();
}