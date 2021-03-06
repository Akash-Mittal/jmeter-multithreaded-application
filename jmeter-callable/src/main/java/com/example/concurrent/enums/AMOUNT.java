package com.example.concurrent.enums;

import java.math.BigDecimal;

public enum AMOUNT {
    ZERO(BigDecimal.valueOf(0)), HUNDERED(BigDecimal.valueOf(100)), TWOHUNDERED(BigDecimal.valueOf(200)), THREEHUNDERED(
            BigDecimal.valueOf(300)), FOURHUNDERED(BigDecimal.valueOf(400)), FIVEHUNDERED(BigDecimal.valueOf(5000));

    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    private AMOUNT(BigDecimal amount) {
        this.amount = amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
