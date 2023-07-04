package dto;

public class AccountDTO {
    private long id;
    private UserDTO user;
    private String iban;
    private double balance;

    public AccountDTO() {}

    public AccountDTO(long id, UserDTO user, String iban, double balance) {
        this.id = id;
        this.user = user;
        this.iban = iban;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
