package account.facade.impl;

import account.facade.AccountFacade;
import account.facade.data.AccountRegistrationFormData;
import account.model.RoleModel;
import account.model.UserModel;
import account.service.RoleService;
import account.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultAccountFacade implements AccountFacade {
    private static final String DEFAULT_USER_ROLE_NAME = "ROLE_user";

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Override
    public void createNewAccount(final AccountRegistrationFormData accountRegistrationFormData) {
        Assert.notNull(accountRegistrationFormData, "Registration form can't not be nullable.");

        if (isExistAccount(accountRegistrationFormData)) {
            throw new IllegalArgumentException("Account with unique fields is exist.");
        }

        final UserModel newUser = new UserModel();
        newUser.setEmail(accountRegistrationFormData.getEmail());
        newUser.setUsername(accountRegistrationFormData.getUsername());
        newUser.setFirstName(accountRegistrationFormData.getFirstName());
        newUser.setLastName(accountRegistrationFormData.getLastName());
        newUser.setPassword(bCryptPasswordEncoder.encode(accountRegistrationFormData.getPassword()));
        newUser.setAccountNonExpired(true);
        newUser.setAccountNonLocked(true);
        newUser.setCredentialsNonExpired(true);
        newUser.setEnabled(true);
        newUser.setEnabled(true);
        newUser.setRoles(getRoles());

        userService.save(newUser);
    }

    private boolean isExistAccount(final AccountRegistrationFormData accountRegistrationFormData) {
        return userService.isExistUserWithEmail(accountRegistrationFormData.getEmail()) || userService.isExistUserWithUsername(accountRegistrationFormData.getUsername());
    }

    private List<RoleModel> getRoles() {
        return Collections.singletonList(roleService.getRoleByName(DEFAULT_USER_ROLE_NAME));
    }

}
