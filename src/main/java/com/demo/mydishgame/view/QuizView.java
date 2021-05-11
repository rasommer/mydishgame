package com.demo.mydishgame.view;

import javax.swing.JOptionPane;

public class QuizView {

	private static final String QUESTION_DIFFERENCE_TEMPLATE = "%s is ___, but %s isn't.";

	public String askForDifference(final String newDish, final String questionSubject) {
		return JOptionPane
				.showInputDialog(String.format(QuizView.QUESTION_DIFFERENCE_TEMPLATE, newDish, questionSubject));
	}

	public String askForNewDish() {
		return JOptionPane.showInputDialog("What dish are you thinking about?");
	}

	public int shouldContinue() {
		return JOptionPane.showConfirmDialog(null, "Would you like to continue the game?", "Question",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	}

	public void showFirstDialog() {
		JOptionPane.showMessageDialog(null, "Think about a dish that you like?", "Question",
				JOptionPane.QUESTION_MESSAGE);
	}

	public void showGamePresentation() {
		JOptionPane.showMessageDialog(null, "MyDishGame will try to figure out which dish you are thinking about.",
				"About the game", JOptionPane.QUESTION_MESSAGE);
	}

	public int showNextQuestion(final String QuestionText) {
		return JOptionPane.showConfirmDialog(null, QuestionText, "Question", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
	}

	public void showOff(final String message) {
		JOptionPane.showMessageDialog(null, message, null, JOptionPane.INFORMATION_MESSAGE);
	}

}
