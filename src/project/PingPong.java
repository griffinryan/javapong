package project;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class PingPong extends JFrame { 
	
    static int[][] board = 
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
	
	public static int[][] display(int[][] board) throws IOException {
		JFrame frame = new JFrame();
		BufferedImage img = ImageIO.read(new File("images.png"));
		int ROWS = 17;
		int COLS = 29;
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);		
		frame.add(new JComponent() {
			protected void paintComponent(Graphics g) {
				Rectangle rec = g.getClipBounds();
				int w = rec.width/COLS;
				int h = rec.height/ROWS;
				for (int i = 0; i < ROWS; i++) {
					for (int j = 0; j < COLS; j++) {
						g.drawImage(img, j*w, i*h, j*w+w, i*h+h, board[i][j]*16, 0, board[i][j]*16+16, 16, null);
					}
				}
			}
		});
		frame.setVisible(true);
		return board;		
	}

    public PingPong() {
        super("PingPong by Team 5");
        setSize(800, 500);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new PongPanel(this));
        setVisible(true);
        Dimension scnSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(scnSize.width/4, 0);
    }

    public static void main(String[] args) throws IOException {
    	board = display(board);
        new PingPong();
    }
}
