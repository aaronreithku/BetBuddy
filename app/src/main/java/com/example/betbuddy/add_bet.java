package com.example.betbuddy;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class add_bet extends AppCompatActivity {

    private Button submit_button;
    private Button back_button;
    private Button spread_button;
    private Button ML_button;
    private Button overUnder_button;
    private Button won_button;
    private int[] checkDoneArray = {1000019, 1000018, 1000004, 1000015, 1000014};


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bet);
        spread_button = (Button) findViewById(R.id.betSpread);
        ML_button = (Button) findViewById(R.id.betMoneyline);
        ML_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOdds();
            }
        });
        overUnder_button = (Button) findViewById(R.id.OverUnder);
        overUnder_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPresetOdds();
            }
        });
        spread_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPresetOdds();
            }
        });
        submit_button = (Button) findViewById(R.id.button);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDone();
                createNewBet();
                openTrackHistory();
            }
        });
        back_button = (Button) findViewById(R.id.back);
        back_button.setOnClickListener(new View.OnClickListener() {
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

    public void checkDone(){
        for (int i=0;i<5;i++) {
            EditText text = (EditText) findViewById(checkDoneArray[i]);
            String value = text.getText().toString();
            String newBetError = "Error: One or more boxes empty";
            TextView errorMessage = (TextView) findViewById(R.id.notDone);
            while (value == "") {
                errorMessage.setText(newBetError);
            }
            errorMessage.setText("");
        }
    }

    public void createNewBet(){
        //get team one from user
        EditText user_t1 = (EditText) findViewById(R.id.teamOne);
        String first_team = user_t1.getText().toString();

        //get team two from user
        EditText user_t2 = (EditText) findViewById(R.id.teamTwo);
        String second_team = user_t2.getText().toString();

        //get sportsbook from user
        EditText user_book = (EditText) findViewById(R.id.sportsbook);
        String  sports_book= user_book.getText().toString();

        //get odds from user
        EditText user_odds = (EditText) findViewById(R.id.odds);
        String string_odds = user_book.getText().toString();

        //get amount bet
        EditText user_amount = (EditText) findViewById(R.id.amountBet);
        String amount_bet = user_amount.getText().toString();


        Bet user_bet = new Bet();
        user_bet.setTeam1(first_team);
        user_bet.setTeam1(second_team);
        user_bet.setSportsBook(sports_book);
        user_bet.setOdds(string_odds);
        user_bet.setAmount(amount_bet);
    }

    public void setPresetOdds() {
        EditText text = (EditText) findViewById(R.id.odds);
        text.setText("-110");
    }

    public void resetOdds() {
        EditText text = (EditText) findViewById(R.id.odds);
        text.setText("");
    }
/*
    public void printText(View view) {
        EditText text = (EditText) findViewById(R.id.teamOne);
        String value = text.getText().toString();
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(value);

    }
 */
}