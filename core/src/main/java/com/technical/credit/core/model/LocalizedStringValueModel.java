package com.technical.credit.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author ilnaz-92@yandex.ru
 * Created on 29.03.2020
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"language_id", "localized_value_id"})})
@Data
@EqualsAndHashCode(callSuper = true)
public class LocalizedStringValueModel extends ItemModel {
    @ManyToOne(optional = false)
    @JoinColumn(name = "language_id")
    private LanguageModel language;
    @Column(nullable = false)
    private String value;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "localized_value_id")
    private LocalizedStringModel localizedString;
}
