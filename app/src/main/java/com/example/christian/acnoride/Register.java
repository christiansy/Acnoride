package com.example.christian.acnoride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

    }

    public void finish(View view){
        EditText findName = (EditText) findViewById(R.id.regUserName);
        EditText findPassword = (EditText) findViewById(R.id.regPW);

        String username = findName.getText().toString();
        String password = findPassword.getText().toString();

        //db player addition
        Player player = new Player();
        player.setUsername(username);
        player.setPassword(password);
        player.setScore("0");
        player.setRank("0");
        PlayerDB db = new PlayerDB(this);
        Log.d("Player added:", player.toString());
        db.insertNewPlayer(player);

        //player was inserted into database, make sure its there before continuing
        Player findPlayer = db.findUsername(player.getUsername());
        if (findPlayer != null) {
            Login.ACTIVE_PLAYER = findPlayer;
            Intent startNewActivity = new Intent(this, MainMenu.class);
            startActivity(startNewActivity);
            this.overridePendingTransition(0, 0);
        }
    }

}
