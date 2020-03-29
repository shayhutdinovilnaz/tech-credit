package com.technical.credit.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author ilnaz-92@yandex.ru
 * Created on 29.03.2020
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class ObligationModel extends ItemModel {
    @ManyToOne(optional = false)
    @JoinColumn(name = "skill_id")
    private SkillModel skillModel;
    @ManyToOne(optional = false)
    @JoinColumn(name = "status_id")
    private StatusModel statusModel;
    @Column(nullable = false, name = "user_id")
    private String userId;
}
