package com.example.concurrent.user.runner;

import java.util.concurrent.ThreadLocalRandom;

import com.example.concurrent.enums.AMOUNT;
import com.example.concurrent.enums.TRANSACTION;

public class RequestRunner implements Runner {
    private Long userID;

    public RequestRunner(Long userID) {
        super();
        this.userID = userID;
    }

    @Override
    public void run() {
        for (int i = 1; i <= numberOfRequests; i++) {
            TRANSACTION.values()[ThreadLocalRandom.current().nextInt(0, TRANSACTION.values().length)].doTransact(userID,
                    AMOUNT.values()[ThreadLocalRandom.current().nextInt(0, AMOUNT.values().length)].getAmount());
        }
    }

}