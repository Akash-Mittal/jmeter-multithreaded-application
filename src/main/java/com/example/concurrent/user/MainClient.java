package com.example.concurrent.user;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainClient {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(5);

        pool.execute(new Thread(new RoundRunner(500, 1)));
        pool.execute(new Thread(new RoundRunner(500, 1)));

        pool.awaitTermination(10, TimeUnit.SECONDS);
        pool.shutdown();

    }

}

class RoundRunner implements Runnable {
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