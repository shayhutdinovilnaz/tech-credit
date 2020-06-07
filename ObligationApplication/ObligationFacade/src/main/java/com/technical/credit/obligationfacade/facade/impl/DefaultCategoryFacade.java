package com.technical.credit.obligationfacade.facade.impl;


import com.technical.credit.obligationfacade.converter.Converter;
import com.technical.credit.obligationfacade.data.CategoryData;
import com.technical.credit.obligationfacade.facade.CategoryFacade;
import com.technical.credit.obligationservice.factory.GenericInstanceFactory;
import com.technical.credit.obligationservice.model.CategoryModel;
import com.technical.credit.obligationservice.service.CategoryService;
import com.technical.credit.obligationservice.service.RequestService;
import com.technical.credit.obligationservice.service.SearchQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultCategoryFacade implements CategoryFacade {
    private final Converter<CategoryData, CategoryModel> categoryConverter;
    private final CategoryService categoryService;
    private final RequestService requestService;
    private final GenericInstanceFactory genericInstanceFactory;

    @Override
    public CategoryData save(final CategoryData CategoryData) {
        Assert.notNull(CategoryData, "Domain class must not be null!");

        final CategoryModel categoryModel = categoryConverter.reverseConvert(CategoryData);
        categoryModel.setUserId(requestService.getCurrentUser().getId());
        categoryService.save(categoryModel);
        return categoryConverter.convert(categoryModel);
    }

    @Override
    public CategoryData search(final long id) {
        return categoryConverter.convert(categoryService.getById(id));
    }

    @Override
    public List<CategoryData> search(final String freeText, final String sortField, final boolean desc, final int page, final int limit) {
        final SearchQuery searchQuery = genericInstanceFactory.getInstance(SearchQuery.class);
        searchQuery.setFreeText(freeText);
        searchQuery.setDesc(desc);
        searchQuery.setSortField(sortField);
        searchQuery.setPage(page);
        searchQuery.setLimit(limit);

        return Optional.ofNullable(categoryService.search(searchQuery, requestService.getCurrentUser()))
                .orElseGet(Collections::emptyList)
                .stream()
                .map(categoryConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Set<CategoryData> findByParentCategoryId(Long parentCategoryId) {
        return Optional.ofNullable(categoryService.findByParentCategoryId(parentCategoryId))
                .orElseGet(Collections::emptySet)
                .stream()
                .map(categoryConverter::convert)
                .collect(Collectors.toSet());
    }


    @Override
    public void delete(final long id) {
        categoryService.delete(categoryService.getById(id));
    }
}
