package com.example.christian.acnoride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    static Player ACTIVE_PLAYER = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

    }

    public void finish(View view){
        Intent startNewActivity = new Intent(this, MainMenu.class);
        startActivity(startNewActivity);
        this.overridePendingTransition(0, 0);
    }

}
