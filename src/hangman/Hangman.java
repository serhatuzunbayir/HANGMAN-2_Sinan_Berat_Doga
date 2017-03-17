package hangman;

import java.util.ArrayList;

public class Hangman {

	private ArrayList<Character> usedLetters;
	private ArrayList<Character> foundLetters;
	private Word word;
	private int totalGuess;
	private final int guessLimit = 5;

	public Hangman(String category) {
		usedLetters = null;
		foundLetters = null;
		word = new WordChooser(category).chooseWord();
		totalGuess = 0;
	}

	public boolean makeGuess(char ch) {
		if (word.contains(ch)) {
			foundLetters.add(ch);

		} else {
			totalGuess++;
			if (isFinished())
				return true;

		}
		usedLetters.add(ch);
		return false;
	}

	public boolean isFinished() {
		return totalGuess == guessLimit;
	}

	public Word getWord() {
		return word;
	}

	public ArrayList<Character> getUsedLetters() {
		return usedLetters;
	}

	public void setUsedLetters(ArrayList<Character> usedLetters) {
		this.usedLetters = usedLetters;
	}

	public ArrayList<Character> getFoundLetters() {
		return foundLetters;
	}

	public void setFoundLetters(ArrayList<Character> foundLetters) {
		this.foundLetters = foundLetters;
	}

}
