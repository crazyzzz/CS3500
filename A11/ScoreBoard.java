public class ScoreBoard {
    Score[] scores; 
    ScoreBoard(Score[] scores) {
        this.scores = scores;
    }
    String totalScoreWinner() {
        int max = -1;
        String total = "Score winner!\n\t";
        for (int i = 0; i < scores.length; i++) {
            if (scores[i].getScore() > max) {
                max = scores[i].getScore();
            }
        }
        for (int i = 0; i < scores.length; i++) {
            if(scores[i].getScore() == max) {
                total += scores[i].getName() + " with " + max + " points\n\t";
            }
        }
        total += "\n";
        return total;
    }
    String roundsWinner() {
        int max = -1;
        String total = "Score rounds winner!\n\t";
        for (int i = 0; i < scores.length; i++) {
            if (scores[i].getWonRounds() > max) {
                max = scores[i].getWonRounds();
            }
            
        }
        for (int i = 0; i < scores.length; i++) {
            if(scores[i].getWonRounds() == max) {
                total += scores[i].getName() + " with " + max + " rounds\n\t";
            }
        }
        total += "\n";
        return total;
    }
    String littleBuncoWinner() {
        int max = -1;
        String total = "Score bunco!\n\t";
        for (int i = 0; i < scores.length; i++) {
            if (scores[i].getBuncos() > max) {
                max = scores[i].getBuncos();
            }
        }
        for (int i = 0; i < scores.length; i++) {
            if(scores[i].getBuncos() == max) {
                total += scores[i].getName() + " with " + max + " buncos\n\t";
            }
        }
        total += "\n";
        return total;
    }
    String bigBuncoWinner() {
        int max = -1;
        String total = "Score big bunco winner!\n\t";
        for (int i = 0; i < scores.length; i++) {
            if (scores[i].getBigBuncos() > max) {
                max = scores[i].getBigBuncos() ;
            }
        }
        for (int i = 0; i < scores.length; i++) {
            if(scores[i].getBigBuncos() == max) {
                total += scores[i].getName() 
                + " with " + max + " big buncos\n\t";
            }
        }
        total += "\n";
        return total;
    }
    String printStats() {
        String total = "\n";
        for( int i = 0; i < scores.length; i++) {
            total += scores[i] + "\n";
        }
        return total+"\n";
    }
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
