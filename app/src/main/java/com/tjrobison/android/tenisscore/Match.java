package com.tjrobison.android.tenisscore;

public class Match {

    private Player Player1;
    private Player Player2;
    private boolean completed;

    public Match() {
        Player1 = new Player();
        Player2 = new Player();
        completed = false;
    }

    public void setPlayerNames(String p1, String p2) {
        Player1.setPlayerName(p1);
        Player2.setPlayerName(p2);
    }

    public void setPlayer1Stats(int s1, int s2, int s3, int finalScore) {
       Player1.setPlayerStats(s1, s2, s3, finalScore);
    }

    public void setPlayer2Stats(int s1, int s2, int s3, int finalScore) {
        Player2.setPlayerStats(s1, s2, s3, finalScore);
    }

    public void setMatchCompleted(int wasCompleted) {
        if (wasCompleted == 1){
            completed = true;
        }
    }
}
