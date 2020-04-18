package com.technical.credit.obligationfacade.converter.impl;


import com.technical.credit.obligationfacade.data.ObligationData;
import com.technical.credit.obligationfacade.data.SkillData;
import com.technical.credit.obligationfacade.data.StatusData;
import com.technical.credit.obligationfacade.data.UserData;
import com.technical.credit.obligationservice.exception.ModelNotFoundException;
import com.technical.credit.obligationservice.factory.GenericInstanceFactory;
import com.technical.credit.obligationservice.model.ObligationModel;
import com.technical.credit.obligationservice.model.SkillModel;
import com.technical.credit.obligationservice.model.StatusModel;
import com.technical.credit.obligationservice.model.UserModel;
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

    @Test
    public void testConvertSuccess() {
        final ObligationConverter underTest = new ObligationConverter(genericInstanceFactory, skillConverter, statusConverter, userConverter, userService);
        final ObligationModel source = mock(ObligationModel.class);
        final ObligationData target = mock(ObligationData.class);
        final StatusModel statusSource = mock(StatusModel.class);
        final StatusData statusTarget = mock(StatusData.class);
        final SkillModel skillSource = mock(SkillModel.class);
        final SkillData skillTarget = mock(SkillData.class);
        final UserModel userSource = mock(UserModel.class);
        final UserData userTarget = mock(UserData.class);
        final Date expiredDate = mock(Date.class);
        final Long obligationId = 1L;
        final String obligationName = "Obligation name";
        final String obligationDescription = "Obligation description";

        when(source.getId()).thenReturn(obligationId);
        when(source.getName()).thenReturn(obligationName);
        when(source.getDescription()).thenReturn(obligationDescription);
        when(source.getExpiredDate()).thenReturn(expiredDate);
        when(source.getSkill()).thenReturn(skillSource);
        when(source.getStatus()).thenReturn(statusSource);
        when(source.getUser()).thenReturn(userSource);
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
        verify(source).getUser();
        verify(source).getSkill();
        verify(source).getStatus();
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
        verifyNoMoreInteractions(target);
        verify(skillConverter).convert(skillSource);
        verifyNoMoreInteractions(skillConverter);
        verify(statusConverter).convert(statusSource);
        verifyNoMoreInteractions(statusConverter);
        verify(userConverter).convert(userSource);
        verifyNoMoreInteractions(userConverter);

        verifyZeroInteractions(userService);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertNullableSource() {
        final ObligationConverter underTest = new ObligationConverter(genericInstanceFactory, skillConverter, statusConverter, userConverter, userService);
        underTest.convert(null);
    }

    @Test
    public void testConvertNullableAttributeSource() {
        final ObligationConverter underTest = new ObligationConverter(genericInstanceFactory, skillConverter, statusConverter, userConverter, userService);
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
        when(source.getUser()).thenReturn(null);
        when(genericInstanceFactory.getInstance(ObligationData.class)).thenReturn(target);

        final ObligationData obligation = underTest.convert(source);
        Assert.assertNotNull(obligation);
        verify(source).getId();
        verify(source).getName();
        verify(source).getDescription();
        verify(source).getExpiredDate();
        verify(source).getUser();
        verify(source).getSkill();
        verify(source).getStatus();
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
        verifyNoMoreInteractions(target);
        verifyZeroInteractions(skillConverter);
        verifyZeroInteractions(statusConverter);
        verifyZeroInteractions(userConverter);

        verifyZeroInteractions(userService);
    }

    @Test(expected = ModelNotFoundException.class)
    public void testConvertAttributeNotFound() {
        final ObligationConverter underTest = new ObligationConverter(genericInstanceFactory, skillConverter, statusConverter, userConverter, userService);
        final ObligationModel source = mock(ObligationModel.class);
        final ObligationData target = mock(ObligationData.class);
        final UserModel userSource = mock(UserModel.class);

        when(genericInstanceFactory.getInstance(ObligationData.class)).thenReturn(target);
        when(source.getUser()).thenReturn(userSource);
        when(userConverter.convert(userSource)).thenThrow(ModelNotFoundException.class);
        underTest.convert(source);
    }

    @Test
    public void testReverseConvertSuccess() {
        final ObligationConverter underTest = new ObligationConverter(genericInstanceFactory, skillConverter, statusConverter, userConverter, userService);
        final ObligationModel target = mock(ObligationModel.class);
        final ObligationData source = mock(ObligationData.class);
        final StatusModel statusTarget = mock(StatusModel.class);
        final StatusData statusSource = mock(StatusData.class);
        final SkillModel skillTarget = mock(SkillModel.class);
        final SkillData skillSource = mock(SkillData.class);
        final UserModel userTarget = mock(UserModel.class);
        final UserData userSource = mock(UserData.class);
        final Date expiredDate = mock(Date.class);
        final Long obligationId = 1L;
        final String obligationName = "Obligation name";
        final String obligationDescription = "Obligation description";
        final Long userId = 1L;

        when(source.getId()).thenReturn(obligationId);
        when(source.getName()).thenReturn(obligationName);
        when(source.getDescription()).thenReturn(obligationDescription);
        when(source.getExpiredDate()).thenReturn(expiredDate);
        when(source.getSkill()).thenReturn(skillSource);
        when(source.getStatus()).thenReturn(statusSource);
        when(source.getUser()).thenReturn(userSource);
        when(userSource.getId()).thenReturn(userId);
        when(genericInstanceFactory.getInstance(ObligationModel.class)).thenReturn(target);
        when(skillConverter.reverseConvert(skillSource)).thenReturn(skillTarget);
        when(statusConverter.reverseConvert(statusSource)).thenReturn(statusTarget);
        when(userService.getById(userId)).thenReturn(userTarget);

        final ObligationModel obligation = underTest.reverseConvert(source);
        Assert.assertNotNull(obligation);
        verify(source).getId();
        verify(source).getName();
        verify(source).getDescription();
        verify(source).getExpiredDate();
        verify(source).getUser();
        verify(source).getSkill();
        verify(source).getStatus();
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
        verify(target).setUser(userTarget);
        verifyNoMoreInteractions(target);
        verify(skillConverter).reverseConvert(skillSource);
        verifyNoMoreInteractions(skillConverter);
        verify(statusConverter).reverseConvert(statusSource);
        verifyNoMoreInteractions(statusConverter);
        verify(userService).getById(userId);
        verifyNoMoreInteractions(userService);
        verifyZeroInteractions(userConverter);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReverseConvertNullableSource() {
        final ObligationConverter underTest = new ObligationConverter(genericInstanceFactory, skillConverter, statusConverter, userConverter, userService);
        underTest.reverseConvert(null);
    }

    @Test
    public void testReverseConvertNullableAttributeSource() {
        final ObligationConverter underTest = new ObligationConverter(genericInstanceFactory, skillConverter, statusConverter, userConverter, userService);
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
        verifyNoMoreInteractions(source);
        verify(genericInstanceFactory).getInstance(ObligationModel.class);
        verifyNoMoreInteractions(genericInstanceFactory);
        verify(target).setId(obligationId);
        verify(target).setName(obligationName);
        verify(target).setDescription(obligationDescription);
        verify(target).setExpiredDate(expiredDate);
        verify(target).setSkill(null);
        verify(target).setStatus(null);
        verify(target).setUser(null);
        verifyNoMoreInteractions(target);
        verifyZeroInteractions(skillConverter);
        verifyZeroInteractions(statusConverter);
        verifyZeroInteractions(userService);
        verifyZeroInteractions(userConverter);
    }

    @Test(expected = ModelNotFoundException.class)
    public void testReverseConvertAttributeNotFound() {
        final ObligationConverter underTest = new ObligationConverter(genericInstanceFactory, skillConverter, statusConverter, userConverter, userService);
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