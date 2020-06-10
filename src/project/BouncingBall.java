package project;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class BouncingBall extends Sprite {

    int colorCount = 0; // Set a counter to make color change not flash. Could be boolean.
    int red = 80;
    int blue = 255;
    int green = 0;
    // Removed color variable. Added color int variables.

    public BouncingBall(PingPong game) {
        super(0, 0, 1, 1, 30, 30, Color.RED);
    }

    public void newPosition() {
        setX(getX() + getXA());
        setY(getY() + getYA());
    }

    public void paint(Graphics g) {
        /* Update color values for glow. || red >= 253 and && red >= 80 && red <= 254*/
        if(colorCount == 1){
            red = red - 1;
            if(red == 80){
                // Set count equal to 0.
                colorCount = 0;
            }
        } else if(colorCount == 0){
            red = red + 1;
            if(red == 254){
                // Set count equal to 1 for reversing glow.
                colorCount = 1;
            }
        }

        g.setColor(new Color(red, green, blue));
        
        // Random r = new Random(); No longer needed with incrementing color.
    	// g.setColor(color);   No longer needed with incrementing color.
        g.fillOval(getX(), getY(), getWidth(), getHeight());
    }
}