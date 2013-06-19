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

import java.util.Scanner;

/*
 * Starts and runs Bunco
 * Decides how many players, their names,
 * and their preferences
 */
public class PlayBunco {

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in); //Capture user input
        Player[] players = getBlankPlayers(in); //create colleciton of players
        boolean seeDice = seeDice(in); //Do we want to see dice?
        boolean seeScore = seeScore(in); //Do we want to see the score?
        int diceSize = getDiceSides(in); //how many sides (6-26)?
        //Instantiate the environment
        Bunco b = new Bunco(diceSize,players,seeDice,seeScore);
        getNames(players,b,in); //Fill in all the player-names (previously left blank)
        b.play(); //Play the entire game
        System.out.println(b); //print stats
    }

    /*
     * produce a blank array of players
     * with the same length as the amount of
     * players in the game
     */
    static Player[] getBlankPlayers(Scanner in) {
        System.out.print("Please enter the number of players in this game: ");
        int numPlayers = in.nextInt();
        Player[] players;
        //Checks to make sure the number of players meets specs
        if (numPlayers >= 2 && numPlayers <= 10) {
            players = new Player[numPlayers];
        } else {
            throw new RuntimeException("Invalid number of players");
        }
        //Chomp rest of line
        in.nextLine();
        System.out.println();
        return players;
    }

    /*
     * specifies if the dice are visible after every roll
     */
    static boolean seeDice(Scanner in) {
        while (true) {
            //Loop until anwser is y/n
            System.out.print(
                "Would you like to see the dice after each roll [Y/N]: ");
            String lineInput = in.nextLine().toLowerCase();
            if (  lineInput.indexOf('y') == 0 ) {
                System.out.println();
                return true;
            } else if ( lineInput.indexOf('n') == 0 ) {
                break;
            } else {
                System.out.println("Invalid input");
            }
            System.out.println();
        }
        return false;
    }

    /*
     * specifies if the scores are visible after every round
     */
    static boolean seeScore(Scanner in) {
        while (true) {
            //Loop until anwser is y/n
            System.out.print(
                "Would you like to see the score after each round [Y/N]: ");
            String lineInput = in.nextLine().toLowerCase();
            if (  lineInput.indexOf('y') == 0 ) {
                System.out.println();
                return true;
            } else if ( lineInput.indexOf('n') == 0 ) {
                break;
            } else {
                System.out.println("Invalid input");
            }
            System.out.println();
        }
        return false;
    }

    /*
     * specifies the sides of the dice (between 6-26)
     */
    static int getDiceSides(Scanner in) {
        int diceSize;
        do {
        System.out.print("Pick a dice size between 6-26: ");
        //Loop until dice is between 6-26
        diceSize = in.nextInt();
        //Chomp rest of line
        in.nextLine();
        } while (diceSize < 6 || diceSize > 26);
        return diceSize;
    }

    /*
     * Fills the players with name references
     * duplicate names are not allowed, case sensitive
     */
    static void getNames(Player[] players, Bunco b, Scanner in) {
        int count = 0;
        boolean dup = false; 
        while (count < players.length) {
            dup = false;
            System.out.print("Please enter player " + (count+1) + "'s name: ");
            String lineInput = in.nextLine();
            Player p = b.player(lineInput);
            /*
             * in the event of duplicate users, 
             * warn the user and loop until the name
             * provided is useable
             */
            for (int i = 0; i < count; i++) {
                if (players[i].equals(p)) { 
                    dup = true;
                    break;
                }
            }
            if (!dup) {
                players[count] = p;
                count++;
            } else {
                System.out.println("The name is already taken");
            }
        }
    }
    
}
