package dao;

import model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements IAccountDAO {
    private static final ArrayList<Account> accounts = new ArrayList<>();

    /**
     * Inserts a new account into the list.
     *
     * @param account
     *               the account to be inserted.
     * @return
     *               the inserted account.
     */
    @Override
    public Account insert(Account account) {
        if (account == null) return null;
        accounts.add(account);
        return account;
    }

    /**
     * Updates an existing account of the list.
     *
     * @param id
     *              the id to be updated.
     * @param account
     *              the account to be updated.
     * @return
     */
    @Override
    public Account update(long id, Account account) {
        int positionToUpdate = getIndexById(id);
        if (positionToUpdate == -1) return null;

        return accounts.set(positionToUpdate, account);
    }

    /**
     * Deletes the account from the list by id.
     *
     * @param id
     *              the id of the account holder.
     */
    @Override
    public void delete(long id) {
        accounts.removeIf(account -> account.getId() == id);
    }

    /**
     * Gets the account from the list by id.
     * @param id
     *              the id of the account holder.
     * @return
     */
    @Override
    public Account get(long id) {
        int pos = getIndexById(id);
        if (pos == -1) return null;

        return accounts.get(pos);
    }

    /**
     * Gets all the accounts.
     * @return
     *          a new list with all the accounts.
     */
    @Override
    public List<Account> getAll() {
        return new ArrayList<>(accounts);
    }

    /**
     * Gets an account by iban.
     * @param iban
     *              the iban of the account.
     * @return
     *              the account.
     */
    @Override
    public Account get(String iban) {
        int pos = getIndexByIban(iban);
        if (pos == -1) return null;

        return accounts.get(pos);
    }

    /**
     * Deletes an account by iban.
     * @param iban
     *              the iban of the account.
     */
    @Override
    public void delete(String iban) {
        accounts.removeIf(account -> account.getIban().equals(iban));
    }

    /**
     * Checks if the iban of an account exists.
     *
     * @param iban
     *              the iban of the account.
     * @return
     *              true if exists, false otherwise
     */
    @Override
    public boolean ibanExists(String iban) {
        return getIndexByIban(iban) != -1;
    }

    /**
     * Checks if the id of an account user exists.
     *
     * @param id
     *              the id of the account.
     * @return
     *              true if exists, false otherwise
     */
    @Override
    public boolean userIdExists(long id) {
        return getIndexById(id) != -1;
    }

    /**
     * Returns the index of the account with the specified ID in the accounts list.
     *
     * @param id
     *              the ID to search for
     * @return
     *              the index of the account with the specified ID, or {@code -1} if not found
     */
    private int getIndexById(long id) {
        int pos = -1;

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getId() == id) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    /**
     * Returns the index of the account with the specified iban in the accounts list.
     *
     * @param iban
     *                 the iban to be searched.
     * @return
     *                 the index of the account with the specified iban, or {@code -1} if not found
     */
    private int getIndexByIban(String iban) {
        int pos = -1;

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getIban().equals(iban)) {
                pos = i;
                break;
            }
        }
        return pos;
    }


}
