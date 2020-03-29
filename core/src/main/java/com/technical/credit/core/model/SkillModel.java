package com.technical.credit.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class SkillModel extends ItemModel {
    @ManyToOne(optional = false)
    @JoinColumn(name = "localized_name_id")
    private LocalizedStringModel name;
}
