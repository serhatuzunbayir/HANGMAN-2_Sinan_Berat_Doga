package hangman;

import java.util.ArrayList;

public class Hangman {

	private ArrayList<Character> usedLetters;
	private ArrayList<Character> foundLetters;
	private Word word;
	private int totalGuess;
	private final int guessLimit = 15;

	public Hangman(String category) {
		usedLetters = new ArrayList<>();
		foundLetters = new ArrayList<>();
		word = new WordChooser(category).chooseWord();
		totalGuess = 0;
	}

	/**
	 * Returns true if the word contains the given char, false otherwise.
	 * 
	 * @param ch
	 *            The char to be checked.
	 * @return boolean
	 */
	public boolean makeGuess(char ch) {
		if (usedLetters.contains(ch)) {
			return false;
		}
		boolean result = false;
		if (word.contains(ch)) {
			foundLetters.add(ch);
			result = true;
		} else {
			totalGuess++;
		}
		usedLetters.add(ch);
		return result;
	}

	/**
	 * Returns true if the given char was found, false otherwise.
	 * 
	 * @author Horse
	 * @param ch
	 *            The char to be checked.
	 * @return
	 */
	public boolean wasFound(char ch) {
		if (foundLetters.contains(ch))
			return true;
		return false;
	}

	/**
	 * Returns true if guessLimit is achieved, false otherwise.
	 * 
	 * @return boolean
	 */
	public boolean lose() {
		return totalGuess == guessLimit;
	}

	/**
	 * Returns true if the game is won, false otherwise.
	 * 
	 * @author Horse
	 * @return boolean
	 */
	public boolean win() {
		String w = word.getContent();
		for (int i = 0; i < w.length(); i++) {
			if (!foundLetters.contains(w.charAt(i))) {
				return false;
			}
		}
		return true;
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
