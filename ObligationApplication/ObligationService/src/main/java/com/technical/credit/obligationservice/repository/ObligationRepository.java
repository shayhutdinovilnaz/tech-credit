package com.technical.credit.obligationservice.repository;

import com.technical.credit.obligationservice.model.ObligationModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ilnaz-92@yandex.ru
 * Created on 30.03.2020
 */
public interface ObligationRepository extends JpaRepository<ObligationModel, Long> {
}
