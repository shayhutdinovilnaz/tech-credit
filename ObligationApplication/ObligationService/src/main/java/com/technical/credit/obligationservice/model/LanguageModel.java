package com.technical.credit.obligationservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class LanguageModel extends ItemModel {
    @Column(nullable = false, unique = true)
    private String isoCode;
    @Column(nullable = false, unique = true)
    private String name;
    @Column
    private boolean active;
}


