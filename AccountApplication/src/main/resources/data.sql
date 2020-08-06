INSERT INTO oauth_client_details (client_id, client_secret, web_server_redirect_uri, scope, access_token_validity, refresh_token_validity, resource_ids, authorized_grant_types, additional_information) VALUES ('mobile', '{bcrypt}$2a$10$gPhlXZfms0EpNHX0.HHptOhoFD1AoxSr/yUIdTqA8vtjeP4zi0DDu', 'http://localhost:8080/code', 'READ,WRITE', '3600', '10000', 'inventory,payment', 'authorization_code,password,refresh_token,implicit', '{}');

INSERT INTO PERMISSION (ID, NAME) VALUES
 (1, 'create_category'),
 (2, 'read_category'),
 (3, 'update_category'),
 (4, 'delete_category'),
 (5, 'create_obligation'),
 (6, 'read_obligation'),
 (7, 'update_obligation'),
 (8, 'delete_obligation');

INSERT INTO role (ID, NAME) VALUES (1, 'ROLE_admin'),(2, 'ROLE_user');

INSERT INTO PERMISSION_ROLE (PERMISSION_ID, ROLE_ID) VALUES
     (1,1),
     (2,1),
     (3,1),
     (4,1),
     (5,1),
     (6,1),
     (7,1),
     (8,1),
     (1,2),
     (2,2),
     (3,2),
     (4,2),
     (5,2),
     (6,2),
     (7,2),
     (8,2);

INSERT INTO user (id, username, firstName, lastName, password, email, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked) VALUES ('1', 'ivan123','Ivan','Ivanov','{bcrypt}$2a$10$ODGwrk2ufy5d7T6afmACwOA/6j6rvXiP5amAMt1YjOQSdEw44QdqG', 's@gmail.com', true, true, true, true);
INSERT INTO user (id, username, firstName, lastName, password, email, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked) VALUES ('2', 'nick123','Nick','Jackson', '{bcrypt}$2a$10$wQ8vZl3Zm3.zDSIcZEYym.bGq3fPMJXH9k.Vhudcfr6O6KQwDPSt6','u@gmail.com', true, true, true, true);

INSERT INTO ROLE_USER (ROLE_ID, USER_ID)
    VALUES
    (1, 1) ,
    (2, 2) ;