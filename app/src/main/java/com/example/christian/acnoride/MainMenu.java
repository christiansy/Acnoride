package com.example.christian.acnoride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {

    static Player ACTIVE_USER = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

    }


    public void settings(View view){
        Intent startNewActivity = new Intent(this, Settings.class);
        startActivity(startNewActivity);
        this.overridePendingTransition(0, 0);
    }
}
