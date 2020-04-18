package com.technical.credit.obligationservice.repository;

import com.technical.credit.obligationservice.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
