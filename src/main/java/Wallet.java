public class Wallet {
    private double fund;
    private double credit;
    private double totalCash;

    public Wallet(double credit) {
        this.fund = 0;
        this.credit = credit;
        this.totalCash = this.fund + this.credit;
    }

    public double getFund() {
        return fund;
    }

    public void setFund(double fund) {
        this.fund = fund;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(double totalCash) {
        this.totalCash = totalCash;
    }

    public void addFund(double fund){
        this.fund += fund;
        this.totalCash += fund;
    }

    public void chargeWallet(double credit){
        this.credit += credit;
        this.totalCash += credit;
    }

    public boolean pay(double price) {
        if (this.totalCash < price) {
            return false;
        } else {
            this.totalCash -= price;
            if (this.fund >= price) {
                this.fund -= price;
            } else {
                price -= fund;
                this.fund = 0;
                this.credit -= price;
            }
            return true;
        }
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "fund=" + fund + "$" +
                ", credit=" + credit + "$" +
                ", totalCash=" + totalCash + "$" +
                '}';
    }
}
