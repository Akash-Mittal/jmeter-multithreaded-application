package com.example.concurrent.user;

public enum Rounds {
    RoundA {
        @Override
        public void goExecute(Integer userID) {
            System.out.println(Thread.currentThread().getThreadGroup().getName() + ":"
                    + Thread.currentThread().getName() + RoundA.name());
            WalletTransactions.BALANCE.doTransact(userID, null);

            WalletTransactions.DEPOSIT.doTransact(userID, AMOUNT.HUNDERED.getAmount());
            assert AMOUNT.HUNDERED.getAmount() == WalletTransactions.BALANCE.doTransact(userID, null);

            WalletTransactions.WITHDRAW.doTransact(userID, AMOUNT.TWOHUNDERED.getAmount());

            WalletTransactions.DEPOSIT.doTransact(userID, AMOUNT.HUNDERED.getAmount());

            WalletTransactions.WITHDRAW.doTransact(userID, AMOUNT.HUNDERED.getAmount());

            WalletTransactions.WITHDRAW.doTransact(userID, AMOUNT.HUNDERED.getAmount());
        }
    },
    RoundB {
        @Override
        public void goExecute(Integer userID) {
            System.out.println(Thread.currentThread().getThreadGroup().getName() + ":"
                    + Thread.currentThread().getName() + ":" + RoundB.name());
            WalletTransactions.BALANCE.doTransact(userID, null);

            WalletTransactions.WITHDRAW.doTransact(userID, AMOUNT.HUNDERED.getAmount());

            WalletTransactions.DEPOSIT.doTransact(userID, AMOUNT.THREEHUNDERED.getAmount());

            WalletTransactions.WITHDRAW.doTransact(userID, AMOUNT.HUNDERED.getAmount());

            WalletTransactions.WITHDRAW.doTransact(userID, AMOUNT.HUNDERED.getAmount());

            WalletTransactions.WITHDRAW.doTransact(userID, AMOUNT.HUNDERED.getAmount());

        }
    },
    RoundC {
        @Override
        public void goExecute(Integer userID) {
            System.out.println(Thread.currentThread().getThreadGroup().getName() + ":"
                    + Thread.currentThread().getName() + RoundC.name());

            WalletTransactions.BALANCE.doTransact(userID, null);

            WalletTransactions.DEPOSIT.doTransact(userID, AMOUNT.HUNDERED.getAmount());

            WalletTransactions.DEPOSIT.doTransact(userID, AMOUNT.HUNDERED.getAmount());

            WalletTransactions.WITHDRAW.doTransact(userID, AMOUNT.HUNDERED.getAmount());

            WalletTransactions.DEPOSIT.doTransact(userID, AMOUNT.HUNDERED.getAmount());

            WalletTransactions.WITHDRAW.doTransact(userID, AMOUNT.TWOHUNDERED.getAmount());

        }
    };

    public abstract void goExecute(Integer userID);

}
