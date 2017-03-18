package hangman;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
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
import java.awt.Font;

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
	private JLabel lblLives;
	private JTextField txtfldLives;
	private JPanel drawPanel;
	private JLabel lblResult;
	private JLabel lblResultWord;

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
		comboBox.addItem("software");
		contentPane.add(comboBox);

		lblInfo = new JLabel("");
		lblInfo.setForeground(Color.RED);
		lblInfo.setBounds(20, 197, 281, 16);
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
		txtfldGuess.setBounds(90, 344, 25, 26);
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
		txtfldWord.setBounds(93, 306, 150, 26);
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
		txtfldCategory.setBounds(93, 268, 150, 26);
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

		lblLives = new JLabel("lives:");
		lblLives.setBounds(20, 225, 61, 16);
		contentPane.add(lblLives);

		txtfldLives = new JTextField();
		txtfldLives.setBounds(93, 220, 25, 26);
		txtfldLives.setEditable(false);
		contentPane.add(txtfldLives);
		txtfldLives.setColumns(10);

		lblResult = new JLabel("");
		lblResult.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblResult.setBounds(20, 100, 150, 52);
		contentPane.add(lblResult);

		lblResultWord = new JLabel("");
		lblResultWord.setBounds(20, 164, 150, 16);
		contentPane.add(lblResultWord);

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

	class HangmanPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			switch (hangman.getTotalGuess()) {
			case 5:
				g.drawLine(115, 180, 90, 200);
				g.drawLine(115, 180, 140, 200);
			case 4:
				g.drawLine(115, 100, 90, 140);
				g.drawLine(115, 100, 140, 140);
			case 3:
				g.drawLine(115, 90, 115, 180);
			case 2:
				g.drawOval(90, 40, 50, 50);
			case 1:
				g.fillRect(110, 30, 10, 10);
				g.fillRect(20, 20, 100, 10);
				g.fillRect(20, 20, 10, 200);
				break;
			}
		}
	}

	private void tryButtonClicked() {
		if (txtfldGuess.getText().equals("")) {
			lblInfo.setText("Enter a letter!");
			return;
		}
		guess = Character.toUpperCase(guess);
		if(hangman.isUsedLetter(guess)){
			lblInfo.setText("You have already tried that letter!");
			return;
		}
		if (hangman.makeGuess(guess)) {
			lblInfo.setText("Good Job!");
		} else {
			lblInfo.setText("Shame!");
		}
		drawPanel.repaint();
		displayWord();
		displayLives();
		displayUsedLetters();
		displayFoundLetters();
		if (hangman.win()) {
			lblResult.setForeground(Color.GREEN);
			lblResult.setText("WIN");
			txtfldGuess.setEnabled(false);
			btnTry.setEnabled(false);
		} else if (hangman.lose()) {
			lblResult.setForeground(Color.RED);
			lblResult.setText("LOSE");
			lblResultWord.setText("word:  " + hangman.getWord().getContent());
			txtfldGuess.setEnabled(false);
			btnTry.setEnabled(false);
		}
		txtfldGuess.setText("");
		txtfldGuess.grabFocus();
	}

	private void startButtonClicked() {
		String category = (String) comboBox.getSelectedItem();
		hangman = new Hangman(category);
		drawPanel = new HangmanPanel();
		drawPanel.setBounds(263, 20, 245, 217);
		contentPane.add(drawPanel);
		drawPanel.repaint();
		btnTry.setEnabled(true);
		txtfldGuess.setEnabled(true);
		displayWord();
		displayLives();
		lblResultWord.setText("");
		txtareaFoundLetters.setText("");
		lblResult.setText("");
		lblInfo.setText("");
		txtareaUsedLetters.setText("");
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

	private void displayLives() {
		txtfldLives.setText(hangman.guessLimit - hangman.getTotalGuess() + "");
	}
}
