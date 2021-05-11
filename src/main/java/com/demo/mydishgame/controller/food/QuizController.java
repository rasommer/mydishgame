package com.demo.mydishgame.controller.food;

import java.util.Optional;

public interface QuizController {

	String getActualQuestionText();

	Optional<String> getWinnerMessage();

	String getActualQuestionSubject();

	Optional<Boolean> isCorrect();

	boolean isFinished();

	void updateForAnswer(boolean answer);
}