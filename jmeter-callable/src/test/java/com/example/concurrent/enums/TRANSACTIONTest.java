package com.example.concurrent.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.example.concurrent.dto.TransactionResponse;

public class TRANSACTIONTest {

    private Long userID;

    @Before
    public void setUp() {
        userID = Long.valueOf(1);
    }

    @Test
    public void testDepositWithdrawBalanceSuccess() {
        TransactionResponse transactionResponse = TRANSACTION.DEPOSIT.doTransact(userID, AMOUNT.HUNDERED.getAmount());
        assertEquals(STATUS.SUCCESS, transactionResponse.getStatus());
        assertEquals(STATUS.SUCCESS, TRANSACTION.BALANCE.doTransact(userID, null).getStatus());
        assertEquals(AMOUNT.HUNDERED.getAmount(), TRANSACTION.BALANCE.doTransact(userID, null).getBalance());
        transactionResponse = TRANSACTION.DEPOSIT.doTransact(userID, AMOUNT.HUNDERED.getAmount());
        assertEquals(STATUS.SUCCESS, transactionResponse.getStatus());
        assertEquals(STATUS.SUCCESS, TRANSACTION.BALANCE.doTransact(userID, null).getStatus());
        assertEquals(AMOUNT.TWOHUNDERED.getAmount(), TRANSACTION.BALANCE.doTransact(userID, null).getBalance());
        transactionResponse = TRANSACTION.WITHDRAW.doTransact(userID, AMOUNT.HUNDERED.getAmount());
        assertEquals(STATUS.SUCCESS, transactionResponse.getStatus());
        assertEquals(STATUS.SUCCESS, TRANSACTION.BALANCE.doTransact(userID, null).getStatus());
        assertEquals(AMOUNT.HUNDERED.getAmount(), TRANSACTION.BALANCE.doTransact(userID, null).getBalance());
        transactionResponse = TRANSACTION.WITHDRAW.doTransact(userID, AMOUNT.HUNDERED.getAmount());
        assertEquals(STATUS.SUCCESS, transactionResponse.getStatus());
        assertEquals(STATUS.SUCCESS, TRANSACTION.BALANCE.doTransact(userID, null).getStatus());
        assertEquals(AMOUNT.ZERO.getAmount(), TRANSACTION.BALANCE.doTransact(userID, null).getBalance());

    }

}
