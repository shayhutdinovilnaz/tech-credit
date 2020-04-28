package account.repository;

import account.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserModel, Integer> {

    Optional<UserModel> findByUsername(String name);
}
