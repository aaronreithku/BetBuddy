package com.example.betbuddy;

import static com.example.betbuddy.Storage_System.display;
import static com.example.betbuddy.Storage_System.multiValueMap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class View_Stats extends AppCompatActivity {

    private Button track_history_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stats);
        track_history_button = (Button) findViewById(R.id.button);
        track_history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTrackHistory();
            }
        });
    }

        // TableLayout tl=(TableLayout)findViewById(R.id.table1);
        //TableRow tr1 = new TableRow(this);
        //tr1.setLayoutParams(new TableRow.LayoutParams( TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        //TextView textview = new TextView(this);
        //textview.setText(data);
        //textview.getTextColors(R.color.)
        //textview.setTextColor(Color.WHITE);
        //tr1.addView(textview);
        //tl.addView(tr1, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        //new table layout idea
        //TableLayout table = (TableLayout) findViewById(R.id.table1);
/*
        ArrayList<Bet> all_bets = Storage_System.get_AllBets();
        for (Bet b: all_bets) {

            // Inflate your row "template" and fill out the fields.
           // TableRow row = (TableRow) LayoutInflater.from(View_Stats.this).inflate(R.layout.activity_view_stats, null);
            //((TextView) row.findViewById(R.id.attrib_team1)).setText(b.getTeam1());
            //((TextView)row.findViewById(R.id.attrib_team2)).setText(b.getTeam2());
            //((TextView)row.findViewById(R.id.attrib_betType)).setText(b.getBetType());
            //((TextView)row.findViewById(R.id.attrib_sportsBook)).setText(b.getSportsBook());
            // ((TextView)row.findViewById(R.id.attrib_amount)).setText(b.getAmount());
           // table.addView(row);
            // table.requestLayout();
        }
    }
    */

    public void openTrackHistory() {
        Intent intent = new Intent(this, Track_History.class);
        startActivity(intent);
    }


}
