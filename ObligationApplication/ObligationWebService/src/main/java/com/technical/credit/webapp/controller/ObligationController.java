package com.technical.credit.webapp.controller;

import com.technical.credit.obligationfacade.data.ObligationData;
import com.technical.credit.obligationfacade.facade.ObligationFacade;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/obligations")
@RequiredArgsConstructor
public class ObligationController extends AbstractController {
    private final ObligationFacade obligationFacade;

    @PutMapping
    @ApiResponse(code = 200, message = "Instance of obligation is saved in the system.")
    public ResponseEntity<ObligationData> save(@RequestBody final ObligationData obligation) {
        return new ResponseEntity<>(obligationFacade.save(obligation), HttpStatus.CREATED);
    }
}
