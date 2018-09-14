package com.example.concurrent.user;

import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class WalletTransactionsTest {

    public static void testRoundA() {
        Integer userID = Integer.valueOf(1);

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

    public static void main(String[] args) {
        Round rounds[] = new Round[3];
        for (int i = 0; i < rounds.length; i++) {
            rounds[i].goExecute();
        }
    }

}
