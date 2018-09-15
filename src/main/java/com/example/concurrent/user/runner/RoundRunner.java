package com.example.concurrent.user.runner;

import java.util.Random;

import com.example.concurrent.enums.Rounds;

public class RoundRunner implements Runnable {
    private Integer numberOfRounds;
    private Integer userID;
    private Random random = new Random();

    public void run() {
        for (int i = 0; i < numberOfRounds; i++) {
            Rounds.values()[random.nextInt(Rounds.values().length)].goExecute(userID);
        }
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public RoundRunner(Integer numberOfRounds, Integer userID) {
        super();
        this.numberOfRounds = numberOfRounds;
        this.userID = userID;
    }

    public Integer getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(Integer numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }
}