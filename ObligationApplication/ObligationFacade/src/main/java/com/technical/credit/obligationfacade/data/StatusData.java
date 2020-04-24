package com.technical.credit.obligationfacade.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class StatusData implements Serializable {
    private Long id;
    private String name;
}
