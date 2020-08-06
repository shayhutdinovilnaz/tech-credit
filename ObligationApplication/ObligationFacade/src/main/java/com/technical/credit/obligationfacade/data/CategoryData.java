package com.technical.credit.obligationfacade.data;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class CategoryData {
    private Long id;
    @NotBlank
    private String name;
    private UserData owner;
    private Long parentCategoryId;
    private Set<Long> childrenCategoriesId;
    private Set<ObligationData> obligations;

}
