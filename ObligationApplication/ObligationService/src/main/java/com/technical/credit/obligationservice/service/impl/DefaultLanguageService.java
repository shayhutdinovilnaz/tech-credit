package com.technical.credit.obligationservice.service.impl;

import com.technical.credit.common.exception.ModelNotFoundException;
import com.technical.credit.obligationservice.model.LanguageModel;
import com.technical.credit.obligationservice.repository.LanguageRepository;
import com.technical.credit.obligationservice.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class DefaultLanguageService extends AbstractModelService<LanguageModel> implements LanguageService {
    private final LanguageRepository languageRepository;

    @Override
    public LanguageModel getByIsoCode(final String isoCode) {
        Assert.notNull(isoCode, "Iso code of language can't be nullable.");
        return languageRepository.findByIsoCodeAndActive(isoCode, true).orElseThrow(() -> new ModelNotFoundException("Language isn't found. Required isoCode = " + isoCode));
    }

    @Override
    protected JpaRepository<LanguageModel, Long> getItemRepository() {
        return languageRepository;
    }
}
