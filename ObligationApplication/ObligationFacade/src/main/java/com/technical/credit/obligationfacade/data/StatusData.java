package com.technical.credit.obligationfacade.data;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class StatusData implements Serializable {
    private Long id;
    @NotBlank
    private String name;
}
