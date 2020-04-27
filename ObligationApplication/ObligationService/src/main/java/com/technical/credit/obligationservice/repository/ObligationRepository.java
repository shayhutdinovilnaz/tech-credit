package com.technical.credit.obligationservice.repository;

import com.technical.credit.obligationservice.model.ObligationModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObligationRepository extends JpaRepository<ObligationModel, Long> {

    List<ObligationModel> findObligationModelByNameContainingIgnoreCaseAndUserId(final String name, final Pageable pageable, final long userId);
}
