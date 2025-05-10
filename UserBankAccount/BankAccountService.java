package userstories.userbankaccount;

import java.util.ArrayList;
import java.util.List;

public class BankAccountService {
    private List<BankAccount> accounts = new ArrayList<>();

    public void addBankAccount(BankAccount account) {
        accounts.add(account);
        System.out.println("Bank account added successfully.");
    }

    public boolean verifyOtp(int accountId, String otp) {
        for (BankAccount account : accounts) {
            if (account.getId() == accountId) {
                if (account.getOtp().equals(otp)) {
                    account.setVerified(true);
                    return true;
                } else {
                    return false;
                }
            }
        }
        System.out.println("Account not found.");
        return false;
    }
}
