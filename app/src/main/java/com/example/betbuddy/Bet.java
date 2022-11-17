package com.example.betbuddy;

public class Bet {

    private String team1;
    private String team2;
    private String betType;
    private String sportsBook;
    private String amount;
    private String odds;
    private String gain;
    private String ID;

    //getters and setters for all betting characteristics
    //everything stored as strings for easier storage
    void setTeam1(String t){
        team1 = t;
    }

    String getTeam1(){
        return team1;
    }

    void setTeam2(String t){
        team2 = t;
    }

    String getTeam2(){
        return team2;
    }

    void setBetType(String b){
        betType = b;
    }

    String getBetType(){
        return betType;
    }

    void setSportsBook(String s){
        sportsBook = s;
    }

    String getSportsBook() {
        return sportsBook;
    }

    void setAmount(String a){
        amount = a;
    }

    String getAmount(){
        return amount;
    }

    void setOdds(String o){
        odds = o;
    }

    String getOdds(){
        return odds;
    }

    void setGain(String g) {
        gain = g;
    }

    String getGain(){
        return gain;
    }

    void setID(String i) {
        ID = i;
    }

    String getID() {
        return ID;
    }

}