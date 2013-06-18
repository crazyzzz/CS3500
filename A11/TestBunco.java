public class TestBunco {
    public static void main(String[] args) {
        Player[] players = new Player[4];
        players[0] = Bunco.player("Kevin");
        players[1] = Bunco.player("Ches");
        players[2] = Bunco.player("Portia");
        players[3] = Bunco.player("Todd");
        Bunco b = new Bunco(20,players,true,true);
        b.play();
    }
}
