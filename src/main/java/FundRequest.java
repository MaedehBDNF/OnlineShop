public class FundRequest extends Request{
    private double requestedFund;

    public FundRequest(double requestedFund) {
        super();
        this.requestedFund = requestedFund;
    }

    public double getRequestedFund() {
        return requestedFund;
    }

    public void setRequestedFund(double requestedFund) {
        this.requestedFund = requestedFund;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", requester=" + requester.getUsername() +
                ", requestedFund= " + requestedFund + " dollars.";
    }
}
