public class TestBunco {
    public static void main(String[] args) {
        Player[] players = new Player[4];
        Bunco b = new Bunco(8,players,false,false);
        players[0] = b.player("Kevin");
        players[1] = b.player("Ches");
        players[2] = b.player("Portia");
        players[3] = b.player("Todd");
        b.play();
    }
}
