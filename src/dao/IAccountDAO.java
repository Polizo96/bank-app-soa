package dao;

import model.Account;

import java.util.List;

/**
 * Defines the operations that can be performed on accounts.
 */
public interface IAccountDAO {
    /**
     * Inserts an account.
     *
     * @param account
     *               the account to be inserted.
     * @return
     *               the inserted account.
     */
    Account insert(Account account);

    /**
     * Updates an account
     *
     * @param id
     *              the id to be updated.
     * @param account
     *              the account to be updated.
     * @return
     *              the updated account.
     */
    Account update(long id, Account account);

    /**
     * Deletes an account by id.
     *
     * @param id
     *              the id of the account holder.
     */
    void delete(long id);

    /**
     * Gets the account by user's id.
     *
     * @param id
     *              the id of the user.
     * @return
     *              the account.
     */
    Account get(long id);

    /**
     * Gets all accounts.
     *
     * @return
     *              a list with all the accounts.
     */
    List<Account> getAll();

    /**
     * Gets an account by iban.
     *
     * @param iban
     *              the iban of the account.
     * @return
     *              the account.
     */
    Account get(String iban);

    /**
     * Deletes an account by iban.
     *
     * @param iban
     *              the iban of the account.
     */
    void delete(String iban);

    /**
     * Checks if the iban of an account exists
     *
     * @param iban
     *              the iban of the account.
     * @return
     *          true if exists, false otherwise.
     */
    boolean ibanExists(String iban);

    /**
     * Checks if the user's id exists
     *
     * @param id
     *              the user's id.
     * @return
     *              true if id exists, false otherwise.
     */
    boolean userIdExists(long id);
}
