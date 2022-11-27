package com.example.betbuddy;

import static com.example.betbuddy.Storage_System.display;
import static com.example.betbuddy.Storage_System.multiValueMap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        //grab table id
        TableLayout table = (TableLayout) findViewById(R.id.table1);

        try {
            ArrayList<Bet> all_bets = Storage_System.get_AllBets();
            for (Bet b: all_bets) {

                //Add textview for each piece of data
                TableRow row = new TableRow(this);
                TextView tv_team1 = new TextView(this);
                tv_team1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                tv_team1.setText(b.getTeam1());
                tv_team1.setTextColor(Color.BLACK);
                tv_team1.setPadding(10, 10 , 10, 10);
                tv_team1.setTextSize(12);
                tv_team1.setGravity(Gravity.CENTER);
                //better way to do this?
                tv_team1.setMinWidth(175);
                row.addView(tv_team1);

                TextView tv_team2 = new TextView(this);
                tv_team2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                tv_team2.setText(b.getTeam2());
                tv_team2.setTextColor(Color.BLACK);
                tv_team2.setPadding(10, 10 , 10, 10);
                tv_team2.setTextSize(12);
                tv_team2.setGravity(Gravity.CENTER);
                tv_team2.setMinWidth(175);
                row.addView(tv_team2);

                TextView tv_betType = new TextView(this);
                tv_betType.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                tv_betType.setText(b.getBetType());
                tv_betType.setTextColor(Color.BLACK);
                tv_betType.setPadding(10, 10 , 10, 10);
                tv_betType.setTextSize(12);
                tv_betType.setGravity(Gravity.CENTER);
                tv_betType.setMinWidth(175);
                row.addView(tv_betType);

                TextView tv_sportsBook = new TextView(this);
                tv_sportsBook.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                tv_sportsBook.setText(b.getSportsBook());
                tv_sportsBook.setTextColor(Color.BLACK);
                tv_sportsBook.setPadding(10, 10 , 10, 10);
                tv_sportsBook.setTextSize(12);
                tv_sportsBook.setGravity(Gravity.CENTER);
                tv_sportsBook.setMinWidth(175);
                row.addView(tv_sportsBook);

                TextView tv_Amount = new TextView(this);
                tv_Amount.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                tv_Amount.setText(b.getAmount());
                tv_Amount.setTextColor(Color.BLACK);
                tv_Amount.setPadding(10, 10 , 10, 10);
                tv_Amount.setTextSize(12);
                tv_Amount.setGravity(Gravity.CENTER);
                tv_Amount.setMinWidth(175);
                row.addView(tv_Amount);

                table.addView(row);
            }

        }

        catch (Exception e) {
            //display that no bets are stored
            TableRow row = new TableRow(this);
            TextView tv_noBets = new TextView(this);
            tv_noBets.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tv_noBets.setText("There are currently no bets stored");
            tv_noBets.setTextColor(Color.BLACK);
            tv_noBets.setPadding(10, 10 , 10, 10);
            tv_noBets.setTextSize(12);
            tv_noBets.setGravity(Gravity.CENTER);
            row.addView(tv_noBets);

            table.addView(row);
        }

    }

    public void openTrackHistory () {
        Intent intent = new Intent(this, Track_History.class);
        startActivity(intent);
    }


}