package project;

import java.awt.Color;
import java.awt.Rectangle;

public class Sprite {
    private int x, y, xa, ya, width, height;
	public Sprite(int x, int y, int xa, int ya, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.xa = xa;
        this.ya = ya;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getXA() {
        return xa;
    }

    public int getYA() {
        return ya;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setXA(int xa) {
        this.xa = xa;
    }

    public void setYA(int ya) {
        this.ya = ya;
    }
}