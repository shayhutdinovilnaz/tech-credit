package com.technical.credit.obligationservice.service.impl;

import com.technical.credit.obligationservice.model.CategoryModel;
import com.technical.credit.obligationservice.model.UserModel;
import com.technical.credit.obligationservice.repository.CategoryRepository;
import com.technical.credit.obligationservice.service.SearchQuery;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class DefaultCategoryServiceTest extends AbstractModelServiceTest<CategoryModel> {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private DefaultCategoryService underTest;

    @Before
    public void setUp() {
        CategoryModel categoryModelOne = new CategoryModel();
        CategoryModel categoryModelTwo = new CategoryModel();
        categoryModelOne.setParentId(1L);
        categoryModelTwo.setParentId(1L);
        final Set<CategoryModel> categoryModelSet = new HashSet<>();
        categoryModelSet.add(categoryModelOne);
        categoryModelSet.add(categoryModelTwo);

        Mockito.when(categoryRepository.findCategoryModelsByParentCategoryIdOrderByCreatedTime(categoryModelOne.getParentId()))
                .thenReturn(categoryModelSet);
    }

    @Test
    public void testSearch() {
        final SearchQuery searchQuery = mock(SearchQuery.class);
        final UserModel currentUser = mock(UserModel.class);
        final String queryText = "freeText";
        final long userId = 1L;
        final List<CategoryModel> resultList = Collections.singletonList(new CategoryModel());

        when(searchQuery.getPage()).thenReturn(0);
        when(searchQuery.getLimit()).thenReturn(100);
        when(searchQuery.isDesc()).thenReturn(false);
        when(searchQuery.getSortField()).thenReturn("sortField");
        when(searchQuery.getFreeText()).thenReturn(queryText);
        when(currentUser.getId()).thenReturn(userId);
        when(categoryRepository.findCategoryModelsByNameContainingIgnoreCaseAndUserId(any(String.class), any(PageRequest.class), any(Long.class))).thenReturn(resultList);

        Assert.assertTrue(CollectionUtils.isNotEmpty(underTest.search(searchQuery, currentUser)));
        verify(searchQuery).getPage();
        verify(searchQuery).getLimit();
        verify(searchQuery).getSortField();
        verify(searchQuery).getFreeText();
        verify(searchQuery).isDesc();
        verifyNoMoreInteractions(searchQuery);
        verify(currentUser).getId();
        verifyNoMoreInteractions(currentUser);
        verify(categoryRepository).findCategoryModelsByNameContainingIgnoreCaseAndUserId(any(String.class), any(PageRequest.class), any(Long.class));
        verifyNoMoreInteractions(categoryRepository);
    }

    @Test
    public void whenValidParentCategoryId_thenSetCategoriesShouldBeFound() {
        Long parentCategoryId = 1L;
        final Set<CategoryModel> categoryModelSet = underTest.findByParentCategoryId(parentCategoryId);
        CategoryModel categoryModel = categoryModelSet.iterator().next();
        assertEquals(categoryModel.getParentId(), parentCategoryId);
    }

    @Override
    protected Class<CategoryModel> getGenericClassOfService() {
        return CategoryModel.class;
    }

    @Override
    protected AbstractModelService<CategoryModel> getGenericModelService() {
        return underTest;
    }

    @Override
    protected JpaRepository<CategoryModel, Long> getModelRepository() {
        return categoryRepository;
    }
}
