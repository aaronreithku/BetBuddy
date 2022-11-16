package com.example.betbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class View_Stats extends AppCompatActivity {

    private Button track_history_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stats);
        track_history_button = (Button) findViewById(R.id.button);
        track_history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openTrackHistory(); }
        });

    }

    public void openTrackHistory() {
        Intent intent = new Intent(this, Track_History.class);
        startActivity(intent);
    }
}