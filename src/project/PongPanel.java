package project;

import java.awt.Color; // Needed to set font to higher contrast color.
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
	Timer timer;
	int speed;

	public int input() {
		// Show up difficult option to pick
		String userInput = JOptionPane
				.showInputDialog("Difficult Level: \n1 for Turtle Mode " + "\n2 for Normal \n3 for Flash Mode");
		try {
			speed = Integer.parseInt(userInput);
			if (userInput.equalsIgnoreCase("1"))
				speed = 6;

			else if (userInput.equalsIgnoreCase("2"))
				speed = 5;

			else if (userInput.equalsIgnoreCase("3"))
				speed = 2;
			// catching wrong number input
			else {
				JOptionPane.showMessageDialog(null, "Please type in 1, 2 or 3");
				input(); // return back to difficult option panel
			}
			// catching letter input
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Please type in number only");
			input();// return back to difficult option panel
		}
		return speed;
	}

	public PongPanel(PingPong game) {
		racket = new Player(game, game.getHeight() - 100);
		ball = new BouncingBall(game);
		speed = input();
		/*
		 * Using Font.CENTER_BASELINE allows for a bolder score. Easier on the eyes
		 * while looking at the ball.
		 */
		scoreLabel = new JLabel(Integer.toString(score));
		scoreLabel.setFont(new Font("arial", Font.CENTER_BASELINE, 30));

		/*
		 * To set the color of the font, the setForeground() must be called. Since
		 * background is dark grey, the text should have more contrast. Set to white for
		 * now.
		 */
		scoreLabel.setForeground(Color.WHITE);
		add(scoreLabel);

		timer = new Timer(speed, new TimerHandler());
		timer.start();

		addKeyListener(new KeyHandler());
		setFocusable(true);

		/* Set background of frame to darker color to track the ball easier. */
		setBackground(Color.DARK_GRAY);
	}

	private class TimerHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// speed up ball at score 5 but come back to normal speed right after that.
			/*
			 * Tried to making the speed incrementation obvious, but not as hard to beat.
			 * Doesn't work though. Just start with lower speed instead. Speed = 4.
			 */
			if (score >= 5) {
				timer = new Timer(speed - 1, new TimerHandler());
				update();
			} else if (score >= 6) {
				timer = new Timer(speed - 2, new TimerHandler());
				update();
			}
			update();

		}

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

		} else if (ball.getY() <= 0) {
			ball.setYA(-ball.getYA() - 1); // minus 1 will change its bouncing route
											// Problem: Ball speed up every hit to side

		} else if (ball.getY() > getHeight() - ball.getHeight()) {

			if (score >= 10) {
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
		if (ball.getBounds().y == racket.getBounds().y - ball.getHeight()
				&& ball.getWidth() > racket.getBounds().x - ball.getBounds().x
				&& racket.getBounds().x + racket.getWidth() > ball.getBounds().x) {
			ball.setYA(-ball.getYA() - 1); // minus 1 will change its bouncing route
											// Problem: Ball speed up every hit to racket
			// ball.setColor(Color.GREEN); //Trying to change ball's color after hit racket
			score += 1;
			scoreLabel.setText(Integer.toString(score));
			/**
			 * double doubleTimer = Double.parseDouble(timer); //speeding up after certain
			 * score but not working doubleTimer+=1; timer = Double.toString( doubleTimer);
			 */
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
}
