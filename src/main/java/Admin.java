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
        this.workHistory.add(request);
    }

    public void rejectSeller(AuthorizationRequest request){
        request.getSeller().setAuthorization(false);
        this.workHistory.add(request);
    }

    public void giveFundToUser(FundRequest request){
        request.getRequester().getWallet().addFund(request.getRequestedFund());
        request.setSubmitted(true);
        request.getRequester().getAdminsResponse(request);
        this.workHistory.add(request);
    }

    public void giveFundToUser(User user, double fund){
        user.getWallet().addFund(fund);
    }

    public void submitOrder(Shop shop, Order order){
        if (order.getBuyer().getWallet().getTotalCash() >= order.getTotalPrice()){
            // reduce users wallet credit
            order.getBuyer().getWallet().pay(order.getTotalPrice());
            // increase shop profit
            double oldProfitOfShop = shop.getTotalProfit();
            shop.setTotalProfit(oldProfitOfShop += (order.getTotalPrice()) * 0.1);
            // increase seller profit
            for (Map.Entry<Product, Integer> entry: order.getListOfProducts().entrySet()){
                entry.getKey().getSeller().getWallet().chargeWallet(entry.getKey().getPrice() * 0.9);
            }
            order.setSubmitted(true);
        }
        order.getBuyer().getAdminsResponse(order);
        this.workHistory.add(order);
    }

    public void viewProfilePhoto(User user){}
}
