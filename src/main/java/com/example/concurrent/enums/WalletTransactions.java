package com.example.concurrent.enums;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum WalletTransactions {

    DEPOSIT {
        @Override
        public Float doTransact(Integer userID, Float amount) {
            Float newBalance = null;
            Float existingBalance = wallets.putIfAbsent(userID, amount);
            if (existingBalance != null) {
                newBalance = Float.sum(existingBalance, amount);
                wallets.replace(userID, newBalance);
            } else {
                newBalance = amount;
            }
            System.out.println(
                    "User:" + userID + " " + DEPOSIT.name() + ":" + amount + BALANCE.name() + ":" + newBalance);

            return newBalance;
        }
    },
    WITHDRAW {
        @Override
        public Float doTransact(Integer userID, Float amount) {
            Float currenctBalance = wallets.getOrDefault(userID, Float.valueOf(0));
            Float newBalance = currenctBalance;
            if (currenctBalance != null && amount > Float.valueOf(0f) && currenctBalance.compareTo(amount) >= 0) {
                newBalance = currenctBalance - amount;
                wallets.replace(userID, newBalance);
            }
            System.out.println(
                    "User:" + userID + " " + WITHDRAW.name() + ":" + amount + BALANCE.name() + ":" + newBalance);

            return newBalance;
        }
    },
    BALANCE {
        @Override
        public Float doTransact(Integer userID, Float amount) {
            Float balance = wallets.getOrDefault(userID, Float.valueOf(0));
            System.out.println("User:" + userID + " " + BALANCE.name() + ":" + balance);
            return balance;

        }
    };

    public abstract Float doTransact(Integer userID, Float amount);

    private static final Map<Integer, Float> wallets = new ConcurrentHashMap<Integer, Float>();

}