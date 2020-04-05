package com.technical.credit.core.service.impl;

import com.technical.credit.core.exception.ModelNotFoundException;
import com.technical.credit.core.model.SkillModel;
import com.technical.credit.core.repository.SkillRepository;
import com.technical.credit.core.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;


/**
 * @author ilnaz-92@yandex.ru
 * Created on 30.03.2020
 */
@Service
@RequiredArgsConstructor
public class DefaultSkillService implements SkillService {
    private final SkillRepository skillRepository;

    @Override
    @Transactional
    public void save(final SkillModel skill) {
        Assert.notNull(skill, "Saving object can't be nullable.");

        updateSaveTime(skill);
        skillRepository.save(skill);
    }

    @Override
    public SkillModel getById(final long id) {
        return skillRepository.findById(id).orElseThrow(() -> new ModelNotFoundException("Model isn't found. Required model id = " + id));
    }

    @Override
    @Transactional
    public void delete(final SkillModel skill) {
        skillRepository.delete(skill);
    }
}
