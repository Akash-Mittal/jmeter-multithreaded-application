package com.example.concurrent.user.client;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.example.concurrent.enums.Rounds;

public class NestedThreads {

    public static void main(String[] args) throws InterruptedException {

        for (int i = 1; i <= Runner.numberOfUsers; i++) {
            Thread thread = new Thread(new UserRunner("", i));
            Runner.pool.execute(thread);
        }
        Runner.pool.awaitTermination(5, TimeUnit.SECONDS);
        Runner.pool.shutdown();
    }
}

interface Runner extends Runnable {
    ExecutorService pool = Executors.newCachedThreadPool();
    Properties props = System.getProperties();
    Integer numberOfUsers = Integer.valueOf(props.getProperty("wallet.users", "1000"));
    Integer numberOfRequests = Integer.valueOf(props.getProperty("wallet.threads", "1000"));
    Integer numberOfRounds = Integer.valueOf(props.getProperty("wallet.rounds", "1000"));
}

class UserRunner implements Runner {
    private String stats;
    private Integer userID;

    public void run() {
        for (int i = 1; i <= numberOfRequests; i++) {
            pool.execute(new RequestRunner("User:" + userID + " Request Number:" + i, userID));
        }
    }

    public UserRunner(String stats, Integer userID) {
        super();
        this.stats = stats;
        this.userID = userID;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

}

class RequestRunner implements Runner {
    private String stats;
    private Integer userID;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void run() {
        for (int i = 1; i <= numberOfRounds; i++) {
            Thread thread = new Thread(new RoundRunner(stats + " Round Number:" + i, userID));
            pool.execute(thread);
        }
        System.out.println(Thread.currentThread().getName() + " Running For: " + stats);
    }

    public RequestRunner(String stats, Integer userID) {
        super();
        this.stats = stats;
        this.userID = userID;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

}

class RoundRunner implements Runner {
    private String stats;
    private Integer userID;

    private Random random = new Random();

    public void run() {
        Rounds.values()[random.nextInt(Rounds.values().length)].goExecute(userID, stats);
    }

    public RoundRunner(String stats, Integer userID) {
        super();
        this.userID = userID;
        this.stats = stats;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public RoundRunner(Integer userID) {
        super();
        this.userID = userID;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }
}
