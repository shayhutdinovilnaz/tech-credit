package com.technical.credit.obligationservice.service.impl;

import com.technical.credit.core.factory.GenericInstanceFactory;
import com.technical.credit.core.exception.ModelNotFoundException;
import com.technical.credit.obligationservice.model.UserModel;
import com.technical.credit.obligationservice.security.AuthenticationDetailsProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class DefaultUserServiceTest {

    @InjectMocks
    private DefaultUserService underTest;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private GenericInstanceFactory genericInstanceFactory;

    @Mock
    private AuthenticationDetailsProvider authenticationDetailsProvider;

    @Test
    public void testSearchByUsername() {
        final HttpHeaders headers = mock(HttpHeaders.class);
        final DefaultUserService.UserDTO userDTO = mock(DefaultUserService.UserDTO.class);
        final UserModel userModel = mock(UserModel.class);
        final ResponseEntity<DefaultUserService.UserDTO> response = mock(ResponseEntity.class);
        final long userId = 1L;
        final String username = "ourUser";
        final String firstName = "Ilnaz";
        final String lastName = "Shaikhutdinov";
        final String email = "ilnaz-92@yandex.ru";


        when(genericInstanceFactory.getInstance(HttpHeaders.class)).thenReturn(headers);
        when(genericInstanceFactory.getInstance(UserModel.class)).thenReturn(userModel);
        when(restTemplate.exchange(eq("http://localhost:9191/api/v1/users/?username=".concat(username)), eq(HttpMethod.GET),
                any(HttpEntity.class), eq(DefaultUserService.UserDTO.class))).thenReturn(response);
        when(authenticationDetailsProvider.getAuthenticationAccessToken()).thenReturn("token");
        when(response.getBody()).thenReturn(userDTO);
        when(userDTO.getId()).thenReturn(userId);
        when(userDTO.getUsername()).thenReturn(username);
        when(userDTO.getFirstName()).thenReturn(firstName);
        when(userDTO.getLastName()).thenReturn(lastName);
        when(userDTO.getEmail()).thenReturn(email);


        Assert.assertNotNull(underTest.getByUsername(username));
        verify(userModel).setId(userId);
        verify(userModel).setUsername(username);
        verify(userModel).setFirstName(firstName);
        verify(userModel).setLastName(lastName);
        verify(userModel).setEmail(email);
        verifyNoMoreInteractions(userModel);
        verify(genericInstanceFactory).getInstance(HttpHeaders.class);
        verify(genericInstanceFactory).getInstance(UserModel.class);
        verifyNoMoreInteractions(genericInstanceFactory);
        verify(restTemplate).exchange(eq("http://localhost:9191/api/v1/users/?username=".concat(username)), eq(HttpMethod.GET),
                any(HttpEntity.class), eq(DefaultUserService.UserDTO.class));
        verifyNoMoreInteractions(restTemplate);
        verify(headers).setBearerAuth("token");
        verify(response).getBody();
        verifyNoMoreInteractions(response);
        verify(authenticationDetailsProvider).getAuthenticationAccessToken();
        verifyNoMoreInteractions(authenticationDetailsProvider);
        verify(userDTO).getId();
        verify(userDTO).getUsername();
        verify(userDTO).getFirstName();
        verify(userDTO).getLastName();
        verify(userDTO).getEmail();
        verifyNoMoreInteractions(userDTO);
    }

    @Test(expected = ModelNotFoundException.class)
    public void testSearchByUsernameHttpResponseException() {
        final HttpHeaders headers = mock(HttpHeaders.class);
        final UserModel userModel = mock(UserModel.class);
        final String username = "ourUser";

        when(genericInstanceFactory.getInstance(HttpHeaders.class)).thenReturn(headers);
        when(genericInstanceFactory.getInstance(UserModel.class)).thenReturn(userModel);
        when(authenticationDetailsProvider.getAuthenticationAccessToken()).thenReturn("token");
        when(restTemplate.exchange(eq("http://localhost:9191/api/v1/users/?username=".concat(username)), eq(HttpMethod.GET),
                any(HttpEntity.class), eq(DefaultUserService.UserDTO.class))).thenThrow(new HttpClientErrorException(HttpStatus.ALREADY_REPORTED));

        Assert.assertNotNull(underTest.getByUsername(username));
    }

    @Test(expected = ModelNotFoundException.class)
    public void testSearchByUsernameNotFound() {
        final HttpHeaders headers = mock(HttpHeaders.class);
        final UserModel userModel = mock(UserModel.class);
        final ResponseEntity<DefaultUserService.UserDTO> response = mock(ResponseEntity.class);
        final String username = "ourUser";

        when(genericInstanceFactory.getInstance(HttpHeaders.class)).thenReturn(headers);
        when(genericInstanceFactory.getInstance(UserModel.class)).thenReturn(userModel);
        when(restTemplate.exchange(eq("http://localhost:9191/api/v1/users/?username=".concat(username)), eq(HttpMethod.GET),
                any(HttpEntity.class), eq(DefaultUserService.UserDTO.class))).thenReturn(response);
        when(authenticationDetailsProvider.getAuthenticationAccessToken()).thenReturn("token");

        Assert.assertNotNull(underTest.getByUsername(username));
    }

    @Test
    public void testSearchById() {
        final HttpHeaders headers = mock(HttpHeaders.class);
        final DefaultUserService.UserDTO userDTO = mock(DefaultUserService.UserDTO.class);
        final UserModel userModel = mock(UserModel.class);
        final ResponseEntity<DefaultUserService.UserDTO> response = mock(ResponseEntity.class);
        final long userId = 1L;
        final String username = "ourUser";
        final String firstName = "Ilnaz";
        final String lastName = "Shaikhutdinov";
        final String email = "ilnaz-92@yandex.ru";


        when(genericInstanceFactory.getInstance(HttpHeaders.class)).thenReturn(headers);
        when(genericInstanceFactory.getInstance(UserModel.class)).thenReturn(userModel);
        when(restTemplate.exchange(eq("http://localhost:9191/api/v1/users/".concat(Long.toString(userId))), eq(HttpMethod.GET),
                any(HttpEntity.class), eq(DefaultUserService.UserDTO.class))).thenReturn(response);
        when(authenticationDetailsProvider.getAuthenticationAccessToken()).thenReturn("token");
        when(response.getBody()).thenReturn(userDTO);
        when(userDTO.getId()).thenReturn(userId);
        when(userDTO.getUsername()).thenReturn(username);
        when(userDTO.getFirstName()).thenReturn(firstName);
        when(userDTO.getLastName()).thenReturn(lastName);
        when(userDTO.getEmail()).thenReturn(email);


        Assert.assertNotNull(underTest.getById(userId));
        verify(userModel).setId(userId);
        verify(userModel).setUsername(username);
        verify(userModel).setFirstName(firstName);
        verify(userModel).setLastName(lastName);
        verify(userModel).setEmail(email);
        verifyNoMoreInteractions(userModel);
        verify(genericInstanceFactory).getInstance(HttpHeaders.class);
        verify(genericInstanceFactory).getInstance(UserModel.class);
        verifyNoMoreInteractions(genericInstanceFactory);
        verify(restTemplate).exchange(eq("http://localhost:9191/api/v1/users/".concat(Long.toString(userId))), eq(HttpMethod.GET),
                any(HttpEntity.class), eq(DefaultUserService.UserDTO.class));
        verifyNoMoreInteractions(restTemplate);
        verify(headers).setBearerAuth("token");
        verify(response).getBody();
        verifyNoMoreInteractions(response);
        verify(authenticationDetailsProvider).getAuthenticationAccessToken();
        verifyNoMoreInteractions(authenticationDetailsProvider);
        verify(userDTO).getId();
        verify(userDTO).getUsername();
        verify(userDTO).getFirstName();
        verify(userDTO).getLastName();
        verify(userDTO).getEmail();
        verifyNoMoreInteractions(userDTO);
    }
}