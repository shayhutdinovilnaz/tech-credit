package com.technical.credit.obligationfacade.facade.impl;

import com.technical.credit.core.converter.Converter;
import com.technical.credit.obligationfacade.data.CategoryData;
import com.technical.credit.obligationfacade.facade.CategoryFacade;
import com.technical.credit.obligationservice.model.CategoryModel;

import com.technical.credit.obligationservice.service.CategoryService;
import com.technical.credit.obligationservice.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultCategoryFacade implements CategoryFacade {
    private final Converter<CategoryData, CategoryModel> categoryConverter;
    private final CategoryService categoryService;
    private final RequestService requestService;

    @Override
    public CategoryData save(CategoryData category) {
        Assert.notNull(category, "Domain class must not be null!");

        final CategoryModel categoryModel = categoryConverter.reverseConvert(category);
        if (Objects.isNull(categoryModel.getOwnerId())) {
            categoryModel.setOwnerId(requestService.getCurrentUser().getId());
        }

        categoryService.save(categoryModel);
        return categoryConverter.convert(categoryModel);
    }

    @Override
    public CategoryData search(long id) {
        return categoryConverter.convert(categoryService.getById(id));
    }

    @Override
    public List<CategoryData> searchRootCategories() {
        return categoryService.getRootCategoriesByOwner(requestService.getCurrentUser())
                .stream()
                .map(categoryConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(long id) {
        categoryService.delete(categoryService.getById(id));
    }
}
