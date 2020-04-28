package com.technical.credit.obligationservice.service.impl;

import com.technical.credit.obligationservice.exception.ModelNotFoundException;
import com.technical.credit.obligationservice.model.LanguageModel;
import com.technical.credit.obligationservice.repository.LanguageRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class DefaultLanguageServiceTest extends AbstractModelServiceTest<LanguageModel> {

    @InjectMocks
    private DefaultLanguageService underTest;

    @Mock
    private LanguageRepository languageRepository;

    @Test
    public void getByIsoCode() {
        final String isoCode = "ru";
        final Optional<LanguageModel> language = Optional.of(new LanguageModel());

        when(languageRepository.findByActiveAndIsoCode(isoCode, true)).thenReturn(language);

        Assert.assertNotNull(underTest.getByIsoCode(isoCode));
        verify(languageRepository).findByActiveAndIsoCode(isoCode, true);
        verifyNoMoreInteractions(languageRepository);
    }

    @Test(expected = ModelNotFoundException.class)
    public void getByIsoCodeNotFound() {
        final String isoCode = "ru";
        final Optional<LanguageModel> language = Optional.empty();

        when(languageRepository.findByActiveAndIsoCode(isoCode, true)).thenReturn(language);

        underTest.getByIsoCode(isoCode);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByIsoCodeNullableArgument() {
        underTest.getByIsoCode(null);
    }

    @Override
    protected Class<LanguageModel> getGenericClassOfService() {
        return LanguageModel.class;
    }

    @Override
    protected AbstractModelService<LanguageModel> getGenericModelService() {
        return underTest;
    }

    @Override
    protected JpaRepository<LanguageModel, Long> getModelRepository() {
        return languageRepository;
    }
}