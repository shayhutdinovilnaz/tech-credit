package com.technical.credit.obligationservice.repository;

import com.technical.credit.obligationservice.model.StatusModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusModel, Long> {
}
