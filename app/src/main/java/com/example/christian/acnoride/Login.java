package com.example.christian.acnoride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Login extends AppCompatActivity {

    static Player ACTIVE_PLAYER = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

    }

    public void login(View view){
        EditText enteredUserName = (EditText)findViewById(R.id.inputUserName);
        EditText enteredPassword = (EditText)findViewById(R.id.inputPassWord);
        String userNameCheck = enteredUserName.getText().toString();
        String userPassCheck = enteredPassword.getText().toString();

        PlayerDB dbHandler = new PlayerDB(this);

        Player currentPlayer = dbHandler.findUsername(userNameCheck);
        TextView errorOut = (TextView) findViewById(R.id.errorOut);

        if (currentPlayer != null)
        {   Log.d("currentPlayer", currentPlayer.toString());
            if (currentPlayer.getPassword().equals(userPassCheck))
            {
                setACTIVE_PLAYER(currentPlayer);

                Intent startNewActivity = new Intent(this, MainMenu.class);
                startActivity(startNewActivity);
                this.overridePendingTransition(0, 0);
            }
            else
                errorOut.setText("Invalid Password");
        }
        else
            errorOut.setText("Invalid Username");

    }

    public void register(View view){
        Intent startNewActivity = new Intent(this, Register.class);
        startActivity(startNewActivity);
        this.overridePendingTransition(0, 0);
    }


    public void setACTIVE_PLAYER(Player activeName)
    {
        this.ACTIVE_PLAYER = activeName;
    }

    public Player getACTIVE_PLAYER(){

        return this.ACTIVE_PLAYER;
    }


}
