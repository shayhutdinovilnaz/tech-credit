package com.technical.credit.obligationservice.repository;

import com.technical.credit.obligationservice.model.ObligationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObligationRepository extends JpaRepository<ObligationModel, Long> {
}
