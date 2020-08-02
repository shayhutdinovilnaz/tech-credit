package com.technical.credit.obligationfacade.converter.impl;


import com.technical.credit.common.exception.ModelNotFoundException;
import com.technical.credit.obligationfacade.converter.ObligationConverter;
import com.technical.credit.obligationfacade.converter.SkillConverter;
import com.technical.credit.obligationfacade.converter.StatusConverter;
import com.technical.credit.obligationfacade.converter.UserConverter;
import com.technical.credit.obligationfacade.data.ObligationData;
import com.technical.credit.obligationfacade.data.SkillData;
import com.technical.credit.obligationfacade.data.StatusData;
import com.technical.credit.obligationfacade.data.UserData;
import com.technical.credit.obligationservice.factory.GenericInstanceFactory;
import com.technical.credit.obligationservice.model.*;
import com.technical.credit.obligationservice.service.CategoryService;
import com.technical.credit.obligationservice.service.SkillService;
import com.technical.credit.obligationservice.service.StatusService;
import com.technical.credit.obligationservice.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ObligationConverterTest {

    @Mock
    private GenericInstanceFactory genericInstanceFactory;

    @Mock
    private SkillConverter skillConverter;

    @Mock
    private StatusConverter statusConverter;

    @Mock
    private UserConverter userConverter;

    @Mock
    private UserService userService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private SkillService skillService;

    @Mock
    private StatusService statusService;

    @Test
    public void testConvertSuccess() {
        final ObligationConverter underTest = new ObligationConverter(genericInstanceFactory, skillConverter, statusConverter, userConverter, userService, categoryService, skillService, statusService);
        final ObligationModel source = mock(ObligationModel.class);
        final ObligationData target = mock(ObligationData.class);
        final StatusModel statusSource = mock(StatusModel.class);
        final StatusData statusTarget = mock(StatusData.class);
        final SkillModel skillSource = mock(SkillModel.class);
        final SkillData skillTarget = mock(SkillData.class);
        final CategoryModel categorySource = mock(CategoryModel.class);
        final UserModel userSource = mock(UserModel.class);
        final UserData userTarget = mock(UserData.class);
        final Date expiredDate = mock(Date.class);
        final Long obligationId = 1L;
        final Long userId = 1L;
        final Long categoryId = 1L;
        final String obligationName = "Obligation name";
        final String obligationDescription = "Obligation description";

        when(source.getId()).thenReturn(obligationId);
        when(source.getName()).thenReturn(obligationName);
        when(source.getDescription()).thenReturn(obligationDescription);
        when(source.getExpiredDate()).thenReturn(expiredDate);
        when(source.getSkill()).thenReturn(skillSource);
        when(source.getStatus()).thenReturn(statusSource);
        when(source.getUserId()).thenReturn(userId);
        when(source.getCategory()).thenReturn(categorySource);
        when(categorySource.getId()).thenReturn(categoryId);
        when(userService.getById(userId)).thenReturn(userSource);
        when(genericInstanceFactory.getInstance(ObligationData.class)).thenReturn(target);
        when(skillConverter.convert(skillSource)).thenReturn(skillTarget);
        when(statusConverter.convert(statusSource)).thenReturn(statusTarget);
        when(userConverter.convert(userSource)).thenReturn(userTarget);

        final ObligationData obligation = underTest.convert(source);
        Assert.assertNotNull(obligation);
        verify(source).getId();
        verify(source).getName();
        verify(source).getDescription();
        verify(source).getExpiredDate();
        verify(source).getUserId();
        verify(source).getSkill();
        verify(source).getStatus();
        verify(source).getCategory();
        verifyNoMoreInteractions(source);
        verify(genericInstanceFactory).getInstance(ObligationData.class);
        verifyNoMoreInteractions(genericInstanceFactory);
        verify(target).setId(obligationId);
        verify(target).setName(obligationName);
        verify(target).setDescription(obligationDescription);
        verify(target).setExpiredDate(expiredDate);
        verify(target).setSkill(skillTarget);
        verify(target).setStatus(statusTarget);
        verify(target).setUser(userTarget);
        verify(target).setCategoryId(categoryId);
        verifyNoMoreInteractions(target);
        verify(skillConverter).convert(skillSource);
        verifyNoMoreInteractions(skillConverter);
        verify(statusConverter).convert(statusSource);
        verifyNoMoreInteractions(statusConverter);
        verify(userConverter).convert(userSource);
        verifyNoMoreInteractions(userConverter);
        verify(userService).getById(userId);
        verifyNoMoreInteractions(userService);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertNullableSource() {
        final ObligationConverter underTest = new ObligationConverter(genericInstanceFactory, skillConverter, statusConverter, userConverter, userService, categoryService, skillService, statusService);
        underTest.convert(null);
    }

    @Test
    public void testConvertNullableAttributeSource() {
        final ObligationConverter underTest = new ObligationConverter(genericInstanceFactory, skillConverter, statusConverter, userConverter, userService, categoryService, skillService, statusService);
        final ObligationModel source = mock(ObligationModel.class);
        final ObligationData target = mock(ObligationData.class);
        final Date expiredDate = mock(Date.class);
        final Long obligationId = 1L;
        final String obligationName = "Obligation name";
        final String obligationDescription = "Obligation description";

        when(source.getId()).thenReturn(obligationId);
        when(source.getName()).thenReturn(obligationName);
        when(source.getDescription()).thenReturn(obligationDescription);
        when(source.getExpiredDate()).thenReturn(expiredDate);
        when(source.getSkill()).thenReturn(null);
        when(source.getStatus()).thenReturn(null);
        when(source.getUserId()).thenReturn(null);
        when(genericInstanceFactory.getInstance(ObligationData.class)).thenReturn(target);

        final ObligationData obligation = underTest.convert(source);
        Assert.assertNotNull(obligation);
        verify(source).getId();
        verify(source).getName();
        verify(source).getDescription();
        verify(source).getExpiredDate();
        verify(source).getUserId();
        verify(source).getSkill();
        verify(source).getStatus();
        verify(source).getCategory();
        verifyNoMoreInteractions(source);
        verify(genericInstanceFactory).getInstance(ObligationData.class);
        verifyNoMoreInteractions(genericInstanceFactory);
        verify(target).setId(obligationId);
        verify(target).setName(obligationName);
        verify(target).setDescription(obligationDescription);
        verify(target).setExpiredDate(expiredDate);
        verify(target).setSkill(null);
        verify(target).setStatus(null);
        verify(target).setUser(null);
        verify(target).setCategoryId(null);
        verifyNoMoreInteractions(target);
        verifyZeroInteractions(skillConverter);
        verifyZeroInteractions(statusConverter);
        verifyZeroInteractions(userConverter);

        verifyZeroInteractions(userService);
    }

    @Test(expected = ModelNotFoundException.class)
    public void testConvertAttributeNotFound() {
        final ObligationConverter underTest = new ObligationConverter(genericInstanceFactory, skillConverter, statusConverter, userConverter, userService, categoryService, skillService, statusService);
        final ObligationModel source = mock(ObligationModel.class);
        final ObligationData target = mock(ObligationData.class);
        final UserModel userSource = mock(UserModel.class);

        when(genericInstanceFactory.getInstance(ObligationData.class)).thenReturn(target);
        when(source.getUserId()).thenReturn(1L);
        when(userService.getById(1L)).thenThrow(ModelNotFoundException.class);
        underTest.convert(source);
    }

    @Test
    public void testReverseConvertSuccess() {
        final ObligationConverter underTest = new ObligationConverter(genericInstanceFactory, skillConverter, statusConverter, userConverter, userService, categoryService, skillService, statusService);
        final ObligationModel target = mock(ObligationModel.class);
        final ObligationData source = mock(ObligationData.class);
        final StatusModel statusTarget = mock(StatusModel.class);
        final StatusData statusSource = mock(StatusData.class);
        final SkillModel skillTarget = mock(SkillModel.class);
        final SkillData skillSource = mock(SkillData.class);
        final CategoryModel categoryTarget = mock(CategoryModel.class);
        final UserModel userTarget = mock(UserModel.class);
        final UserData userSource = mock(UserData.class);
        final Date expiredDate = mock(Date.class);
        final Long obligationId = 1L;
        final String obligationName = "Obligation name";
        final String obligationDescription = "Obligation description";
        final Long userId = 1L;
        final Long categoryId = 1L;
        final Long statusId = 1L;
        final Long skillId = 1L;

        when(source.getId()).thenReturn(obligationId);
        when(source.getName()).thenReturn(obligationName);
        when(source.getDescription()).thenReturn(obligationDescription);
        when(source.getExpiredDate()).thenReturn(expiredDate);
        when(source.getSkill()).thenReturn(skillSource);
        when(skillSource.getId()).thenReturn(skillId);
        when(source.getStatus()).thenReturn(statusSource);
        when(statusSource.getId()).thenReturn(statusId);
        when(source.getUser()).thenReturn(userSource);
        when(userSource.getId()).thenReturn(userId);
        when(source.getCategoryId()).thenReturn(categoryId);
        when(genericInstanceFactory.getInstance(ObligationModel.class)).thenReturn(target);
        when(skillService.getById(skillId)).thenReturn(skillTarget);
        when(statusService.getById(statusId)).thenReturn(statusTarget);
        when(categoryService.getById(categoryId)).thenReturn(categoryTarget);
        when(userService.getById(userId)).thenReturn(userTarget);
        when(userTarget.getId()).thenReturn(userId);

        final ObligationModel obligation = underTest.reverseConvert(source);
        Assert.assertNotNull(obligation);
        verify(source).getId();
        verify(source).getName();
        verify(source).getDescription();
        verify(source).getExpiredDate();
        verify(source).getUser();
        verify(source).getSkill();
        verify(source).getStatus();
        verify(source).getCategoryId();
        verifyNoMoreInteractions(source);
        verify(userSource).getId();
        verifyNoMoreInteractions(userSource);
        verify(genericInstanceFactory).getInstance(ObligationModel.class);
        verifyNoMoreInteractions(genericInstanceFactory);
        verify(target).setId(obligationId);
        verify(target).setName(obligationName);
        verify(target).setDescription(obligationDescription);
        verify(target).setExpiredDate(expiredDate);
        verify(target).setSkill(skillTarget);
        verify(target).setStatus(statusTarget);
        verify(target).setUserId(userId);
        verify(target).setCategory(categoryTarget);
        verifyNoMoreInteractions(target);
        verify(skillService).getById(skillId);
        verifyNoMoreInteractions(skillService);
        verify(statusService).getById(statusId);
        verifyNoMoreInteractions(statusService);
        verify(categoryService).getById(categoryId);
        verifyNoMoreInteractions(categoryService);
        verify(userService).getById(userId);
        verifyNoMoreInteractions(userService);
        verifyZeroInteractions(userConverter);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReverseConvertNullableSource() {
        final ObligationConverter underTest = new ObligationConverter(genericInstanceFactory, skillConverter, statusConverter, userConverter, userService, categoryService, skillService, statusService);
        underTest.reverseConvert(null);
    }

    @Test
    public void testReverseConvertNullableAttributeSource() {
        final ObligationConverter underTest = new ObligationConverter(genericInstanceFactory, skillConverter, statusConverter, userConverter, userService, categoryService, skillService, statusService);
        final ObligationModel target = mock(ObligationModel.class);
        final ObligationData source = mock(ObligationData.class);
        final Date expiredDate = mock(Date.class);
        final Long obligationId = 1L;
        final String obligationName = "Obligation name";
        final String obligationDescription = "Obligation description";

        when(source.getId()).thenReturn(obligationId);
        when(source.getName()).thenReturn(obligationName);
        when(source.getDescription()).thenReturn(obligationDescription);
        when(source.getExpiredDate()).thenReturn(expiredDate);
        when(source.getSkill()).thenReturn(null);
        when(source.getStatus()).thenReturn(null);
        when(source.getUser()).thenReturn(null);
        when(genericInstanceFactory.getInstance(ObligationModel.class)).thenReturn(target);

        final ObligationModel obligation = underTest.reverseConvert(source);
        Assert.assertNotNull(obligation);
        verify(source).getId();
        verify(source).getName();
        verify(source).getDescription();
        verify(source).getExpiredDate();
        verify(source).getUser();
        verify(source).getSkill();
        verify(source).getStatus();
        verify(source).getCategoryId();
        verifyNoMoreInteractions(source);
        verify(genericInstanceFactory).getInstance(ObligationModel.class);
        verifyNoMoreInteractions(genericInstanceFactory);
        verify(target).setId(obligationId);
        verify(target).setName(obligationName);
        verify(target).setDescription(obligationDescription);
        verify(target).setExpiredDate(expiredDate);
        verify(target).setSkill(null);
        verify(target).setStatus(null);
        verify(target).setUserId(null);
        verify(target).setCategory(null);
        verifyNoMoreInteractions(target);
        verifyZeroInteractions(skillConverter);
        verifyZeroInteractions(statusConverter);
        verifyZeroInteractions(userService);
        verifyZeroInteractions(userConverter);
    }

    @Test(expected = ModelNotFoundException.class)
    public void testReverseConvertAttributeNotFound() {
        final ObligationConverter underTest = new ObligationConverter(genericInstanceFactory, skillConverter, statusConverter, userConverter, userService, categoryService, skillService, statusService);
        final ObligationModel target = mock(ObligationModel.class);
        final ObligationData source = mock(ObligationData.class);
        final UserData userSource = mock(UserData.class);

        when(genericInstanceFactory.getInstance(ObligationModel.class)).thenReturn(target);
        when(source.getUser()).thenReturn(userSource);
        when(userSource.getId()).thenReturn(Long.MAX_VALUE);
        when(userService.getById(Long.MAX_VALUE)).thenThrow(ModelNotFoundException.class);
        underTest.reverseConvert(source);
    }
}