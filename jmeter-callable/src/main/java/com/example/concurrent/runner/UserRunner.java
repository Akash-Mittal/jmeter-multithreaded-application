package com.example.concurrent.runner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

import com.example.concurrent.dto.RunnerRequest;
import com.example.concurrent.dto.RunnerResponse;

public class UserRunner implements Callable<List<RunnerResponse>> {

	private static final Logger logger = Logger.getLogger(UserRunner.class.getSimpleName());
	private final RunnerRequest runnerRequest;

	public UserRunner(RunnerRequest runnerRequest) {
		super();
		this.runnerRequest = runnerRequest;
	}

	@Override
	public List<RunnerResponse> call() throws Exception {
		List<RunnerResponse> runnerResponse = new ArrayList<>();
		for (Long i = 1L; i <= runnerRequest.getNumberOfUsers(); i++) {
			runnerResponse.addAll(runnerRequest.getPool().submit(new RequestRunner(runnerRequest, i)).get());
		}
		return runnerResponse;
	}

}