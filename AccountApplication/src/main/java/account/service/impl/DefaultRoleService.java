package account.service.impl;

import account.model.RoleModel;
import account.repository.RoleRepository;
import account.service.RoleService;
import com.technical.credit.common.exception.ModelNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class DefaultRoleService implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public RoleModel getRoleByName(final String name) {
        Assert.notNull(name, "A role name could not be nullable.");

        return roleRepository.getRoleModelByNameIgnoreCase(name).orElseThrow(() -> new ModelNotFoundException("Role by name is not found. Name:" + name));
    }

}
