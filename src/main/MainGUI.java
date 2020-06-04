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

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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

	private JFrame frame;
	
	private ArrayList<String> enArray;
	private ArrayList<String> jpArray;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frame.setVisible(true);
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
		
		
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fileChooserVal = fileChooser.showOpenDialog(frameMain);
				File readFile = null;
				Scanner inFS = null;
				FileInputStream fileInput = null; 
				String readLine = null;
				enArray = new ArrayList<String>();
				jpArray = new ArrayList<String>();
				
				if (fileChooserVal == JFileChooser.APPROVE_OPTION) {
					readFile = fileChooser.getSelectedFile();
					
					if (readFile.canRead()) {
						try {
								fileInput = new FileInputStream(readFile);
								inFS = new Scanner(fileInput);
								
								while (inFS.hasNext()) {
									readLine = inFS.nextLine();
									
									String[] tempArray = readLine.split(":", 2);
									enArray.add(tempArray[1]);
									jpArray.add(tempArray[0]);
									Arrays.fill(tempArray, ""); 
								}
						}
						catch (IOException e) {
							JOptionPane.showMessageDialog(frameMain, e);
						}
					}
					else {
						JOptionPane.showMessageDialog(frameMain, "File can't be opened or read from. Please try a different file");
					}
				}
				JOptionPane.showMessageDialog(frameMain, "Data Loaded");
			}
		});
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
		frameMain.setTitle("Vocab");
		frameMain.setResizable(false);
		frameMain.setBounds(100, 100, 300, 160);
		frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
