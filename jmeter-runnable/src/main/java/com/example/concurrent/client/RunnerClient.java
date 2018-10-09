package com.example.concurrent.client;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.concurrent.runner.Runner;
import com.example.concurrent.runner.UserRunner;

public class RunnerClient {
	private static final Logger logger = Logger.getLogger(RunnerClient.class.getSimpleName());

	public static void main(String[] args) throws InterruptedException {
		Properties props = System.getProperties();
		Integer numberOfUsers = Integer.valueOf(props.getProperty("users", "2"));
		Integer numberOfRquests = Integer.valueOf(props.getProperty("requests", "1"));
		logger.log(Level.INFO, String.format("Total Threads %s = numberOfUsers(%s) * numberOfRquests(%s)",
				numberOfUsers * numberOfRquests, numberOfRquests, numberOfUsers));

		for (Long user = 1L; user <= numberOfUsers; user++) {
			Runner.pool.execute(new UserRunner(numberOfUsers, numberOfRquests));
		}
		Runner.pool.awaitTermination(5, TimeUnit.SECONDS);
		Runner.pool.shutdown();
	}

}
