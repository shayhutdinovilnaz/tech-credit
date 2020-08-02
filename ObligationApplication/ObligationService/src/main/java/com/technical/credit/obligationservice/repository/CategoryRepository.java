package com.technical.credit.obligationservice.repository;

import com.technical.credit.obligationservice.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {

    List<CategoryModel> findCategoryModelsByOwnerId(Long ownerId);

    List<CategoryModel> findCategoryModelsByParentCategoryIsNullAndOwnerId(Long ownerId);
}
