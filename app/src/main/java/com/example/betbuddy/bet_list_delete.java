package com.example.betbuddy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class bet_list_delete extends AppCompatActivity implements RecyclerViewClickInterface {
    Button back_button;
    RecyclerView recyclerView;
    ArrayList<String> sportsbook, team1, team2, betType, odds, amountBet, amountWon, won;
    DBHelper DB;
    DB_Adapter adapter;
    protected boolean close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_list_delete);
        DB = new DBHelper(this);
        sportsbook = new ArrayList<>();
        team1 = new ArrayList<>();
        team2 = new ArrayList<>();
        betType = new ArrayList<>();
        odds = new ArrayList<>();
        amountBet = new ArrayList<>();
        amountWon = new ArrayList<>();
        won = new ArrayList<>();
        recyclerView = findViewById(R.id.betListDelete);
        adapter = new DB_Adapter(this, sportsbook, team1, team2, betType, odds, amountBet, amountWon, won, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayData();

        back_button = (Button) findViewById(R.id.back);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTrackHistory();
            }
        });
    }

    private void displayData() {
        Cursor cursor = DB.getUserdata();
        if(cursor.getCount()==0)
        {
            Toast.makeText(bet_list_delete.this, "User has no existing bets", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {
                sportsbook.add(cursor.getString(0));
                team1.add(cursor.getString(1));
                team2.add(cursor.getString(2));
                betType.add(cursor.getString(3));
                odds.add(cursor.getString(4));
                amountBet.add(cursor.getString(5));
                amountWon.add(cursor.getString(6));
                won.add(cursor.getString(7));
            }
        }
    }

    private void openTrackHistory() {
        Intent intent = new Intent(this, Track_History.class);
        startActivity(intent);
    }

    private void openTrackHistoryWithSnackbar() {
        Intent intent = new Intent(this, Track_History.class);
        intent.putExtra("snackbarShow","yes");
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position) {
        close = true;
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Confirm Deletion")
                .setMessage("Are you sure you would like to delete this bet?")
                .setPositiveButton("I'm Sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        close = false;
                        Cursor cursor = DB.getUserdata();
                        cursor.moveToPosition(position);
                        String id = cursor.getString(8);
                        Boolean checkDeleteData = DB.deleteUserdata(id);
                        openTrackHistoryWithSnackbar();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        close = true;
                    }
                })
                .show();
        if(close == true)
        {
            return;
        }
    }

}