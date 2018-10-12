package com.example.concurrent.dto;

public final class RunnerResponse {
	private TransactionResponse response;

	public RunnerResponse(TransactionResponse response) {
		super();
		this.response = response;
	}

	public TransactionResponse getResponse() {
		return response;
	}

	public void setResponse(TransactionResponse response) {
		this.response = response;
	}
}
