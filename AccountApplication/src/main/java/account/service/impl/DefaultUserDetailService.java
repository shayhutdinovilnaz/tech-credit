package account.service.impl;

import account.model.AuthDetailModel;
import account.model.UserModel;
import account.repository.UserRepository;
import com.technical.credit.core.factory.GenericInstanceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class DefaultUserDetailService implements UserDetailsService {
    private final GenericInstanceFactory genericInstanceFactory;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        Assert.notNull(username, "A username could not be nullable.");
        final UserModel user =
                userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username or password wrong"));
        final AuthDetailModel userDetails = getAuthDetail(user);
        checkAccount(userDetails);
        return userDetails;
    }

    private AuthDetailModel getAuthDetail(final UserModel user) {
        final AuthDetailModel userDetails = genericInstanceFactory.getInstance(AuthDetailModel.class);

        userDetails.setUsername(user.getUsername());
        userDetails.setFirstName(user.getFirstName());
        userDetails.setLastName(user.getLastName());
        userDetails.setPassword(user.getPassword());
        userDetails.setEmail(user.getEmail());
        userDetails.setEnabled(user.isEnabled());
        userDetails.setAccountNonExpired(user.isAccountNonExpired());
        userDetails.setCredentialsNonExpired(user.isCredentialsNonExpired());
        userDetails.setAccountNonLocked(user.isAccountNonLocked());
        userDetails.setRoles(user.getRoles());
        return userDetails;
    }

    private void checkAccount(final AuthDetailModel userDetails) {
        final AccountStatusUserDetailsChecker checker = genericInstanceFactory.getInstance(AccountStatusUserDetailsChecker.class);
        checker.check(userDetails);
    }
}
