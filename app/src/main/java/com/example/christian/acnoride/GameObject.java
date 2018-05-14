package com.example.christian.acnoride;

import android.support.v7.app.AppCompatActivity;
import android.graphics.Rect;

/**
 * Created by Christian on 4/29/2018.
 */

//finished
public abstract class GameObject {
    private int x;
    private int y;
    private int dy;
    private int dx;
    private int width;
    private int height;

    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public void setDX(int dx)
    {
        this.dx = dx;
    }
    public void setDY(int dy)
    {
        this.dy = dy;
    }
    public void setHeight(int height)
    {
        this.height = height;
    }
    public void setWidth(int width) { this.width = width; }
    public int getX() { return x; }
    public int getY()
    {
        return y;
    }
    public int getDX() { return dx; }
    public int getDY()
    {
        return dy;
    }
    public int getHeight() { return height; }
    public int getWidth()
    {
        return width;
    }
    public Rect getRectangle()
    {
        return new Rect(x, y, x+width, y+height);
    }

}
