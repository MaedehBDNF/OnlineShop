import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

    public void acceptSeller(Shop shop, AuthorizationRequest request){
        request.getSeller().setAuthorization(true);
        this.pendingRequests.remove(request);
        this.workHistory.add(request);
        shop.updateAccount(request.getSeller());
        shop.updateAccount(this);
    }

    public void rejectSeller(Shop shop, AuthorizationRequest request){
        request.getSeller().setAuthorization(false);
        this.pendingRequests.remove(request);
        this.workHistory.add(request);
        shop.updateAccount(request.getSeller());
        shop.updateAccount(this);
    }

    public void giveFundToUser(Shop shop, Request fundRequest){
        ((FundRequest) fundRequest).getRequester().getWallet().addFund(((FundRequest) fundRequest).getRequestedFund());
        fundRequest.setSubmitted(true);
        ((FundRequest) fundRequest).getRequester().getAdminsResponse(fundRequest);
        this.pendingRequests.remove(fundRequest);
        this.workHistory.add(fundRequest);
        shop.updateAccount(((FundRequest) fundRequest).getRequester());
        shop.updateAccount(this);
    }

    public void giveFundToUser(Shop shop, User user, double fund){
        user.getWallet().addFund(fund);
        shop.updateAccount(user);
        shop.updateAccount(this);
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
            shop.getOrders().add((Order) order);
        }
        ((Order) order).getBuyer().getAdminsResponse(order);
        this.pendingRequests.remove(order);
        this.workHistory.add(order);
        shop.updateAccount(((Order) order).getBuyer());
        shop.updateAccount(this);
    }


    // body of this method is copied form https://www.delftstack.com/howto/java/display-an-image-in-java/
    public void viewProfilePhoto(User user) throws IOException {
        File file = new File(user.getProfileScreen());
        BufferedImage bufferedImage = ImageIO.read(file);

        ImageIcon imageIcon = new ImageIcon(bufferedImage);
        JFrame jFrame = new JFrame();

        jFrame.setLayout(new FlowLayout());

        jFrame.setSize(500, 500);
        JLabel jLabel = new JLabel();

        jLabel.setIcon(imageIcon);
        jFrame.add(jLabel);
        jFrame.setVisible(true);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
