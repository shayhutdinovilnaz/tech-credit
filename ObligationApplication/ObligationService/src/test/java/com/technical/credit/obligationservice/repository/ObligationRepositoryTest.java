package com.technical.credit.obligationservice.repository;


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

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ObligationRepositoryTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    ObligationRepository underTest;

    @Before
    public void createTestDates() {
        ObligationModel obligationModel = new ObligationModel();
        obligationModel.setName("ObligationTest");
        obligationModel.setDescription("Obligation description");
        obligationModel.setUserId(1L);
        obligationModel.setModifiedTime(new Date());
        obligationModel.setCreatedTime(new Date());
        obligationModel.setExpiredDate(new Date());
        entityManager.persist(obligationModel);

        obligationModel = new ObligationModel();
        obligationModel.setName("Obligation NamE1");
        obligationModel.setDescription("Obligation description");
        obligationModel.setUserId(1L);
        obligationModel.setModifiedTime(new Date());
        obligationModel.setCreatedTime(new Date());
        obligationModel.setExpiredDate(new Date());
        entityManager.persist(obligationModel);

        obligationModel = new ObligationModel();
        obligationModel.setName("Obligation name");
        obligationModel.setDescription("Obligation description");
        obligationModel.setUserId(2L);
        obligationModel.setModifiedTime(new Date());
        obligationModel.setCreatedTime(new Date());
        obligationModel.setExpiredDate(new Date());
        entityManager.persist(obligationModel);
    }

    @Test
    public void testFindByNameForUser() {
        final List<ObligationModel> resultList = underTest.findObligationModelByNameContainingIgnoreCaseAndUserId("name", PageRequest.of(0, 100), 1L);
        Assert.assertNotNull(resultList);
        Assert.assertEquals(1, resultList.size());
    }

    @Test
    public void testFindByBlankNameForUser() {
        final List<ObligationModel> resultList = underTest.findObligationModelByNameContainingIgnoreCaseAndUserId("", PageRequest.of(0, 100), 1L);
        Assert.assertNotNull(resultList);
        Assert.assertEquals(2, resultList.size());
    }
}