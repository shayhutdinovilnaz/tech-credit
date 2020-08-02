package account.service.impl;

import account.model.AuthDetailModel;
import account.model.RoleModel;
import account.model.UserModel;
import account.repository.UserRepository;
import com.technical.credit.core.factory.GenericInstanceFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserDetailServiceTest {

    @InjectMocks
    private DefaultUserDetailService underTest;

    @Mock
    private UserRepository userRepository;

    @Mock
    private GenericInstanceFactory genericInstanceFactory;

    @Test
    public void testLoadUserByUsername() {
        final UserModel user = mock(UserModel.class);
        final AuthDetailModel authDetail = mock(AuthDetailModel.class);
        final AccountStatusUserDetailsChecker accountStatusChecker = mock(AccountStatusUserDetailsChecker.class);
        final String username = "ourUser";
        final String firstName = "John";
        final String lastName = "Smith";
        final String email = "il@yan.ru";
        final String password = "pswd";
        final boolean enabled = true;
        final boolean accountNonExpired = true;
        final boolean credentialsNonExpired = true;
        final boolean locked = false;
        final List<RoleModel> roles = new ArrayList<>();

        when(userRepository.findByUsernameIgnoreCase(username)).thenReturn(Optional.of(user));
        when(user.getUsername()).thenReturn(username);
        when(user.getFirstName()).thenReturn(firstName);
        when(user.getLastName()).thenReturn(lastName);
        when(user.getPassword()).thenReturn(password);
        when(user.getEmail()).thenReturn(email);
        when(user.isEnabled()).thenReturn(enabled);
        when(user.isAccountNonExpired()).thenReturn(accountNonExpired);
        when(user.isCredentialsNonExpired()).thenReturn(credentialsNonExpired);
        when(user.isAccountNonLocked()).thenReturn(locked);
        when(user.getRoles()).thenReturn(roles);
        verifyNoMoreInteractions(user);
        when(genericInstanceFactory.getInstance(AuthDetailModel.class)).thenReturn(authDetail);
        when(genericInstanceFactory.getInstance(AccountStatusUserDetailsChecker.class)).thenReturn(accountStatusChecker);

        Assert.assertNotNull(underTest.loadUserByUsername(username));
        verify(userRepository).findByUsernameIgnoreCase(username);
        verifyNoMoreInteractions(userRepository);

        verify(genericInstanceFactory).getInstance(AuthDetailModel.class);
        verify(authDetail).setUsername(username);
        verify(authDetail).setFirstName(firstName);
        verify(authDetail).setLastName(lastName);
        verify(authDetail).setPassword(password);
        verify(authDetail).setEmail(email);
        verify(authDetail).setEnabled(enabled);
        verify(authDetail).setAccountNonExpired(accountNonExpired);
        verify(authDetail).setCredentialsNonExpired(credentialsNonExpired);
        verify(authDetail).setAccountNonLocked(locked);
        verify(authDetail).setRoles(roles);
        verifyNoMoreInteractions(authDetail);

        verify(user).getUsername();
        verify(user).getFirstName();
        verify(user).getLastName();
        verify(user).getPassword();
        verify(user).getEmail();
        verify(user).isEnabled();
        verify(user).isAccountNonExpired();
        verify(user).isCredentialsNonExpired();
        verify(user).isAccountNonLocked();
        verify(user).getRoles();
        verifyNoMoreInteractions(user);

        verify(genericInstanceFactory).getInstance(AccountStatusUserDetailsChecker.class);
        verify(accountStatusChecker).check(authDetail);
        verifyNoMoreInteractions(genericInstanceFactory);
        verifyNoMoreInteractions(accountStatusChecker);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameNotFound() {
        final String username = "ourUser";

        when(userRepository.findByUsernameIgnoreCase(username)).thenReturn(Optional.empty());

        underTest.loadUserByUsername(username);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLoadUserByUsernameNullableUsername() {
        Assert.assertNotNull(underTest.loadUserByUsername(null));
    }

}