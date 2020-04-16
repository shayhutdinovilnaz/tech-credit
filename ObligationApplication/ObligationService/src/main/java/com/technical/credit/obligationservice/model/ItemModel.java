package com.technical.credit.obligationservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ilnaz-92@yandex.ru
 * Created on 29.03.2020
 */
@MappedSuperclass
@Getter
@Setter
public abstract class ItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdTime;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date modifiedTime;
}
