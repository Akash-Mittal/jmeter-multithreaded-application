package com.example.concurrent.runner;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.concurrent.dto.RunnerRequest;
import com.example.concurrent.dto.RunnerResponse;
import com.example.concurrent.enums.AMOUNT;
import com.example.concurrent.enums.TRANSACTION;

public class RequestRunner implements Callable<RunnerResponse> {
	private static final Logger logger = Logger.getLogger(RequestRunner.class.getSimpleName());

	private final RunnerRequest runnerRequest;
	private Long userID;

	@Override
	public RunnerResponse call() throws Exception {
		for (int i = 1; i <= runnerRequest.getNumberOfRequests(); i++) {
			logger.log(Level.INFO,
					TRANSACTION.values()[ThreadLocalRandom.current().nextInt(0, TRANSACTION.values().length)]
							.doTransact(userID,
									AMOUNT.values()[ThreadLocalRandom.current().nextInt(0, AMOUNT.values().length)]
											.getAmount())
							.toString());
		}
		return null;
	}

	public RequestRunner(RunnerRequest runnerRequest, Long userID) {
		super();
		this.runnerRequest = runnerRequest;
		this.userID = userID;
	}

}