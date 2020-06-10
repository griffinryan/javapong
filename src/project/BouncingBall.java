package project;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class BouncingBall extends Sprite {

    int colorCount = 0; // Set a counter to make color change not flash.
    int red, green, blue;
    // Removed color variable.

    public BouncingBall(PingPong game) {
        super(0, 0, 1, 1, 30, 30, Color.RED);
    }

    public void newPosition() {
        setX(getX() + getXA());
        setY(getY() + getYA());
    }

    public void paint(Graphics g) {
        Random r = new Random();
        g.setColor(new Color(red, green, blue));
    	// g.setColor(color);
        g.fillOval(getX(), getY(), getWidth(), getHeight());
    }
}