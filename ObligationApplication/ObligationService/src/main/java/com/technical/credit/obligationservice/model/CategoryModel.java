package com.technical.credit.obligationservice.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//todo написать сервисные методы для отчистки коллекций от удаленных id

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryModel extends ItemModel {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long userId;

    private Long parentId;
    @ElementCollection
    @CollectionTable(name = "listOfchildrenCategoriesIds")
    private Set<Long> childrenCategoriesIds = new HashSet<>();
    @ElementCollection
    @CollectionTable(name = "listOfobligationIds")
    private Set<Long> obligationIds = new HashSet<>();

    @Column(nullable = false)
    private String description;
    @ManyToOne
    @JoinColumn
    private CategoryModel parentCategory;
    @OneToMany(mappedBy = "parentCategory")
    private Set<CategoryModel> childrenCategories;
    @OneToMany(mappedBy = "category")
    private Set<ObligationModel> obligations;
}
