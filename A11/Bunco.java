/*
 * Kevin Langer
 * Anders Dahl
 * Valerie Charry
 * 
 * langer.k@husky.neu.edu
 * charry.v@husky.neu.edu
 * dahl.a@husky.neu.edu
 * 
 * Group 1
 *
 * Directions to compile & play: 
 * make && make play
 */

/*
 * Bunco class
 * Repository of players and paramaters. 
 * Instantiates and stores players.
 * Dispatches gameplay. 
 * Owns the scoreboard. 
 * Owns the dice
 */
public class Bunco {

    ThreeDice dice; //Class for dispatching dice
    Player[] players;
    boolean seeDice;
    boolean seeScore;
    int round;
    ScoreBoard s; //Class for displaying score

    /*
     * constructor
     * requires diceSize to create dice class
     * requires players to instantiate turns and scoring
     * requires seeDice and seeScore to control display
     */
    Bunco(int diceSize, Player[] players, boolean seeDice, boolean seeScore) {
        dice = new ThreeDice(diceSize);
        s = new ScoreBoard(players);
        this.players = players;  
        this.seeDice = seeDice;  
        this.seeScore = seeScore; 
        round = 0;               
    }
    
    /*
     * Resets bunco round. 
     * displays new rounds. 
     * sets all round counters to zero.
     */
    void newRound() {
        round++;
        for (int i =0; i < players.length; i++ ) {
            players[i].newRound();
        }
          
        if ( round > dice.getSides()) {
        } else {
            System.out.println("Round: " + round);
        } 
            
        //new java.util.Scanner(System.in).nextLine();
    }
    
    /*
     * Plays the bunco game. 
     * Plays each players turn, in-order, for all rounds.
     * Players turns last if they continue to gain points (up to 21).
     * Rounds continue until one player achives 21 points.  
     * Round numbers are increased up to the sides of the dice.
     * Round number dictates which number side on the dice is valid.
     */
    public void play() {
        int count = 0;
        int i;
        newRound();

        while (round < dice.getSides()+1) {
            i = count%players.length;
            while ( players[i].turn(round) ) { }
            if ( players[i].wonRound()) {
                if (seeScore) {
                    System.out.println(s.printStats());
                }                 
                newRound();
            }
            count++;
        }
    }

    /*
     * facotry method for creating new players, such that 
     * the player class can remain defined with the default
     * modifer. Increases encapsulation and security by
     * making sure that the dice paramaters are the same among
     * all players
     */
    public Player player(String name) {
        return new Player(name, dice, seeDice );
    }

    /*
     * Override the string method with ScoreBoard's toString method.
     * Calling toString on Bunco displays all players scores and names.
     */
    public String toString() {
        return s.toString();
    }      
}

/*
 * Score class
 * Score is the basis for Player.
 * Score's functions are used purely for
 * scoreboarding. 
 */
class Score {

    String name;

    int score; 
    int roundScore;
    int buncos;
    int bigBuncos;
    int wonRounds;
    
    /*
     * returns player's name
     */
    public String getName() {
        return name;
    }

    /*
     * returns player's score
     */
    public int getScore() {
        return score;
    }

    /*
     * returns player's total buncos
     */
    public int getBuncos() {
        return buncos;
    }

    /*
     * returns player's total big buncos
     */
    public int getBigBuncos() {
        return bigBuncos;
    }

    /*
     * returns player's total number of rounds won
     */
    public int getWonRounds() {
        return wonRounds;
    }

    /*
     * Overrides toString method
     * Returns player's score with player's name
     */
    public String toString() {
        return name + " has " + score + " points";
    }
    
    /*
     * Overrides equals method
     * Only relevant information for equals is the name.
     */
    public boolean equals(Object o) {
        if (o instanceof Score) {
            return ((Score) o).getName().equals(this.getName());
        }
        return false;
    }

    /*
     * Overrides hashCode method
     * Only relevant information for hashCode is the name.
     * note this funciton is for future use, and is not currently used
     */
    public int hashCode() {
        return getName().hashCode();
    }

}

/* 
 * Player class
 * Implements turns and scoring.
 * Is a member of Score. 
 * Knows when it has won the round, and can reset itself
 */
class Player extends Score { 

    //define ROUND_POINTS to be 21
    private static final int ROUND_POINTS = 21; 
    //There are three dice
    private static final int NUMBER_OF_DICE = 3;

    //Store the dice class
    ThreeDice dice; 

    boolean seeScore;
    boolean seeDice;

    /*
     * Player constructor
     * Must store the name, the dice, and the display option for dice
     */
    Player(String name, ThreeDice dice, boolean seeDice) {
        this.name = name; 
        this.seeScore = seeScore;
        this.seeDice = seeDice;
        this.dice = dice;
        score = 0;
        buncos = 0;
        bigBuncos = 0;
    }

    /*
     * Runs a single roll and scores it. 
     * returns true if it is still the player's turn
     * false if the player lost turn from losing a dice
     * roll or earning 21 points.
     */
    public boolean turn(int round) {
        boolean scored;
        //Roll the dice for the user
        dice.roll();

        //Print if requested
        if (seeDice) {
            System.out.println(name + "'s turn!");
            System.out.println(dice);
        }        
        //Score the roll and update internal state
        scored = scoreRoll(round);
        //new java.util.Scanner(System.in).nextLine();
        return  scored;
    }
    /*
     * After a roll, evalulate how many points are aquired.
     * Can increment points by 0, 1, 2, 3, or 5 on any call.
     * Also increments bunco and bigBunco accordingly.
     * Will never exced 21 round points. 
     * will return false when the player's turn is over
     */
    private boolean scoreRoll(int round) {
        int score = 0;
        boolean littleBunco = true;

        for ( int i = 0; i < NUMBER_OF_DICE; i++) {
            if (dice.getDie(i) == round) {
                littleBunco = false;
                score++;
            } else if ( i > 0 && dice.getDie(i) != dice.getDie(i-1) ) {
                littleBunco = false;
            }
        }
        
        if (score == 3) {
            bigBuncos++;
            score = 5;
            //System.out.println("Big bunco!");
        } else if ( littleBunco ) {
            buncos++;
            score = 3;
            //System.out.println("Little bunco!");
        }
        
        this.score += score;
        this.roundScore += score;
        if (roundScore > ROUND_POINTS) {
            this.score += ROUND_POINTS-roundScore;
        }
        return (score != 0 && score != 3 && roundScore <= ROUND_POINTS);
    }   

    /*
     * Called to see if the player has achived over 21 points.
     */
    public boolean wonRound() {
        if (roundScore >= ROUND_POINTS) {
            wonRounds++;
            return true;
        }
        return false;
    }
    
    /*
     *resets the roundScore counter to restart the round
     */
    public void newRound() {
        roundScore = 0;
    }
}

    

