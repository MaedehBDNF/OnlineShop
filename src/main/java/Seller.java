public class Seller extends Account{
    private String companyName;
    private Wallet wallet;
    private boolean authorization;

    public Seller(String username, String password, String companyName) {
        super(username, password);
        this.companyName = companyName;
        this.authorization = false;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public boolean isAuthorization() {
        return authorization;
    }

    public void setAuthorization(boolean authorization) {
        this.authorization = authorization;
    }

    @Override
    public String toString() {
        return "Seller ='" + companyName + '\'';
    }
}
