import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shop {
    private String name;
    private String webAddress;
    private String supportPhone;
    private double totalProfit;
    private ArrayList<Account> accounts = new ArrayList<Account>();
    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<Request> pendingRequests = new ArrayList<Request>();
    private ArrayList<Order> orders = new ArrayList<Order>();
    private ArrayList<Admin> adminsOfShop = new ArrayList<Admin>();
    private int index = 0; // this attribute is for assign requests to admins

    public Shop(String name, String webAddress, String supportPhone) {
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

    public String getSupportPhone() {
        return supportPhone;
    }

    public void setSupportPhone(String supportPhone) {
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

    public void addNewTV(String name, double price, Seller seller, int quantity, String category, double inches, int pixels){
        TV newProduct = new TV(name, price, seller, quantity, category, inches, pixels);
        products.add(newProduct);
    }

    public void addNewCoat(String name, double price, Seller seller, int quantity, String category, float size, int pockets){
        Coat newProduct = new Coat(name, price, seller, quantity, category, size, pockets);
        products.add(newProduct);
    }

    public void addNewBook(String name, double price, Seller seller, int quantity, String category, String author, int yearOfPublish, String isbn){
        Book newProduct = new Book(name, price, seller, quantity, category, author, yearOfPublish, isbn);
        products.add(newProduct);
    }

    public void addNewHummer(String name, double price, Seller seller, int quantity, String category, String commodity, double mass){
        Hummer newProduct = new Hummer(name, price, seller, quantity, category, commodity, mass);
        products.add(newProduct);
    }

    public void addNewSeat(String name, double price, Seller seller, int quantity, String category, String commodityOfStructure, String commodityOfSeat){
        Seat newProduct = new Seat(name, price, seller, quantity, category, commodityOfStructure, commodityOfSeat);
        products.add(newProduct);
    }

    public void addNewRing(String name, double price, Seller seller, int quantity, String category, double size, String commodity){
        Ring newProduct = new Ring(name, price, seller, quantity, category, size, commodity);
        products.add(newProduct);
    }

    public void addNewPot(String name, double price, Seller seller, int quantity, String category, String commodity, double volume){
        Pot newProduct = new Pot(name, price, seller, quantity, category, commodity, volume);
        products.add(newProduct);
    }

    public void addNewCar(String name, double price, Seller seller, int quantity, String category, String manufacture, String gear, String color, int numOfSeats, int numOfDoors){
        Car newProduct = new Car(name, price, seller, quantity, category, manufacture, gear, color, numOfSeats, numOfDoors);
        products.add(newProduct);
    }

    public void addNewPen(String name, double price, Seller seller, int quantity, String category, String manufacture, float thickness, String color){
        Pen newProduct = new Pen(name, price, seller, quantity, category, manufacture, thickness, color);
        products.add(newProduct);
    }

    public void addNewPuzzle(String name, double price, Seller seller, int quantity, String category, int numOfPieces){
        Puzzle newProduct = new Puzzle(name, price, seller, quantity, category, numOfPieces);
        products.add(newProduct);
    }

    public boolean increaseNumberOfProduct(int numberOfIncrease, Product product){
        if(this.products.contains(product)){
            int indexOfProduct = products.indexOf(product);
            product.setQuantity(product.getQuantity() + numberOfIncrease);
            products.set(indexOfProduct, product);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Product> searchProductsByName(String name){
        Pattern pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        boolean matcherFind;
        ArrayList<Product> foundProducts = new ArrayList<>();
        for (Product prod: products){
            matcher = pattern.matcher(prod.getName());
            matcherFind = matcher.find();
            if (matcherFind){
                foundProducts.add(prod);
            }
        }
        return foundProducts;
    }

    public ArrayList<Product> searchProductsByCategory(String category){
        ArrayList<Product> foundProducts = new ArrayList<>();
        for (Product prod: products){
            if (prod.getCategory().equalsIgnoreCase(category)){
                foundProducts.add(prod);
            }
        }
        return foundProducts;
    }

    public void assignRequestsToAdmins(){
        int numOfAdmins = adminsOfShop.size();

        if (pendingRequests.size() != 0) {
           adminsOfShop.get((index - 1) % numOfAdmins).getPendingRequests().add(pendingRequests.get(0));
           pendingRequests.remove(0);
        }
    }

    public boolean makeOrder(User buyer){
        if (!buyer.getShoppingCart().isEmpty()){
            Order order = new Order(buyer);
            this.pendingRequests.add(order);
            index++;
            buyer.getInProcessRequests().add(order);
            buyer.getShoppingCart().clear(); // clear user cart after register order
            assignRequestsToAdmins();
            return true;
        }
        return false;
    }

    public void makeFundRequest(double fund, User requester){
        FundRequest fundRequest = new FundRequest(fund, requester);
        this.pendingRequests.add(fundRequest);
        index++;
        requester.getInProcessRequests().add(fundRequest);
        assignRequestsToAdmins();
    }

    public void makeAuthorizationRequest(Seller seller){
        AuthorizationRequest authorizationRequest = new AuthorizationRequest(seller);
        this.pendingRequests.add(authorizationRequest);
        index++;
        assignRequestsToAdmins();
    }

    public void addNewAdmin(String username, String password, String emailAddress){
        Admin newAdmin = new Admin(username, password, emailAddress);
        this.adminsOfShop.add(newAdmin);
    }
}
