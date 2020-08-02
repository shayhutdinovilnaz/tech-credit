package account.repository;

import account.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUsernameIgnoreCase(String name);

    Optional<UserModel> findByEmailIgnoreCase(String email);
}
