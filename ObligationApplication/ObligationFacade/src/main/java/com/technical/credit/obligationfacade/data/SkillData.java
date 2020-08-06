package com.technical.credit.obligationfacade.data;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class SkillData implements Serializable {
    private Long id;
    @NotBlank
    private String name;
}
