package model;

public class Account extends AbstractEntity{
    private User holder = new User();
    private String iban;
    private double balance;

    public Account() {}

    public Account(User holder, String iban, double balance) {
        this.holder = holder;
        this.iban = iban;
        this.balance = balance;
    }

    public User getHolder() {
        return holder;
    }

    public void setHolder(User holder) {
        this.holder = holder;
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

    @Override
    public String toString() {
        return "Account{" +
                "holder=" + holder +
                ", iban='" + iban + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (Double.compare(account.balance, balance) != 0) return false;
        if (!holder.equals(account.holder)) return false;
        return iban.equals(account.iban);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = holder.hashCode();
        result = 31 * result + iban.hashCode();
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public boolean isSsnValid(String ssn){

        if (ssn == null || holder.getSsn() == null) return false;

        return holder.getSsn().equals(ssn);
    }
}
