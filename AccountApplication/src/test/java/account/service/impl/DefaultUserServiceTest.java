package account.service.impl;

import account.model.UserModel;
import account.repository.UserRepository;
import com.technical.credit.core.exception.ModelNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserServiceTest {

    @InjectMocks
    private DefaultUserService underTest;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testGetByUsername() {
        final UserModel user = mock(UserModel.class);
        final Optional<UserModel> userOptional = Optional.of(user);
        final String username = "username";

        when(userRepository.findByUsername(username)).thenReturn(userOptional);

        Assert.assertNotNull(underTest.getByUsername(username));
        verify(userRepository).findByUsername(username);
        verifyNoMoreInteractions(userRepository);
        verifyNoInteractions(user);
    }

    @Test(expected = ModelNotFoundException.class)
    public void testGetByUsernameNotFound() {
        final String username = "username";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        underTest.getByUsername(username);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByUsernameNullableUsername() {
        underTest.getByUsername(null);
    }

    @Test
    public void testGetById() {
        final UserModel user = mock(UserModel.class);
        final Optional<UserModel> userOptional = Optional.of(user);
        final long id = 1L;

        when(userRepository.findById(id)).thenReturn(userOptional);

        Assert.assertNotNull(underTest.getById(id));
        verify(userRepository).findById(id);
        verifyNoMoreInteractions(userRepository);
        verifyNoInteractions(user);
    }

    @Test(expected = ModelNotFoundException.class)
    public void testGetByIdNotFound() {
        final long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        underTest.getById(id);
    }

}