package com.technical.credit.obligationservice.service.impl;

import com.technical.credit.obligationservice.model.CategoryModel;
import com.technical.credit.obligationservice.model.UserModel;
import com.technical.credit.obligationservice.repository.CategoryRepository;
import com.technical.credit.obligationservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultCategoryService extends AbstractModelService<CategoryModel> implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryModel> getCategoriesByOwner(final UserModel user) {
        Assert.notNull(user, "User domain class must not be null!");

        return categoryRepository.findCategoryModelsByOwnerId(user.getId());
    }

    @Override
    public List<CategoryModel> getRootCategoriesByOwner(UserModel owner) {
        Assert.notNull(owner, "User domain class must not be null!");

        return categoryRepository.findCategoryModelsByParentCategoryIsNullAndOwnerId(owner.getId());
    }

    @Override
    protected JpaRepository<CategoryModel, Long> getItemRepository() {
        return categoryRepository;
    }
}
