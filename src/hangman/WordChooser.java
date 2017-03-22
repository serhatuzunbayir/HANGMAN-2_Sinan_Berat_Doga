package hangman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComboBox;

public class WordChooser {

	private FileReader fileRead;
	private BufferedReader bufferRead;
	private ArrayList<Word> words;
	private String category;
	public ArrayList<String> categories;

	public WordChooser(String category) {
		words = new ArrayList<>();
		this.category = category;
	}

	public WordChooser() {

	}

	private void OpenFile() {

		try {
			File Words = new File("words.txt");
			fileRead = new FileReader(Words);
		} catch (FileNotFoundException ex) {
			System.err.println(
					"An error occurred while opening file. Please check the file path. Contact with me via e-mail below.");
			System.err.println("'sinan.kucukyilmaz@std.izmirekonomi.edu.tr'");
			System.exit(1);
		}

	}

	private void ReadFile() {

		bufferRead = new BufferedReader(fileRead);
		String line;

		try {
			while ((line = bufferRead.readLine()) != null) {
				String[] splitLine = line.split(",");
				String content = splitLine[0];
				String category = splitLine[1];

				if (category.equals(this.category))
					words.add(new Word(content, category));
			}
		} catch (IOException ex) {
			System.err.println("Error while reading file.");
		} finally {
			try {
				fileRead.close();
				bufferRead.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void ReadFileFillCategories(JComboBox<String> c) {
		OpenFile();
		categories = new ArrayList<>();
		bufferRead = new BufferedReader(fileRead);
		String line;
		c.addItem("random");
		try {
			while ((line = bufferRead.readLine()) != null) {
				String[] splitLine = line.split(",");
				String tempCategory = splitLine[1];
				if (!categories.contains(tempCategory)) {
					categories.add(tempCategory);
					c.addItem(tempCategory);
				}
			}

		} catch (IOException ex) {
			System.err.println("Error while reading file.");
		} finally {
			try {
				fileRead.close();
				bufferRead.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public Word chooseWord() {
		OpenFile();
		ReadFile();
		Random rndm = new Random();
		int rndmNumer = rndm.nextInt(words.size());
		return words.get(rndmNumer);
	}

}
