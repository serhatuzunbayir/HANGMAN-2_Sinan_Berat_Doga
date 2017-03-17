package hangman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class WordChooser {

	private FileReader fileRead;
	private BufferedReader bufferRead;
	private ArrayList<Word> words;
	
	public WordChooser(String category) {
		words = new ArrayList<>();
	}

	private void OpenFile(String category) {

		try {
			File Words = new File(category + ".txt");
			fileRead = new FileReader(Words);
		} catch (FileNotFoundException ex) {
			System.err.println(
					"Dosya Açýlýrken Hata Oluþtu.\n Dosyanýn Uzantýsýný Ve Konumunu Kontrol Edin Yada Dosyayý Yeniden Yaratýn.\n Sorun Çözülmez Ýse Lütfen Mail Atýn.");
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
	
	public Word chooseWord(String category){
		OpenFile(category);
		ReadFile();
		Random rndm = new Random();
		int rndmNumer = rndm.nextInt(words.size());
		return words.get(rndmNumer);
	}

}
