package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JRadioButton;

public class MainGUI {
	
	private JFrame frameMain;
	private JLabel lblWord;
	private JTextField textWord;
	private JTextField textAnswer;
	private JTextArea textOutput;
	private JButton btnStart;
	private JButton btnLoad;
	private JButton btnQuit;
	private JButton btnCheck;
	private JLabel lblAnswer;
	private JFileChooser fileChooser;
	private ButtonGroup translateToButtonGroup = new ButtonGroup();
	
	private ArrayList<String> enArray; //Array to hold English words
	private ArrayList<String> jpArray; //Array to hold Japanese words
	
	private Boolean started = false; //Determines if the game is started or not.
	private int currentNum = 0; //Num to hold which array item the game is currently on.
	int numWrong = 0; //Num to keep track of the number of wrong answers in a row.
	int numCorrect = 0; //Num to keep track of the number of correct answers in a row.
	private JRadioButton rbEnglish;
	private JRadioButton rbJapanese;
	private JButton btnShowAnswer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frameMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI() {
		initialize();
		createEvents();
	}
	
	
	//Handles all events for the program
	private void createEvents() {
		
		//Load button pressed
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadData();
			}
		});
		
		
		//Starts the game if the game isn't already running. FIXME: Add in the ability to check if an vocabulary array is loaded or not
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Game not already running
				if (started == false) {
					JOptionPane.showMessageDialog(frameMain, "Starting game");
					startGame();
				}
				//Game already running
				else {
					JOptionPane.showMessageDialog(frameMain, "Game already in progress");
				}
			}
		});
		
		//Quits the program
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameMain.dispose();
			}
		});
		
		//Checks the textAnswer TextBox to see if it matches the current element in the English array.
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//If game is started
				if (started == true) {			
					
					//FIXME: Fix the redundant code by combining the below code and having only one if else instead of an if else with nested if else statements.
					//If translate to English is selected
					if (rbEnglish.isSelected()) {
						
						//If the user's answer equals the element stored in the English array
						if (enArray.get(findElement(jpArray, textWord.getText())).equals(textAnswer.getText())) { //|| jpArray.get(findElement(enArray, textWord.getText())).equals(textAnswer.getText())) {
							numWrong = 0;
							numCorrect++;
							textOutput.setText("Correct: " + numCorrect);
							nextWord(); //Load next word
						}
						//If user's answer isn't correct
						else {
							numCorrect = 0;
							numWrong++;
							textOutput.setText("Incorrect: " + numWrong);
						}
					}
					
					//Else translate to Japanese is selected
					else {
						
						//If the user's answer equals the element stored in the Japanese array
						if (jpArray.get(findElement(enArray, textWord.getText())).equals(textAnswer.getText())) {
							numWrong = 0;
							numCorrect++;
							textOutput.setText("Correct: " + numCorrect);
							nextWord(); //Load next word
						}
						//If user's answer isn't correct
						else {
							numCorrect = 0;
							numWrong++;
							textOutput.setText("Incorrect: " + numWrong);
						}
					}
					
				}
				//If game is not started
				else {
					JOptionPane.showMessageDialog(frameMain, "Game not in progress");
				}
			}
		});
		
		//Shows the answer for the current word to the user. Follows up by moving onto the next word in the list.
		btnShowAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//If game is started
				if (started == true) {
					
					//If translate to English is selected
					if (rbEnglish.isSelected()) {
						JOptionPane.showMessageDialog(frameMain, "Answer: " + enArray.get(findElement(jpArray, textWord.getText())));
					}
					
					//Else translate to Japanese is selected
					else {
						JOptionPane.showMessageDialog(frameMain, "Answer: " + jpArray.get(findElement(enArray, textWord.getText())));
					}
					
					numCorrect = 0;
					numWrong++;
					textOutput.setText("Incorrect: " + numWrong);
					nextWord();
				}
				//If game is not started
				else {
					JOptionPane.showMessageDialog(frameMain, "Game not in progress");
				}
			}
		});
	}
	
	//Starts the game.
	private void startGame() {
		//If no vocab list is loaded, inform the user and tell them to load one before playing.
		if (jpArray == null) {
			JOptionPane.showMessageDialog(frameMain, "No vocabulary list loaded!\nLoad a vocabulary list first and try again.");
		}
		//If a vocab list is loaded, start the game like normal.
		else {
			currentNum = 0;
			
			if (rbEnglish.isSelected()) { //If translate to English is selected, then load the first Japanese word.
				textWord.setText(jpArray.get(currentNum));
			}
			else { //Else translate to Japanese is selected, so load the first English word.
				textWord.setText(enArray.get(currentNum));
			}
			
			started = true;
		}
	}
	
	//Loads the next Japanese word into the textWord TextBox and sets textAnswer to blank
	private void nextWord() {
		currentNum++; //Keeps track of the current word that the game is on
		//If currentNum is greater than or equal to the arraySize, end the game as all words have been completed.
		if (currentNum >= jpArray.size()) {
			JOptionPane.showMessageDialog(frameMain, "Game Complete");
			textAnswer.setText("");
			textWord.setText("");
			numWrong = 0;
			numCorrect = 0;
			started = false;
		}
		//If currentNum is smaller than the arraySize, load the next word for the user to translate.
		else {
			if (rbEnglish.isSelected()) { //If translate to English is selected, then load the next Japanese word.
				textWord.setText(jpArray.get(currentNum));
			}
			else { //Else translate to Japanese is selected, so load the next English word.
				textWord.setText(enArray.get(currentNum));
			}
			textAnswer.setText("");
		}
	}
	
	//Looks for a matching string in a given array and returns the elements location (number)
	private int findElement(ArrayList<String> array, String toFind) {
		for (int i = 0; i < array.size(); i++) {
			if (toFind.equals(array.get(i))) {
				return i;
			}
		}
		return 0; //Return 0 if and only if no match is found above. FIXME: Add a catch just in case this case is ever reached.
	}
	
	private void loadData() {
		int fileChooserVal = fileChooser.showOpenDialog(frameMain);
		File readFile = null;
		Scanner inFS = null;
		FileInputStream fileInput = null; 
		String readLine = null;
		enArray = new ArrayList<String>();
		jpArray = new ArrayList<String>();
		
		if (fileChooserVal == JFileChooser.APPROVE_OPTION) {
			readFile = fileChooser.getSelectedFile();
			
			//If the file can be read from
			if (readFile.canRead()) {
				try {
						fileInput = new FileInputStream(readFile);
						inFS = new Scanner(fileInput);
						
						populateLanguageArrays(inFS);
				}
				//If an issue arises when trying to open/read the file
				catch (IOException e) {
					JOptionPane.showMessageDialog(frameMain, e);
				}
			}
			//If the file can't be read from at all or if it's empty
			else {
				JOptionPane.showMessageDialog(frameMain, "File can't be opened or read from. Please try a different file");
			}
		}
		//Tell user that the data is loaded. FIXME: Fix to show when the data fails to load vs. when it actually loads
		JOptionPane.showMessageDialog(frameMain, "Data Loaded");
	}

	private void populateLanguageArrays(Scanner inFS) {
		String readLine;
		while (inFS.hasNext()) {
			readLine = inFS.nextLine();
			
			String[] tempArray = readLine.split(":", 2);
			enArray.add(tempArray[1]);
			jpArray.add(tempArray[0]);
			Arrays.fill(tempArray, ""); 
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameMain = new JFrame();
		frameMain.getContentPane().setBackground(Color.LIGHT_GRAY);
		frameMain.getContentPane().setLayout(null);
		frameMain.setLocationRelativeTo(null);
		
		fileChooser = new JFileChooser();
		
		lblWord = new JLabel("Word:");
		lblWord.setBounds(10, 11, 52, 14);
		frameMain.getContentPane().add(lblWord);
		
		lblAnswer = new JLabel("Answer:");
		lblAnswer.setBounds(10, 36, 52, 14);
		frameMain.getContentPane().add(lblAnswer);
		
		textWord = new JTextField();
		textWord.setEditable(false);
		textWord.setBounds(64, 8, 220, 20);
		frameMain.getContentPane().add(textWord);
		textWord.setColumns(10);
		
		textAnswer = new JTextField();
		textAnswer.setBounds(64, 33, 220, 20);
		frameMain.getContentPane().add(textAnswer);
		textAnswer.setColumns(10);
		
		btnQuit = new JButton("Quit");
		btnQuit.setBounds(195, 98, 89, 23);
		frameMain.getContentPane().add(btnQuit);
		
		btnLoad = new JButton("Load Data");
		btnLoad.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnLoad.setBounds(102, 98, 89, 23);
		frameMain.getContentPane().add(btnLoad);
		
		btnStart = new JButton("Start");
		btnStart.setBounds(10, 98, 89, 23);
		frameMain.getContentPane().add(btnStart);
		
		btnCheck = new JButton("Check");
		btnCheck.setBounds(195, 64, 89, 23);
		frameMain.getContentPane().add(btnCheck);
		
		textOutput = new JTextArea();
		textOutput.setEditable(false);
		textOutput.setBounds(10, 61, 178, 28);
		frameMain.getContentPane().add(textOutput);
		
		JLabel lblTranslateTo = new JLabel("Translate to:");
		lblTranslateTo.setBounds(10, 132, 83, 14);
		frameMain.getContentPane().add(lblTranslateTo);
		
		rbEnglish = new JRadioButton("English");
		rbEnglish.setSelected(true);
		rbEnglish.setBounds(99, 128, 82, 23);
		translateToButtonGroup.add(rbEnglish);
		frameMain.getContentPane().add(rbEnglish);
		
		rbJapanese = new JRadioButton("Japanese");
		rbJapanese.setBounds(183, 128, 89, 23);
		frameMain.getContentPane().add(rbJapanese);
		frameMain.setTitle("Vocab");
		frameMain.setResizable(false);
		frameMain.setBounds(100, 100, 310, 230);
		translateToButtonGroup.add(rbJapanese);
		
		btnShowAnswer = new JButton("Show Answer");
		btnShowAnswer.setBounds(166, 158, 118, 23);
		frameMain.getContentPane().add(btnShowAnswer);
		frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
