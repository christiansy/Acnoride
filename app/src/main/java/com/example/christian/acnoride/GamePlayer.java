package com.example.christian.acnoride;

import android.graphics.Bitmap;
import android.graphics.Canvas;
//edited by James

public class GamePlayer extends GameObject{
    private Bitmap spritesheet;
    private int score;

    private boolean up;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;

    public GamePlayer(Bitmap res, int w, int h, int numFrames) {

        super.setX(100);
        super.setY(Game.HEIGHT / 2);
        super.setDY(0);
        score = 0;
        super.setHeight(h);
        super.setWidth(w);

        Bitmap[] image = new Bitmap[numFrames];
        spritesheet = res;

        for (int i = 0; i < image.length; i++)
        {
            image[i] = Bitmap.createBitmap(spritesheet, i*super.getWidth(), 0, super.getWidth(), super.getHeight());
        }

        animation.setFrames(image);
        animation.setDelay(10);
        startTime = System.nanoTime();

    }

    public void setUp(boolean b){up = b;}

    public void update()
    {
        long elapsed = (System.nanoTime()-startTime)/1000000;
        if(elapsed>100)
        {
            score++;
            startTime = System.nanoTime();
        }
        animation.update();

        if(up){
            //dy -=1;
            super.setDY(super.getDY()-1);
        }
        else{
            //dy +=1;
            super.setDY(super.getDY()+1);
        }

        if(super.getDY()>14)super.setDY(14);
        if(super.getDY()<-14)super.setDY(-14);

        //y += dy*2;
        super.setY(super.getDY()*2);

    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(animation.getImage(),super.getX(),super.getY(),null);
    }
    public int getScore(){return score;}
    public boolean getPlaying(){return playing;}
    public void setPlaying(boolean b){playing = b;}
    public void resetDY(){super.setDY(0);}
    public void resetScore(){score = 0;}
}
