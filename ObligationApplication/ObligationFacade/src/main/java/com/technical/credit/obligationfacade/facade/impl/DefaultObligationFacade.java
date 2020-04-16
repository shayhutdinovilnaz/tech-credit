package com.technical.credit.obligationfacade.facade.impl;


import com.technical.credit.obligationfacade.facade.ObligationFacade;
import com.technical.credit.obligationservice.service.ObligationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultObligationFacade implements ObligationFacade {
    private final ObligationService obligationService;
}
