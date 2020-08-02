package com.technical.credit.obligationfacade.converter;

import com.technical.credit.core.converter.Converter;
import com.technical.credit.core.factory.GenericInstanceFactory;
import com.technical.credit.obligationfacade.data.CategoryData;
import com.technical.credit.obligationfacade.data.ObligationData;
import com.technical.credit.obligationfacade.data.UserData;
import com.technical.credit.obligationservice.model.CategoryModel;
import com.technical.credit.obligationservice.model.ItemModel;
import com.technical.credit.obligationservice.model.ObligationModel;
import com.technical.credit.obligationservice.model.UserModel;
import com.technical.credit.obligationservice.service.CategoryService;
import com.technical.credit.obligationservice.service.ObligationService;
import com.technical.credit.obligationservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryConverter implements Converter<CategoryData, CategoryModel> {
    private final GenericInstanceFactory genericInstanceFactory;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ObligationService obligationService;
    private final Converter<UserData, UserModel> userConverter;
    private final Converter<ObligationData, ObligationModel> obligationConverter;

    @Override
    public CategoryData convert(final CategoryModel source) {
        Assert.notNull(source, "The source instance of converting can't be nullable.");

        final CategoryData target = genericInstanceFactory.getInstance(CategoryData.class);
        target.setId(source.getId());
        target.setName(source.getName());
        target.setOwner(Optional.ofNullable(source.getOwnerId()).map(userService::getById).map(userConverter::convert).orElse(null));
        target.setChildrenCategoriesId(convertChildrenCategories(source));
        target.setParentCategoryId(Optional.ofNullable(source.getParentCategory()).map(ItemModel::getId).orElse(null));
        target.setObligations(convertObligations(source));
        return target;
    }

    private Set<Long> convertChildrenCategories(final CategoryModel source) {
        return CollectionUtils.emptyIfNull(source.getChildrenCategories())
                .stream()
                .map(ItemModel::getId)
                .collect(Collectors.toSet());
    }

    private Set<ObligationData> convertObligations(final CategoryModel source) {
        return CollectionUtils.emptyIfNull(source.getObligations())
                .stream()
                .map(obligationConverter::convert)
                .collect(Collectors.toSet());
    }


    @Override
    public CategoryModel reverseConvert(final CategoryData source) {
        Assert.notNull(source, "The source instance of converting can't be nullable.");

        final CategoryModel target = genericInstanceFactory.getInstance(CategoryModel.class);
        target.setId(source.getId());
        target.setName(source.getName());
        target.setOwnerId(Optional.ofNullable(source.getOwner()).map(UserData::getId).orElse(null));
        target.setChildrenCategories(convertChildrenCategories(source));
        target.setParentCategory(Optional.ofNullable(source.getParentCategoryId()).map(categoryService::getById).orElse(null));
        target.setObligations(convertObligations(source));

        return target;

    }

    private Set<ObligationModel> convertObligations(final CategoryData source) {
        return CollectionUtils.emptyIfNull(source.getObligations()).stream().map(ObligationData::getId).map(obligationService::getById).collect(Collectors.toSet());
    }

    private Set<CategoryModel> convertChildrenCategories(final CategoryData source) {
        return CollectionUtils.emptyIfNull(source.getChildrenCategoriesId()).stream().map(categoryService::getById).collect(Collectors.toSet());
    }
}
