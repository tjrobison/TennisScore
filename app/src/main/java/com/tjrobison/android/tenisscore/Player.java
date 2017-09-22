package com.tjrobison.android.tenisscore;

public class Player {

    private String mPlayerName;
    private int mSet0;
    private int mSet1;
    private int mSet2;
    private int mScore;
    private int mSetsWon;

    private static String[] sGameScores = {"0", "15", "30", "40"};

    public Player(){
        mPlayerName = "";
        mSet0 = 0;
        mSet1 = 0;
        mSet2 = 0;
        mScore = 0;
        mSetsWon = 0;
    }

    public Player(String name){
        mPlayerName = name;
        mSet0 = 0;
        mSet1 = 0;
        mSet2 = 0;
        mScore = 0;
        mSetsWon = 0;
    }

    public void setPlayerName(String mPlayerName) {
        this.mPlayerName = mPlayerName;
    }

    public String getPlayerName() {
        return mPlayerName;
    }

    public void setPlayerStats(int s1, int s2, int s3, int finalScore) {
        mSet0 = s1;
        mSet1 = s3;
        mSet2 = s3;
        mSetsWon = finalScore;
    }

    public int getScore() {
        return mScore;
    }

    public int getSetsWon() {
        return mSetsWon;
    }

    public int incrementSetsWon() {
        mSetsWon++;
        return mSetsWon;
    }

    public int getSetScore(int currentSet) {
        switch (currentSet) {
            case 0:
                return mSet0;
            case 1:
                return mSet1;
            case 2:
                return mSet2;
            default:
                return 0;
        }
    }

    public int decrementScore() {
        mScore--;
        return mScore;
    }

    public int incrementScore(int currentSet, Player opponent) {
            int oppScore = opponent.getScore();
            if (mScore < 3 || (mScore == 3 && oppScore == 3)){
                mScore++;
                return mScore;
            } else if (mScore == 3 && oppScore == 4) {
                opponent.decrementScore();
                return mScore;
            } else if (mScore == 4 && oppScore == 3) {
                incrementSetAndReset(currentSet);
                opponent.incrementSetAndReset(currentSet);
                return -1;
            } else {
                incrementSetAndReset(currentSet);
                return -1;
            }
    }

    private void incrementSetAndReset(int currentSet){
                mScore = 0;
                switch (currentSet) {
                    case 0:
                        mSet0 = mSet0 + 1;
                        break;
                    case 1:
                        mSet1 = mSet1 + 1;
                        break;
                    case 2:
                        mSet2 = mSet2 + 1;
                        break;
                    default:
                        break;
                }
    }

    public void resetNoSave(){
        mSet0 = 0;
        mSet1 = 0;
        mSet2 = 0;
        mScore = 0;
        mSetsWon = 0;
    }
}
