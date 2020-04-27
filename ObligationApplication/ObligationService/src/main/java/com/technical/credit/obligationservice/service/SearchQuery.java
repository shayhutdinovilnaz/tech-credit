package com.technical.credit.obligationservice.service;

import lombok.Data;

@Data
public class SearchQuery {
    private String freeText;
    private String sortField;
    private boolean desc;
    private int page;
    private int limit;
}
