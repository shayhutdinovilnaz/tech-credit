package com.technical.credit.obligationservice.repository;

import com.technical.credit.obligationservice.model.LanguageModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LanguageRepositoryTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    LanguageRepository underTest;

    @Before
    public void createTestDates() {
        final LanguageModel ruLanguage = new LanguageModel();
        ruLanguage.setIsoCode("ru");
        ruLanguage.setName("Русский");
        ruLanguage.setActive(true);
        ruLanguage.setModifiedTime(new Date());
        ruLanguage.setCreatedTime(new Date());
        entityManager.persist(ruLanguage);

        final LanguageModel enLanguage = new LanguageModel();
        enLanguage.setIsoCode("en");
        enLanguage.setName("English");
        enLanguage.setActive(false);
        enLanguage.setModifiedTime(new Date());
        enLanguage.setCreatedTime(new Date());
        entityManager.persist(enLanguage);
    }

    @Test
    public void testFindByActiveIsTrueAndIsoCode() {
        final Optional<LanguageModel> result = underTest.findByActiveAndIsoCode("ru", true);
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals("ru", result.get().getIsoCode());
    }

    @Test
    public void testFindByActiveIsTrueAndIsoCode_InActive() {
        final Optional<LanguageModel> result = underTest.findByActiveAndIsoCode("en", false);
        Assert.assertFalse(result.isPresent());
    }
}