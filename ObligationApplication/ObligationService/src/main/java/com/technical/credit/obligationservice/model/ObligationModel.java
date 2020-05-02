package com.technical.credit.obligationservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ilnaz-92@yandex.ru
 * Created on 29.03.2020
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class ObligationModel extends ItemModel {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private SkillModel skill;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusModel status;
    @Column(nullable = false)
    private Long userId;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date expiredDate;
    @ManyToOne
    @JoinColumn
    private CategoryModel category;

}
