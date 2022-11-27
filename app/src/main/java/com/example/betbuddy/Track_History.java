package com.example.betbuddy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class Track_History extends AppCompatActivity {

    private Button add_New_Bet_button;
    private Button view_stats_button;
    Button update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_history);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Snackbar snackbar= Snackbar.make(findViewById(R.id.track_history_activity), "Bet Deleted", Snackbar.LENGTH_SHORT);
            View mView = snackbar.getView();
            mView.setBackgroundColor(Color.GREEN);
            TextView mTextView = (TextView) mView.findViewById(com.google.android.material.R.id.snackbar_text);
            mTextView.setTextColor(Color.BLACK);
            mTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            snackbar.show();
        }
        update = findViewById(R.id.button2);
        delete = findViewById(R.id.button3);
        view = findViewById(R.id.button5);
        DB = new DBHelper(this);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBetList();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBetListDelete();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getUserdata();
                if(res.getCount() == 0){
                    Toast.makeText(Track_History.this,"No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Sportsbook: "+res.getString(0)+"\n");
                    buffer.append("Team 1: "+res.getString(1)+"\n");
                    buffer.append("Team 2: "+res.getString(2)+"\n");
                    buffer.append("Bet Type: "+res.getString(3)+"\n");
                    buffer.append("Odds: "+res.getString(4)+"\n");
                    buffer.append("Amount Bet: "+res.getString(5)+"\n");
                    buffer.append("Amount Won: "+res.getString(6)+"\n");
                    buffer.append("W/L: "+res.getString(7)+"\n");
                    buffer.append("Bet ID: "+res.getString(8)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(Track_History.this);
                builder.setCancelable(true);
                builder.setTitle("User Bets");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

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
        Intent intent2 = new Intent(this, view_stats_listview.class);
        startActivity(intent2);
    }

    public void openBetList(){
        Intent intent3 = new Intent(this, bet_list.class);
        startActivity(intent3);    }

    public void openBetListDelete(){
        Intent intent4 = new Intent(this, bet_list_delete.class);
        startActivity(intent4);    }
}