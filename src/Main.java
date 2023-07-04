import dao.AccountDAOImpl;
import dao.IAccountDAO;
import dto.AccountDTO;
import dto.UserDTO;
import model.Account;
import service.AccountServiceImpl;
import service.IAccountService;

import java.util.List;

public class Main {

    private final static IAccountDAO dao = new AccountDAOImpl();
    private final static IAccountService service = new AccountServiceImpl(dao);

    public static void main(String[] args) {

        try {
            UserDTO userDTO = new UserDTO(1L,"Alice","Wonderland","12345");
            AccountDTO accountDTO = new AccountDTO(1L,userDTO,"GR123456",100.0);
            UserDTO user2DTO = new UserDTO(2L,"Bob","M.","12347");
            AccountDTO account2DTO = new AccountDTO(2L,user2DTO,"GR123457",1000.0);


            service.insertAccount(accountDTO);
            service.insertAccount(account2DTO);
            account2DTO.setBalance(200.0);
            service.updateAccount(2L,account2DTO);
            service.deposit(account2DTO.getId(),50.0);
            service.deleteAccount(1L);

            List<Account> accounts = service.getAllAccounts();
            for (Account account : accounts) {
                System.out.println(account);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
