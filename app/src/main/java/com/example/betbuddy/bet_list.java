package com.example.betbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class bet_list extends AppCompatActivity implements RecyclerViewClickInterface {
    Button back_button;
    RecyclerView recyclerView;
    ArrayList<String> sportsbook, team1, team2, betType, odds, amountBet, amountWon, won;
    DBHelper DB;
    DB_Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_list);
        DB = new DBHelper(this);
        sportsbook = new ArrayList<>();
        team1 = new ArrayList<>();
        team2 = new ArrayList<>();
        betType = new ArrayList<>();
        odds = new ArrayList<>();
        amountBet = new ArrayList<>();
        amountWon = new ArrayList<>();
        won = new ArrayList<>();
        recyclerView = findViewById(R.id.betList);
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
            Toast.makeText(bet_list.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
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

    private void addBetWithID(int betPosition) {
        Intent intent = new Intent(this, add_bet.class);
        intent.putExtra("position",betPosition);
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position) {
        Cursor cursor = DB.getUserdata();
        cursor.moveToPosition(position);
        String id = cursor.getString(8);
        //Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
        addBetWithID(position);
    }
}
