package com.example.concurrent.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class STATUSTest {

    private static final String AMOUNT_DEPOSIT = "Amount Deposited Succesfully";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testSuccess() {
        STATUS success = STATUS.SUCCESS;
        assertEquals(success.getResponseMessage(), "SUCCESS");

        success.setResponseMessage(AMOUNT_DEPOSIT);
        assertEquals(success.getResponseMessage(), AMOUNT_DEPOSIT);

    }

}
