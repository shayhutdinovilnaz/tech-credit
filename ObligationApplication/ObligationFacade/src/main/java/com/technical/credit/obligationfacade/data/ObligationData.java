package com.technical.credit.obligationfacade.data;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ObligationData implements Serializable {
    private Long id;
    private String name;
    private String description;
    private SkillData skill;
    private StatusData status;
    private Date expiredDate;
    private UserData user;
    private Long categoryId;
}
