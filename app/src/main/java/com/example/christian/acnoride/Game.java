package com.example.christian.acnoride;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Christian on 4/29/2018.
 */

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

    }

//    public void settings(View view){
//        Intent startNewActivity = new Intent(this, Settings.class);
//        startActivity(startNewActivity);
//        this.overridePendingTransition(0, 0);
//    }
}
