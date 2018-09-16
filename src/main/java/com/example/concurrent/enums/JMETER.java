package com.example.concurrent.enums;

import java.util.Random;

import com.example.concurrent.user.runner.Runner;

public enum JMETER implements Runner {
    USER_RUNNER {
        public void run() {
            for (int i = 1; i <= numberOfRequests; i++) {
                setStats("User:" + getUserID() + " Request Number:" + i);
                pool.execute(REQUEST_RUNNER);
            }
        }
    },
    REQUEST_RUNNER {
        public void run() {
            for (int i = 1; i <= numberOfRounds; i++) {
                // String stats = getStats();
                // stats.concat(" Round Number:" + i);
                // setStats(stats);
                pool.execute(ROUND_RUNNER);
            }
        }
    },
    ROUND_RUNNER {

        public void run() {
            ROUND.values()[random.nextInt(ROUND.values().length)].goExecute(getUserID(), getStats());
        }

    };

    private static Random random = new Random();
    private String stats;
    private Integer userID;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

}
