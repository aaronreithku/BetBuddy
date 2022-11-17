package com.example.betbuddy;

import java.util.HashMap; // import the HashMap class
import java.util.Map;
import java.util.ArrayList; //import ArrayList

public class Storage_System{

    //method that displays all Bet information
    static void display(Bet bet) {
        System.out.print("Team1: ");
        System.out.print(bet.getTeam1() + " ");
        System.out.print("Team2: ");
        System.out.print(bet.getTeam2() + " ");
        System.out.print("betType: ");
        System.out.print(bet.getBetType() + " ");
        System.out.print("Sportsbook: ");
        System.out.print(bet.getSportsBook() + " \n");
        System.out.print("Amount: ");
        System.out.print(bet.getAmount() + " ");
        System.out.print("Odds: ");
        System.out.print(bet.getOdds() + " ");
        System.out.print("Gain/Loss: ");
        System.out.print(bet.getGain() + " \n\n");
    }

    static void delete_Bet(Bet bet,Map<String, ArrayList<Bet>> map) {
        //get bet by id and delete it
        try{

            map.get(bet.getID()).remove(bet);

        } catch (Exception e) {
            System.out.print("That bet does not exist so it cannot be deleted");
        }

        //delete in team1 map
        try{

            ArrayList<Bet> search = map.get(bet.getTeam1());
            for (Bet bets: search) {
                if (bets.getID() == bet.getID()) {
                    search.remove(bets);
                }
            }

        } catch (Exception e) {
            System.out.print("That bet does not exist so it cannot be deleted");
        }

        //delete in team 2 map
        try{

            ArrayList<Bet> search = map.get(bet.getTeam2());
            for (Bet bets: search) {
                if (bets.getID() == bet.getID()) {
                    search.remove(bets);
                } }
        }
        catch (Exception e) {
            System.out.print("That bet does not exist so it cannot be deleted");
        }

        //delete in amount map
        try{

            ArrayList<Bet> search = map.get(bet.getAmount());
            for (Bet bets: search) {
                if (bets.getID() == bet.getID()) {
                    search.remove(bets);
                } }

        } catch (Exception e) {
            System.out.print("That bet does not exist so it cannot be deleted");
        }

    }

    //edit a bet by replacing it with a new one
    static void edit_Bet(Bet oldBet, Bet newBet, Map<String, ArrayList<Bet>> map) {
        oldBet.setTeam1(newBet.getTeam1());
        oldBet.setTeam2(newBet.getTeam2());
        oldBet.setBetType(newBet.getBetType());
        oldBet.setSportsBook(newBet.getSportsBook());
        oldBet.setAmount(newBet.getAmount());
        oldBet.setOdds(newBet.getOdds());
        oldBet.setGain(newBet.getGain());
        delete_Bet(newBet, map);

    }

    public static void main(String[] args) {

        //implement in Android Studio, get amounts from input new form
        String input_team1 = "Bears";
        String input_team2 = "Team 2";
        String input_betType = "Type";
        String input_sportsbook = "Book";
        String input_amount = "a lot";
        String input_odds = "3/2";
        String input_gain = "none";

        //term that user will search
        String search = "";



        //Team 1, Team 2, Bet type, sportsbook, amount, odds, return
        Bet new_bet = new Bet();
        new_bet.setTeam1(input_team1);
        new_bet.setTeam2(input_team2);
        new_bet.setBetType(input_betType);
        new_bet.setSportsBook(input_sportsbook);
        new_bet.setAmount(input_amount);
        new_bet.setOdds(input_odds);
        new_bet.setGain(input_gain);

        //create a hashmap to store everything
        Map<String, ArrayList<Bet>> multiValueMap = new HashMap<String, ArrayList<Bet>>();

        //create unique ID for each bet
        Integer new_ID = multiValueMap.size() +1;
        new_bet.setID(String.valueOf(new_ID));

        //try to store to an already stored key if possible, else make a new key
        //unique ID storage
        try{

            multiValueMap.get(new_bet.getID()).add(new_bet);

        } catch (Exception e) {
            multiValueMap.put(new_bet.getID(), new ArrayList<Bet>());
            multiValueMap.get(new_bet.getID()).add(new_bet);
        }
        //this is storage for first team
        try{

            multiValueMap.get(input_team1).add(new_bet);

        } catch (Exception e) {
            multiValueMap.put(input_team1, new ArrayList<Bet>());
            multiValueMap.get(input_team1).add(new_bet);
        }

        //same but for team 2
        try{

            multiValueMap.get(input_team2).add(new_bet);

        } catch (Exception e) {
            multiValueMap.put(input_team2, new ArrayList<Bet>());
            multiValueMap.get(input_team2).add(new_bet);
        }

        //same but for amount
        try{

            multiValueMap.get(input_amount).add(new_bet);

        } catch (Exception e) {
            multiValueMap.put(input_amount, new ArrayList<Bet>());
            multiValueMap.get(input_amount).add(new_bet);
        }


        //get a bet and assign a property to a variable
        ArrayList<Bet> return_bets = multiValueMap.get(search);

        for (Bet bets: return_bets)
        {
            display(bets);
        }

    }
}


