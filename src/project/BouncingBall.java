package project;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class BouncingBall extends Sprite {
	// Color color = Color.RED;
    public BouncingBall(PingPong game) {
        super(0, 0, 1, 1, 30, 30, Color.RED);
    }

    public void newPosition() {
        setX(getX() + getXA());
        setY(getY() + getYA());
    }

    public void paint(Graphics g) {
    	Random r = new Random();
    	g.setColor(new Color(155 + r.nextInt(100), 225 + r.nextInt(30), 0)); //random color changing but too fast
    	// g.setColor(color);
        g.fillOval(getX(), getY(), getWidth(), getHeight());
    }
}