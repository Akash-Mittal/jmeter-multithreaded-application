package com.example.concurrent.user;

import static org.junit.Assert.assertEquals;

public enum Rounds {
    RoundA {
        @Override
        public void goExecute(Integer userID) {
            System.out.println(Thread.currentThread().getThreadGroup().getName() + ":"
                    + Thread.currentThread().getName() + RoundA.name());
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
            assertEquals(AMOUNT.ZERO.getAmount(), WalletTransactions.BALANCE.doTransact(userID, null),
                    Float.valueOf(0F));
        }
    },
    RoundB {
        @Override
        public void goExecute(Integer userID) {
            System.out.println(Thread.currentThread().getThreadGroup().getName() + ":"
                    + Thread.currentThread().getName() + RoundB.name());
            WalletTransactions.BALANCE.doTransact(userID, null);

            WalletTransactions.WITHDRAW.doTransact(userID, AMOUNT.HUNDERED.getAmount());
            assertEquals(AMOUNT.ZERO.getAmount(), WalletTransactions.BALANCE.doTransact(userID, null),
                    Float.valueOf(0F));

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
            assertEquals(AMOUNT.ZERO.getAmount(), WalletTransactions.BALANCE.doTransact(userID, null),
                    Float.valueOf(0F));

        }
    },
    RoundC {
        @Override
        public void goExecute(Integer userID) {
            System.out.println(Thread.currentThread().getThreadGroup().getName() + ":"
                    + Thread.currentThread().getName() + RoundC.name());

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
            assertEquals(AMOUNT.ZERO.getAmount(), WalletTransactions.BALANCE.doTransact(userID, null),
                    Float.valueOf(0F));

        }
    };

    public abstract void goExecute(Integer userID);

}
