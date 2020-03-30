package com.technical.credit.core.service.impl;

import com.technical.credit.core.model.SkillModel;
import com.technical.credit.core.repository.SkillRepository;
import com.technical.credit.core.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;


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
    public void save(SkillModel skill) {
        if (skill.getCreatedTime() == null) {
            skill.setCreatedTime(new Date());
        }

        skill.setModifiedTime(new Date());
        skillRepository.save(skill);
    }

    @Override
    public SkillModel getById(Long id) {
        return skillRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(SkillModel skill) {
        skillRepository.delete(skill);
    }
}
