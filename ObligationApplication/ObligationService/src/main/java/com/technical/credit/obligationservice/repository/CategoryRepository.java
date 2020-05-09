package com.technical.credit.obligationservice.repository;

import com.technical.credit.obligationservice.model.CategoryModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {

    List<CategoryModel> findCategoryModelsByNameContainingIgnoreCaseAndUserId(final String name, final Pageable pageable, final long userId);
    Optional<CategoryModel> findCategoryModelById(Long categoryId);
    Set<CategoryModel> findCategoryModelsByParentCategoryIdOrderByCreatedTime(Long parentCategoryId);
}
