package com.example.betbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button track_history_button;
    private Button view_stats_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        track_history_button = (Button) findViewById(R.id.button);
        track_history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTrackHistory();
            }
        });
        view_stats_button = (Button) findViewById(R.id.button2);
        view_stats_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openViewStats(); }
        });

        }

    public void openTrackHistory() {
        Intent intent = new Intent(this, Track_History.class);
        startActivity(intent);
    }

    public void openViewStats() {
        Intent intent2 = new Intent(this, view_stats_listview.class);
        startActivity(intent2);
    }
}