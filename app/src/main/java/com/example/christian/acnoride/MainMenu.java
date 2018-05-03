package com.example.christian.acnoride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

    }

    public void settings(View view){
        Intent startNewActivity = new Intent(this, Settings.class);
        startActivity(startNewActivity);
        //this.overridePendingTransition(0, 0);
    }
}
