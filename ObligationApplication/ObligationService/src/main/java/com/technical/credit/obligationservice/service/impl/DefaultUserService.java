package com.technical.credit.obligationservice.service.impl;

import com.technical.credit.core.factory.GenericInstanceFactory;
import com.technical.credit.core.exception.ModelNotFoundException;
import com.technical.credit.obligationservice.model.UserModel;
import com.technical.credit.obligationservice.security.AuthenticationDetailsProvider;
import com.technical.credit.obligationservice.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DefaultUserService implements UserService {
    private static final String ACCOUNT_SERVICE_ADDRESS = "localhost:9191";
    private final RestTemplate restTemplate;
    private final GenericInstanceFactory genericInstanceFactory;
    private final AuthenticationDetailsProvider authenticationDetailsProvider;

    @Override
    public UserModel getById(long id) {
        return searchUser(String.valueOf(id));
    }

    @Override
    public UserModel getByUsername(final String username) {
        return searchUser("?username=".concat(username));
    }

    private UserModel searchUser(final String requestParam) {
        final HttpHeaders headers = genericInstanceFactory.getInstance(HttpHeaders.class);
        headers.setBearerAuth(authenticationDetailsProvider.getAuthenticationAccessToken());
        final HttpEntity<UserDTO> request = new HttpEntity<>(headers);

        try {
            final ResponseEntity<UserDTO> response = restTemplate.exchange("http://" + ACCOUNT_SERVICE_ADDRESS + "/api/v1/users/".concat(requestParam), HttpMethod.GET,
                    request, UserDTO.class);
            return Optional.ofNullable(response.getBody()).map(this::createUserModel).orElseThrow(() -> new ModelNotFoundException("The user domain is not found. Request param: " + requestParam));
        } catch (HttpClientErrorException e) {
            throw new ModelNotFoundException("The user domain is not found. Request param: " + requestParam, e);
        }
    }

    private UserModel createUserModel(final UserDTO userDTO) {
        final UserModel userModel = genericInstanceFactory.getInstance(UserModel.class);
        userModel.setId(userDTO.getId());
        userModel.setUsername(userDTO.getUsername());
        userModel.setFirstName(userDTO.getFirstName());
        userModel.setLastName(userDTO.getLastName());
        userModel.setEmail(userDTO.getEmail());
        return userModel;
    }

    @Data
    public static class UserDTO implements Serializable {
        private long id;
        private String username;
        private String firstName;
        private String lastName;
        private String email;
    }
}
