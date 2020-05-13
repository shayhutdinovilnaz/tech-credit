package account.facade.converter.impl;

import account.facade.data.UserData;
import account.model.UserModel;
import com.technical.credit.core.factory.GenericInstanceFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserConverterTest {

    @InjectMocks
    private UserConverter underTest;

    @Mock
    private GenericInstanceFactory genericInstanceFactory;

    @Test
    public void testConvert() {
        final UserModel source = mock(UserModel.class);
        final UserData target = mock(UserData.class);
        final long id = 1L;
        final String username = "Johnny";
        final String firstName = "John";
        final String lastName = "Smith";
        final String email = "em@ym.ru";

        when(genericInstanceFactory.getInstance(UserData.class)).thenReturn(target);
        when(source.getId()).thenReturn(id);
        when(source.getUsername()).thenReturn(username);
        when(source.getFirstName()).thenReturn(firstName);
        when(source.getLastName()).thenReturn(lastName);
        when(source.getEmail()).thenReturn(email);

        Assert.assertNotNull(underTest.convert(source));
        verify(genericInstanceFactory).getInstance(UserData.class);
        verifyNoMoreInteractions(genericInstanceFactory);
        verify(source).getId();
        verify(source).getUsername();
        verify(source).getFirstName();
        verify(source).getLastName();
        verify(source).getEmail();
        verifyNoMoreInteractions(source);
        verify(target).setId(id);
        verify(target).setUsername(username);
        verify(target).setFirstName(firstName);
        verify(target).setLastName(lastName);
        verify(target).setEmail(email);
        verifyNoMoreInteractions(target);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertNullableSource() {
        underTest.convert(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testReverseConvert() {
        underTest.reverseConvert(new UserData());
    }
}