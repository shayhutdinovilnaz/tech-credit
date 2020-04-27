package com.technical.credit.webapp.controller;

import com.technical.credit.obligationfacade.data.ObligationData;
import com.technical.credit.obligationfacade.facade.ObligationFacade;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/obligations")
@RequiredArgsConstructor
public class ObligationController extends AbstractController {
    private final ObligationFacade obligationFacade;

    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Instance of obligation is saved in the system."),
            @ApiResponse(code = 410, message = "A some of the domain isn't found in the system"),
    })
    public ResponseEntity<ObligationData> save(@RequestBody final ObligationData obligation) {
        return new ResponseEntity<>(obligationFacade.save(obligation), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Instance of obligation is found in the system."),
            @ApiResponse(code = 410, message = "A some of the domain isn't found in the system"),
    })
    public ResponseEntity<ObligationData> search(@PathVariable long id) {
        return new ResponseEntity<>(obligationFacade.search(id), HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Instances of obligations are found in the system."),
    })
    public ResponseEntity<List<ObligationData>> search(@RequestParam(required = false) String query,
                                                       @RequestParam(defaultValue = "createdTime", required = false) String sortField,
                                                       @RequestParam(required = false, defaultValue = "false") boolean desc,
                                                       @RequestParam int page,
                                                       @RequestParam int limit) {
        return new ResponseEntity<>(obligationFacade.search(query, sortField, desc, page, limit), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "The instance of obligation is removed from the system."),
            @ApiResponse(code = 410, message = "A required domain isn't found in the system")
    })
    public ResponseEntity<ObligationData> delete(@PathVariable long id) {
        obligationFacade.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
