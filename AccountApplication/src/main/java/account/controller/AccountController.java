package account.controller;

import account.facade.AccountFacade;
import account.facade.data.AccountRegistrationFormData;
import account.facade.data.UserData;
import com.technical.credit.core.controller.AbstractController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/accounts")
public class AccountController extends AbstractController {

    private final AccountFacade accountFacade;

    @PostMapping("/{register}")
    public ResponseEntity<UserData> searchUser(@Valid @RequestBody AccountRegistrationFormData newAccountData) {
        accountFacade.createNewAccount(newAccountData);
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
