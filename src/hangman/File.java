package hangman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class File {
	
	private static FileReader FileRead;
    private static BufferedReader BufferRead;
    ArrayList<Word> Words = new ArrayList<Word>();

    public  void OpenFile() {

        try {
            java.io.File Words = new java.io.File("D:\\User\\Hangman\\src\\Centrall\\Words");
            FileRead = new FileReader(Words);
        } catch (FileNotFoundException ex) {
            System.err.println("Dosya A��l�rken Hata Olu�tu.\n Dosyan�n Uzant�s�n� Ve Konumunu Kontrol Edin Yada Dosyay� Yeniden Yarat�n.\n Sorun ��z�lmez �se L�tfen Mail At�n.");
            System.err.println("'sinan.kucukyilmaz@std.izmirekonomi.edu.tr'");
            System.exit(1);
        }

    }
    
    public void ReadFile() {

        BufferRead = new BufferedReader(FileRead);

        String Word = "";
        String line = null;

        try {
            while ((line = BufferRead.readLine()) != null) {
                Word = line;
                Addlist(Word.split(","));
            }
        } catch (IOException ex) {
            System.err.println("Error while reading file.");
        }

    }

    public  void Addlist(String[] Word) {
        Word w = new Word( Word[0], Word[1]);
        Words.add(w); 

    }

}
