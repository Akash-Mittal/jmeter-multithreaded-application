package com.example.concurrent.user;

import static org.junit.Assert.assertEquals;

public class RoundA implements Round {

    public void goExecute() {
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

}
