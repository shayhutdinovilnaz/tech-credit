package com.technical.credit.obligationfacade.converter.impl;


import com.technical.credit.obligationfacade.converter.SkillConverter;
import com.technical.credit.obligationfacade.data.SkillData;
import com.technical.credit.obligationservice.factory.GenericInstanceFactory;
import com.technical.credit.obligationservice.model.LanguageModel;
import com.technical.credit.obligationservice.model.LocalizedStringModel;
import com.technical.credit.obligationservice.model.SkillModel;
import com.technical.credit.obligationservice.service.LocalizationService;
import com.technical.credit.obligationservice.service.RequestService;
import com.technical.credit.obligationservice.service.SkillService;
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
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class SkillConverterTest {

    @InjectMocks
    private SkillConverter underTest;

    @Mock
    private GenericInstanceFactory genericInstanceFactory;

    @Mock
    private RequestService requestService;

    @Mock
    private LocalizationService localizationService;

    @Mock
    private SkillService skillService;

    @Test
    public void testConvert() {
        final SkillModel source = mock(SkillModel.class);
        final SkillData target = mock(SkillData.class);
        final LanguageModel ruLang = mock(LanguageModel.class);
        final LocalizedStringModel localizedSourceName = mock(LocalizedStringModel.class);
        final String ruSourceName = "Значение на русском языке";
        final long sourceId = 1L;

        when(source.getName()).thenReturn(localizedSourceName);
        when(source.getId()).thenReturn(sourceId);
        when(requestService.getCurrentLanguage()).thenReturn(ruLang);
        when(localizationService.getLocalizedStringValue(ruLang, localizedSourceName)).thenReturn(ruSourceName);
        when(genericInstanceFactory.getInstance(SkillData.class)).thenReturn(target);

        Assert.assertNotNull(underTest.convert(source));
        verify(source).getName();
        verify(source).getId();
        verifyNoMoreInteractions(source);
        verify(requestService).getCurrentLanguage();
        verifyNoMoreInteractions(requestService);
        verify(localizationService).getLocalizedStringValue(ruLang, localizedSourceName);
        verifyNoMoreInteractions(localizationService);
        verify(genericInstanceFactory).getInstance(SkillData.class);
        verifyNoMoreInteractions(genericInstanceFactory);
        verify(target).setId(sourceId);
        verify(target).setName(ruSourceName);
        verifyNoMoreInteractions(target);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertNullableSource() {
        underTest.convert(null);
    }

    @Test
    public void testConvertWithNullableAttribute() {
        final SkillModel source = mock(SkillModel.class);
        final SkillData target = mock(SkillData.class);
        final LanguageModel ruLang = mock(LanguageModel.class);
        final LocalizedStringModel localizedSourceName = mock(LocalizedStringModel.class);
        final long sourceId = 1L;

        when(source.getName()).thenReturn(localizedSourceName);
        when(source.getId()).thenReturn(sourceId);
        when(requestService.getCurrentLanguage()).thenReturn(ruLang);
        when(localizationService.getLocalizedStringValue(ruLang, localizedSourceName)).thenReturn(null);
        when(genericInstanceFactory.getInstance(SkillData.class)).thenReturn(target);

        Assert.assertNotNull(underTest.convert(source));
        verify(source).getName();
        verify(source).getId();
        verifyNoMoreInteractions(source);
        verify(requestService).getCurrentLanguage();
        verifyNoMoreInteractions(requestService);
        verify(localizationService).getLocalizedStringValue(ruLang, localizedSourceName);
        verifyNoMoreInteractions(localizationService);
        verify(genericInstanceFactory).getInstance(SkillData.class);
        verifyNoMoreInteractions(genericInstanceFactory);
        verify(target).setId(sourceId);
        verify(target).setName(null);
        verifyNoMoreInteractions(target);
    }

    @Test
    public void testReverseConvert() {
        final SkillModel externalTarget = mock(SkillModel.class);
        final SkillModel internalTarget = mock(SkillModel.class);
        final SkillData source = mock(SkillData.class);
        final LanguageModel ruLang = mock(LanguageModel.class);
        final LocalizedStringModel localizedTargetName = mock(LocalizedStringModel.class);
        final String ruSourceName = "Значение на русском языке";
        final long sourceId = 1L;

        when(source.getName()).thenReturn(ruSourceName);
        when(source.getId()).thenReturn(sourceId);
        when(externalTarget.getId()).thenReturn(sourceId);
        when(externalTarget.getName()).thenReturn(localizedTargetName);
        when(requestService.getCurrentLanguage()).thenReturn(ruLang);
        when(skillService.getById(sourceId)).thenReturn(internalTarget);
        when(internalTarget.getName()).thenReturn(localizedTargetName);
        when(localizationService.addLocalizedStringValue(ruLang, ruSourceName, localizedTargetName)).thenReturn(localizedTargetName);
        when(genericInstanceFactory.getInstance(SkillModel.class)).thenReturn(externalTarget);

        Assert.assertNotNull(underTest.reverseConvert(source));
        verify(source).getName();
        verify(source).getId();
        verifyNoMoreInteractions(source);
        verify(skillService).getById(sourceId);
        verify(requestService).getCurrentLanguage();
        verifyNoMoreInteractions(requestService);
        verify(localizationService).addLocalizedStringValue(ruLang, ruSourceName, localizedTargetName);
        verifyNoMoreInteractions(localizationService);
        verify(genericInstanceFactory).getInstance(SkillModel.class);
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
        final SkillModel externalTarget = mock(SkillModel.class);
        final SkillModel internalTarget = mock(SkillModel.class);
        final SkillData source = mock(SkillData.class);
        final LanguageModel ruLang = mock(LanguageModel.class);
        final LocalizedStringModel localizedTargetName = mock(LocalizedStringModel.class);
        final long sourceId = 1L;

        when(source.getName()).thenReturn(null);
        when(source.getId()).thenReturn(sourceId);
        when(externalTarget.getId()).thenReturn(sourceId);
        when(externalTarget.getName()).thenReturn(localizedTargetName);
        when(requestService.getCurrentLanguage()).thenReturn(ruLang);
        when(skillService.getById(sourceId)).thenReturn(internalTarget);
        when(internalTarget.getName()).thenReturn(localizedTargetName);
        when(localizationService.addLocalizedStringValue(ruLang, null, localizedTargetName)).thenReturn(localizedTargetName);
        when(genericInstanceFactory.getInstance(SkillModel.class)).thenReturn(externalTarget);

        Assert.assertNotNull(underTest.reverseConvert(source));
        verify(source).getName();
        verify(source).getId();
        verifyNoMoreInteractions(source);
        verify(skillService).getById(sourceId);
        verify(requestService).getCurrentLanguage();
        verifyNoMoreInteractions(requestService);
        verify(localizationService).addLocalizedStringValue(ruLang, null, localizedTargetName);
        verifyNoMoreInteractions(localizationService);
        verify(genericInstanceFactory).getInstance(SkillModel.class);
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