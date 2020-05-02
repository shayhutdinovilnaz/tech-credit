package account.service.impl;

import account.model.UserModel;
import account.repository.UserRepository;
import account.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {
    private final UserRepository authRepository;

    @Override
    public UserModel getByUsername(final String username) {
        Assert.notNull(username, "A username could not be nullable.");
        return authRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User by username is not found. Username:" + username));
    }

    @Override
    public UserModel getById(long id) {
        return authRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User by id is not found. Id:" + id));
    }
}
