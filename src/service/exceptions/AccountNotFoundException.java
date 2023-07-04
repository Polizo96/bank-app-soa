package service.exceptions;

import model.Account;

public class AccountNotFoundException extends Exception {
    private final static long serialVersionUID = 1L;

    public AccountNotFoundException(Account account) {
        super("The account with the IBAN " + account.getIban() + " was not found");
    }

    public AccountNotFoundException(String iban) {
        super("The account with the IBAN " + iban + " was not found");
    }

    public AccountNotFoundException(long id) {
        super("The account with the id " + id + " was not found");
    }
}
