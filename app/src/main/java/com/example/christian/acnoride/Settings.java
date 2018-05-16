package com.example.christian.acnoride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Christian on 4/8/2018.
 */
//edited by James

//settings fragment view
public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

    }

    public void back(View view){
        Intent startNewActivity = new Intent(this, MainMenu.class);
        startActivity(startNewActivity);
        this.overridePendingTransition(0, 0);
    }
}
