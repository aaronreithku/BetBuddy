package com.example.betbuddy;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.UUID;

public class add_bet extends AppCompatActivity {

    private Button submit_button;
    private Button back_button;
    private Button spread_button;
    private Button ML_button;
    private Button overUnder_button;
    private Button won_button;
    private Button lost_button;
    RadioGroup rgBetType;
    RadioGroup rgWon;
    DBHelper DB;
    Boolean updateMode;
    //private int[] checkDoneArray = {1000021, 1000019, 1000004, 1000017, 1000016};
//teamOne, teamTwo, Sportsbook, Odds, Bet Amount

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bet);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int betPosition = getIntent().getIntExtra("position",0);
            populateFields(betPosition);
            updateMode = true;
        }
        else{
            updateMode = false;
        }

        spread_button = (Button) findViewById(R.id.betSpread);
        ML_button = (Button) findViewById(R.id.betMoneyline);
        DB = new DBHelper(this);
        ML_button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                RadioButton lastRadioBtn = (RadioButton) findViewById(R.id.OverUnder);
                lastRadioBtn.setError(null);
                resetOdds();

            }
        });
        overUnder_button = (Button) findViewById(R.id.OverUnder);
        overUnder_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton lastRadioBtn = (RadioButton) findViewById(R.id.OverUnder);
                lastRadioBtn.setError(null);
                setPresetOdds();
            }
        });
        spread_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton lastRadioBtn = (RadioButton) findViewById(R.id.OverUnder);
                lastRadioBtn.setError(null);
                setPresetOdds();
            }
        });
        submit_button = (Button) findViewById(R.id.button);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //checkDone();
                if (checkDoneExperimental()){
                    return;
                }
                if (updateMode == true) {
                    int betPosition = getIntent().getIntExtra("position",0);
                    Cursor cursor = DB.getUserdata();
                    cursor.moveToPosition(betPosition);
                    String sameKey = cursor.getString(8);
                    updateUserBet(sameKey);
                }
                else{
                    createNewBet();
                }
                submitSuccess();
                updateMode = false;
                //openTrackHistory();
            }
        });
        back_button = (Button) findViewById(R.id.back);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTrackHistory();
            }
        });

        RadioButton won_button = (RadioButton) findViewById(R.id.betWon);
        won_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcEarnings();
                /*
                if (!won_button.isSelected()) {
                    won_button.setChecked(true);
                    won_button.setSelected(true);
                    calcEarnings();
                } else {
                    won_button.setChecked(false);
                    won_button.setSelected(false);
                    EditText text = (EditText) findViewById(R.id.earningsBet);
                    text.setText("");
                }
                 */
            }
        });

        RadioButton lost_button = (RadioButton) findViewById(R.id.betLost);
        lost_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText text = (EditText) findViewById(R.id.earningsBet);
                EditText betAmount = (EditText) findViewById(R.id.amountBet);
                String amountBet = betAmount.getText().toString();
                if(amountBet.length() != 0) {
                    text.setText("-" + amountBet);
                }
            }
        });

    }

    private boolean checkDoneExperimental() {
        boolean errorFlag = false;
        EditText text = (EditText) findViewById(R.id.teamOne);
        if (text.length() == 0) {
            text.setError("Please Complete Field");
            errorFlag = true;
        }
        text = (EditText) findViewById(R.id.teamTwo);
        if (text.length() == 0) {
            text.setError("Please Complete Field");
            errorFlag = true;
        }
        text = (EditText) findViewById(R.id.odds);
        if (text.length() == 0) {
            text.setError("Please Complete Field");
            errorFlag = true;
        }
        text = (EditText) findViewById(R.id.amountBet);
        if (text.length() == 0) {
            text.setError("Please Complete Field");
            errorFlag = true;
        }
        text = (EditText) findViewById(R.id.sportsbook);
        if (text.length() == 0) {
            text.setError("Please Complete Field");
            errorFlag = true;
        }
        rgBetType = findViewById(R.id.Group1);
        int checkedId = rgBetType.getCheckedRadioButtonId();
        RadioButton lastRadioBtn = (RadioButton) findViewById(R.id.OverUnder);
        if(checkedId == -1){
            lastRadioBtn.setError("Please Complete Field");
            errorFlag = true;
        }
        else{
            lastRadioBtn.setError(null);
        }
        return(errorFlag);
    }

    public void openTrackHistory() {
        Intent intent = new Intent(this, Track_History.class);
        startActivity(intent);
    }

    /*
    public void checkDone(){
       for (int i=0;i<5;i++) {
            EditText text = (EditText) findViewById(R.id.teamOne);
            String value = text.getText().toString();
            String newBetError = "Error: One or more boxes empty";
            TextView errorMessage = (TextView) findViewById(R.id.notDone);
            if (text.length() == 0) {
                text.setError("Please Complete Field");
            }
            errorMessage.setText("");
        }
    }
    */

    public void createNewBet(){
        Bet user_bet = new Bet();

        //get team one from user
        EditText user_t1 = (EditText) findViewById(R.id.teamOne);
        String first_team = user_t1.getText().toString();

        //get team two from user
        EditText user_t2 = (EditText) findViewById(R.id.teamTwo);
        String second_team = user_t2.getText().toString();

        //get sportsbook from user
        EditText user_book = (EditText) findViewById(R.id.sportsbook);
        String sports_book= user_book.getText().toString();

        //get odds from user
        EditText user_odds = (EditText) findViewById(R.id.odds);
        String string_odds = user_odds.getText().toString();

        //get amount bet
        EditText user_amount = (EditText) findViewById(R.id.amountBet);
        String amount_bet = user_amount.getText().toString();

        //get earnings
        EditText user_amountWon = (EditText) findViewById(R.id.earningsBet);
        String amount_Won = user_amountWon.getText().toString();

        //get bet type
        rgBetType = findViewById(R.id.Group1);
        int checkedId = rgBetType.getCheckedRadioButtonId();

        //add whether the user has won lost or pending
        rgWon = findViewById(R.id.Group2);
        int checkedIdWon = rgWon.getCheckedRadioButtonId();

        findRadioButton(checkedId, user_bet);
        if(checkedIdWon == -1){
            user_bet.setWon("Pending");
        }
        else{
            findRadioButtonWon(checkedIdWon, user_bet);
        }
        user_bet.setTeam1(first_team);
        user_bet.setTeam2(second_team);
        user_bet.setSportsBook(sports_book);
        user_bet.setOdds(string_odds);
        user_bet.setAmount(amount_bet);
        if(amount_Won.length() == 0){
            user_bet.setAmountWon("Pending");
        }
        else{
            user_bet.setAmountWon(amount_Won);
        }

        Boolean checkinsertdata = DB.insertUserdata(user_bet.getSportsBook(), user_bet.getTeam1(), user_bet.getTeam2(), user_bet.getBetType(), user_bet.getOdds(), user_bet.getAmount(), user_bet.getAmountWon(), user_bet.getWon(), getKey());
        //Storage_System new_system = new Storage_System();
        //new_system.add_Bet(user_bet);
    }

    private void findRadioButton(int checkedId, Bet user_bet) {
        switch (checkedId){
            case R.id.betSpread:
                user_bet.setBetType("Spread");
                break;

            case R.id.betMoneyline:
                user_bet.setBetType("Moneyline");
                break;

            case R.id.OverUnder:
                user_bet.setBetType("O/U");
                break;
        }
    }

    private void findRadioButtonWon(int checkedId, Bet user_bet) {
        switch (checkedId){
            case R.id.betWon:
                user_bet.setWon("Won");
                break;

            case R.id.betLost:
                user_bet.setWon("Lost");
                break;

        }
    }

    public void calcEarnings() {
        boolean oddsFilled = false;
        boolean betAmountFilled = false;
        double earnings;
        EditText odds = (EditText) findViewById(R.id.odds);
        EditText betAmount = (EditText) findViewById(R.id.amountBet);
        EditText betEarnings = (EditText) findViewById(R.id.earningsBet);
        if (odds.getText().toString().trim().length() != 0) {
            oddsFilled = true;
        }
        if (betAmount.getText().toString().trim().length() != 0) {
            betAmountFilled = true;
        }
        if (oddsFilled == true && betAmountFilled == true) {
            double multiplicandOdds = Double.parseDouble(odds.getText().toString());
            double multiplicandAmount = Double.parseDouble(betAmount.getText().toString());
            if (multiplicandOdds < 100) {
                earnings = (multiplicandAmount/(multiplicandOdds/-100))+multiplicandAmount;
            }
            else{
                earnings = ((multiplicandAmount*multiplicandOdds)/100)+multiplicandAmount;
            }
            earnings = (Math.round(earnings * 100) / 100.0);
            String stringEarnings = earnings+"";
            betEarnings.setText(stringEarnings);
        }

    }

    public void setPresetOdds() {
        EditText text = (EditText) findViewById(R.id.odds);
        text.setText("-110");
        text.setError(null);

    }

    public void resetOdds() {
        EditText text = (EditText) findViewById(R.id.odds);
        text.setText("");
    }

    public void submitSuccess(){

        EditText text = (EditText) findViewById(R.id.sportsbook);
        text.setText("");

        text = (EditText) findViewById(R.id.teamOne);
        text.setText("");

        text = (EditText) findViewById(R.id.teamTwo);
        text.setText("");

        text = (EditText) findViewById(R.id.odds);
        text.setText("");

        text = (EditText) findViewById(R.id.amountBet);
        text.setText("");

        text = (EditText) findViewById(R.id.earningsBet);
        text.setText("");

        rgBetType = findViewById(R.id.Group1);
        rgBetType.clearCheck();

        rgWon = findViewById(R.id.Group2);
        rgWon.clearCheck();

        if(updateMode == false) {
            Snackbar snackbar = Snackbar.make(findViewById(R.id.add_bet_activity), "Bet Submitted", Snackbar.LENGTH_SHORT);
            View mView = snackbar.getView();
            mView.setBackgroundColor(Color.GREEN);
            TextView mTextView = (TextView) mView.findViewById(com.google.android.material.R.id.snackbar_text);
            mTextView.setTextColor(Color.BLACK);
            mTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            snackbar.show();
        }

    }

    private String getKey()
    {
        String id = UUID.randomUUID().toString().substring(0,8);
        return id;
    }

    public void populateFields(int betPosition){
        DB = new DBHelper(this);
        Cursor cursor = DB.getUserdata();
        cursor.moveToPosition(betPosition);
        String sameKey = cursor.getString(8);

        EditText text = (EditText) findViewById(R.id.sportsbook);
        text.setText(cursor.getString(0));

        text = (EditText) findViewById(R.id.teamOne);
        text.setText(cursor.getString(1));

        text = (EditText) findViewById(R.id.teamTwo);
        text.setText(cursor.getString(2));

        rgBetType = findViewById(R.id.Group1);
        String betType = cursor.getString(3);

        if(betType.length() == 6){
           rgBetType.check(R.id.betSpread);
        }
        else if(betType.length() == 9){
            rgBetType.check(R.id.betMoneyline);
        }
        else if(betType.length() == 3){
            rgBetType.check(R.id.OverUnder);
        }

        rgWon = findViewById(R.id.Group2);
        String isWon = cursor.getString(7);

        if(isWon.equals("Won")){
            rgWon.check(R.id.betWon);
        }
        else if(isWon.equals("Lost")){
            rgWon.check(R.id.betLost);
        }

        text = (EditText) findViewById(R.id.odds);
        text.setText(cursor.getString(4));

        text = (EditText) findViewById(R.id.amountBet);
        text.setText(cursor.getString(5));


        text = (EditText) findViewById(R.id.earningsBet);
        if(!cursor.getString(6).equals("Pending"))
        {
            text.setText(cursor.getString(6));
        }

    }

    private void updateUserBet(String sameKey) {
        Bet user_bet = new Bet();

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
        String string_odds = user_odds.getText().toString();

        //get amount bet
        EditText user_amount = (EditText) findViewById(R.id.amountBet);
        String amount_bet = user_amount.getText().toString();

        //get earnings
        EditText user_amountWon = (EditText) findViewById(R.id.earningsBet);
        String amount_Won = user_amountWon.getText().toString();

        //get bet type
        rgBetType = findViewById(R.id.Group1);
        int checkedId = rgBetType.getCheckedRadioButtonId();

        //add whether the user has won lost or pending
        //add whether the user has won lost or pending
        rgWon = findViewById(R.id.Group2);
        int checkedIdWon = rgWon.getCheckedRadioButtonId();

        findRadioButton(checkedId, user_bet);
        if(checkedIdWon == -1){
            user_bet.setWon("Pending");
        }
        else{
            findRadioButtonWon(checkedIdWon, user_bet);
        }

        findRadioButton(checkedId, user_bet);
        user_bet.setTeam1(first_team);
        user_bet.setTeam2(second_team);
        user_bet.setSportsBook(sports_book);
        user_bet.setOdds(string_odds);
        user_bet.setAmount(amount_bet);
        if(amount_Won.length() == 0){
            user_bet.setAmountWon("Pending");
        }
        else{
            user_bet.setAmountWon(amount_Won);
        }

        Boolean checkupdatedata = DB.updateUserdata(user_bet.getSportsBook(), user_bet.getTeam1(), user_bet.getTeam2(), user_bet.getBetType(), user_bet.getOdds(), user_bet.getAmount(), user_bet.getAmountWon(), user_bet.getWon(), sameKey);
        if(checkupdatedata == true) {
            Snackbar snackbar = Snackbar.make(findViewById(R.id.add_bet_activity), "Bet Updated", Snackbar.LENGTH_SHORT);
            View mView = snackbar.getView();
            mView.setBackgroundColor(Color.GREEN);
            TextView mTextView = (TextView) mView.findViewById(com.google.android.material.R.id.snackbar_text);
            mTextView.setTextColor(Color.BLACK);
            mTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            snackbar.show();
        }
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