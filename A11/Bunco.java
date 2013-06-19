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
    ThreeDice dice;
    Player[] players;
    boolean seeDice;
    boolean seeScore;
    int round;
    
    Bunco(int diceSize, Player[] players, boolean seeDice, boolean seeScore) {
        dice = new ThreeDice(diceSize);
        this.players = players;  
        this.seeDice = seeDice;  
        this.seeScore = seeScore; 
        round = 0;               
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
          
        if ( round > dice.getSize()) {
            printStats();
        } else {
            System.out.println("Round: " + round);
        } 
        
        //new java.util.Scanner(System.in).nextLine();
    }
    void printStats() {
         System.out.println();
        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i].scoreBoard() + " ");
        }
        //System.out.println();
    }
    public void play() {
        int count = 0;
        int i;
        newRound();

        while (round < dice.getSize()+1) {
            i = count%players.length;
            while ( players[i].turn(round) ) { }
            if ( players[i].wonRound()) {                 
                newRound();
            }
            count++;
        }
    }
    public Player player(String name) {
        return new Player(name, dice, seeScore, seeDice );
    }
       
}

class Player { 
    String name; 
    int score; 
    int roundScore;
    int buncos;
    int bigBuncos;
    int wonRounds;
    ThreeDice dice; 

    boolean seeScore;
    boolean seeDice;

    Player(String name, ThreeDice dice, boolean seeScore, boolean seeDice) {
        this.name = name; 
        this.seeScore = seeScore;
        this.seeDice = seeDice;
        this.dice = dice;
        score = 0;
        buncos = 0;
        bigBuncos = 0;
    }
    public boolean turn(int round) {
        boolean scored;
        dice.roll();
        if (seeDice) {
            System.out.println(dice);
            //new java.util.Scanner(System.in).nextLine();
        }        
        scored = scoreRoll(round);
        if (seeScore) {
            System.out.println(this);
            //new java.util.Scanner(System.in).nextLine();
        }
        return  scored;
    }
    
    private boolean scoreRoll(int round) {
        int score = 0;
        for ( int i = 0; i < 3; i++) {
            if (dice.getDie(i) == round) {
                buncos++;
                score++;
            }
        }
        if (score == 3) {
            bigBuncos++;
            buncos-=3;
            score = 5;
        }
        if (score == 2) {
            buncos--;
            score--;
        }
        this.score += score;
        this.roundScore += score;
        return (score != 0 && roundScore < 22);
    }   
    public boolean wonRound() {
        if (roundScore >= 21) {
            wonRounds++;
            return true;
        }
        return false;
    }
    public void newRound() {
        roundScore = 0;
    }
   
    public String scoreBoard() {
        return this.toString() + "\n\tBuncos: " + buncos 
            + "\n\tBig Buncos: " + bigBuncos + "\n\trounds won " + wonRounds;     
    }
    public String toString() {
        String toString = name;
        if (seeScore) {
            toString = toString + " score: " + score;
        }
        return toString;
    }
    public boolean equals(Object o) {
        if (o instanceof Player) {
            return ((Player) o).name.equals(this.name);
        }
        return false;
    }
}

