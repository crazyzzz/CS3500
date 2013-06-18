/*
 * Kevin Langer
 * Anders Dahl
 * Valerie Charry
 * A11 - Bunco
 *
 *
 */

import java.lang.String;
import java.lang.Math;

public class Bunco {
    int diceSize; 
    Player[] players;
    boolean seeDice;
    boolean seeScore;
    int round;
    
    Bunco(int diceSize, Player[] players, boolean seeDice, boolean seeScore) {
        this.diceSize = diceSize; 
        this.players = players; 
        this.seeDice = seeDice; 
        this.seeScore = seeScore; 
        round = 1;
    }
    String getScore() {
        String scores = "";
        if ( seeScore ) {
            for (int i = 0; i < players.length; i++ ) {
                scores += players[i] + "\n";
            }
        }
        return scores;
    }
    void newRound() {
        round++;
        for (int i =0; i < players.length; i++ ) {
            players[i].newRound();
        }
        System.out.println("Round: " + round);  
        //new java.util.Scanner(System.in).nextLine();
    }
    boolean playAllPlayers() {
        //for (int i = 0; i < players.length; i++) {
        int count = 0;
        int i;
        while (round < 22) {
            i = count%players.length;
            while ( players[i].play(diceSize,round) ) { 
                //System.out.println(players[i].getDice());  
                //System.out.println(getScore());          
            }
            if ( players[i].wonRound()) { 
                newRound();
            }
            if (round == diceSize+1) {
                return false;
            }
            count++;
        }
        return true;
    }
    void play() {
        while (playAllPlayers()) { }
    }
    public static Player player(String name) {
        return new Player(name, true, true);
    }
       
}
class Player { 
    String name; 
    int score; 
    int roundScore;
    int buncos;
    int bigBuncos;
    int[] diceRoll;
    boolean seeScore;
    boolean seeDice;

    Player(String name, boolean seeScore, boolean seeDice) {
        this.name = name; 
        this.seeScore = seeScore;
        this.seeDice = seeDice;
        score = 0;
        buncos = 0;
        bigBuncos = 0;
        diceRoll = new int[3];
    }
    public boolean play(int diceSize, int round) {
        boolean scored;
        getRoll(diceSize);
        if (seeDice) {
            System.out.println(getDice());
            //new java.util.Scanner(System.in).nextLine();
        }        
        scored = scoreRoll(round);
        if (seeScore) {
            System.out.println(this);
            //new java.util.Scanner(System.in).nextLine();
        }
        return  scored;
    }
    private void getRoll(int diceSize) {   
        diceRoll[0] = (int) ((Math.random()*diceSize))+1;
        diceRoll[1] = (int) ((Math.random()*diceSize))+1;
        diceRoll[2] = (int) ((Math.random()*diceSize))+1;
    }
    private boolean scoreRoll(int round) {
        int score = 0;
        for ( int i = 0; i < 3; i++) {
            if (diceRoll[i] == round) {
                score++;
            }
        }
        if (score == 3) {
            score = 5;
        }
        this.score += score;
        this.roundScore += score;
        return (score != 0 && roundScore < 22);
    }   
    public boolean wonRound() {
        return roundScore >= 21;
    }
    public void newRound() {
        roundScore = 0;
    }
    public String getDice() {
        return diceRoll[0] + " " + diceRoll[1] + " " + diceRoll[2];
    }
    public String toString() {
        String toString = name;
        if (seeScore) {
            toString = toString + " score: " + score;
        }
        return toString;
    }
}
