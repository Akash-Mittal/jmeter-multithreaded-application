package com.example.concurrent.runner;

import java.util.logging.Logger;

public class UserRunner implements Runner {
	private Integer numberOfUsers;
	private Integer numberOfRequests;

	private static final Logger logger = Logger.getLogger(UserRunner.class.getSimpleName());

	public UserRunner(Integer numberOfUsers, Integer numberOfRequests) {
		super();
		this.numberOfUsers = numberOfUsers;
		this.numberOfRequests = numberOfRequests;
	}

	@Override
	public void run() {
		for (Long i = 1L; i <= numberOfUsers; i++) {
			pool.execute(new RequestRunner(i, numberOfRequests));
		}
	}

}