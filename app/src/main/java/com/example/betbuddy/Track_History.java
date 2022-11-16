package com.example.betbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Track_History extends AppCompatActivity {

    private Button add_New_Bet_button;
    private Button view_stats_button;

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
        view_stats_button = (Button) findViewById(R.id.button4);
        view_stats_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewStats();
            }
        });
    }

    public void openAddBet() {
        Intent intent = new Intent(this, add_bet.class);
        startActivity(intent);
    }

    public void openViewStats() {
        Intent intent2 = new Intent(this, View_Stats.class);
        startActivity(intent2);
    }
}