package account.controller;

import account.facade.UserFacade;
import account.facade.data.UserData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users")
//todo а регистрацию новых юзеров мы тут будем делать?
public class UserController {
    private final UserFacade userFacade;

    @GetMapping("/{username}")
    public ResponseEntity<UserData> searchUser(@PathVariable String username) {
        return new ResponseEntity<>(userFacade.getByUsername(username), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<UserData> searchUser(@RequestParam long id) {
        return new ResponseEntity<>(userFacade.getById(id), HttpStatus.OK);
    }
}
