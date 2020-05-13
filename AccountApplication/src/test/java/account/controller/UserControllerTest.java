package account.controller;

import account.facade.UserFacade;
import account.facade.data.UserData;
import com.technical.credit.core.exception.ModelNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserControllerTest extends AbstractControllerTest {
    @MockBean
    UserFacade userFacade;

    @Test
    @WithMockUser
    public void testGetByUsername() throws Exception {
        final String url = "/api/v1/users/";
        final UserData user = new UserData();
        final String username = "John";

        when(userFacade.getByUsername(username)).thenReturn(user);

        mvc.perform(MockMvcRequestBuilders.get(url).param("username", username)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        verify(userFacade).getByUsername(username);
        verifyNoMoreInteractions(userFacade);
    }

    @Test
    @WithMockUser
    public void testGetByUsernameNotFound() throws Exception {
        final String url = "/api/v1/users/";
        final UserData user = new UserData();
        final String username = "John";

        when(userFacade.getByUsername(username)).thenThrow(ModelNotFoundException.class);

        mvc.perform(MockMvcRequestBuilders.get(url).param("username", username)).andExpect(MockMvcResultMatchers.status().is4xxClientError());
        verify(userFacade).getByUsername(username);
        verifyNoMoreInteractions(userFacade);
    }

    @Test
    @WithMockUser
    public void testGetById() throws Exception {
        final String url = "/api/v1/users/1";
        final UserData user = new UserData();

        when(userFacade.getById(1L)).thenReturn(user);

        mvc.perform(MockMvcRequestBuilders.get(url)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        verify(userFacade).getById(1L);
        verifyNoMoreInteractions(userFacade);
    }

    @Test
    @WithMockUser
    public void testGetByIdNotFound() throws Exception {
        final String url = "/api/v1/users/1";
        final UserData user = new UserData();

        when(userFacade.getById(1L)).thenThrow(ModelNotFoundException.class);

        mvc.perform(MockMvcRequestBuilders.get(url)).andExpect(MockMvcResultMatchers.status().is4xxClientError());
        verify(userFacade).getById(1L);
        verifyNoMoreInteractions(userFacade);
    }
}