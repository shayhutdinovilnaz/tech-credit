package com.technical.credit.obligationservice.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultDisplayNameServiceTest extends TestConfigurationConfig{

    @Spy
    @InjectMocks
    private DefaultDisplayNameService displayNameService = new DefaultDisplayNameService();

    @Test
    public void testConvert() {

        when(displayNameService.createDisplayName("f", "l", "p")).thenReturn("displayName");

    }
}
