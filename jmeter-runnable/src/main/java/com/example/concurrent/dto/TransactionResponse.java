package com.example.concurrent.dto;

import java.math.BigDecimal;

import com.example.concurrent.enums.STATUS;

public class TransactionResponse {
	@Override
	public String toString() {
		return "TransactionResponse [status=" + status + ", balance=" + balance + "]";
	}

	private STATUS status;
	private BigDecimal balance;

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public static class Builder {
		private STATUS status;
		private BigDecimal balance;

		public Builder status(STATUS status) {
			this.status = status;
			return this;
		}

		public Builder balance(BigDecimal balance) {
			this.balance = balance;
			return this;
		}

		public TransactionResponse build() {
			return new TransactionResponse(this);
		}
	}

	private TransactionResponse(Builder builder) {
		this.status = builder.status;
		this.balance = builder.balance;
	}
}
