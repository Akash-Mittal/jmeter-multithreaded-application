package com.example.concurrent.runner;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import com.example.concurrent.dto.RunnerRequest;
import com.example.concurrent.dto.RunnerResponse;

public class UserRunner implements Callable<RunnerResponse> {

	private static final Logger logger = Logger.getLogger(UserRunner.class.getSimpleName());
	private final RunnerRequest runnerRequest;

	public UserRunner(RunnerRequest runnerRequest) {
		super();
		this.runnerRequest = runnerRequest;
	}

	@Override
	public RunnerResponse call() throws Exception {
		for (Long i = 1L; i <= runnerRequest.getNumberOfUsers(); i++) {

			Future<RunnerResponse> rr = runnerRequest.getPool().submit(new RequestRunner(runnerRequest, i));
		}
		return null;
	}

}