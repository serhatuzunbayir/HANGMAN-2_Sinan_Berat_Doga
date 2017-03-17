package hangman;

import java.util.ArrayList;

public class Hangman {

	private ArrayList<Character> usedLetters;
	private ArrayList<Character> foundLetters;
	private Word word;
	public final int guessLimit = 5;

	public Hangman() {
		usedLetters = null;
		foundLetters = null;
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
