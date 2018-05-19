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
import android.graphics.Typeface;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;

/**
 * Created by Christian on 4/29/2018.
 */
//edited by James

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    public static final int WIDTH = 856;
    public static final int HEIGHT = 480;
    public static final int MOVESPEED = -5;
    private NewThread thread;
    private Background bg;
    private GamePlayer player;
    private GameObjectLaneThreshold gameBar;
    private ArrayList<GameObjectEnemy> enemies;
    int total_score_counter = 0;
    private Random rand = new Random();

    private long enemyStartTime;

    //button enemy interaction algorithim part 1
    private boolean canBeDestroyed = false;
    private boolean screenPress = false;
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

        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.blankcanvas));
        player = new GamePlayer(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter), 65, 25, 3);
        gameBar = new GameObjectLaneThreshold(BitmapFactory.decodeResource(getResources(),R.drawable.greybar1),1000,70,1);
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
        if(event.getAction()==MotionEvent.ACTION_DOWN ){ //User presses screen
            if(!player.getPlaying()) {
                player.setPlaying(true);
                player.setUp(true);
                screenPress = true;
            }
            if(player.getPlaying()) {
                player.setUp(true);
            }
            screenPress = true;
            return true;
        }

        if(event.getAction()==MotionEvent.ACTION_UP)   //user is not pressing screen
        {
            player.setUp(false);
            screenPress = false;
            return true;
        }

        return screenPress;
    }

    public void destroyEnemy(boolean a, boolean b, GameObjectEnemy e, boolean c)
    {
        if (screenPress  && canBeDestroyed)
        {
            enemies.remove(e);
            c = true;
            total_score_counter += 25;
        }
    }

    public void update() {
        boolean destroyed = false;

        if (player.getPlaying()) {

            bg.update();
            player.update();

            //add missiles on timer
            long timeElapsed = (System.nanoTime() - enemyStartTime) / 800000;
            if (timeElapsed > (2000 - player.getScore() / 4)) {

                System.out.println("making enemy");
                //first missile always goes down the middle
                if (enemies.size() == 0) {
                    enemies.add(new GameObjectEnemy(BitmapFactory.decodeResource(getResources(), R.drawable.android),
                            (int) (rand.nextDouble() * (WIDTH)), HEIGHT + 5, 110, 110, player.getScore(), 1));
                } else {
                    enemies.add(new GameObjectEnemy(BitmapFactory.decodeResource(getResources(), R.drawable.android),
                            (int) (rand.nextDouble() * (WIDTH)), HEIGHT + 5, 110, 110, player.getScore(), 1));
                }

                //reset timer
                enemyStartTime = System.nanoTime();
            }
            Log.i("GameActivity", "ENEMY CREATED");

            //loop through every missile and check collision and remove
            for (int i = 0; i < enemies.size(); i++) {
                //update missile
                enemies.get(i).update();

                if (collision(enemies.get(i), gameBar)) {
                    canBeDestroyed = true;
                    destroyEnemy(canBeDestroyed, screenPress, enemies.get(i),destroyed);
                    //player.setPlaying(false); //game over
                }
                if (destroyed == true)
                {
                    total_score_counter += 25;
                }
                destroyed = false;
                //remove missile if it is way off the screen
                if (enemies.get(i).getY() < -200) {
                    enemies.remove(i);
                    Log.i("GameActivity", "ENEMY REMOVED");
                    total_score_counter -= 25;
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
            //player.draw(canvas);
            gameBar.draw(canvas);
            //draw
            for (GameObjectEnemy e: enemies){
                e.draw(canvas);
            }
            drawText(canvas);
            canvas.restoreToCount(savedState);
        }
    }

    public void drawText(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("SCORE: " + total_score_counter, 10, HEIGHT - 10, paint);
        if(!player.getPlaying())
        {
            Paint paint1 = new Paint();
            paint1.setTextSize(40);
            paint1.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            canvas.drawText("PRESS TO START", WIDTH/2-50, HEIGHT/2, paint1);

            paint1.setTextSize(20);
            canvas.drawText("PRESS THE SCREEN WHEN THE ANDROIDS", WIDTH/2-50, HEIGHT/2 + 20, paint1);
            canvas.drawText("HIT THE BAR", WIDTH/2-50, HEIGHT/2 + 40, paint1);
        }
    }
}
