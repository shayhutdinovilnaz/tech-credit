package account.service.impl;

import account.model.UserModel;
import account.repository.UserRepository;
import account.service.UserService;
import com.technical.credit.core.exception.ModelNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserModel save(UserModel user) {
        Assert.notNull(user, "A user could not be nullable.");
        return userRepository.save(user);
    }

    @Override
    public UserModel getByUsername(final String username) {
        Assert.notNull(username, "A username could not be nullable.");
        return userRepository.findByUsernameIgnoreCase(username).orElseThrow(() -> new ModelNotFoundException("User by username is not found. Username:" + username));
    }

    @Override
    public UserModel getById(final long id) {
        return userRepository.findById(id).orElseThrow(() -> new ModelNotFoundException("User by id is not found. Id:" + id));
    }

    @Override
    public UserModel getByEmail(final String email) {
        Assert.notNull(email, "A email could not be nullable.");

        return userRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new ModelNotFoundException("User by email is not found. Email:" + email));
    }

    @Override
    public boolean isExistUserWithUsername(final String username) {
        Assert.notNull(username, "A username could not be nullable.");

        try {
            final UserModel user = getByUsername(username);
            return user != null;
        } catch (ModelNotFoundException e) {
            return false;
        }
    }

    @Override
    public boolean isExistUserWithEmail(final String email) {
        Assert.notNull(email, "A email could not be nullable.");

        try {
            final UserModel user = getByEmail(email);
            return user != null;
        } catch (ModelNotFoundException e) {
            return false;
        }

    }
}
