package com.demo.mydishgame.controller.food;

import java.util.Optional;

import com.demo.mydishgame.model.FoodTypeQuestion;
import com.demo.mydishgame.model.FoodQuestion;
import com.demo.mydishgame.model.FoodQuestionTree;
import com.demo.mydishgame.model.DishQuestion;
import com.demo.mydishgame.model.QuestionTree;

public class FoodQuizController implements ExtensibleFoodQuizController {

	private Optional<Boolean> correct = Optional.empty();
	private final QuestionTree<FoodQuestion> questionTree;

	public FoodQuizController(final FoodQuestionTree questionTree) {
		this.questionTree = questionTree;
	}

	@Override
	public void extendQuiz(final FoodTypeQuestion foodTypeQuestion, final DishQuestion dishQuestion) {
		final DishQuestion actualQuestion = (DishQuestion) this.questionTree.getActualQuestion();
		foodTypeQuestion.setQuestionForAnswerNo(actualQuestion);
		foodTypeQuestion.setQuestionForAnswerYes(dishQuestion);
		this.questionTree.changeActualQuestion(foodTypeQuestion);
	}

	@Override
	public String getActualQuestionSubject() {
		return this.questionTree.getActualQuestionSubject();
	}

	@Override
	public String getActualQuestionText() {
		return this.questionTree.getActualQuestionText();
	}

	@Override
	public Optional<String> getWinnerMessage() {
		return this.questionTree.getWinnerMessage();
	}

	@Override
	public Optional<Boolean> isCorrect() {
		return this.correct;
	}

	@Override
	public boolean isFinished() {
		return this.correct.isPresent();
	}

	@Override
	public void reset() {
		this.questionTree.reset();
		this.correct = Optional.empty();
	}

	@Override
	public void updateForAnswer(final boolean answer) {
		if (this.questionTree.hasNextQuestion()) {
			this.questionTree.moveToNextFor(answer);
		} else {
			this.correct = Optional.of(answer);
		}
	}

}
