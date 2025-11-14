package com.risyad.frontend; // CHANGE THIS to your actual package!

public class ScoreDisplay implements ScoreObserver {
    @Override
    public void onScoreUpdate(int newScore) {
        System.out.println("Score updated: " + newScore);
    }
}
