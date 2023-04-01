import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User extends Account{
    private String emailAddress;
    private String phoneNumber;
    private Address address;
    private String profileScreen;
    private Wallet wallet;
    private Map<Product, Integer> shoppingCart = new HashMap<>();
    private ArrayList<Request> finishedRequests = new ArrayList<Request>();
    private ArrayList<Request> inProcessRequests = new ArrayList<Request>();
    private ArrayList<Request> rejectedRequests = new ArrayList<Request>();

    public User(String username, String password, String emailAddress, String phoneNumber, Address address, String profileScreen, Wallet wallet) {
        super(username, password);
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.profileScreen = profileScreen;
        this.wallet = wallet;
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

    public String getProfileScreen() {
        return profileScreen;
    }

    public void setProfileScreen(String profileScreen) {
        this.profileScreen = profileScreen;
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

    public boolean editPhoneNumber(String newPhoneNumber){
        if (newPhoneNumber.equals(this.phoneNumber)){
            return false;
        } else {
            setPhoneNumber(newPhoneNumber);
            return true;
        }
    }

    public boolean editAddress(Address newAddress){
        if (newAddress.equals(this.address)){
            return false;
        } else {
            setAddress(newAddress);
            return true;
        }
    }


    // todo
    public boolean editProfileScreen(){
        return false;
    }

    // shopping cart related methods
    public void addProductToCart(Product product, int numOfProduct){
        this.shoppingCart.put(product, numOfProduct);
    }

    public boolean removeItemFromShoppingCart(Product product){
        if (this.shoppingCart.containsKey(product)){
            this.shoppingCart.remove(product);
            return true;
        } else { return false; }
    }

    public boolean editNumOfProduct(int newNumber, Product product){
        if (this.shoppingCart.containsKey(product)){
            int oldNumber = this.shoppingCart.get(product);
            if (newNumber == 0){
                removeItemFromShoppingCart(product);
                return true;
            }
            if (oldNumber > newNumber){
                this.shoppingCart.replace(product, oldNumber, newNumber);
                return true;
            } else if (oldNumber < newNumber){
                int quantity = product.getQuantity();
                if (quantity >= newNumber){
                    this.shoppingCart.replace(product, oldNumber, newNumber);
                    return true;
                } else { return false;}
            } else { return true;}
        } else { return false;}
    }

    // other methods
    public void leaveComment(Product product, String comment){
        product.getComments().add(comment + "\\'based " + this.getUsername() + " opinion.\\'");
    }

    public void sendFundRequest(Shop shop, double requestedFund){
        shop.makeFundRequest(requestedFund, this);
    }

    public void confirmOrder(Shop shop){
        shop.makeOrder(this);
    }

    public void getAdminsResponse(Request checkedRequest){
        if (checkedRequest.isSubmitted()){
            this.finishedRequests.add(checkedRequest);
        } else {
            this.rejectedRequests.add(checkedRequest);
        }
    }

    public void chargeWallet(double credit){
        this.wallet.chargeWallet(credit);
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
