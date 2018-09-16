package com.example.concurrent.user.runner;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.example.concurrent.enums.AMOUNT;
import com.example.concurrent.enums.ROUND;
import com.example.concurrent.enums.TRANSACTION;

@RunWith(JUnit4.class)
public class WalletTransactionsTest {

    @Test
    public void testRoundA() {
        Integer userID = 1;
        System.out.println(Thread.currentThread().getThreadGroup().getName() + ":" + Thread.currentThread().getName()
                + ROUND.A.name());
        TRANSACTION.BALANCE.doTransact(userID, null, "");

        TRANSACTION.DEPOSIT.doTransact(userID, AMOUNT.HUNDERED.getAmount(), "");
        assertEquals(AMOUNT.HUNDERED.getAmount(), TRANSACTION.BALANCE.doTransact(userID, null, ""), Float.valueOf(0F));

        TRANSACTION.WITHDRAW.doTransact(userID, AMOUNT.TWOHUNDERED.getAmount(), "");
        assertEquals(AMOUNT.HUNDERED.getAmount(), TRANSACTION.BALANCE.doTransact(userID, null, ""), Float.valueOf(0F));

        TRANSACTION.DEPOSIT.doTransact(userID, AMOUNT.HUNDERED.getAmount(), "");
        assertEquals(AMOUNT.TWOHUNDERED.getAmount(), TRANSACTION.BALANCE.doTransact(userID, null, ""),
                Float.valueOf(0F));

        TRANSACTION.WITHDRAW.doTransact(userID, AMOUNT.HUNDERED.getAmount(), "");
        assertEquals(AMOUNT.HUNDERED.getAmount(), TRANSACTION.BALANCE.doTransact(userID, null, ""), Float.valueOf(0F));

        TRANSACTION.WITHDRAW.doTransact(userID, AMOUNT.HUNDERED.getAmount(), "");
        assertEquals(AMOUNT.ZERO.getAmount(), TRANSACTION.BALANCE.doTransact(userID, null, ""), Float.valueOf(0F));
    }

    @Test
    public void TestRoundC() {
        Integer userID = 1;
        System.out.println(Thread.currentThread().getThreadGroup().getName() + ":" + Thread.currentThread().getName()
                + ROUND.C.name());

        TRANSACTION.BALANCE.doTransact(userID, null, "");

        TRANSACTION.DEPOSIT.doTransact(userID, AMOUNT.HUNDERED.getAmount(), "");
        assertEquals(AMOUNT.HUNDERED.getAmount(), TRANSACTION.BALANCE.doTransact(userID, null, ""), Float.valueOf(0F));

        TRANSACTION.DEPOSIT.doTransact(userID, AMOUNT.HUNDERED.getAmount(), "");
        assertEquals(AMOUNT.TWOHUNDERED.getAmount(), TRANSACTION.BALANCE.doTransact(userID, null, ""),
                Float.valueOf(0F));

        TRANSACTION.WITHDRAW.doTransact(userID, AMOUNT.HUNDERED.getAmount(), "");
        assertEquals(AMOUNT.HUNDERED.getAmount(), TRANSACTION.BALANCE.doTransact(userID, null, ""), Float.valueOf(0F));

        TRANSACTION.DEPOSIT.doTransact(userID, AMOUNT.HUNDERED.getAmount(), "");
        assertEquals(AMOUNT.TWOHUNDERED.getAmount(), TRANSACTION.BALANCE.doTransact(userID, null, ""),
                Float.valueOf(0F));

        TRANSACTION.WITHDRAW.doTransact(userID, AMOUNT.TWOHUNDERED.getAmount(), "");
        assertEquals(AMOUNT.ZERO.getAmount(), TRANSACTION.BALANCE.doTransact(userID, null, ""), Float.valueOf(0F));

    }
}
