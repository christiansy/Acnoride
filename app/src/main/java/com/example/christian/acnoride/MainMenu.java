package com.example.christian.acnoride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.view.MenuItem;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        TextView welcomeText = (TextView) findViewById(R.id.welcome);
        welcomeText.setText("Welcome back,"+ Login.ACTIVE_PLAYER.getUsername() );
        //back button
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //title change
        this.setTitle("Acnoride Menu");
    }

    //back button process
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == android.R.id.home){
            // activity end
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void settings(View view){
        Intent startNewActivity = new Intent(this, Settings.class);
        startActivity(startNewActivity);
        this.overridePendingTransition(0, 0);
    }

    public void start(View view){
        Intent startNewActivity = new Intent(this, Game.class);
        startActivity(startNewActivity);
        this.overridePendingTransition(0, 0);
    }
}
