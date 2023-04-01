import java.util.ArrayList;
import java.util.Map;

public class Admin extends Account{
    private String emailAddress;
    private ArrayList<Request> pendingRequests = new ArrayList<Request>();
    private ArrayList<Request> workHistory = new ArrayList<Request>();

    public Admin(String username, String password, String emailAddress) {
        super(username, password);
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public ArrayList<Request> getPendingRequests() {
        return pendingRequests;
    }

    public void setPendingRequests(ArrayList<Request> pendingRequests) {
        this.pendingRequests = pendingRequests;
    }

    public ArrayList<Request> getWorkHistory() {
        return workHistory;
    }

    public void setWorkHistory(ArrayList<Request> workHistory) {
        this.workHistory = workHistory;
    }

    public void addNewAdmin(Shop shop, String username, String password, String emailAddress){
        shop.addNewAdmin(username, password, emailAddress);
    }

    public void acceptSeller(AuthorizationRequest request){
        request.getSeller().setAuthorization(true);
        this.pendingRequests.remove(request);
        this.workHistory.add(request);
    }

    public void rejectSeller(AuthorizationRequest request){
        request.getSeller().setAuthorization(false);
        this.pendingRequests.remove(request);
        this.workHistory.add(request);
    }

    public void giveFundToUser(Request fundRequest){
        ((FundRequest) fundRequest).getRequester().getWallet().addFund(((FundRequest) fundRequest).getRequestedFund());
        fundRequest.setSubmitted(true);
        ((FundRequest) fundRequest).getRequester().getAdminsResponse(fundRequest);
        this.pendingRequests.remove(fundRequest);
        this.workHistory.add(fundRequest);
    }

    public void giveFundToUser(User user, double fund){
        user.getWallet().addFund(fund);
    }

    public void submitOrder(Shop shop, Request order){
        if ((((Order) order).getBuyer().getWallet().getTotalCash() >= ((Order) order).getTotalPrice())){
            // reduce users wallet credit
            ((Order) order).getBuyer().getWallet().pay(((Order) order).getTotalPrice());
            // increase shop profit
            double oldProfitOfShop = shop.getTotalProfit();
            shop.setTotalProfit(oldProfitOfShop += (((Order) order).getTotalPrice()) * 0.1);
            // increase seller profit
            for (Map.Entry<Product, Integer> entry: ((Order) order).getListOfProducts().entrySet()){
                entry.getKey().getSeller().getWallet().chargeWallet(entry.getKey().getPrice() * 0.9);
            }
            order.setSubmitted(true);
        }
        ((Order) order).getBuyer().getAdminsResponse(order);
        this.pendingRequests.remove(order);
        this.workHistory.add(order);
    }

    public void viewProfilePhoto(User user){}
}
