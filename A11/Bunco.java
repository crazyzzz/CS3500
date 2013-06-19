/*
 * Kevin Langer
 * Anders Dahl
 * Valerie Charry
 * A11 - Bunco
 *
 *
 */

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
        } else {
            System.out.println("Round: " + round);
        } 
        
        //new java.util.Scanner(System.in).nextLine();
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

    public static Player player(String name, 
    boolean seeScore, boolean seeDice, int diceSize) {
        return new Player(name, new ThreeDice(diceSize), seeScore, seeDice );
    }

    public String toString() {
        ScoreBoard s = new ScoreBoard(players);
        return s.toString();
    }      
}

class Score {

    String name;

    int score; 
    int roundScore;
    int buncos;
    int bigBuncos;
    int wonRounds;
    
    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
    public int getBuncos() {
        return buncos;
    }
    public int getBigBuncos() {
        return bigBuncos;
    }
    public int getWonRounds() {
        return wonRounds;
    }

    public String toString() {
        return name + " has " + score + " points";
    }
    public boolean equals(Object o) {
        if (o instanceof Player) {
            return ((Player) o).name.equals(this.name);
        }
        return false;
    }

}
class Player extends Score { 

    private static final int ROUND_POINTS = 21; 

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
        if (seeDice || seeScore) {
            System.out.println(name + "'s turn!");
        }
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
                score++;
            }
        }
        if (score < 3 && score > 0) {
            buncos++;
            score = 1;
        } else if (score == 3) {
            bigBuncos++;
            score = 5;
        }

        this.score += score;
        this.roundScore += score;
        return (score != 0 && roundScore <= ROUND_POINTS);
    }   
    public boolean wonRound() {
        if (roundScore >= ROUND_POINTS) {
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
    
}

    

