package com.example.christian.acnoride;

import android.graphics.Bitmap;
import android.graphics.Canvas;
//edited by James

public class GameObjectLaneThreshold extends GameObject{
    private Bitmap spritesheet;
    private int score;

    private boolean up;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;

    public GameObjectLaneThreshold(Bitmap res, int w, int h, int numFrames) {

        super.setX(0);
        super.setY(350);
        super.setHeight(h);
        super.setWidth(w);

        Bitmap[] image = new Bitmap[numFrames];
        spritesheet = res;

        for (int i = 0; i < image.length; i++)
        {
            image[i] = Bitmap.createBitmap(spritesheet, i, 0, super.getWidth(), super.getHeight());
        }

        animation.setFrames(image);
        animation.setDelay(0);
        startTime = System.nanoTime();
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(animation.getImage(),super.getX(),super.getY(),null);
    }
}
