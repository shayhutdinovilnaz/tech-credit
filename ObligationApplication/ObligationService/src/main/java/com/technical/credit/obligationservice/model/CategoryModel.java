package com.technical.credit.obligationservice.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryModel extends ItemModel {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long ownerId;
    @ManyToOne
    @JoinColumn
    private CategoryModel parentCategory;
    @OneToMany(mappedBy = "parentCategory")
    private Set<CategoryModel> childrenCategories;
    @OneToMany(mappedBy = "category")
    private Set<ObligationModel> obligations;
}
