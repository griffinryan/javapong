package project;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class PongPanel extends JPanel {
	private Player racket;
	private BouncingBall ball;
	private JLabel scoreLabel;
	private int score = 0;
	//public String timer;
	int bx, xSpeed;
	
	public PongPanel(PingPong game) {
		racket = new Player(game, game.getHeight() - 100);
		ball = new BouncingBall(game);

		scoreLabel = new JLabel(Integer.toString(score));
		scoreLabel.setFont(new Font("arial", Font.PLAIN, 30));
		add(scoreLabel);

		Timer timer = new Timer(5, new TimerHandler());
		timer.start();

		addKeyListener(new KeyHandler());
		setFocusable(true);
	}

	private void update() {
		racket.newPosition();
		ball.newPosition();
		hitSidesCheck();
		hitRacketCheck();
		repaint();
	}

	private void hitSidesCheck() {

		if (ball.getX() <= 0 || ball.getX() + (getInsets().left + getInsets().right) > getWidth() - ball.getWidth()) {
			ball.setXA(-ball.getXA()); 	

		} 
		else if (ball.getY() <= 0) {
			ball.setYA(-ball.getYA()-1); // minus 1 will change its bouncing route
										//Problem: Ball speed up every hit to side

		} 
		else if (ball.getY() > getHeight() - ball.getHeight()) {
			
			if (score >=10) {			
				JOptionPane.showMessageDialog(null, "You scored " + score + ". Well played", "Game Over",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
			JOptionPane.showMessageDialog(null, "You scored " + score, "Game Over",
					JOptionPane.INFORMATION_MESSAGE);
			}
			System.exit(0);
		}
	}

	private void hitRacketCheck() {
		if (ball.getBounds().y == racket.getBounds().y -ball.getHeight()
				&& ball.getWidth() > racket.getBounds().x - ball.getBounds().x
				&& racket.getBounds().x + racket.getWidth() > ball.getBounds().x) {
			ball.setYA(-ball.getYA()-1); 	// minus 1 will change its bouncing route
											//Problem: Ball speed up every hit to racket 
			//ball.setColor(Color.GREEN); //Trying to change ball's color after hit racket
			score+=1;
			scoreLabel.setText(Integer.toString(score));
		/**	double doubleTimer = Double.parseDouble(timer); //speeding up after certain score but not working
			doubleTimer+=1;
			timer = Double.toString( doubleTimer);*/
		}

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		racket.paint(g);
		ball.paint(g);

	}

	private class KeyHandler implements KeyListener {
		
		public void keyPressed(KeyEvent e) {
			racket.pressed(e);
		}

		
		public void keyReleased(KeyEvent e) {
			racket.released(e);
		}

		
		public void keyTyped(KeyEvent e) {
		}
	}

	private class TimerHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// xSpeed+=3;
			// bx+=xSpeed;			
			update();
		}
	}
}
