package com.technical.credit.webapp.controller;

import com.technical.credit.core.controller.AbstractController;
import com.technical.credit.obligationfacade.data.CategoryData;
import com.technical.credit.obligationfacade.facade.CategoryFacade;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController extends AbstractController {
    private final CategoryFacade categoryFacade;

    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Instance of category is saved in the system."),
            @ApiResponse(code = 410, message = "A some of the domain isn't found in the system"),
    })
    @PreAuthorize("hasAuthority('create_category')")
    public ResponseEntity<CategoryData> save(@Valid @RequestBody final CategoryData category) {
        return new ResponseEntity<>(categoryFacade.save(category), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Instance of category is found in the system."),
            @ApiResponse(code = 410, message = "A some of the domain isn't found in the system"),
    })
    @PreAuthorize("hasAuthority('read_category')")
    public ResponseEntity<CategoryData> search(@PathVariable long id) {
        return new ResponseEntity<>(categoryFacade.search(id), HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Instances of categories are found in the system."),
    })
    @PreAuthorize("hasAuthority('read_category')")
    public ResponseEntity<List<CategoryData>> searchRootCategories() {
        return new ResponseEntity<>(categoryFacade.searchRootCategories(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "The instance of category is removed from the system."),
            @ApiResponse(code = 410, message = "A required domain isn't found in the system")
    })
    @PreAuthorize("hasAuthority('delete_category')")
    public ResponseEntity<CategoryData> delete(@PathVariable long id) {
        categoryFacade.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
