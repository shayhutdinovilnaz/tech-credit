package com.technical.credit.obligationservice.factory;

import com.technical.credit.obligationservice.model.LanguageModel;
import com.technical.credit.obligationservice.model.ObligationModel;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class GenericInstanceFactoryTest {

    private final GenericInstanceFactory underTest = new GenericInstanceFactory();

    @Test
    public void testGetInstanceSuccess() {
        assertNotNull(underTest.getInstance(LanguageModel.class));
        assertNotNull(underTest.getInstance(ObligationModel.class));
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