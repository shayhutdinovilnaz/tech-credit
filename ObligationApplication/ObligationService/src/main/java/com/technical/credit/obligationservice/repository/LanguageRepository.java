package com.technical.credit.obligationservice.repository;

import com.technical.credit.obligationservice.model.LanguageModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface LanguageRepository extends JpaRepository<LanguageModel, Long> {
    Optional<LanguageModel> findByIsoCodeAndActive(String isoCode, boolean active);
}
