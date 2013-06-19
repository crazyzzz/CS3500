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
 * class ScoreBoard
 * Creates Strings for displaying for
 * a collection of Scores
 */
public class ScoreBoard {

    Score[] scores; //Rembering that Player extends Score

    /*
     * Scoreboard only holds a reference to external scores
     */
    ScoreBoard(Score[] scores) {
        this.scores = scores; 
    }

    /* 
     * Produce a string where the highest
     * score is returned (along with an asscoated name) 
     * is returned among all players
     */
    String totalScoreWinner() {
        int max = -1;
        String total = "Score winner!\n\t";
        //Find maxium
        for (int i = 0; i < scores.length; i++) {
            //find max
            if (scores[i].getScore() > max) {
                max = scores[i].getScore();
            }
        }
        for (int i = 0; i < scores.length; i++) {
            //print all with that maximum value (including ties)
            if(scores[i].getScore() == max) {
                total += scores[i].getName() 
                    + " with " + max + " points\n\t";
            }
        }
        total += "\n";
        return total;
    }

    /* 
     * Produce a string where the highest
     * number of rounds won
     * (along with an asscoated name) 
     * is returned among all players
     */
    String roundsWinner() {
        int max = -1;
        String total = "Score rounds winner!\n\t";
        for (int i = 0; i < scores.length; i++) {
            //find max
            if (scores[i].getWonRounds() > max) {
                max = scores[i].getWonRounds();
            }
            
        }
        for (int i = 0; i < scores.length; i++) {
            //print all with that maximum value (including ties)
            if(scores[i].getWonRounds() == max) {
                total += scores[i].getName() 
                    + " with " + max + " rounds\n\t";
            }
        }
        total += "\n";
        return total;
    }

    /* 
     * Produce a string where the highest
     * number of buncos
     * (along with an asscoated name) 
     * is returned among all players
     */
    String littleBuncoWinner() {
        int max = -1;
        String total = "Score bunco!\n\t";
        for (int i = 0; i < scores.length; i++) {
            //find max
            if (scores[i].getBuncos() > max) {
                max = scores[i].getBuncos();
            }
        }
        for (int i = 0; i < scores.length; i++) {
            //print all with that maximum value (including ties)
            if(scores[i].getBuncos() == max) {
                total += scores[i].getName() 
                + " with " + max + " buncos\n\t";
            }
        }
        total += "\n";
        return total;
    }

    /* 
     * Produce a string where the highest
     * number of big buncos
     * (along with an asscoated name) 
     * is returned among all players
     */
    String bigBuncoWinner() {
        int max = -1;
        String total = "Score big bunco winner!\n\t";
        for (int i = 0; i < scores.length; i++) {
            //find max
            if (scores[i].getBigBuncos() > max) {
                max = scores[i].getBigBuncos() ;
            }
        }
        for (int i = 0; i < scores.length; i++) {
            if(scores[i].getBigBuncos() == max) {
                //print all with that maximum value (including ties)
                total += scores[i].getName() 
                + " with " + max + " big buncos\n\t";
            }
        }
        total += "\n";
        return total;
    }

    /*
     * Display simple score and name for all contendors
     */
    public String printStats() {
        String total = "\n";
        for( int i = 0; i < scores.length; i++) {
            total += scores[i] + "\n";
        }
        return total+"\n";
    }

    /*
     * overrides the toString method.
     * Produces the entire winners display
     */
    public String toString() {
        String total = 
        printStats() +
        totalScoreWinner() +
        roundsWinner() +
        littleBuncoWinner() +
        bigBuncoWinner();

        return total;  
    }
}
