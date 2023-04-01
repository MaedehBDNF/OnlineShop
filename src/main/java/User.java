import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User extends Account{
    private String emailAddress;
    private String phoneNumber;
    private Address address;
    // private profileScreen;
    private Wallet wallet;
    private Map<Product, Integer> shoppingCart = new HashMap<>();
    private ArrayList<Request> finishedRequests = new ArrayList<Request>();
    private ArrayList<Request> inProcessRequests = new ArrayList<Request>();
    private ArrayList<Request> rejectedRequests = new ArrayList<Request>();

    public User(String username, String password, String emailAddress, String phoneNumber, Address address) {
        super(username, password);
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Map<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(Map<Product, Integer> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public ArrayList<Request> getFinishedRequests() {
        return finishedRequests;
    }

    public void setFinishedRequests(ArrayList<Request> finishedRequests) {
        this.finishedRequests = finishedRequests;
    }

    public ArrayList<Request> getInProcessRequests() {
        return inProcessRequests;
    }

    public void setInProcessRequests(ArrayList<Request> inProcessRequests) {
        this.inProcessRequests = inProcessRequests;
    }

    public ArrayList<Request> getRejectedRequests() {
        return rejectedRequests;
    }

    public void setRejectedRequests(ArrayList<Request> rejectedRequests) {
        this.rejectedRequests = rejectedRequests;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return "User{" +
                "emailAddress='" + emailAddress + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", address=" + address +
                ", wallet=" + wallet +
                '}';
    }
}
