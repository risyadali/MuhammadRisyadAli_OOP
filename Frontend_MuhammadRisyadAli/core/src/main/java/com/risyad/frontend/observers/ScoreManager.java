package com.risyad.frontend.observers;

import java.util.ArrayList;
import java.util.List;

public class ScoreManager implements Subject {
    private List<Observer> observers;
    private int score;

    public ScoreManager() {
        this.observers = new ArrayList<>();
        this.score = 0;
    }

    @Override
    public void addObserver(Observer observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(int score) {
        for (Observer observer : observers) {
            observer.update(score);
        }
    }

    public void setScore(int newScore) {
        if (this.score != newScore) {
            this.score = newScore;
            notifyObservers(score);
        }
    }

    public int getScore() {
        return score;
    }
}
