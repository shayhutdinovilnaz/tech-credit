package com.technical.credit.obligationservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author ilnaz-92@yandex.ru
 * Created on 29.03.2020
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class LanguageModel extends ItemModel {
    @Column(nullable = false)
    private String isoCode;
}


