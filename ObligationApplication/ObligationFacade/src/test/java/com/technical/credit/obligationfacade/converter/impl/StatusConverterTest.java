package com.technical.credit.obligationfacade.converter.impl;


import com.technical.credit.obligationfacade.converter.StatusConverter;
import com.technical.credit.obligationfacade.data.StatusData;
import com.technical.credit.obligationservice.factory.GenericInstanceFactory;
import com.technical.credit.obligationservice.model.LanguageModel;
import com.technical.credit.obligationservice.model.LocalizedStringModel;
import com.technical.credit.obligationservice.model.StatusModel;
import com.technical.credit.obligationservice.service.LocalizationService;
import com.technical.credit.obligationservice.service.RequestService;
import com.technical.credit.obligationservice.service.StatusService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class StatusConverterTest {

    @InjectMocks
    private StatusConverter underTest;

    @Mock
    private GenericInstanceFactory genericInstanceFactory;

    @Mock
    private RequestService requestService;

    @Mock
    private LocalizationService localizationService;

    @Mock
    private StatusService statusService;

    @Test
    public void testConvert() {
        final StatusModel source = mock(StatusModel.class);
        final StatusData target = mock(StatusData.class);
        final LanguageModel ruLang = mock(LanguageModel.class);
        final LocalizedStringModel localizedSourceName = mock(LocalizedStringModel.class);
        final String ruSourceName = "Значение на русском языке";
        final long sourceId = 1L;

        when(source.getName()).thenReturn(localizedSourceName);
        when(source.getId()).thenReturn(sourceId);
        when(requestService.getCurrentLanguage()).thenReturn(ruLang);
        when(localizationService.getLocalizedStringValue(ruLang, localizedSourceName)).thenReturn(ruSourceName);
        when(genericInstanceFactory.getInstance(StatusData.class)).thenReturn(target);

        Assert.assertNotNull(underTest.convert(source));
        verify(source).getName();
        verify(source).getId();
        verifyNoMoreInteractions(source);
        verify(requestService).getCurrentLanguage();
        verifyNoMoreInteractions(requestService);
        verify(localizationService).getLocalizedStringValue(ruLang, localizedSourceName);
        verifyNoMoreInteractions(localizationService);
        verify(genericInstanceFactory).getInstance(StatusData.class);
        verifyNoMoreInteractions(genericInstanceFactory);
        verify(target).setId(sourceId);
        verify(target).setName(ruSourceName);
        verifyNoMoreInteractions(target);
        verifyZeroInteractions(statusService);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertNullableSource() {
        underTest.convert(null);
    }

    @Test
    public void testConvertWithNullableAttribute() {
        final StatusModel source = mock(StatusModel.class);
        final StatusData target = mock(StatusData.class);
        final LanguageModel ruLang = mock(LanguageModel.class);
        final LocalizedStringModel localizedSourceName = mock(LocalizedStringModel.class);
        final long sourceId = 1L;

        when(source.getName()).thenReturn(localizedSourceName);
        when(source.getId()).thenReturn(sourceId);
        when(requestService.getCurrentLanguage()).thenReturn(ruLang);
        when(localizationService.getLocalizedStringValue(ruLang, localizedSourceName)).thenReturn(null);
        when(genericInstanceFactory.getInstance(StatusData.class)).thenReturn(target);

        Assert.assertNotNull(underTest.convert(source));
        verify(source).getName();
        verify(source).getId();
        verifyNoMoreInteractions(source);
        verify(requestService).getCurrentLanguage();
        verifyNoMoreInteractions(requestService);
        verify(localizationService).getLocalizedStringValue(ruLang, localizedSourceName);
        verifyNoMoreInteractions(localizationService);
        verify(genericInstanceFactory).getInstance(StatusData.class);
        verifyNoMoreInteractions(genericInstanceFactory);
        verify(target).setId(sourceId);
        verify(target).setName(null);
        verifyNoMoreInteractions(target);
        verifyZeroInteractions(statusService);
    }

    @Test
    public void testReverseConvert() {
        final StatusModel externalTarget = mock(StatusModel.class);
        final StatusModel internalTarget = mock(StatusModel.class);
        final StatusData source = mock(StatusData.class);
        final LanguageModel ruLang = mock(LanguageModel.class);
        final LocalizedStringModel localizedTargetName = mock(LocalizedStringModel.class);
        final String ruSourceName = "Значение на русском языке";
        final long sourceId = 1L;

        when(source.getName()).thenReturn(ruSourceName);
        when(source.getId()).thenReturn(sourceId);
        when(externalTarget.getId()).thenReturn(sourceId);
        when(externalTarget.getName()).thenReturn(localizedTargetName);
        when(requestService.getCurrentLanguage()).thenReturn(ruLang);
        when(statusService.getById(sourceId)).thenReturn(internalTarget);
        when(internalTarget.getName()).thenReturn(localizedTargetName);
        when(localizationService.addLocalizedStringValue(ruLang, ruSourceName, localizedTargetName)).thenReturn(localizedTargetName);
        when(genericInstanceFactory.getInstance(StatusModel.class)).thenReturn(externalTarget);

        Assert.assertNotNull(underTest.reverseConvert(source));
        verify(source).getName();
        verify(source).getId();
        verifyNoMoreInteractions(source);
        verify(statusService).getById(sourceId);
        verify(requestService).getCurrentLanguage();
        verifyNoMoreInteractions(requestService);
        verify(localizationService).addLocalizedStringValue(ruLang, ruSourceName, localizedTargetName);
        verifyNoMoreInteractions(localizationService);
        verify(genericInstanceFactory).getInstance(StatusModel.class);
        verifyNoMoreInteractions(genericInstanceFactory);
        verify(externalTarget).setId(sourceId);
        verify(externalTarget).getName();
        verify(externalTarget).getId();
        verify(externalTarget, times(2)).setName(localizedTargetName);
        verifyNoMoreInteractions(externalTarget);
        verify(internalTarget).getName();
        verifyNoMoreInteractions(internalTarget);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReverseConvertNullableSource() {
        underTest.reverseConvert(null);
    }

    @Test
    public void testReverseConvertWithNullableAttribute() {
        final StatusModel externalTarget = mock(StatusModel.class);
        final StatusModel internalTarget = mock(StatusModel.class);
        final StatusData source = mock(StatusData.class);
        final LanguageModel ruLang = mock(LanguageModel.class);
        final LocalizedStringModel localizedTargetName = mock(LocalizedStringModel.class);
        final long sourceId = 1L;

        when(source.getName()).thenReturn(null);
        when(source.getId()).thenReturn(sourceId);
        when(externalTarget.getId()).thenReturn(sourceId);
        when(externalTarget.getName()).thenReturn(localizedTargetName);
        when(requestService.getCurrentLanguage()).thenReturn(ruLang);
        when(statusService.getById(sourceId)).thenReturn(internalTarget);
        when(internalTarget.getName()).thenReturn(localizedTargetName);
        when(localizationService.addLocalizedStringValue(ruLang, null, localizedTargetName)).thenReturn(localizedTargetName);
        when(genericInstanceFactory.getInstance(StatusModel.class)).thenReturn(externalTarget);

        Assert.assertNotNull(underTest.reverseConvert(source));
        verify(source).getName();
        verify(source).getId();
        verifyNoMoreInteractions(source);
        verify(statusService).getById(sourceId);
        verify(requestService).getCurrentLanguage();
        verifyNoMoreInteractions(requestService);
        verify(localizationService).addLocalizedStringValue(ruLang, null, localizedTargetName);
        verifyNoMoreInteractions(localizationService);
        verify(genericInstanceFactory).getInstance(StatusModel.class);
        verifyNoMoreInteractions(genericInstanceFactory);
        verify(externalTarget).setId(sourceId);
        verify(externalTarget).getName();
        verify(externalTarget).getId();
        verify(externalTarget, times(2)).setName(localizedTargetName);
        verifyNoMoreInteractions(externalTarget);
        verify(internalTarget).getName();
        verifyNoMoreInteractions(internalTarget);
    }
}