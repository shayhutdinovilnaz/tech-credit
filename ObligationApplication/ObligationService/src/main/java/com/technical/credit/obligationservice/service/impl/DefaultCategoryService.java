package com.technical.credit.obligationservice.service.impl;

import com.technical.credit.obligationservice.model.CategoryModel;
import com.technical.credit.obligationservice.model.UserModel;
import com.technical.credit.obligationservice.repository.CategoryRepository;
import com.technical.credit.obligationservice.service.CategoryService;
import com.technical.credit.obligationservice.service.SearchQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DefaultCategoryService extends AbstractModelService<CategoryModel> implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryModel> search(final SearchQuery searchQuery, final UserModel user) {
        Assert.notNull(searchQuery, "SearchQuery domain class must not be null!");
        Assert.notNull(user, "User domain class must not be null!");

        return categoryRepository.findCategoryModelsByNameContainingIgnoreCaseAndUserId(getQueryText(searchQuery.getFreeText()), buildPageRequest(searchQuery), user.getId());
    }

    @Override
    public Set<CategoryModel> findByParentCategoryId(Long parentCategoryId) {
        return categoryRepository.findCategoryModelsByParentCategoryIdOrderByCreatedTime(parentCategoryId);
    }

    @Override
    protected JpaRepository<CategoryModel, Long> getItemRepository() {
        return categoryRepository;
    }
}
