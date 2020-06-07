package com.technical.credit.obligationservice.repository;


import com.technical.credit.obligationservice.model.CategoryModel;
import com.technical.credit.obligationservice.model.ObligationModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    CategoryRepository underTest;

    CategoryModel parentCategoryModel = new CategoryModel();

    @Before
    public void createTestDates() {
        Set<Long> childrenCategoriesIdsOne = new HashSet<>();
        Set<Long> childrenCategoriesIdsTwo = new HashSet<>();
        Set<Long> obligationIds = new HashSet<>();

        ObligationModel obligationModel = new ObligationModel();
        obligationModel.setName("Obligation NamE1");
        obligationModel.setDescription("Obligation description");
        obligationModel.setUserId(1L);
        obligationModel.setModifiedTime(new Date());
        obligationModel.setCreatedTime(new Date());
        obligationModel.setExpiredDate(new Date());
        entityManager.persist(obligationModel);
        obligationIds.add(obligationModel.getId());

        obligationModel = new ObligationModel();
        obligationModel.setName("Obligation name");
        obligationModel.setDescription("Obligation description");
        obligationModel.setUserId(2L);
        obligationModel.setModifiedTime(new Date());
        obligationModel.setCreatedTime(new Date());
        obligationModel.setExpiredDate(new Date());
        entityManager.persist(obligationModel);
        obligationIds.add(obligationModel.getId());

        CategoryModel childrenCategoryModel = new CategoryModel();
        childrenCategoryModel.setName("childrenCategoryModel name");
        childrenCategoryModel.setDescription("childrenCategoryModel description");
        childrenCategoryModel.setUserId(1L);
        childrenCategoryModel.setModifiedTime(new Date());
        childrenCategoryModel.setCreatedTime(new Date());
        entityManager.persist(childrenCategoryModel);
        childrenCategoriesIdsOne.add(childrenCategoryModel.getId());

        CategoryModel currentCategoryModel = new CategoryModel();
        currentCategoryModel.setName("currentCategoryModel name");
        currentCategoryModel.setDescription("currentCategoryModel description");
        currentCategoryModel.setUserId(1L);
        currentCategoryModel.setModifiedTime(new Date());
        currentCategoryModel.setCreatedTime(new Date());
        entityManager.persist(currentCategoryModel);
        childrenCategoriesIdsTwo.add(currentCategoryModel.getId());

        parentCategoryModel.setName("parentCategoryModel name");
        parentCategoryModel.setDescription("parentCategoryModel description");
        parentCategoryModel.setUserId(1L);
        parentCategoryModel.setModifiedTime(new Date());
        parentCategoryModel.setCreatedTime(new Date());
        entityManager.persist(parentCategoryModel);
        currentCategoryModel.setParentId(parentCategoryModel.getId());
        entityManager.persist(currentCategoryModel);
    }

    @Test
    public void testFindByNameForUser() {
        final List<CategoryModel> resultList = underTest.findCategoryModelsByNameContainingIgnoreCaseAndUserId("name", PageRequest.of(0, 100), 1L);
        final List<CategoryModel> resultList2 = underTest.findCategoryModelsByNameContainingIgnoreCaseAndUserId("", PageRequest.of(0, 100), 1L);
        Assert.assertNotNull(resultList);
        Assert.assertNotNull(resultList2);
        Assert.assertEquals(3, resultList.size());
        Assert.assertEquals(3, resultList2.size());
    }

    @Test
    public void testFindCategoryModelsByParentCategoryOrderByCreatedTime() {
        final Set<CategoryModel> resultList = underTest.findCategoryModelsByParentCategoryIdOrderByCreatedTime(parentCategoryModel.getId());
        Assert.assertNotNull(resultList);
    }
}