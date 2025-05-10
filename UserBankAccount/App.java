package UserBankAccount;

public class App {
    public static void main(String[] args) {
        BankAccountService bankService = new BankAccountService();

        // إضافة حساب بنكي
        BankAccount account = new BankAccount(1, "Bank of America", "1234567812345678", "John Doe", "123456");
        bankService.addBankAccount(account);

        // التحقق من OTP
        boolean isVerified = bankService.verifyOtp(1, "123456");
        if (isVerified) {
            System.out.println("Bank account successfully verified!");
        } else {
            System.out.println("Invalid OTP. Please try again.");
        }
    }
}
