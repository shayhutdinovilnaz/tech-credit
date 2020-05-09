package com.technical.credit.obligationfacade.converter.impl;


import com.technical.credit.obligationfacade.converter.Converter;
import com.technical.credit.obligationfacade.data.*;
import com.technical.credit.obligationservice.factory.GenericInstanceFactory;
import com.technical.credit.obligationservice.model.*;
import com.technical.credit.obligationservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryConverter implements Converter<CategoryData, CategoryModel> {
    private final GenericInstanceFactory genericInstanceFactory;
    private final Converter<UserData, UserModel> userConverter;
    private final UserService userService;

    @Override
    public CategoryData convert(final CategoryModel source) {
        Assert.notNull(source, "The source instance of converting can't be nullable.");

        final CategoryData target = genericInstanceFactory.getInstance(CategoryData.class);
        target.setId(source.getId());
        target.setUserId(source.getUserId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setParentCategoryId(source.getParentId());
        target.setChildrenCategoriesIds(source.getChildrenCategoriesIds());
        target.setObligationIds(source.getObligationIds());
        target.setUser(Optional.ofNullable(source.getUserId()).map(userService::getById).map(userConverter::convert).orElse(null));
        return target;
    }

    @Override
    public CategoryModel reverseConvert(final CategoryData source) {
        Assert.notNull(source, "The source instance of converting can't be nullable.");

        final CategoryModel target = genericInstanceFactory.getInstance(CategoryModel.class);
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setUserId(source.getUserId());
        target.setParentId(source.getParentCategoryId());
        target.setChildrenCategoriesIds(source.getChildrenCategoriesIds());
        target.setObligationIds(source.getObligationIds());
        return target;
    }
}
