package service.exceptions;

import model.Account;

public class UserIdAlreadyExistsException extends Exception{
    private final static long serialVersionUID = 1L;

    public UserIdAlreadyExistsException(Account account) {
        super("Account with id " + account.getId() + " already exists");
    }
}
