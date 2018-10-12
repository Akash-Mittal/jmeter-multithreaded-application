package com.example.concurrent.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.concurrent.dto.RunnerRequest;
import com.example.concurrent.dto.RunnerResponse;
import com.example.concurrent.runner.UserRunner;

public class RunnerClient {
	private static final Logger logger = Logger.getLogger(RunnerClient.class.getSimpleName());

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Properties props = System.getProperties();
		Integer numberOfUsers = Integer.valueOf(props.getProperty("users", "1000"));
		Integer numberOfRquests = Integer.valueOf(props.getProperty("requests", "1"));
		logger.log(Level.INFO, String.format("Total Requests %s = numberOfUsers(%s) * numberOfRquests(%s)",
				numberOfUsers * numberOfRquests, numberOfRquests, numberOfUsers));

		RunnerRequest request = new RunnerRequest(50, numberOfUsers, numberOfRquests, 0);
		List<RunnerResponse> runnerResponse = new ArrayList<>();

		for (Long user = 1L; user <= numberOfUsers; user++) {
			runnerResponse.addAll(request.getPool().submit(new UserRunner(request)).get());
		}

		runnerResponse.stream().forEach(response -> {
			logger.log(Level.INFO, response.getResponse().toString());
		});

		request.getPool().awaitTermination(5, TimeUnit.SECONDS);
		request.getPool().shutdown();
	}

}
