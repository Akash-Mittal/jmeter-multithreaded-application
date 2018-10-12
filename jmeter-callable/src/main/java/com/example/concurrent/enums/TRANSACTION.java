
package com.example.concurrent.enums;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.concurrent.dto.TransactionResponse;

public enum TRANSACTION {

	DEPOSIT {
		@Override
		public TransactionResponse doTransact(Long userID, BigDecimal amount) {
			TransactionResponse response = new TransactionResponse.Builder().status(STATUS.FAILURE).build();
			BigDecimal existingBalance = wallets.putIfAbsent(userID, amount);
			if (existingBalance != null) {
				if (wallets.replace(userID, existingBalance, existingBalance.add(amount))) {
					response.setStatus(STATUS.SUCCESS);
					logger.log(Level.INFO, String.format("%s Amount Deposited SuccessFully.", amount));
				}
			} else {
				response.setStatus(STATUS.SUCCESS);
				logger.log(Level.INFO, String.format("%s Amount Deposited SuccessFully.", amount));
			}
			return response;
		}
	},
	WITHDRAW {
		@Override
		public TransactionResponse doTransact(Long userID, BigDecimal amount) {
			TransactionResponse response = new TransactionResponse.Builder().status(STATUS.FAILURE).build();

			BigDecimal currenctBalance = wallets.getOrDefault(userID, BigDecimal.valueOf(0));
			if (amount.compareTo(BigDecimal.valueOf(0f)) > 0 && currenctBalance.compareTo(amount) >= 0) {
				if (wallets.replace(userID, currenctBalance, currenctBalance.subtract(amount))) {
					response.setStatus(STATUS.SUCCESS);
					logger.log(Level.INFO, String.format("%s Amount Withdrawn SuccessFully.", amount));
				}
			}
			return response;
		}
	},
	BALANCE {
		@Override
		public TransactionResponse doTransact(Long userID, BigDecimal amount) {
			BigDecimal balance = wallets.getOrDefault(userID, BigDecimal.valueOf(0));
			logger.log(Level.INFO, String.format("Remaining Balance: %s", balance.toPlainString()));
			return new TransactionResponse.Builder().status(STATUS.SUCCESS).balance(balance).build();
		}
	};

	public abstract TransactionResponse doTransact(Long userID, BigDecimal amount);

	private static final Map<Long, BigDecimal> wallets = new ConcurrentHashMap<Long, BigDecimal>();
	private static final Logger logger = Logger.getLogger(TRANSACTION.class.getSimpleName());

}