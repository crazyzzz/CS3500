public class ThreeDice { 
    int[] diceRoll = new int[3];
    int diceSize;

    public ThreeDice(int diceSize) {
        if (diceSize < 6 || diceSize > 26) {
            throw new RuntimeException("Invalid dice size");
        }
        this.diceSize = diceSize;
    }
    public int getSize() {
        return diceSize;
    }
    public void roll() {   
        diceRoll[0] = (int) ((Math.random()*diceSize))+1;
        diceRoll[1] = (int) ((Math.random()*diceSize))+1;
        diceRoll[2] = (int) ((Math.random()*diceSize))+1;
    }
    public int getDie(int index) {
        if (index < 0 || index > 2) {
            throw new RuntimeException("Index out of bounds");
        }
        return diceRoll[index];
    } 
    public String toString() {
        return diceRoll[0] + " " + diceRoll[1] + " " + diceRoll[2];
    }
	
}
