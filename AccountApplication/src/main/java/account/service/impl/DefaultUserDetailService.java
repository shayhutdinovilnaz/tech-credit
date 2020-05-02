package account.service.impl;

import account.model.AuthDetailModel;
import account.model.UserModel;
import account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        final UserModel user =
                userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("Username or password wrong"));

        final UserDetails userDetails = new AuthDetailModel(user);
        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;
    }
}
