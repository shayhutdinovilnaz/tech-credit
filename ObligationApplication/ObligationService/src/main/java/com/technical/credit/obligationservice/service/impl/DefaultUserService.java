package com.technical.credit.obligationservice.service.impl;

import com.technical.credit.obligationservice.configuration.AccessToken;
import com.technical.credit.obligationservice.exception.ModelNotFoundException;
import com.technical.credit.obligationservice.factory.GenericInstanceFactory;
import com.technical.credit.obligationservice.model.UserModel;
import com.technical.credit.obligationservice.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOG = LoggerFactory.getLogger(DefaultUserService.class);
    private static final String ACCOUNT_SERVICE_ADDRESS = "localhost:9191";
    private final RestTemplate restTemplate;
    private final GenericInstanceFactory genericInstanceFactory;

    @Override
    public UserModel getById(long id) {
        return searchUser("?id=".concat(Long.toString(id)));
    }

    @Override
    public UserModel getByUsername(final String username) {
        return searchUser(username);
    }

    private UserModel searchUser(final String requestParam) {
        final HttpHeaders headers = genericInstanceFactory.getInstance(HttpHeaders.class);
        headers.add("Authorization", AccessToken.getAccessToken());
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
    private static class UserDTO implements Serializable {
        private long id;
        private String username;
        private String firstName;
        private String lastName;
        private String email;
    }
}
