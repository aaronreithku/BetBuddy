package com.example.betbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class add_bet extends AppCompatActivity {

    private Button submit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bet);
        submit_button = (Button) findViewById(R.id.button);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTrackHistory();
            }
        });
    }
    public void openTrackHistory() {
        Intent intent = new Intent(this, Track_History.class);
        startActivity(intent);
    }
}