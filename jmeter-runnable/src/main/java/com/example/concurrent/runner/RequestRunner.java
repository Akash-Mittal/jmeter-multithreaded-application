package com.example.concurrent.runner;

import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.concurrent.enums.AMOUNT;
import com.example.concurrent.enums.TRANSACTION;

public class RequestRunner implements Runnable {
	private static final Logger logger = Logger.getLogger(RequestRunner.class.getSimpleName());

	private Long userID;
	private Integer numberOfRequests;

	public RequestRunner(Long userID, Integer numberOfRequests) {
		super();
		this.userID = userID;
		this.numberOfRequests = numberOfRequests;
	}

	@Override
	public void run() {
		for (int i = 1; i <= numberOfRequests; i++) {
			logger.log(Level.INFO,

					TRANSACTION.values()[ThreadLocalRandom.current().nextInt(0, TRANSACTION.values().length)]
							.doTransact(userID,
									AMOUNT.values()[ThreadLocalRandom.current().nextInt(0, AMOUNT.values().length)]
											.getAmount())
							.toString());
		}
	}
}