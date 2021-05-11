package com.demo.mydishgame;

import com.demo.mydishgame.controller.game.GameController;

public class App {

	public static void main(final String[] args) {

		final GameController gameController = new GameController("Italian Food", "Lasagna", "Chocolate Cake");

		gameController.setGameExitHandler(() -> System.exit(0));

		gameController.startGame();

		if (gameController.isActive()) {
			gameController.showPresentation();
		}

		while (gameController.isActive()) {
			gameController.showFirstQuestion();
			App.manageGame(gameController);
		}

	}

	private static void manageGame(final GameController gameController) {
		do {
			gameController.provideQuestion();
		} while (!gameController.isFinished());
		if (gameController.shouldExtendQuiz()) {
			gameController.extendQuiz();
		} else {
			gameController.showOff();
			gameController.reset();
		}

		gameController.shouldContinue();
	}
}
