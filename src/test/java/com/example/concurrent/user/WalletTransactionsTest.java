package com.example.concurrent.user;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class WalletTransactionsTest {

    @Test
    public void testRoundA() {
        Integer userID = 1;
        System.out.println(Thread.currentThread().getThreadGroup().getName() + ":" + Thread.currentThread().getName()
                + Rounds.RoundA.name());
        WalletTransactions.BALANCE.doTransact(userID, null);

        WalletTransactions.DEPOSIT.doTransact(userID, AMOUNT.HUNDERED.getAmount());
        assertEquals(AMOUNT.HUNDERED.getAmount(), WalletTransactions.BALANCE.doTransact(userID, null),
                Float.valueOf(0F));

        WalletTransactions.WITHDRAW.doTransact(userID, AMOUNT.TWOHUNDERED.getAmount());
        assertEquals(AMOUNT.HUNDERED.getAmount(), WalletTransactions.BALANCE.doTransact(userID, null),
                Float.valueOf(0F));

        WalletTransactions.DEPOSIT.doTransact(userID, AMOUNT.HUNDERED.getAmount());
        assertEquals(AMOUNT.TWOHUNDERED.getAmount(), WalletTransactions.BALANCE.doTransact(userID, null),
                Float.valueOf(0F));

        WalletTransactions.WITHDRAW.doTransact(userID, AMOUNT.HUNDERED.getAmount());
        assertEquals(AMOUNT.HUNDERED.getAmount(), WalletTransactions.BALANCE.doTransact(userID, null),
                Float.valueOf(0F));

        WalletTransactions.WITHDRAW.doTransact(userID, AMOUNT.HUNDERED.getAmount());
        assertEquals(AMOUNT.ZERO.getAmount(), WalletTransactions.BALANCE.doTransact(userID, null), Float.valueOf(0F));
    }

    @Test
    public void testRoundC() {
        Integer userID = 1;
        System.out.println(Thread.currentThread().getThreadGroup().getName() + ":" + Thread.currentThread().getName()
                + Rounds.RoundB.name());
        WalletTransactions.BALANCE.doTransact(userID, null);

        WalletTransactions.WITHDRAW.doTransact(userID, AMOUNT.HUNDERED.getAmount());
        assertEquals(AMOUNT.ZERO.getAmount(), WalletTransactions.BALANCE.doTransact(userID, null), Float.valueOf(0F));

        WalletTransactions.DEPOSIT.doTransact(userID, AMOUNT.THREEHUNDERED.getAmount());
        assertEquals(AMOUNT.THREEHUNDERED.getAmount(), WalletTransactions.BALANCE.doTransact(userID, null),
                Float.valueOf(0F));

        WalletTransactions.WITHDRAW.doTransact(userID, AMOUNT.HUNDERED.getAmount());
        assertEquals(AMOUNT.TWOHUNDERED.getAmount(), WalletTransactions.BALANCE.doTransact(userID, null),
                Float.valueOf(0F));

        WalletTransactions.WITHDRAW.doTransact(userID, AMOUNT.HUNDERED.getAmount());
        assertEquals(AMOUNT.HUNDERED.getAmount(), WalletTransactions.BALANCE.doTransact(userID, null),
                Float.valueOf(0F));

        WalletTransactions.WITHDRAW.doTransact(userID, AMOUNT.HUNDERED.getAmount());
        assertEquals(AMOUNT.ZERO.getAmount(), WalletTransactions.BALANCE.doTransact(userID, null), Float.valueOf(0F));

    }

    @Test
    public void TestRoundC() {
        Integer userID = 1;
        System.out.println(Thread.currentThread().getThreadGroup().getName() + ":" + Thread.currentThread().getName()
                + Rounds.RoundC.name());

        WalletTransactions.BALANCE.doTransact(userID, null);

        WalletTransactions.DEPOSIT.doTransact(userID, AMOUNT.HUNDERED.getAmount());
        assertEquals(AMOUNT.HUNDERED.getAmount(), WalletTransactions.BALANCE.doTransact(userID, null),
                Float.valueOf(0F));

        WalletTransactions.DEPOSIT.doTransact(userID, AMOUNT.HUNDERED.getAmount());
        assertEquals(AMOUNT.TWOHUNDERED.getAmount(), WalletTransactions.BALANCE.doTransact(userID, null),
                Float.valueOf(0F));

        WalletTransactions.WITHDRAW.doTransact(userID, AMOUNT.HUNDERED.getAmount());
        assertEquals(AMOUNT.HUNDERED.getAmount(), WalletTransactions.BALANCE.doTransact(userID, null),
                Float.valueOf(0F));

        WalletTransactions.DEPOSIT.doTransact(userID, AMOUNT.HUNDERED.getAmount());
        assertEquals(AMOUNT.TWOHUNDERED.getAmount(), WalletTransactions.BALANCE.doTransact(userID, null),
                Float.valueOf(0F));

        WalletTransactions.WITHDRAW.doTransact(userID, AMOUNT.TWOHUNDERED.getAmount());
        assertEquals(AMOUNT.ZERO.getAmount(), WalletTransactions.BALANCE.doTransact(userID, null), Float.valueOf(0F));

    }
}
