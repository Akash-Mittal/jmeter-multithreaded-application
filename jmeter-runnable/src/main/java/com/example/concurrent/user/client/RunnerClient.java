package com.example.concurrent.user.client;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.example.concurrent.user.runner.Runner;
import com.example.concurrent.user.runner.UserRunner;

public class RunnerClient {

    public static void main(String[] args) throws InterruptedException {
        Properties props = System.getProperties();
        Long numberOfUsers = Long.valueOf(props.getProperty("wallet.users", "2000000000000"));
        Integer numberOfThread = Integer.valueOf(props.getProperty("wallet.threads", "2"));
        Integer numberOfRounds = Integer.valueOf(props.getProperty("wallet.rounds", "2"));

        for (Long user = 1L; user <= numberOfUsers; user++) {
            Thread thread = new Thread(new UserRunner(user));
            Runner.pool.execute(thread);
        }
        Runner.pool.awaitTermination(5, TimeUnit.SECONDS);
        Runner.pool.shutdown();
    }

}
