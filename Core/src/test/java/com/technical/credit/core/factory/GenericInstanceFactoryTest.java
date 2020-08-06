package com.technical.credit.core.factory;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class GenericInstanceFactoryTest {

    private final GenericInstanceFactory underTest = new GenericInstanceFactory();

    @Test
    public void testGetInstanceSuccess() {
        assertNotNull(underTest.getInstance(Date.class));
        assertNotNull(underTest.getInstance(String.class));
    }

    @Test
    public void testGetInstanceFailure() {
        assertNull(underTest.getInstance(GenericInstanceFactoryTestClass.class));
    }

    static class GenericInstanceFactoryTestClass {
        public GenericInstanceFactoryTestClass(final String attribute) {
        }
    }
}