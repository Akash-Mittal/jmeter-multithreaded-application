package com.example.concurrent.user;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClient {

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        Properties props = System.getProperties();
        Integer numberOfUsers = Integer.valueOf(props.getProperty("wallet.users", "1"));
        Integer numberOfThread = Integer.valueOf(props.getProperty("wallet.threads", "1"));
        Integer numberOfRounds = Integer.valueOf(props.getProperty("wallet.rounds", "1"));

        System.out.println("Total users:" + numberOfUsers + " Threads Per user: " + numberOfThread
                + " Rounds Per Thread: " + numberOfRounds);

        ExecutorService pool = Executors.newFixedThreadPool(numberOfThread);

        for (int user = 1; user <= numberOfUsers; user++) {
            for (int userThread = 1; userThread <= numberOfThread; userThread++) {
                Thread thread = new Thread(new RoundRunner(numberOfRounds, user));
                thread.setName("User:" + user + " Thread " + thread);
                pool.execute(thread);
            }
        }
        // pool.awaitTermination(1, TimeUnit.SECONDS);
        pool.shutdown();
        long endTime = System.currentTimeMillis();
        System.out.println("It took " + (((endTime - startTime) / 1000) - 1) + " Seconds");
    }
}

class ThreadRunner implements Runnable {
    private RoundRunner roundRunner;

    public void run() {
        new Thread(roundRunner).start();

    }

    public ThreadRunner(RoundRunner roundRunner) {
        super();
        this.roundRunner = roundRunner;
    }

    public RoundRunner getRoundRunner() {
        return roundRunner;
    }

    public void setRoundRunner(RoundRunner roundRunner) {
        this.roundRunner = roundRunner;
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
