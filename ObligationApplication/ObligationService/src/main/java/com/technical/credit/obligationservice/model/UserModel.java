package com.technical.credit.obligationservice.model;

import lombok.Data;


@Data
public class UserModel {
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
