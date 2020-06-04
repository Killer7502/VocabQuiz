package main;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
