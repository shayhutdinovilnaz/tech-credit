package com.technical.credit.core.service.impl;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.technical.credit.core.exception.ModelNotFoundException;
import com.technical.credit.core.model.SkillModel;
import com.technical.credit.core.repository.SkillRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class AbstractModelServiceTest {

    @InjectMocks
    private DefaultSkillService underTest;

    @Mock
    private SkillRepository skillRepository;

    @Test
    public void testSaveNewItem() {
        final SkillModel skillModel = mock(SkillModel.class);

        underTest.save(skillModel);
        verify(skillModel).getCreatedTime();
        verify(skillModel).setCreatedTime(anyObject());
        verify(skillModel).setModifiedTime(anyObject());
        verifyNoMoreInteractions(skillModel);
        verify(skillRepository).save(skillModel);
        verifyNoMoreInteractions(skillRepository);
    }

    @Test
    public void testSaveExistItem() {
        final SkillModel skillModel = mock(SkillModel.class);

        when(skillModel.getCreatedTime()).thenReturn(new Date());

        underTest.save(skillModel);
        verify(skillModel).getCreatedTime();
        verify(skillModel).setModifiedTime(anyObject());
        verifyNoMoreInteractions(skillModel);
        verify(skillRepository).save(skillModel);
        verifyNoMoreInteractions(skillRepository);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveIllegalItem() {
        underTest.save(null);
    }

    @Test(expected = ModelNotFoundException.class)
    public void testGetByIdNotExistItem() {
        final Long itemId = 1L;

        when(skillRepository.findById(itemId)).thenReturn(Optional.empty());

        underTest.getById(itemId);
    }

    @Test
    public void testGetByIdExistItem() {
        final Long itemId = 1L;
        final SkillModel skillModel = mock(SkillModel.class);

        when(skillRepository.findById(itemId)).thenReturn(Optional.of(skillModel));

        Assert.assertEquals(skillModel, underTest.getById(itemId));
        verify(skillRepository).findById(itemId);
        verifyNoMoreInteractions(skillRepository);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNullableItem() {

        doThrow(new IllegalArgumentException()).when(skillRepository).delete(null);

        underTest.delete(null);
    }

    @Test
    public void testDelete() {
        final SkillModel skillModel = mock(SkillModel.class);

        underTest.delete(skillModel);
        verify(skillRepository).delete(skillModel);
        verifyNoMoreInteractions(skillRepository);
    }
}