package com.technical.credit.obligationfacade.converter.impl;


import com.technical.credit.obligationfacade.data.*;
import com.technical.credit.obligationservice.exception.ModelNotFoundException;
import com.technical.credit.obligationservice.factory.GenericInstanceFactory;
import com.technical.credit.obligationservice.model.*;
import com.technical.credit.obligationservice.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryConverterTest {

    @Mock
    private GenericInstanceFactory genericInstanceFactory;
    
    @Mock
    private UserConverter userConverter;

    @Mock
    private UserService userService;

    @Test
    public void testConvertSuccess() {
        final CategoryConverter underTest = new CategoryConverter(genericInstanceFactory, userConverter, userService);
        final CategoryModel source = mock(CategoryModel.class);
        final CategoryData target = mock(CategoryData.class);
        final UserModel userSource = mock(UserModel.class);
        final UserData userTarget = mock(UserData.class);
        final Long categoryId = 1L;
        final long userId = 1L;
        final String categoryName = "Category name";
        final String categoryDescription = "Category description";

        when(source.getId()).thenReturn(categoryId);
        when(source.getName()).thenReturn(categoryName);
        when(source.getDescription()).thenReturn(categoryDescription);
        when(source.getUserId()).thenReturn(userId);
        when(userService.getById(userId)).thenReturn(userSource);
        when(genericInstanceFactory.getInstance(CategoryData.class)).thenReturn(target);
        when(userConverter.convert(userSource)).thenReturn(userTarget);

        final CategoryData categoryData = underTest.convert(source);
        Assert.assertNotNull(categoryData);
        verify(genericInstanceFactory).getInstance(CategoryData.class);
        verifyNoMoreInteractions(genericInstanceFactory);
        verify(target).setId(categoryId);
        verify(target).setName(categoryName);
        verify(target).setDescription(categoryDescription);
        verify(target).setUser(userTarget);
        verifyNoMoreInteractions(target);
        verify(userConverter).convert(userSource);
        verifyNoMoreInteractions(userConverter);
        verify(userService).getById(userId);
        verifyNoMoreInteractions(userService);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertNullableSource() {
        final CategoryConverter underTest = new CategoryConverter(genericInstanceFactory, userConverter, userService);
        underTest.convert(null);
    }

    @Test
    public void testConvertNullableAttributeSource() {
        final CategoryConverter underTest = new CategoryConverter(genericInstanceFactory, userConverter, userService);
        final CategoryModel source = mock(CategoryModel.class);
        final CategoryData target = mock(CategoryData.class);
        final Long categoryId = 1L;
        final String categoryName = "Category name";
        final String categoryDescription = "Category description";

        when(source.getId()).thenReturn(categoryId);
        when(source.getName()).thenReturn(categoryName);
        when(source.getDescription()).thenReturn(categoryDescription);
        when(source.getUserId()).thenReturn(null);
        when(genericInstanceFactory.getInstance(CategoryData.class)).thenReturn(target);

        final CategoryData category = underTest.convert(source);
        Assert.assertNotNull(category);
        verify(source).getId();
        verify(source).getName();
        verify(source).getDescription();
        verify(source).getUserId();
        verifyNoMoreInteractions(source);
        verify(genericInstanceFactory).getInstance(CategoryData.class);
        verifyNoMoreInteractions(genericInstanceFactory);
        verify(target).setId(categoryId);
        verify(target).setName(categoryName);
        verify(target).setDescription(categoryDescription);
        verify(target).setUser(null);
        verifyNoMoreInteractions(target);
        verifyZeroInteractions(userConverter);
        verifyZeroInteractions(userService);
    }

    @Test(expected = ModelNotFoundException.class)
    public void testConvertAttributeNotFound() {
        final CategoryConverter underTest = new CategoryConverter(genericInstanceFactory, userConverter, userService);
        final CategoryModel source = mock(CategoryModel.class);
        final CategoryData target = mock(CategoryData.class);

        when(genericInstanceFactory.getInstance(CategoryData.class)).thenReturn(target);
        when(source.getUserId()).thenReturn(1L);
        when(userService.getById(1L)).thenThrow(ModelNotFoundException.class);
        underTest.convert(source);
    }

    @Test
    public void testReverseConvertSuccess() {
        final CategoryConverter underTest = new CategoryConverter(genericInstanceFactory, userConverter, userService);
        final CategoryModel target = mock(CategoryModel.class);
        final CategoryData source = mock(CategoryData.class);
        final UserModel userTarget = mock(UserModel.class);
        final UserData userSource = mock(UserData.class);
        final Long categoryId = 1L;
        final String categoryName = "Category name";
        final String categoryDescription = "Category description";
        final Long userId = 1L;

        when(source.getId()).thenReturn(categoryId);
        when(source.getName()).thenReturn(categoryName);
        when(source.getDescription()).thenReturn(categoryDescription);
        when(source.getUser()).thenReturn(userSource);
        when(userSource.getId()).thenReturn(userId);
        when(genericInstanceFactory.getInstance(CategoryModel.class)).thenReturn(target);
        when(userService.getById(userId)).thenReturn(userTarget);
        when(userTarget.getId()).thenReturn(userId);

        final CategoryModel category = underTest.reverseConvert(source);
        Assert.assertNotNull(category);
        verify(source).getId();
        verify(source).getName();
        verify(source).getDescription();
        verify(source).getUser();
        verifyNoMoreInteractions(source);
        verify(userSource).getId();
        verifyNoMoreInteractions(userSource);
        verify(genericInstanceFactory).getInstance(CategoryModel.class);
        verifyNoMoreInteractions(genericInstanceFactory);
        verify(target).setId(categoryId);
        verify(target).setName(categoryName);
        verify(target).setDescription(categoryDescription);
        verify(target).setUserId(userId);
        verifyNoMoreInteractions(target);
        verify(userService).getById(userId);
        verifyNoMoreInteractions(userService);
        verifyZeroInteractions(userConverter);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReverseConvertNullableSource() {
        final CategoryConverter underTest = new CategoryConverter(genericInstanceFactory, userConverter, userService);
        underTest.reverseConvert(null);
    }

    @Test
    public void testReverseConvertNullableAttributeSource() {
        final CategoryConverter underTest = new CategoryConverter(genericInstanceFactory, userConverter, userService);
        final CategoryModel target = mock(CategoryModel.class);
        final CategoryData source = mock(CategoryData.class);
        final Long categoryId = 1L;
        final String categoryName = "Category name";
        final String categoryDescription = "Category description";

        when(source.getId()).thenReturn(categoryId);
        when(source.getName()).thenReturn(categoryName);
        when(source.getDescription()).thenReturn(categoryDescription);
        when(source.getUser()).thenReturn(null);
        when(genericInstanceFactory.getInstance(CategoryModel.class)).thenReturn(target);

        final CategoryModel category = underTest.reverseConvert(source);
        Assert.assertNotNull(category);
        verify(source).getId();
        verify(source).getName();
        verify(source).getDescription();
        verifyNoMoreInteractions(source);
        verify(genericInstanceFactory).getInstance(ObligationModel.class);
        verifyNoMoreInteractions(genericInstanceFactory);
        verify(target).setId(categoryId);
        verify(target).setName(categoryName);
        verify(target).setDescription(categoryDescription);
        verifyNoMoreInteractions(target);
        verifyZeroInteractions(userService);
        verifyZeroInteractions(userConverter);
    }

    @Test(expected = ModelNotFoundException.class)
    public void testReverseConvertAttributeNotFound() {
        final CategoryConverter underTest = new CategoryConverter(genericInstanceFactory, userConverter, userService);
        final CategoryModel target = mock(CategoryModel.class);
        final CategoryData source = mock(CategoryData.class);
        final UserData userSource = mock(UserData.class);

        when(genericInstanceFactory.getInstance(CategoryModel.class)).thenReturn(target);
        when(source.getUser()).thenReturn(userSource);
        when(userSource.getId()).thenReturn(Long.MAX_VALUE);
        //when(userService.getById(Long.MAX_VALUE)).thenThrow(ModelNotFoundException.class);
        underTest.reverseConvert(source);
    }
}