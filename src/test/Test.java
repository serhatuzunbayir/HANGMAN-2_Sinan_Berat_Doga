package test;

import static org.junit.Assert.*;

import hangman.Hangman;
import hangman.Word;

public class Test {

	@org.junit.Test
	public void usedLetter() {
		Hangman hangman = new Hangman("animals");
		assertEquals(false, hangman.isUsedLetter('c'));
	}
	
	@org.junit.Test
	public void lose() {
		Hangman hangman = new Hangman("animals");
		assertEquals(false, hangman.lose());
	}
	
	@org.junit.Test
	public void win() {
		Hangman hangman = new Hangman("animals");
		assertEquals(false, hangman.win());
	}
	
	@org.junit.Test
	public void usedLeter() {
		Word word = new Word("zinan", "name");
		assertEquals(true, word.contains('z'));
	}
	
	@org.junit.Test
	public void usedLeter1() {
		Word word = new Word("zinan", "name");
		assertEquals(true, word.contains('i'));
	}
	
	@org.junit.Test
	public void usedLeter2() {
		Word word = new Word("zinan", "name");
		assertEquals(true, word.contains('n'));
	}
	
	@org.junit.Test
	public void usedLeter3() {
		Word word = new Word("zinan", "name");
		assertEquals(true, word.contains('a'));
	}
	
	@org.junit.Test
	public void usedLeter4() {
		Word word = new Word("zinan", "name");
		assertEquals(false, word.contains('p'));
	}
	
	@org.junit.Test
	public void getWord() {
		Word word = new Word("zinan", "name");
		assertEquals("zinan", word.getContent());
	}
	
	@org.junit.Test
	public void getCategory() {
		Word word = new Word("zinan", "name");
		assertEquals("name", word.getCategory());
	}

}
