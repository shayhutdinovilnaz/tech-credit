package com.technical.credit.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author ilnaz-92@yandex.ru
 * Created on 29.03.2020
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class LocalizedStringModel extends ItemModel {
    @Column(name = "default_value")
    private String defaultValue;
    @OneToMany(mappedBy = "localizedString", fetch = FetchType.EAGER)
    private List<LocalizedStringValueModel> localizedValues;
}
