package com.technical.credit.webapp.configuaration;

import com.technical.credit.obligationservice.model.CategoryModel;
import com.technical.credit.obligationservice.model.LanguageModel;
import com.technical.credit.obligationservice.model.ObligationModel;
import com.technical.credit.obligationservice.service.CategoryService;
import com.technical.credit.obligationservice.service.LanguageService;
import com.technical.credit.obligationservice.service.ObligationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ObligationWebApplicationInitializer {
    private static final Logger LOG = LoggerFactory.getLogger(ObligationWebApplicationInitializer.class);
    private final LanguageService languageService;
    private final ObligationService obligationService;
    private final CategoryService categoryService;
    private Set<ObligationModel> obligationModelSet = new HashSet<>();
    private Set<CategoryModel> childrenCategories = new HashSet<>();

    @PostConstruct
    public void loadInitializedDates() {
        LOG.info("###### The initialization system dates is started. ######");
        try {
            loadLanguages();
            loadObligations();
            loadCategories();
        } catch (Exception e) {
            LOG.info("###### The error during initialization. ######");
        }

        LOG.info("###### The initialization system dates is finished. ######");
    }

    private void loadLanguages() {
        LOG.info("###### The initialization of system language is started. ######");
        final LanguageModel ruLang = new LanguageModel();
        ruLang.setIsoCode("ru");//todo разве iso код не символьный?
        ruLang.setName("Русский");
        ruLang.setActive(true);
        languageService.save(ruLang);
    }


    private void loadObligations() {
        LOG.info("###### The initialization of test obligation is started. ######");
        final ObligationModel obligation = new ObligationModel();
        obligation.setExpiredDate(new Date());
        obligation.setUserId(1L);
        obligation.setName("Test obligation");
        obligation.setDescription("Description of test obligation.");
        obligationModelSet.add(obligation);
        obligationService.save(obligation);
    }

    private void loadCategories() {
        LOG.info("###### The initialization of test categories is started. ######");
        final CategoryModel childrenCategory = new CategoryModel();
        childrenCategory.setName("childrenCategory");
        childrenCategory.setUserId(1L);
        childrenCategory.setObligations(obligationModelSet);
        childrenCategories.add(childrenCategory);

        final CategoryModel parentCategory = new CategoryModel();
        parentCategory.setName("parentCategory");
        parentCategory.setUserId(1L);
        parentCategory.setChildrenCategories(childrenCategories);

        childrenCategory.setParentCategory(parentCategory);

        categoryService.save(parentCategory);
        categoryService.save(childrenCategory);
    }
}
