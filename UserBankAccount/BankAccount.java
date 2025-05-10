public class BankAccount {
    private int id;
    private String bankName;
    private String cardNumber;
    private String cardHolderName;
    private String otp;
    private boolean isVerified;

    public BankAccount(int id, String bankName, String cardNumber, String cardHolderName, String otp) {
        this.id = id;
        this.bankName = bankName;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.otp = otp;
        this.isVerified = false;
    }

    public int getId() {
        return id;
    }

    public String getOtp() {
        return otp;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public boolean isVerified() {
        return isVerified;
    }
}