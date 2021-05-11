package com.demo.mydishgame.controller.food;

import com.demo.mydishgame.model.FoodTypeQuestion;
import com.demo.mydishgame.model.DishQuestion;

public interface ExtensibleFoodQuizController extends QuizController {

	void extendQuiz(FoodTypeQuestion foodTypeQuestion, DishQuestion dishQuestion);

	public void reset();
}
