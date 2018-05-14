package com.example.christian.acnoride;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;
/**
 * Created by Christian on 4/29/2018.
 */


//change from missile to enemy spawner
    //vertical movement
    //change destroy on collision
    
public class GameObjectEnemy extends GameObject{
    private int score;
    private int speed;
    private Random rand = new Random();
    private Animation animation = new Animation();
    private Bitmap spritesheet;

    public GameObjectEnemy(Bitmap res, int x, int y, int w, int h, int s, int numFrames)
    {
        super.setX(x);
        super.setY(y);
        super.setHeight(h);
        super.setWidth(w);
        score = s;

        speed = 7 + (int) (rand.nextDouble()*score/30);

        //cap missile speed
        if(speed>40)speed = 40;

        Bitmap[] image = new Bitmap[numFrames];

        spritesheet = res;

        for(int i = 0; i<image.length;i++)
        {
            image[i] = Bitmap.createBitmap(spritesheet, 0, i*this.getHeight(), this.getWidth(), this.getHeight());
        }

        animation.setFrames(image);
        animation.setDelay(100-speed);

    }
    public void update()
    {
        this.setY(this.getY()-speed);
        animation.update();
    }
    public void draw(Canvas canvas)
    {
        try{
            canvas.drawBitmap(animation.getImage(),this.getX(),this.getY(),null);
        }catch(Exception e){}
    }

    @Override
    public int getWidth()
    {
        //offset slightly for more realistic collision detection
        return this.getWidth()-10;
    }

}


