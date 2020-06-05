package project;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class SudokuSolver {
	static JFrame frame = new JFrame();
	static JFrame frame1 = new JFrame();
	static JFrame frame2 = new JFrame();
	static final int ROWS = 17, COLS = 29;
	public static int size; // size of Sudoku grids
	public static int empty = 0;
	
	static int[][] board;
	
	static int[][] board1 = 
		{
				{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,7,7,7,7,7,0,7,7,7,0,0,0,7,0,0,0,7,7,0,7,7,0,0,0,0,2},
				{2,0,0,0,0,7,0,0,0,7,0,0,0,0,7,0,7,0,0,7,0,7,0,7,0,0,0,0,2},
				{2,0,0,0,0,7,0,0,0,7,7,7,0,7,7,7,7,7,0,7,0,0,0,7,0,0,0,0,2},
				{2,0,0,0,0,7,0,0,0,7,0,0,0,7,0,0,0,7,0,7,0,0,0,7,0,0,0,0,2},
				{2,0,0,0,0,7,0,0,0,7,7,7,0,7,0,0,0,7,0,7,0,0,0,7,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,7,7,7,7,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,7,7,7,7,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,7,7,7,7,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},};
	
	public static int[][] display1(int[][] board1) throws IOException {
		BufferedImage img = ImageIO.read(new File("images.png"));
		
		frame1.setSize(500, 500);
		frame1.setTitle("Sudoku Solver by Team 5");
		frame1.setSize(500, 500);
		frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(400, 400));
		frame1.add(new JComponent() {
			protected void paintComponent(Graphics g) {
				Rectangle rec = g.getClipBounds();
				int w = rec.width/COLS;
				int h = rec.height/ROWS;
				for (int i = 0; i < ROWS; i++) {
					for (int j = 0; j < COLS; j++) {
						g.drawImage(img, j*w, i*h, j*w+w, i*h+h, board1[i][j]*16, 0, board1[i][j]*16+16, 16, null);
					}
				}
			}
		});
		frame1.setVisible(true);
		return board1;		
	}
	// GUI method
	
	public static int[][] display(int[][] board) {
		frame2 = new JFrame();		
		frame2.setTitle("Sudoku Solver by Team 5");		
		frame2.setSize(500, 500);
		frame2.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//Big panel contains grid panel and button
		JPanel panel = new JPanel();
		JPanel gridPanel = new JPanel();
		//panel of grid 
		gridPanel.setPreferredSize(new Dimension(400, 400));
		gridPanel.setLayout(new java.awt.GridLayout(size, size, 3, 3));
		JTextArea[][] text = new JTextArea[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				text[i][j] = new JTextArea();
				text[i][j].setText("0");
				text[i][j].setEditable(true);
				Font font = new Font("Arial", Font.ITALIC, 30);
				text[i][j].setFont(font);

				gridPanel.add(text[i][j]);
			}
		}
		// panel of Solve button
		JPanel subpanel = new JPanel();
		JButton btnSolve = new JButton("Solve");
		btnSolve.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						String s = text[i][j].getText();
						board[i][j] = Integer.valueOf(s);
						helper(1);
					}
				}

			}
		});
		subpanel.add(btnSolve);
		panel.add(gridPanel, BorderLayout.WEST);
		panel.add(subpanel, BorderLayout.EAST);
		frame2.add(panel);
		frame2.setVisible(true);
		Dimension scnSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame2.setLocation(scnSize.width/4, 0);
		//frame2.setLocation(300,0);		
		while (helper(0)) {
		}	
		frame1.dispose();
		return board;
	}

	public static void solutionDisplay(int[][] board) {
		frame = new JFrame();
		frame.setTitle("Sudoku Solver by Team 5");
		frame.setSize(500, 500);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(size, size, 3, 3));
		JTextArea[][] text = new JTextArea[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				text[i][j] = new JTextArea();
				text[i][j].setText("" + board[i][j]);
				text[i][j].setEditable(false);
				Font font = new Font("Arial", Font.BOLD, 30);
				text[i][j].setFont(font);

				panel.add(text[i][j]);
			}
		}
		frame.add(panel);
		frame.setVisible(true);
		//frame.setLocation(300,500);
		Dimension scnSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(scnSize.width/4, scnSize.height/2);
	}

	public static void main(String[] args) throws IOException {		
		board1 = display1(board1);
		while (true) {
			//board = new int[0][0];
			String userInput = JOptionPane.showInputDialog("Type 4 for 4x4 and 9 for 9x9");
			size = Integer.parseInt(userInput);
			if (!userInput.equalsIgnoreCase("9") && !userInput.equalsIgnoreCase("4"))
				// catch wrong inputs
				JOptionPane.showMessageDialog(null, "Please type 4 or 9 only");
			if (userInput.equalsIgnoreCase("4"))
				board = new int[4][4]; // 4x4

			if (userInput.equalsIgnoreCase("9"))
				board = new int[9][9]; // 9x9

			board = display(board);
			boolean solve = solve(board);
			if (solve) {
				solutionDisplay(board);
			} else {
				frame.dispose();
				JOptionPane.showMessageDialog(null, "Fail to find solution.");
			}

			String userinput = JOptionPane.showInputDialog("Do you want to try again? y/n");
			if (userinput.equalsIgnoreCase("y")) {
				frame.dispose();
				frame2.dispose();
			} else {
				break;
			}
		}
		JOptionPane.showMessageDialog(null, "Have a good one!");
	}

	public static boolean solve(int[][] board) {

		for (int row = 0; row < size; row++) {

			for (int col = 0; col < size; col++) {

				if (board[row][col] == empty) {

					for (int number = 1; number <= size; number++) {

						if (checkOK(board, row, col, number, 0)) {

							board[row][col] = number;
							if (solve(board)) {
								return true;
							} else {
								board[row][col] = empty;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	public static boolean checkOK(int[][] board, int row, int col, int num, int i) {
		// check each row
		for (i = 0; i < size; i++) {
			if (board[row][i] == num) {
				return false;
			}
		}
		// check each column
		for (i = 0; i < size; i++) {
			if (board[i][col] == num) {
				return false;
			}
		}
		// check whole board for rule violations
		int sqrtSize = (int) Math.sqrt(size);
		int r = row - row % sqrtSize; // 4x4: sqrt(4)=2
		int c = col - col % sqrtSize; // 9x9: sqrt(9)=3
		for (i = r; i < r + sqrtSize; i++) {
			for (int j = c; j < c + sqrtSize; j++) {
				if (board[i][j] == num) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean check = true;

	public static boolean helper(int x) {
		if (x != 1) {
			check = true;
		} else {
			check = false;
		}
		System.out.print("");
		return check;
	}

}