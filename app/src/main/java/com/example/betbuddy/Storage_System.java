package com.example.betbuddy;

import java.util.HashMap; // import the HashMap class
import java.util.Map;
import java.util.ArrayList; //import ArrayList

//package com.example.betbuddy;

import java.util.HashMap; // import the HashMap class
import java.util.Map;
import java.util.ArrayList; //import ArrayList

public class Storage_System{

    public static Map<String, ArrayList<Bet>> multiValueMap = new HashMap<String, ArrayList<Bet>>();;

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

    public static ArrayList<Bet> get_AllBets() {
        return multiValueMap.get("Bet");
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
        //term that user will search
        //String search = "";


        //create a hashmap to store everything
        // multiValueMap = new HashMap<String, ArrayList<Bet>>();


        //get a bet and assign a property to a variable

        //  ArrayList<Bet> return_bets = new_system.get_AllBets();

        //   for (Bet bets: return_bets)
        //   {
        //       display(bets);
        //   }

    }

    static void add_Bet(Bet bet){
        //unique ID storage
        Integer new_ID = multiValueMap.size() +1;
        bet.setID(String.valueOf(new_ID));

        //try to store to an already stored key if possible, else make a new key
        try{

            multiValueMap.get(bet.getID()).add(bet);

        } catch (Exception e) {
            multiValueMap.put(bet.getID(), new ArrayList<Bet>());
            multiValueMap.get(bet.getID()).add(bet);
        }
        //this is storage for first team
        try{

            multiValueMap.get(bet.getTeam1()).add(bet);

        } catch (Exception e) {
            multiValueMap.put(bet.getTeam1(), new ArrayList<Bet>());
            multiValueMap.get(bet.getTeam1()).add(bet);
        }

        //same but for team 2
        try{

            multiValueMap.get(bet.getTeam2()).add(bet);

        } catch (Exception e) {
            multiValueMap.put(bet.getTeam2(), new ArrayList<Bet>());
            multiValueMap.get(bet.getTeam2()).add(bet);
        }

        //same but for amount
        try{

            multiValueMap.get(bet.getAmount()).add(bet);

        } catch (Exception e) {
            multiValueMap.put(bet.getAmount(), new ArrayList<Bet>());
            multiValueMap.get(bet.getAmount()).add(bet);
        }

        //same but for a general key of "bet" where all bets are stored
        try{

            multiValueMap.get("Bet").add(bet);

        } catch (Exception e) {
            multiValueMap.put("Bet", new ArrayList<Bet>());
            multiValueMap.get("Bet").add(bet);
        }
    }
}


