package com.technical.credit.obligationfacade.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserData implements Serializable {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String displayName;
}
