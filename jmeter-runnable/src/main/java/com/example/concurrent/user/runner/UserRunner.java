package com.example.concurrent.user.runner;

public class UserRunner implements Runner {
    private Long userID;

    public UserRunner(Long userID) {
        super();
        this.userID = userID;
    }

    @Override
    public void run() {
        for (int i = 1; i <= numberOfUsers; i++) {
            pool.execute(new RequestRunner(userID));
        }
    }

}