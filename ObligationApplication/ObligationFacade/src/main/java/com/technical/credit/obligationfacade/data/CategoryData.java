package com.technical.credit.obligationfacade.data;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class CategoryData implements Serializable {
    private Long id;
    private Long userId;
    private Long parentCategoryId;
    private String name;
    private String description;
    private Set<Long> childrenCategoriesIds;
    private Set<Long> obligationIds;
    private UserData user;
}
