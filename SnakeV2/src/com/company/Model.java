package com.company;

public class Model {
    private int bestScore = 0;
    private int currentScore = 0;

    public int getBestScore() {
        return bestScore;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public void compareScores() {
        if(currentScore > bestScore) {
            bestScore = currentScore;
        }
    }
}
