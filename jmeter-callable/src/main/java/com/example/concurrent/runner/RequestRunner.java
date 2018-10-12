package com.example.concurrent.runner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

import com.example.concurrent.dto.RunnerRequest;
import com.example.concurrent.dto.RunnerResponse;
import com.example.concurrent.dto.TransactionResponse;
import com.example.concurrent.enums.AMOUNT;
import com.example.concurrent.enums.TRANSACTION;

public class RequestRunner implements Callable<List<RunnerResponse>> {
	private static final Logger logger = Logger.getLogger(RequestRunner.class.getSimpleName());

	private final RunnerRequest runnerRequest;
	private Long userID;

	@Override
	public List<RunnerResponse> call() throws Exception {

		List<RunnerResponse> runnerResponses = new ArrayList<>();
		for (int i = 1; i <= runnerRequest.getNumberOfRequests(); i++) {
			TransactionResponse response = TRANSACTION.values()[ThreadLocalRandom.current().nextInt(0,
					TRANSACTION.values().length)].doTransact(userID,
							AMOUNT.values()[ThreadLocalRandom.current().nextInt(0, AMOUNT.values().length)]
									.getAmount());
			runnerResponses.add(new RunnerResponse(response));

		}
		return runnerResponses;
	}

	public RequestRunner(RunnerRequest runnerRequest, Long userID) {
		super();
		this.runnerRequest = runnerRequest;
		this.userID = userID;
	}

}