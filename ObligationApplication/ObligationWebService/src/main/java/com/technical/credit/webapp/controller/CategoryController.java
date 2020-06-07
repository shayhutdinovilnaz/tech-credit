package com.technical.credit.webapp.controller;

import com.technical.credit.obligationfacade.data.CategoryData;
import com.technical.credit.obligationfacade.facade.CategoryFacade;
import com.technical.credit.obligationservice.model.CategoryModel;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
    public ResponseEntity<CategoryData> save(@RequestBody final CategoryData category) {
        return new ResponseEntity<>(categoryFacade.save(category), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{categoryId}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Instance of category is found in the system."),
            @ApiResponse(code = 410, message = "A some of the domain isn't found in the system"),
    })
    @PreAuthorize("hasAuthority('read_category')")
    public ResponseEntity<CategoryData> search(@PathVariable Long categoryId) {
        return new ResponseEntity<>(categoryFacade.search(categoryId), HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Instances of categories are found in the system."),
    })
    @PreAuthorize("hasAuthority('read_category')")
    public ResponseEntity<List<CategoryData>> search(@RequestParam(required = false) String query,
                                                       @RequestParam(defaultValue = "createdTime", required = false) String sortField,
                                                       @RequestParam(required = false, defaultValue = "false") boolean desc,
                                                       @RequestParam int page,
                                                       @RequestParam int limit) {
        return new ResponseEntity<>(categoryFacade.search(query, sortField, desc, page, limit), HttpStatus.OK);
    }

    @GetMapping(value = "/searchByParentId/{parentCategoryId}")
    @PreAuthorize("hasAuthority('read_category_by_parentCategoryId')")
    public ResponseEntity<Set<CategoryData>> searchByParentCategoryId(@PathVariable long parentCategoryId) {
        return new ResponseEntity<>(categoryFacade.findByParentCategoryId(parentCategoryId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{categoryId}")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "The instance of category is removed from the system."),
            @ApiResponse(code = 410, message = "A required domain isn't found in the system")
    })
    @PreAuthorize("hasAuthority('delete_category')")
    public ResponseEntity<CategoryData> delete(@PathVariable Long categoryId) {
        categoryFacade.delete(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
