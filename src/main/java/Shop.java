import java.util.ArrayList;

public class Shop {
    private String name;
    private String webAddress;
    private int supportPhone;
    private double totalProfit;
    private ArrayList<Account> accounts = new ArrayList<Account>();
    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<Request> pendingRequests = new ArrayList<Request>();
    private ArrayList<Order> orders = new ArrayList<Order>();
    private ArrayList<Admin> adminsOfShop = new ArrayList<Admin>();

    public Shop(String name, String webAddress, int supportPhone) {
        this.name = name;
        this.webAddress = webAddress;
        this.supportPhone = supportPhone;
        this.totalProfit = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public int getSupportPhone() {
        return supportPhone;
    }

    public void setSupportPhone(int supportPhone) {
        this.supportPhone = supportPhone;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Request> getPendingRequests() {
        return pendingRequests;
    }

    public void setPendingRequests(ArrayList<Request> pendingRequests) {
        this.pendingRequests = pendingRequests;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public ArrayList<Admin> getAdminsOfShop() {
        return adminsOfShop;
    }

    public void setAdminsOfShop(ArrayList<Admin> adminsOfShop) {
        this.adminsOfShop = adminsOfShop;
    }
}
