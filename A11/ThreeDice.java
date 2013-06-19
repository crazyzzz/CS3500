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
 * three dice class. 
 * Simulates rolling three dice.
 * Three dice of sides from 6-26 are simulated.
 * Likely Math.random() is not as good as a real dice roll
 */    
public class ThreeDice { 
    //always going to be three dice
    int[] diceRoll = new int[3];
    int diceSides;

    /*
     * Constructor
     * If the dice size is withen 6-26 make a new dice set
     * otherwise, throw an exception
     */
    public ThreeDice(int diceSides) {
        if (diceSides < 6 || diceSides > 26) {
            throw new RuntimeException("Invalid dice size");
        }
        this.diceSides = diceSides;
    }
    
    /*
     * return diceSides specified in the constructor
     * is between 6-26
     */
    public int getSides() {
        return diceSides;
    }
    
    /*
     * roll
     * roll the three dice and update internal state accordingly
     * note Math.random()'s range is 0-.99
     */
    public void roll() {   
        diceRoll[0] = (int) ((Math.random()*diceSides))+1;
        diceRoll[1] = (int) ((Math.random()*diceSides))+1;
        diceRoll[2] = (int) ((Math.random()*diceSides))+1;
    }

    /*
     * returns the value of an individual die if it is one of the
     * three dice rolled
     */
    public int getDie(int index) {
        if (index < 0 || index > 2) {
            throw new RuntimeException("Index out of bounds");
        }
        return diceRoll[index];
    } 

    /*
     * overridge the toString method to display 
     * the dice internal state visually
     */
    public String toString() {
        return "Roll: [ " + diceRoll[0] + " " 
            + diceRoll[1] + " " + diceRoll[2] + " ]";
    }
	
}
