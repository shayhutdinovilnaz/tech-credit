package com.technical.credit.obligationfacade.converter.impl;

import com.technical.credit.obligationservice.model.*;
import com.technical.credit.obligationservice.service.impl.DefaultDisplayNameService;
import com.technical.credit.obligationservice.service.impl.DefaultRequestService;
import com.technical.credit.obligationservice.utils.YAMLConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserConverterTest {
    @InjectMocks
    private UserConverter userConverter;

    @Spy
    @InjectMocks
    private LanguageModel languageModelSpy = new LanguageModel();

    @Mock
    private YAMLConfig yamlConfig;

    @Mock
    private DefaultDisplayNameService defaultDisplayNameService;

    @Test
    public void testConvert() {
        final UserModel userModel = mock(UserModel.class);

        final ObligationModel obligationModel = mock(ObligationModel.class);
        final DefaultRequestService defaultRequestService = mock(DefaultRequestService.class);
        final LanguageModel languageModel = mock(LanguageModel.class);
        final LocalizedStringValueModel localizedStringValueModel = mock(LocalizedStringValueModel.class);
        final SkillModel skillModel = mock(SkillModel.class);
        final LocalizedStringModel localizedStringModel = mock(LocalizedStringModel.class);

        when(obligationModel.getSkill()).thenReturn(skillModel);
        when(skillModel.getName()).thenReturn(localizedStringModel);
        when(localizedStringValueModel.getLanguage()).thenReturn(languageModel);
        when(defaultRequestService.getCurrentLanguage()).thenReturn(languageModel);
        when(languageModelSpy.getIsoCode()).thenReturn("isoCode");

        Assert.assertNotNull(userConverter.convert(userModel));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertNullableSource() {
        userConverter.convert(null);
    }
}
