package com.example.christian.acnoride;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;
/**
 * Created by Christian on 4/29/2018.
 */
//edited by James

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
        super.x = x;
        super.y = y;
        width = w;
        height = h;
        score = s;

        speed = 15;


        Bitmap[] image = new Bitmap[numFrames];

        spritesheet = res;

        for(int i = 0; i<image.length;i++)
        {
            image[i] = Bitmap.createBitmap(spritesheet, i*width, 0, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(100-speed);

    }
    public void update()
    {
        y-=speed;
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
        return width-10;
    }
    @Override
    public int getHeight()
    {
        //offset slightly for more realistic collision detection
        return height-10;
    }
}


