package authorization.repository;

import authorization.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

//@Repository
public interface UserDetailRepository extends JpaRepository<User, Integer> {

    @NotNull Optional<User> findByUsername(String name);

}
