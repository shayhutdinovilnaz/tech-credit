package account.service.impl;

import account.model.UserModel;
import account.repository.UserRepository;
import account.service.UserService;
import com.technical.credit.core.exception.ModelNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserModel getByUsername(final String username) {
        Assert.notNull(username, "A username could not be nullable.");
        return userRepository.findByUsername(username).orElseThrow(() -> new ModelNotFoundException("User by username is not found. Username:" + username));
    }

    @Override
    public UserModel getById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new ModelNotFoundException("User by id is not found. Id:" + id));
    }
}
