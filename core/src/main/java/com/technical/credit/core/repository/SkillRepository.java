package com.technical.credit.core.repository;

import com.technical.credit.core.model.SkillModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ilnaz-92@yandex.ru
 * Created on 30.03.2020
 */
public interface SkillRepository extends JpaRepository<SkillModel, Long> {
}