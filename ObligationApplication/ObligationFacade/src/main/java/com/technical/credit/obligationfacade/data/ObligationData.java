package com.technical.credit.obligationfacade.data;

import lombok.Data;

import java.util.Date;

@Data
public class ObligationData {
    private Long id;
    private String name;
    private String description;
    private SkillData skill;
    private StatusData status;
    private Date expiredDate;
    private UserData userData;
}
