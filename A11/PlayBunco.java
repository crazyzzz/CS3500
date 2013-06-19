import java.util.Scanner;

public class PlayBunco {

    static Player[] getEmptyPlayers(Scanner in) {
        System.out.print("Please enter the number of players in this game: ");
        int numPlayers = in.nextInt();
        Player[] players;
        if (numPlayers >= 2 && numPlayers <= 10) {
            players = new Player[numPlayers];
        } else {
            throw new RuntimeException("Invalid number of players");
        }
        in.nextLine();
        System.out.println();
        return players;
    }
    static boolean seeDice(Scanner in) {
        while (true) {
            System.out.print("Would you like to see the dice after each roll [Y/N]: ");
            String lineInput = in.nextLine().toLowerCase();
            if (  lineInput.indexOf('y') == 0 ) {
                System.out.println();
                return true;
            }
            if ( lineInput.indexOf('n') == 0 ) {
                break;
            }
            System.out.println();
        }
        return false;
    }
    static boolean seeScore(Scanner in) {
        while (true) {
            System.out.print("Would you like to see the score after each roll [Y/N]: ");
            String lineInput = in.nextLine().toLowerCase();
            if (  lineInput.indexOf('y') == 0 ) {
                System.out.println();
                return true;
            }
            if ( lineInput.indexOf('n') == 0 ) {
                break;
            }
            System.out.println();
        }
        return false;
    }
    static int getDiceSides(Scanner in) {
        System.out.print("Pick a dice size between 6-26: ");
        int diceSize = in.nextInt();
        in.nextLine();
        return diceSize;
    }
    static void getNames(Player[] players, Bunco b, Scanner in) {
        int count = 0;
        boolean dup = false; 
        while (count < players.length) {
            dup = false;
            System.out.print("Please enter player " + (count+1) + "'s name: ");
            String lineInput = in.nextLine();
            Player p = b.player(lineInput);
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
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        Player[] players = getEmptyPlayers(in);
        boolean seeDice = seeDice(in);
        boolean seeScore = seeScore(in);
        int diceSize = getDiceSides(in);
        Bunco b = new Bunco(diceSize,players,seeDice,seeScore);
        getNames(players,b,in);
        b.play();
        System.out.println(b);
    }
}
