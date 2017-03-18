package hangman;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Game extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Hangman hangman;
	private JComboBox<String> comboBox;
	private JLabel lblInfo;
	private JTextField txtfldGuess;
	private JTextField txtfldWord;
	private JButton btnTry;
	private char guess;
	private JTextField txtfldCategory;
	private JTextArea txtareaUsedLetters;
	private JTextArea txtareaFoundLetters;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game frame = new Game();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Game() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 450);
		setTitle("HANGMAN");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startButtonClicked();
			}
		});
		btnStart.setBounds(20, 59, 150, 29);
		contentPane.add(btnStart);

		comboBox = new JComboBox<>();
		comboBox.setBounds(20, 20, 150, 27);
		comboBox.addItem("animals");
		comboBox.addItem("cities");
		comboBox.addItem("instruments");
		contentPane.add(comboBox);

		lblInfo = new JLabel("");
		lblInfo.setBounds(122, 164, 281, 16);
		contentPane.add(lblInfo);

		txtfldGuess = new JTextField();
		txtfldGuess.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char input = e.getKeyChar();
				if (!Character.isLetter(input)) {
					if (input == 8) {
						e.consume();
						return;
					}
					if (input == 10) {
						tryButtonClicked();
						e.consume();
						return;
					}
					e.consume();
					lblInfo.setText("You must enter letter!");
				} else if (txtfldGuess.getText().length() > 0) {
					e.consume();
					txtfldGuess.setText(e.getKeyChar() + "");
				}
				guess = input;
			}
		});
		txtfldGuess.setBounds(90, 344, 130, 26);
		txtfldGuess.setEnabled(false);
		contentPane.add(txtfldGuess);
		txtfldGuess.setColumns(10);

		btnTry = new JButton("Try");
		btnTry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tryButtonClicked();
			}
		});
		btnTry.setBounds(90, 382, 130, 29);
		btnTry.setEnabled(false);
		contentPane.add(btnTry);

		txtfldWord = new JTextField();
		txtfldWord.setBounds(93, 306, 130, 26);
		txtfldWord.setEditable(false);
		contentPane.add(txtfldWord);
		txtfldWord.setColumns(10);

		JLabel lblWord = new JLabel("word:");
		lblWord.setBounds(20, 311, 61, 16);
		contentPane.add(lblWord);

		JLabel lblGuess = new JLabel("guess:");
		lblGuess.setBounds(20, 349, 61, 16);
		contentPane.add(lblGuess);

		JLabel lblCategory = new JLabel("category:");
		lblCategory.setBounds(20, 277, 61, 16);
		contentPane.add(lblCategory);

		txtfldCategory = new JTextField();
		txtfldCategory.setBounds(93, 268, 130, 26);
		txtfldCategory.setEditable(false);
		contentPane.add(txtfldCategory);
		txtfldCategory.setColumns(10);

		txtareaUsedLetters = new JTextArea();
		txtareaUsedLetters.setBounds(263, 311, 112, 100);
		txtareaUsedLetters.setEditable(false);
		contentPane.add(txtareaUsedLetters);

		txtareaFoundLetters = new JTextArea();
		txtareaFoundLetters.setBounds(400, 311, 112, 100);
		txtareaFoundLetters.setEditable(false);
		contentPane.add(txtareaFoundLetters);

		JLabel lblUsedLetters = new JLabel("used letters");
		lblUsedLetters.setBounds(279, 277, 80, 16);
		contentPane.add(lblUsedLetters);

		JLabel lblFoundLetters = new JLabel("found letters");
		lblFoundLetters.setBounds(412, 277, 88, 16);
		contentPane.add(lblFoundLetters);
	}

	/**
	 * Displays the word which is being sought. If there are letter that found,
	 * they will be displayed as they are otherwise '_' is going to appear.
	 * 
	 * @author Horse
	 */
	private void displayWord() {
		String word = hangman.getWord().getContent();
		String disp = "";
		for (int i = 0; i < word.length(); i++) {
			if (hangman.wasFound(word.charAt(i))) {
				disp += word.charAt(i) + " ";
			} else {
				disp += "_ ";
			}
		}
		txtfldWord.setText(disp);
	}

	private void tryButtonClicked() {
		if (txtfldGuess.getText().equals("")) {
			lblInfo.setText("enter a letter");
			return;
		}
		guess = Character.toUpperCase(guess);
		if (hangman.makeGuess(guess)) {
			lblInfo.setText("Good Job!");
		} else {
			lblInfo.setText("Shame!");
		}
		if (hangman.win()) {
			lblInfo.setText("You Win!");
			txtfldGuess.setEnabled(false);
			btnTry.setEnabled(false);
		} else if (hangman.lose()) {
			lblInfo.setText("You Lose!   word was " + hangman.getWord().getContent());
			txtfldGuess.setEnabled(false);
			btnTry.setEnabled(false);
		}
		displayWord();
		displayUsedLetters();
		displayFoundLetters();
		txtfldGuess.setText("");
		txtfldGuess.grabFocus();
	}

	private void startButtonClicked() {
		String category = (String) comboBox.getSelectedItem();
		hangman = new Hangman(category);
		btnTry.setEnabled(true);
		txtfldGuess.setEnabled(true);
		displayWord();
		txtfldGuess.grabFocus();
		txtfldCategory.setText(hangman.getWord().getCategory());
	}

	private void displayUsedLetters() {
		String disp = "";
		for (Character c : hangman.getUsedLetters()) {
			if ((disp.length() % 16) > 13) {
				disp += "\n";
			}
			disp += c + " ";
		}
		txtareaUsedLetters.setText(disp);
	}

	private void displayFoundLetters() {
		String disp = "";
		for (Character c : hangman.getFoundLetters()) {
			if ((disp.length() % 16) > 13) {
				disp += "\n";
			}
			disp += c + " ";
		}
		txtareaFoundLetters.setText(disp);
	}
}
