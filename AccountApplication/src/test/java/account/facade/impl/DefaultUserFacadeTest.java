package account.facade.impl;

import account.facade.data.UserData;
import account.model.UserModel;
import account.service.UserService;
import com.technical.credit.core.converter.Converter;
import com.technical.credit.core.exception.ModelNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserFacadeTest {
    @Mock
    private UserService userService;

    @Mock
    private Converter<UserData, UserModel> userConverter;

    @Test
    public void testGetByUsername() {
        final String username = "Johnny";
        final UserModel userModel = mock(UserModel.class);
        final UserData userData = mock(UserData.class);

        when(userService.getByUsername(username)).thenReturn(userModel);
        when(userConverter.convert(userModel)).thenReturn(userData);

        final DefaultUserFacade underTest = new DefaultUserFacade(userService, userConverter);
        Assert.assertNotNull(underTest.getByUsername(username));
        verify(userService).getByUsername(username);
        verify(userConverter).convert(userModel);
        verifyNoMoreInteractions(userService, userConverter);
    }

    @Test(expected = ModelNotFoundException.class)
    public void testGetByUsernameNotFound() {
        final String username = "Johnny";

        when(userService.getByUsername(username)).thenThrow(ModelNotFoundException.class);

        final DefaultUserFacade underTest = new DefaultUserFacade(userService, userConverter);
        underTest.getByUsername(username);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByUsernameNullable() {
        final DefaultUserFacade underTest = new DefaultUserFacade(userService, userConverter);
        underTest.getByUsername(null);
    }

    @Test
    public void testGetById() {
        final long id = 1L;
        final UserModel userModel = mock(UserModel.class);
        final UserData userData = mock(UserData.class);

        when(userService.getById(id)).thenReturn(userModel);
        when(userConverter.convert(userModel)).thenReturn(userData);

        final DefaultUserFacade underTest = new DefaultUserFacade(userService, userConverter);
        Assert.assertNotNull(underTest.getById(id));
        verify(userService).getById(id);
        verify(userConverter).convert(userModel);
        verifyNoMoreInteractions(userService, userConverter);
    }

    @Test(expected = ModelNotFoundException.class)
    public void testGetByIdNotFound() {
        final long id = 1L;

        when(userService.getById(id)).thenThrow(ModelNotFoundException.class);

        final DefaultUserFacade underTest = new DefaultUserFacade(userService, userConverter);
        underTest.getById(id);
    }
}