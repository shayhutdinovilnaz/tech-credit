package com.technical.credit.obligationfacade.data;

import lombok.Data;

import java.util.Set;

@Data
public class CategoryData {
    private Long id;
    private String name;
    private UserData owner;
    private Long parentCategoryId;
    private Set<Long> childrenCategoriesId;
    private Set<ObligationData> obligations;

}
