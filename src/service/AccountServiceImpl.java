package service;

import dao.IAccountDAO;
import dto.AccountDTO;
import dto.UserDTO;
import model.Account;
import model.User;
import service.exceptions.*;

import java.util.List;

public class AccountServiceImpl implements IAccountService {
    private final IAccountDAO dao;

    public AccountServiceImpl(IAccountDAO dao) {this.dao = dao;}

    /**
     * Inserts an account into a list.
     *
     * @param accountDTO
     *                  the account to be inserted.
     * @return
     *                  the inserted account.
     * @throws IbanAlreadyExistsException
     *                  if iban already exists.
     * @throws UserIdAlreadyExistsException
     *                  if the id already exists.
     */
    @Override
    public Account insertAccount(AccountDTO accountDTO) throws IbanAlreadyExistsException,
            UserIdAlreadyExistsException {
        Account account = null;
        try {
            account = new Account();
            mapAccount(account,accountDTO);
            if (dao.ibanExists(accountDTO.getIban())) {
                throw new IbanAlreadyExistsException(account);
            }

            if (dao.userIdExists(accountDTO.getId())) {
                throw new UserIdAlreadyExistsException(account);
            }

            account = dao.insert(account);
        } catch (IbanAlreadyExistsException | UserIdAlreadyExistsException e) {
            System.err.println("Error in insert.");
            throw e;
        }

        return account;
    }

    /**
     * Updates an account from the list.
     *
     * @param id
     *                 the id of account to be updated.
     * @param accountDTO
     *                  the account to be updated.
     * @return          the updated account.
     * @throws AccountNotFoundException
     *                  if account to be updated not found.
     * @throws IbanAlreadyExistsException
     *                  if iban already exists.
     */
    @Override
    public Account updateAccount(long id, AccountDTO accountDTO)
            throws AccountNotFoundException, IbanAlreadyExistsException {
        Account account = null;
        try {
            account = new Account();
            mapAccount(account, accountDTO);

            if (id != accountDTO.getId() || !dao.userIdExists(id)) {
                throw new AccountNotFoundException(id);
            }

            if (dao.ibanExists(accountDTO.getIban())) {
                if (!dao.get(accountDTO.getId()).equals(dao.get(accountDTO.getIban()))) {
                    throw new IbanAlreadyExistsException(account);
                }
            }

            account = dao.update(id, account);
        } catch (AccountNotFoundException | IbanAlreadyExistsException  e) {
            System.err.println("Error in update");
            throw e;
        }

        return account;
    }

    /**
     * Deletes an account from the list by id.
     *
     * @param id
     *            the account's id to be deleted.
     * @throws AccountNotFoundException
     *            if the account not found.
     */
    @Override
    public void deleteAccount(long id) throws AccountNotFoundException {
        Account account = null;
        try {
            account = new Account();
            if (!dao.userIdExists(id)) {
                throw new AccountNotFoundException(account);
            }

            dao.delete(id);
        } catch (AccountNotFoundException e) {
            System.err.println("Account not found");
            throw e;
        }
    }

    /**
     * Deletes an account from the list by iban.
     * @param iban
     *              the account's iban to be deleted.
     * @throws AccountNotFoundException
     *              if account not found.
     */
    @Override
    public void deleteAccount(String iban) throws AccountNotFoundException {
        Account account;
        try {
            account = new Account();
            if (!dao.ibanExists(iban)) {
                throw new AccountNotFoundException(account);
            }

            dao.delete(iban);
        } catch (AccountNotFoundException e) {
            System.err.println("Account not found");
            throw e;
        }
    }

    /**
     * Gets an account by id.
     *
     * @param id
     *              the given id of the account.
     * @return
     *              the account.
     * @throws AccountNotFoundException
     *              if the account not found.
     */
    @Override
    public Account getAccount(long id) throws AccountNotFoundException {
        Account account;
        try {
            account = dao.get(id);
            if (account == null) {
                throw new AccountNotFoundException(id);
            }

            return account;
        } catch (AccountNotFoundException e) {
            System.err.println("Account not found");
            throw e;
        }
    }

    /**
     * Gets an account by iban.
     *
     * @param iban
     *              the given iban of the account.
     * @return
     *              the account.
     * @throws AccountNotFoundException
     *              if the account not found.
     */
    @Override
    public Account getAccount(String iban) throws AccountNotFoundException {
        Account account;
        try {
            account = dao.get(iban);
            if (account == null) {
                throw new AccountNotFoundException(iban);
            }

            return account;
        } catch (AccountNotFoundException e) {
            System.err.println("Account not found");
            throw e;
        }
    }

    /**
     * Gets a new list of accounts.
     *
     * @return
     *          the list of accounts.
     */
    @Override
    public List<Account> getAllAccounts() {
        return dao.getAll();
    }

    /**
     * Deposits a certain amount of money to an account.
     *
     * @param id
     *               the id of the account.
     * @param amount
     *              the amount to be deposited.
     * @return
     *              the deposited account.
     * @throws NegativeAmountException
     *              if the amount is negative.
     * @throws AccountNotFoundException
     *              if the account not found.
     */
    @Override
    public Account deposit(long id, double amount) throws  NegativeAmountException, AccountNotFoundException {
        Account account = null;
        try {
            account = dao.get(id);
            if (amount < 0 ) {
                throw new NegativeAmountException(amount);
            }

            if (account == null) {
                throw new AccountNotFoundException(account);
            }

            double newBalance = account.getBalance() + amount;
            account.setBalance(newBalance);
            dao.update(account.getId(), account);
        } catch (NegativeAmountException e) {
            System.err.println("Negative amount error");
            throw e;
        }
        return account;
    }

    /**
     * Withdraws a certain amount of money.
     * @param id
     *              the id of account.
     * @param ssn
     *              the ssn to be checked.
     * @param amount
     *              the amount of money to be withdrawn.
     * @return
     *              the withdrawn account.
     * @throws NegativeAmountException
     *              if the amount is negative.
     * @throws InsufficientBalanceException
     *              if the amount is greater than the balance.
     * @throws AccountNotFoundException
     *              if account not found.
     * @throws SsnNotValidException
     *              if ssn is not valid.
     */
    @Override
    public Account withdraw(long id, String ssn, double amount)
            throws NegativeAmountException, InsufficientBalanceException, AccountNotFoundException, SsnNotValidException {
        Account account = null;
        try {
            account = dao.get(id);

            if (!isSsnValid(ssn)) throw new SsnNotValidException(ssn);
            if (amount < 0) throw new NegativeAmountException(amount);
            if (account == null) throw new AccountNotFoundException(id);
            if (amount > account.getBalance()) throw new InsufficientBalanceException(account.getBalance(), amount);

            double newBalance = account.getBalance() - amount;
            account.setBalance(newBalance);
            dao.update(account.getId(), account);

        } catch (NegativeAmountException | InsufficientBalanceException |
                 AccountNotFoundException | SsnNotValidException e) {
            System.err.println("Error in withdrawal");
            throw e;
        }
        return account;
    }

    /**
     * Maps the properties of the account DTO to the account.
     *
     * @param account
     *              the account object to be mapped to.
     * @param accountDTO
     *              the account DTO having the information.
     */
    private void mapAccount(Account account, AccountDTO accountDTO) {
        account.setId(accountDTO.getId());
        account.setBalance(accountDTO.getBalance());
        account.setIban(accountDTO.getIban());
        User holder = new User();
        mapUser(holder, accountDTO.getUser());
        account.setHolder(holder);
    }

    /**
     * Maps the properties of the user DTO to the user.
     *
     * @param holder
     *              the user object to be mapped to.
     * @param userDTO
     *              the user DTO having the information
     */
    private void mapUser(User holder, UserDTO userDTO) {
        holder.setId(userDTO.getId());
        holder.setFirstname(userDTO.getFirstname());
        holder.setLastname(userDTO.getLastname());
        holder.setSsn(userDTO.getSsn());
    }

    /**
     * Checks if the ssn is valid.
     *
     * @param ssn
     *          the given ssn.
     * @return
     *          true if is valid, false otherwise.
     */
    private boolean isSsnValid(String ssn){
        Account account = new Account();

        if (ssn == null || account.getHolder().getSsn() == null) return false;

        return account.getHolder().getSsn().equals(ssn);
    }
}
