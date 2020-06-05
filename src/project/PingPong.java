package project;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PingPong extends JFrame {
	static JFrame frame = new JFrame();
	static final int ROWS = 17, COLS = 29;
    //private static final int HEIGHT = 500, WIDTH = 800;
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
		
		frame.setSize(500, 500);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(400, 400));
		frame.add(new JComponent() {
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
		frame.setVisible(true);
		return board1;		
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
    	board1 = display1(board1);
        new PingPong();
    }
}