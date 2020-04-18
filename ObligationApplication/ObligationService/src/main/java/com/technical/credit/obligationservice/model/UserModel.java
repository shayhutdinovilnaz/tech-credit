package com.technical.credit.obligationservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class UserModel extends ItemModel {
    @Column(nullable = false)
    private String firstName;
    @Column
    private String middleName;
    @Column(nullable = false)
    private String lastName;
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<ObligationModel> obligations;
}
