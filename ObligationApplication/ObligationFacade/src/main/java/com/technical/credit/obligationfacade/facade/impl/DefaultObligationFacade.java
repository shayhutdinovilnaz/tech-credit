package com.technical.credit.obligationfacade.facade.impl;


import com.technical.credit.obligationfacade.converter.Converter;
import com.technical.credit.obligationfacade.data.ObligationData;
import com.technical.credit.obligationfacade.facade.ObligationFacade;
import com.technical.credit.obligationservice.model.ObligationModel;
import com.technical.credit.obligationservice.service.ObligationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class DefaultObligationFacade implements ObligationFacade {
    private final Converter<ObligationData, ObligationModel> obligationConverter;
    private final ObligationService obligationService;

    @Override
    public ObligationData save(final ObligationData obligationData) {
        Assert.notNull(obligationData, "The source instance of converting can't be nullable.");

        final ObligationModel obligationModel = obligationConverter.reverseConvert(obligationData);
        obligationService.save(obligationModel);
        return obligationConverter.convert(obligationModel);
    }
}
