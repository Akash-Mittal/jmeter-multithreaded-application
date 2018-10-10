package com.example.concurrent.client;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.concurrent.dto.RunnerRequest;
import com.example.concurrent.runner.UserRunner;

public class RunnerClient {
	private static final Logger logger = Logger.getLogger(RunnerClient.class.getSimpleName());

	public static void main(String[] args) throws InterruptedException {
		Properties props = System.getProperties();
		Integer numberOfUsers = Integer.valueOf(props.getProperty("users", "1"));
		Integer numberOfRquests = Integer.valueOf(props.getProperty("requests", "1"));
		logger.log(Level.INFO, String.format("Total Threads %s = numberOfUsers(%s) * numberOfRquests(%s)",
				numberOfUsers * numberOfRquests, numberOfRquests, numberOfUsers));

		RunnerRequest request = new RunnerRequest(5, numberOfUsers, numberOfRquests, 0);

		for (Long user = 1L; user <= numberOfUsers; user++) {
			request.getPool().submit(new UserRunner(request));
		}
		request.getPool().awaitTermination(5, TimeUnit.SECONDS);
		request.getPool().shutdown();
	}

}
