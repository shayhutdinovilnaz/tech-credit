package authorization.repository;

import authorization.model.Role;
import authorization.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

//@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @NotNull Optional<Role> findByName(String roleName);

}
