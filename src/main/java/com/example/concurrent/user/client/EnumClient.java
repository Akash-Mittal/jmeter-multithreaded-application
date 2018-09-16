package com.example.concurrent.user.client;

import com.example.concurrent.enums.JMETER;
import com.example.concurrent.user.runner.Runner;

public class EnumClient {

    public static void main(String[] args) {
        for (int user = 1; user <= Runner.numberOfUsers; user++) {
            JMETER.USER_RUNNER.setUserID(user);
            Runner.pool.execute(JMETER.USER_RUNNER);
        }
    }

}
