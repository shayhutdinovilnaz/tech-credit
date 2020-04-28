package com.technical.credit.obligationservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;


@Data
@EqualsAndHashCode(callSuper = true)
public class UserModel extends ItemModel {
    private String firstName;
    private String middleName;
    private String lastName;
    private Set<ObligationModel> obligations = new HashSet<>();
}
