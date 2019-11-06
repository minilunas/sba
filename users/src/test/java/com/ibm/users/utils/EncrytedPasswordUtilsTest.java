package com.ibm.users.utils;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EncrytedPasswordUtilsTest {

    @Test
    public void encrytePassword() {
        EncrytedPasswordUtils.encrytePassword("1");
    }

    @Test
    public void matchPassword() {
        assertTrue(EncrytedPasswordUtils.matchPassword("1", EncrytedPasswordUtils.encrytePassword("1")));
    }
}