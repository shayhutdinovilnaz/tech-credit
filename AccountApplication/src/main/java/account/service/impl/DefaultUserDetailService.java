package account.service.impl;

import account.model.AuthUserDetailModel;
import account.model.UserModel;
import account.repository.UserDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultUserDetailService implements UserDetailsService {

    private final UserDetailRepository userDetailRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        final UserModel user =
                userDetailRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("Username or password wrong"));

        final UserDetails userDetails = new AuthUserDetailModel(user);
        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;
    }
}
