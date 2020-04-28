package com.technical.credit.obligationservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class StatusModel extends ItemModel {
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "localized_name_id")
    private LocalizedStringModel name;
}
