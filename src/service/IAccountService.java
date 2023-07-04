package service;

import dto.AccountDTO;
import model.Account;
import service.exceptions.*;

import java.util.List;

/**
 * The operations that can be performed on accounts.
 */
public interface IAccountService {
    /**
     * Inserts a new account to the list.
     * @param accountDTO
     *                  the account to be inserted.
     * @return
     *                  the inserted account.
     * @throws IbanAlreadyExistsException
     *                  if the iban already exists.
     * @throws UserIdAlreadyExistsException
     *                  if the id already exists.
     */
    Account insertAccount(AccountDTO accountDTO)
            throws  IbanAlreadyExistsException,
            UserIdAlreadyExistsException;

    /**
     * Updates an existing account with a specified id.
     *
     * @param id
     *                 the id of account to be updated.
     * @param accountDTO
     *                  the account to be updated.
     * @return
     *                  the updated account.
     * @throws AccountNotFoundException
     *                  if the account is null.
     * @throws IbanAlreadyExistsException
     *                  if the account's iban already exists.
     */
    Account updateAccount(long id, AccountDTO accountDTO)
            throws AccountNotFoundException,IbanAlreadyExistsException;

    /**
     * Deletes the account by id.
     *
     * @param id
     *            the account's id to be deleted.
     * @throws AccountNotFoundException
     *            if the account not found.
     */
    void deleteAccount(long id) throws AccountNotFoundException;

    /**
     * Deletes the account by iban.
     *
     * @param iban
     *              the account's iban to be deleted.
     * @throws AccountNotFoundException
     *              if the account not found.
     */
    void deleteAccount(String iban) throws AccountNotFoundException;

    /**
     * Retrieves the account by id.
     *
     * @param id
     *              the given id of the account.
     * @return
     *              the account by id.
     * @throws AccountNotFoundException
     *              if the account not found.
     */
    Account getAccount(long id) throws AccountNotFoundException;

    /**
     * Retrieves the account by iban.
     * @param iban
     *              the given iban of the account.
     * @return
     *              the account by iban.
     * @throws AccountNotFoundException
     *              if the account not found.
     */
    Account getAccount(String iban) throws AccountNotFoundException;

    /**
     * Retrieves a list with all the accounts.
     *
     * @return
     *              a new list of all the accounts.
     */
    List<Account> getAllAccounts();

    /**
     * Deposits a specified amount of money into the account.
     * @param amount
     *              the amount to be deposited.
     * @param id
     *              the id of the account.
     * @return
     *              the deposited account.
     * @throws NegativeAmountException
     *              if the amount is negative.
     * @throws AccountNotFoundException
     *              if the account not found.
     */
    Account deposit(long id, double amount) throws  NegativeAmountException, AccountNotFoundException;

    /**
     * Withdraws a specified amount of money from the account
     * with a ssn check from the holder.
     *
     * @param ssn
     *              the ssn to be checked.
     * @param amount
     *              the amount of money to be withdrawn.
     * @param id
     *              the id of account.
     * @return
     *              the withdrawn account.
     * @throws NegativeAmountException
     *              if the amount is negative.
     * @throws InsufficientBalanceException
     *              if the balance is less than the given amount.
     * @throws AccountNotFoundException
     *              if the account not found.
     * @throws SsnNotValidException
     *              if the ssn is not valid.
     */
    Account withdraw(long id, String ssn, double amount)
            throws NegativeAmountException,InsufficientBalanceException,
            AccountNotFoundException,SsnNotValidException;
}
