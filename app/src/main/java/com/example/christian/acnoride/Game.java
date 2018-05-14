package com.example.christian.acnoride;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
<<<<<<< HEAD
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

=======
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.lang.reflect.Array;
>>>>>>> master
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Christian on 4/29/2018.
 */


//gamePanel equivalent

<<<<<<< HEAD
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{
    public static final int WIDTH = 856;
    public static final int HEIGHT = 480;
    public static final int MOVESPEED = -5;
    //private long smokeStartTime;
    private long enemiestartTime;
    private NewThread thread;
    //private Background bg;
    private Player player;
    //private ArrayList<Smokepuff> smoke;
    private ArrayList<GameObjectEnemy> enemies;
    private Random rand = new Random();


    public GamePanel(Context context)
=======
public class Game extends SurfaceView implements SurfaceHolder.Callback {

    public static final int WIDTH = 856;
    public static final int HEIGHT = 480;
    public static final int MOVESPEED = -5;
    private long smokeStartTime;
    private NewThread thread;
    private Background bg;
    private GamePlayer player;
    private ArrayList<GameObjectEnemy> enemies;

    private Random rand = new Random();

    private long missileStartTime;

    public Game(Context context)
>>>>>>> master
    {
        super(context);


        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

<<<<<<< HEAD
        thread = new MainThread(getHolder(), this);
=======
        thread = new NewThread(getHolder(), this);
>>>>>>> master

        //make gamePanel focusable so it can handle events
        setFocusable(true);
    }

<<<<<<< HEAD
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}
=======
    //+++++++++++++++++SURFACEHOLDER METHODS+++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++
>>>>>>> master

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
<<<<<<< HEAD

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){

        //bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.grassbg1));
        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter), 65, 25, 3);
        //smoke = new ArrayList<Smokepuff>();
        enemies = new ArrayList<GameObjectEnemy>();
        smokeStartTime=  System.nanoTime();
        enemiestartTime = System.nanoTime();



        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();
=======
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}
>>>>>>> master

    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            if(!player.getPlaying())
            {
                player.setPlaying(true);
            }
            else
            {
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

    public void update()

    {
        if(player.getPlaying()) {

            //bg.update();
            player.update();

            //add enemies on timer
            long missileElapsed = (System.nanoTime()-enemiestartTime)/1000000;
            if(missileElapsed >(2000 - player.getScore()/4)){

                System.out.println("making missile");
                //first missile always goes down the middle
                if(enemies.size()==0)
                {
                    enemies.add(new GameObjectEnemy(BitmapFactory.decodeResource(getResources(),R.drawable.
                            missile),WIDTH + 10, HEIGHT/2, 45, 15, player.getScore(), 13));
                }
                else
                {

                    enemies.add(new GameObjectEnemy(BitmapFactory.decodeResource(getResources(),R.drawable.missile),
                            WIDTH+10, (int)(rand.nextDouble()*(HEIGHT)),45,15, player.getScore(),13));
                }

                //reset timer
                enemiestartTime = System.nanoTime();
            }
            //loop through every missile and check collision and remove
            for(int i = 0; i<enemies.size();i++)
            {
                //update missile
                enemies.get(i).update();

                if(collision(enemies.get(i),player))
                {
                    enemies.remove(i);
                    player.setPlaying(false);
                    break;
                }
                //remove missile if it is way off the screen
                if(enemies.get(i).getY()<-100)
                {
                    enemies.remove(i);
                    break;
                }
            }



//            //add smoke puffs on timer
//            long elapsed = (System.nanoTime() - smokeStartTime)/1000000;
//            if(elapsed > 120){
//                smoke.add(new Smokepuff(player.getX(), player.getY()+10));
//                smokeStartTime = System.nanoTime();
//            }

//            for(int i = 0; i<smoke.size();i++)
//            {
//                smoke.get(i).update();
//                if(smoke.get(i).getX()<-10)
//                {
//                    smoke.remove(i);
//                }
//            }
        }
    }
    public boolean collision(GameObject a, GameObject b)
    {
        if(Rect.intersects(a.getRectangle(),b.getRectangle()))
        {
            return true;
        }
        return false;
    }
    @Override
<<<<<<< HEAD
    public void draw(Canvas canvas)
    {
        final float scaleFactorX = getWidth()/(WIDTH*1.f);
        final float scaleFactorY = getHeight()/(HEIGHT*1.f);

        if(canvas!=null) {
            final int savedState = canvas.save();



            canvas.scale(scaleFactorX, scaleFactorY);
            //bg.draw(canvas);
            //player.draw(canvas);
            //draw smokepuffs
//            for(Smokepuff sp: smoke)
//            {
//                sp.draw(canvas);
//            }
            //draw enemies
            for(GameObjectEnemy m: enemies)
            {
                m.draw(canvas);
            }

            canvas.restoreToCount(savedState);
        }
=======
    public void surfaceCreated(SurfaceHolder holder){

        //bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.grassbg1));
        player = new GamePlayer(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter), 65, 25, 3);


        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();
    }
    //+++++++++++++++++END SURFACEHOLDER METHODS+++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++



    public void update() {
        if (player.getPlaying()) {

            bg.update();
            player.update();

            //add missiles on timer
            long missileElapsed = (System.nanoTime() - missileStartTime) / 1000000;
            if (missileElapsed > (2000 - player.getScore() / 4)) {

                System.out.println("making missile");
                //first missile always goes down the middle
                if (enemies.size() == 0) {
                    enemies.add(new GameObjectEnemy(BitmapFactory.decodeResource(getResources(), R.drawable.
                            missile), WIDTH + 10, HEIGHT / 2, 45, 15, player.getScore(), 13));
                } else {

                    enemies.add(new GameObjectEnemy(BitmapFactory.decodeResource(getResources(), R.drawable.missile),
                            WIDTH + 10, (int) (rand.nextDouble() * (HEIGHT)), 45, 15, player.getScore(), 13));
                }

                //reset timer
                missileStartTime = System.nanoTime();
            }
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
                if (enemies.get(i).getX() < -100) {
                    enemies.remove(i);
                    break;
                }
            }
        }
    }

    /*
    public void update()
    {
        if(player.getPlaying()) {

            if(botborder.isEmpty())
            {
                player.setPlaying(false);
                return;
            }
            if(topborder.isEmpty())
            {
                player.setPlaying(false);
                return;
            }

            bg.update();
            player.update();

            //calculate the threshold of height the border can have based on the score
            //max and min border heart are updated, and the border switched direction when either max or
            //min is met

            maxBorderHeight = 30+player.getScore()/progressDenom;
            //cap max border height so that borders can only take up a total of 1/2 the screen
            if(maxBorderHeight > HEIGHT/4)maxBorderHeight = HEIGHT/4;
            minBorderHeight = 5+player.getScore()/progressDenom;

            //check bottom border collision
            for(int i = 0; i<botborder.size(); i++)
            {
                if(collision(botborder.get(i), player))
                    player.setPlaying(false);
            }

            //check top border collision
            for(int i = 0; i <topborder.size(); i++)
            {
                if(collision(topborder.get(i),player))
                    player.setPlaying(false);
            }

            //update top border
            this.updateTopBorder();

            //udpate bottom border
            this.updateBottomBorder();

            //add missiles on timer
            long missileElapsed = (System.nanoTime()-missileStartTime)/1000000;
            if(missileElapsed >(2000 - player.getScore()/4)){


                //first missile always goes down the middle
                if(missiles.size()==0)
                {
                    missiles.add(new Missile(BitmapFactory.decodeResource(getResources(),R.drawable.
                            missile),WIDTH + 10, HEIGHT/2, 45, 15, player.getScore(), 13));
                }
                else
                {

                    missiles.add(new Missile(BitmapFactory.decodeResource(getResources(),R.drawable.missile),
                            WIDTH+10, (int)(rand.nextDouble()*(HEIGHT - (maxBorderHeight * 2))+maxBorderHeight),45,15, player.getScore(),13));
                }

                //reset timer
                missileStartTime = System.nanoTime();
            }
            //loop through every missile and check collision and remove
            for(int i = 0; i<missiles.size();i++)
            {
                //update missile
                missiles.get(i).update();

                if(collision(missiles.get(i),player))
                {
                    missiles.remove(i);
                    player.setPlaying(false);
                    break;
                }
                //remove missile if it is way off the screen
                if(missiles.get(i).getX()<-100)
                {
                    missiles.remove(i);
                    break;
                }
            }

            //add smoke puffs on timer
            long elapsed = (System.nanoTime() - smokeStartTime)/1000000;
            if(elapsed > 120){
                smoke.add(new Smokepuff(player.getX(), player.getY()+10));
                smokeStartTime = System.nanoTime();
            }

            for(int i = 0; i<smoke.size();i++)
            {
                smoke.get(i).update();
                if(smoke.get(i).getX()<-10)
                {
                    smoke.remove(i);
                }
            }
        }
        else{
            player.resetDY();
            if(!reset)
            {
                newGameCreated = false;
                startReset = System.nanoTime();
                reset = true;
                dissapear = true;
                explosion = new Explosion(BitmapFactory.decodeResource(getResources(),R.drawable.explosion),player.getX(),
                        player.getY()-30, 100, 100, 25);
            }

            explosion.update();
            long resetElapsed = (System.nanoTime()-startReset)/1000000;

            if(resetElapsed > 2500 && !newGameCreated)
            {
                newGame();
            }


        }

    }*/

    //collision checking
    public boolean collision(GameObject a, GameObject b){
        if (Rect.intersects(a.getRectangle(), b.getRectangle())){
            return true;
        }
        return false;
>>>>>>> master
    }

//    @Override
//    public void draw(Canvas canvas)
//    {
//        final float scaleFactorX = getWidth()/(WIDTH*1.f);
//        final float scaleFactorY = getHeight()/(HEIGHT*1.f);
//
//        if(canvas!=null) {
//            final int savedState = canvas.save();
//            canvas.scale(scaleFactorX, scaleFactorY);
//            bg.draw(canvas);
//            if(!dissapear) {
//                player.draw(canvas);
//            }
//
//            for (GameObjectEnemy e: enemies){
//                e.draw(canvas);
//            }
//
//
//            canvas.restoreToCount(savedState);
//
//        }
//    }

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        final float scaleFactorX = getWidth()/(WIDTH*1.f);
        final float scaleFactorY = getHeight()/(HEIGHT*1.f);

        if(canvas!=null) {
            final int savedState = canvas.save();


//YEAAAAH
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            player.draw(canvas);
            //draw
            for (GameObjectEnemy e: enemies){
                e.draw(canvas);
            }



            canvas.restoreToCount(savedState);
        }
        //super.draw(canvas);//???
    }


}
