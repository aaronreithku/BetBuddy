package com.example.betbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class view_stats_listview extends AppCompatActivity {

    ListView listView;
    Button track_history_button;
    ArrayList<String> amountBet, amountWon, won;
    DBHelper DB;
    float netEarnings, totalBet, gainPercent, totalEarnings = 0;
    int betsWon,betsLost, betsPending = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stats_listview);
        DB = new DBHelper(this);
        amountBet = new ArrayList<>();
        amountWon = new ArrayList<>();
        won = new ArrayList<>();
        fillLists();
        getStats();

        track_history_button = (Button) findViewById(R.id.button);
        track_history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTrackHistory();
            }
        });

        listView = findViewById(R.id.selectView);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Bets Won: " + betsWon);
        arrayList.add("Bets Lost: " + betsLost);
        arrayList.add("Bets Pending: " + betsPending);
        arrayList.add("Money bet: $" + totalBet);
        if(netEarnings>=0)
        {
            arrayList.add("Total Earnings: $" + totalEarnings);
        }
        else
        {
            arrayList.add("Total Earnings: -$" + (-1*totalEarnings));
        }
        if(netEarnings>=0)
        {
            arrayList.add("Net Earnings: $" + netEarnings);
        }
        else
        {
            arrayList.add("Net Earnings: -$" + (-1*netEarnings));
        }

        arrayList.add("Net Gain: " + gainPercent + "%");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(adapter);

    }

    private void getStats() {
        //total amount bet
        for(int i = 0; i < amountBet.size(); i++){
            totalBet += Float.parseFloat(amountBet.get(i));
        }

        //net earnings
        for(int i = 0; i < amountWon.size(); i++){
            if(!amountWon.get(i).equals("Pending") && won.get(i).equals("Won")) {
                netEarnings += (Float.parseFloat(amountWon.get(i))-Float.parseFloat(amountBet.get(i)));
            }
            else if (!amountWon.get(i).equals("Pending") && won.get(i).equals("Lost")){
                netEarnings += Float.parseFloat(amountWon.get(i));
            }
        }
        netEarnings = (float)(((int)(Math.pow(10,2)*netEarnings))/Math.pow(10,2));

        //totalEarnings
        for(int i = 0; i < amountWon.size(); i++){
            if(!amountWon.get(i).equals("Pending")) {
                totalEarnings += Float.parseFloat(amountWon.get(i));
            }
        }
        totalEarnings = (float)(((int)(Math.pow(10,2)*totalEarnings))/Math.pow(10,2));

        //Gain Percentage
        gainPercent = ((netEarnings)/totalBet)*100;
        gainPercent = (float)(((int)(Math.pow(10,2)*gainPercent))/Math.pow(10,2));

        //# of bets won and lost
        for(int i = 0; i < won.size(); i++){
            if(won.get(i).equals("Won")) {
                betsWon += 1;
            }
            else if(won.get(i).equals("Lost")){
                betsLost += 1;
            }
            else{
                betsPending += 1;
            }
        }

    }

    private void fillLists() {
        Cursor cursor = DB.getUserdata();
        if(cursor.getCount()==0)
        {
            Toast.makeText(view_stats_listview.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {
                amountBet.add(cursor.getString(5));
                amountWon.add(cursor.getString(6));
                won.add(cursor.getString(7));
            }
        }
    }

    public void openTrackHistory() {
        Intent intent = new Intent(this, Track_History.class);
        startActivity(intent);
    }
}