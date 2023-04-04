public class FundRequest extends Request{
    private double requestedFund;
    private User requester;

    public FundRequest(double requestedFund, User requester) {
        super();
        this.requestedFund = requestedFund;
        this.requester = requester;
    }

    public double getRequestedFund() {
        return requestedFund;
    }

    public void setRequestedFund(double requestedFund) {
        this.requestedFund = requestedFund;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", requester=" + requester.getUsername() + "\n" +
                ", requestedFund= " + requestedFund + " dollars.";
    }
}
