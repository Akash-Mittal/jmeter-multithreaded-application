package com.example.concurrent.dto;

import java.math.BigDecimal;

import com.example.concurrent.enums.STATUS;
import com.example.concurrent.enums.TRANSACTION;

public class TransactionResponse {
	@Override
	public String toString() {
		return "TransactionResponse [status=" + status + ", balance=" + balance + ", transaction=" + transaction + "]";
	}

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

	public TRANSACTION getTransaction() {
		return transaction;
	}

	public void setTransaction(TRANSACTION transaction) {
		this.transaction = transaction;
	}

	private STATUS status;
	private BigDecimal balance;
	private TRANSACTION transaction;

	public TransactionResponse(STATUS status, BigDecimal balance, TRANSACTION transaction) {
		super();
		this.status = status;
		this.balance = balance;
		this.transaction = transaction;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((transaction == null) ? 0 : transaction.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionResponse other = (TransactionResponse) obj;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (status != other.status)
			return false;
		if (transaction != other.transaction)
			return false;
		return true;
	}

	public static class Builder {
		private STATUS status;
		private BigDecimal balance;
		private TRANSACTION transaction;

		public Builder status(STATUS status) {
			this.status = status;
			return this;
		}

		public Builder balance(BigDecimal balance) {
			this.balance = balance;
			return this;
		}

		public Builder transaction(TRANSACTION transaction) {
			this.transaction = transaction;
			return this;
		}

		public TransactionResponse build() {
			return new TransactionResponse(this);
		}
	}

	private TransactionResponse(Builder builder) {
		this.status = builder.status;
		this.balance = builder.balance;
		this.transaction = builder.transaction;
	}
}
