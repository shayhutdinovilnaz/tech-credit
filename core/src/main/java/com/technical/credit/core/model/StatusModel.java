package com.technical.credit.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Optional;

/**
 * @author ilnaz-92@yandex.ru
 * Created on 29.03.2020
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class StatusModel extends ItemModel {
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "localized_name_id")
    private LocalizedStringModel name;

    public String getName(final LanguageModel language) {
        return Optional.ofNullable(getLocalizedValue(language, name)).orElse("");
    }
}
