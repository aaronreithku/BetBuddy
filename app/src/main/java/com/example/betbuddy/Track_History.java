package com.example.betbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Track_History extends AppCompatActivity {

    private Button add_New_Bet_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_history);
        add_New_Bet_button = (Button) findViewById(R.id.button);
        add_New_Bet_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddBet();
            }
        });

    }

    public void openAddBet() {
        Intent intent = new Intent(this, fragment_addBet.class);
        startActivity(intent);
    }
}