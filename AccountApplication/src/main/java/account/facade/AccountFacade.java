package account.facade;

import account.facade.data.AccountRegistrationFormData;

public interface AccountFacade {
    /**
     * Method create a new account according to required dates. If account with same email or username is exist, then method throws exception.
     *
     * @param registrationAccountFormData - dates for creation a new account,
     */
    void createNewAccount(AccountRegistrationFormData registrationAccountFormData);
}
