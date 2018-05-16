package com.example.christian.acnoride;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.MainThread;

import android.util.Log;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Christian on 4/29/2018.
 */
//edited by James

//gamePanel equivalent

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    public static final int WIDTH = 856;
    public static final int HEIGHT = 480;
    public static final int MOVESPEED = -5;
    private NewThread thread;
    private Background bg;
    private GamePlayer player;
    private GameObjectLaneThreshold gameBar;
    private ArrayList<GameObjectEnemy> enemies;

    private Random rand = new Random();

    private long enemyStartTime;

    //button enemy interaction algorithim part 1
    boolean canBeDestroyed = false;

    //

    public Game(Context context)
    {
        super(context);

        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        thread = new NewThread(getHolder(), this);

        //make gamePanel focusable so it can handle events
        setFocusable(true);
    }

    //+++++++++++++++++SURFACEHOLDER METHODS+++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        int counter = 0;
        while(retry && counter<1000)
        {
            counter++;
            try{thread.setRunning(false);
                thread.join();
                retry = false;

            }catch(InterruptedException e){e.printStackTrace();}
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){

        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.grassbg1));
        player = new GamePlayer(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter), 65, 25, 3);
        gameBar = new GameObjectLaneThreshold(BitmapFactory.decodeResource(getResources(),R.drawable.greybar1),1000,35,1);
        bg.setVector(MOVESPEED);
        enemies = new ArrayList<GameObjectEnemy>();
        enemyStartTime = System.nanoTime();

        //safely start the game loop
        thread.setRunning(true);
        thread.start();
    }
    //+++++++++++++++++END SURFACEHOLDER METHODS+++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            if(!player.getPlaying()) {
                player.setPlaying(true);
                player.setUp(true);
            }
            if(player.getPlaying()) {
                player.setUp(true);
            }
            return true;
        }

        if(event.getAction()==MotionEvent.ACTION_UP)
        {
            player.setUp(false);
            return true;
        }

        return super.onTouchEvent(event);
    }

    public void update() {
        if (player.getPlaying()) {

            bg.update();
            player.update();

            //add missiles on timer
            long missileElapsed = (System.nanoTime() - enemyStartTime) / 1000000;
            if (missileElapsed > (2000 - player.getScore() / 4)) {

                System.out.println("making enemy");
                //first missile always goes down the middle
                if (enemies.size() == 0) {
                    enemies.add(new GameObjectEnemy(BitmapFactory.decodeResource(getResources(), R.drawable.
                            missile), WIDTH / 2, HEIGHT * 10, 45, 15, player.getScore(), 1));
                } else {

                    enemies.add(new GameObjectEnemy(BitmapFactory.decodeResource(getResources(), R.drawable.missile),
                            (int) (rand.nextDouble() * (WIDTH)), HEIGHT + 10, 45, 15, player.getScore(), 1));
                }

                //reset timer
                enemyStartTime = System.nanoTime();
            }
            Log.i("GameActivity", "ENEMY CREATED");

            //loop through every missile and check collision and remove
            for (int i = 0; i < enemies.size(); i++) {
                //update missile
                enemies.get(i).update();

                if (collision(enemies.get(i), player)) {
                    enemies.remove(i);
                    player.setPlaying(false);
                    break;
                }
                //remove missile if it is way off the screen
                if (enemies.get(i).getY() < -45) {
                    enemies.remove(i);
                    Log.i("GameActivity", "ENEMY REMOVED");
                    break;
                }
            }
        }
    }

    //collision checking
    public boolean collision(GameObject a, GameObject b){
        if (Rect.intersects(a.getRectangle(), b.getRectangle())){
            return true;
        }
        return false;
    }

    @Override
    public void draw(Canvas canvas) //treat this as a warning code WILL compile and run!!!
    {
        //super.draw(canvas);
        final float scaleFactorX = getWidth()/(WIDTH*1.f);
        final float scaleFactorY = getHeight()/(HEIGHT*1.f);

        if(canvas!=null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            player.draw(canvas);
            gameBar.draw(canvas);
            //draw
            for (GameObjectEnemy e: enemies){
                e.draw(canvas);
            }

            canvas.restoreToCount(savedState);
        }
    }
}