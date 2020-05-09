package com.technical.credit.obligationfacade.facade.impl;

import com.technical.credit.obligationfacade.converter.impl.CategoryConverter;
import com.technical.credit.obligationfacade.data.CategoryData;
import com.technical.credit.obligationservice.exception.ModelNotFoundException;
import com.technical.credit.obligationservice.factory.GenericInstanceFactory;
import com.technical.credit.obligationservice.model.CategoryModel;
import com.technical.credit.obligationservice.model.UserModel;
import com.technical.credit.obligationservice.service.CategoryService;
import com.technical.credit.obligationservice.service.RequestService;
import com.technical.credit.obligationservice.service.SearchQuery;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCategoryFacadeTest {
    @Mock
    CategoryConverter categoryConverter;

    @Mock
    CategoryService categoryService;

    @Mock
    RequestService requestService;

    @Mock
    GenericInstanceFactory genericInstanceFactory;

    @Test
    public void testSaveSuccess() {
        final CategoryData incomeCategoryData = mock(CategoryData.class);
        final CategoryData outcomeCategoryData = mock(CategoryData.class);
        final CategoryModel CategoryModel = mock(CategoryModel.class);
        final UserModel currentUser = mock(UserModel.class);
        final long userId = 1L;

        when(categoryConverter.reverseConvert(incomeCategoryData)).thenReturn(CategoryModel);
        when(categoryConverter.convert(CategoryModel)).thenReturn(outcomeCategoryData);
        when(requestService.getCurrentUser()).thenReturn(currentUser);
        when(currentUser.getId()).thenReturn(userId);

        final DefaultCategoryFacade underTest = new DefaultCategoryFacade(categoryConverter, categoryService, requestService, genericInstanceFactory);
        Assert.assertNotNull(underTest.save(incomeCategoryData));
        verify(categoryService).save(CategoryModel);
        verifyNoMoreInteractions(categoryService);
        verify(categoryConverter).reverseConvert(incomeCategoryData);
        verify(categoryConverter).convert(CategoryModel);
        verifyNoMoreInteractions(categoryConverter);
        verify(requestService).getCurrentUser();
        verifyNoMoreInteractions(requestService);
        verify(CategoryModel).setUserId(userId);
        verify(currentUser).getId();
        verifyNoMoreInteractions(currentUser);
        verifyZeroInteractions(incomeCategoryData);
        verifyZeroInteractions(outcomeCategoryData);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveNullableObligation() {
        new DefaultCategoryFacade(categoryConverter, categoryService, requestService, genericInstanceFactory).save(null);
    }

    @Test(expected = ModelNotFoundException.class)
    public void testSaveExceptionInConverter() {
        final CategoryData CategoryData = mock(CategoryData.class);

        when(categoryConverter.reverseConvert(CategoryData)).thenThrow(ModelNotFoundException.class);

        new DefaultCategoryFacade(categoryConverter, categoryService, requestService, genericInstanceFactory).save(CategoryData);
    }

    @Test
    public void testSearchById() {
        final long id = 1L;
        final CategoryModel CategoryModel = mock(CategoryModel.class);
        final CategoryData CategoryData = mock(CategoryData.class);

        when(categoryService.getById(id)).thenReturn(CategoryModel);
        when(categoryConverter.convert(CategoryModel)).thenReturn(CategoryData);

        Assert.assertNotNull(new DefaultCategoryFacade(categoryConverter, categoryService, requestService, genericInstanceFactory).search(id));
        verify(categoryService).getById(id);
        verifyNoMoreInteractions(categoryService);
        verify(categoryConverter).convert(CategoryModel);
        verifyNoMoreInteractions(categoryConverter);
        verifyZeroInteractions(requestService);
        verifyZeroInteractions(CategoryData);
        verifyZeroInteractions(CategoryModel);
    }

    @Test(expected = ModelNotFoundException.class)
    public void testSearchByIdNotFound() {
        final long id = 1L;

        when(categoryService.getById(id)).thenThrow(ModelNotFoundException.class);

        new DefaultCategoryFacade(categoryConverter, categoryService, requestService, genericInstanceFactory).search(id);
    }

    @Test
    public void testDelete() {
        final long id = 1L;
        final CategoryModel obligation = mock(CategoryModel.class);

        when(categoryService.getById(id)).thenReturn(obligation);

        new DefaultCategoryFacade(categoryConverter, categoryService, requestService, genericInstanceFactory).delete(id);
        verify(categoryService).getById(id);
        verify(categoryService).delete(obligation);
        verifyNoMoreInteractions(categoryService);
        verifyZeroInteractions(categoryConverter);
        verifyZeroInteractions(requestService);
        verifyZeroInteractions(obligation);
    }

    @Test(expected = ModelNotFoundException.class)
    public void testDeleteNotExistObligation() {
        final long id = 1L;

        when(categoryService.getById(id)).thenThrow(ModelNotFoundException.class);

        new DefaultCategoryFacade(categoryConverter, categoryService, requestService, genericInstanceFactory).delete(id);
    }

    @Test
    public void testSearch() {
        final String freeText = "Free text";
        final String sortField = "Sort field";
        final boolean desc = true;
        final int page = 0;
        final int limit = 100;
        final SearchQuery searchQuery = mock(SearchQuery.class);
        final List<CategoryModel> categoryModels = mock(ArrayList.class);
        final Set<CategoryModel> childrenCategoryModels = mock(HashSet.class);
        final UserModel currentUser = mock(UserModel.class);
        final CategoryModel CategoryModel = mock(CategoryModel.class);
        final CategoryData CategoryData = mock(CategoryData.class);

        when(genericInstanceFactory.getInstance(SearchQuery.class)).thenReturn(searchQuery);
        when(requestService.getCurrentUser()).thenReturn(currentUser);
        when(categoryService.search(searchQuery, currentUser)).thenReturn(categoryModels);
        when(categoryService.findByParentCategoryId(1L)).thenReturn(childrenCategoryModels);
        when(categoryModels.stream()).thenReturn(Stream.of(CategoryModel));
        when(categoryConverter.convert(CategoryModel)).thenReturn(CategoryData);

        final DefaultCategoryFacade underTest = new DefaultCategoryFacade(categoryConverter, categoryService, requestService, genericInstanceFactory);
        final List<CategoryData> result = underTest.search(freeText, sortField, desc, page, limit);
        Assert.assertEquals(1, result.size());
        verify(searchQuery).setFreeText(freeText);
        verify(searchQuery).setSortField(sortField);
        verify(searchQuery).setDesc(desc);
        verify(searchQuery).setPage(page);
        verify(searchQuery).setLimit(limit);
        verify(genericInstanceFactory).getInstance(SearchQuery.class);
        verifyNoMoreInteractions(genericInstanceFactory);
        verify(requestService).getCurrentUser();
        verifyNoMoreInteractions(requestService);
        verify(categoryService).search(searchQuery, currentUser);
        verify(categoryService).findByParentCategoryId(anyLong()); // todo
        verifyNoMoreInteractions(categoryService);
        verify(categoryConverter).convert(CategoryModel);
        verifyNoMoreInteractions(categoryConverter);
        verifyZeroInteractions(CategoryData, CategoryModel);
    }

}