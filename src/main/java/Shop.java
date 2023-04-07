import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shop {
    private Account currentUser;
    private String name;
    private String webAddress;
    private String supportPhone;
    private double totalProfit;
    private ArrayList<Account> accounts = new ArrayList<Account>();
    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<Request> pendingRequests = new ArrayList<Request>();
    private ArrayList<Order> orders = new ArrayList<Order>();
    private ArrayList<Admin> adminsOfShop = new ArrayList<Admin>();
    private int indexOfLastAdminGotJob = 0; // this attribute is for assign requests to admins

    public Shop(String name, String webAddress, String supportPhone) {
        this.name = name;
        this.webAddress = webAddress;
        this.supportPhone = supportPhone;
        this.totalProfit = 0;
    }

    public Account getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
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

    public Account findAccount(String username) {
        for (Account account: this.accounts) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }

    private int findIndexOfAccount(UUID id){
        for (int i = 0; i < this.accounts.size(); i++){
            if (this.accounts.get(i).getId().equals(id)){
                return i;
            }
        }
        return -1;
    }

    // this method exists because I didn't use database. It calls when every changes happen.
    public void updateAccount(Account updatedAccount) {
        int index = findIndexOfAccount(updatedAccount.getId());
        this.accounts.set(index, updatedAccount);
    }

    public void adminSignUp(String username, String password, String emailAddress){
        this.currentUser = new Admin(username, password, emailAddress);
        this.accounts.add(this.currentUser);
        this.adminsOfShop.add((Admin) this.currentUser);
    }

    public void sellerSignUp(String username, String password, String companyName){
        this.currentUser = new Seller(username, password, companyName);
        this.accounts.add(this.currentUser);
    }

    public void userSignUp(String username, String password, String emailAddress, String phoneNumber, Address address, String profileScreen, Wallet wallet){
        this.currentUser = new User(username, password, emailAddress, phoneNumber, address, profileScreen, wallet);
        this.accounts.add(this.currentUser);
    }

    public boolean login(String username, String password) {
        Account foundAccount = findAccount(username);
        if (foundAccount.getPassword().equals(password)) {
            this.currentUser = foundAccount;
            return true;
        }
        return false;
    }

    public void logout() {
        this.currentUser = null;
    }

    public void addNewTV(String name, double price, Seller seller, int quantity, String category, double inches, int pixels){
        TV newProduct = new TV(name, price, seller, quantity, category, inches, pixels);
        products.add(newProduct);
    }

    public void addNewCoat(String name, double price, Seller seller, int quantity, String category, double size, int pockets){
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
           adminsOfShop.get((indexOfLastAdminGotJob - 1) % numOfAdmins).getPendingRequests().add(pendingRequests.get(0));
           pendingRequests.remove(0);
        }
    }

    public Order makeOrder(User buyer){
        if (!buyer.getShoppingCart().isEmpty()){
            Order order = new Order(buyer);
            this.pendingRequests.add(order);
            indexOfLastAdminGotJob++;
            buyer.getInProcessRequests().add(order);
            buyer.getShoppingCart().clear(); // clear user cart after register order
            updateAccount(buyer);
            assignRequestsToAdmins();
            return order;
        }
        return null;
    }

    public FundRequest makeFundRequest(double fund, User requester){
        FundRequest fundRequest = new FundRequest(fund, requester);
        this.pendingRequests.add(fundRequest);
        indexOfLastAdminGotJob++;
        requester.getInProcessRequests().add(fundRequest);
        updateAccount(requester);
        assignRequestsToAdmins();
        return fundRequest;
    }

    public AuthorizationRequest makeAuthorizationRequest(Seller seller){
        AuthorizationRequest authorizationRequest = new AuthorizationRequest(seller);
        this.pendingRequests.add(authorizationRequest);
        indexOfLastAdminGotJob++;
        updateAccount(seller);
        assignRequestsToAdmins();
        return authorizationRequest;
    }

    public void updateProduct(Product updatedProduct){
        int index;
        for (int i =0; i < products.size(); i++) {
            if (products.get(i).getUuid().equals(updatedProduct.getUuid())) {
                index = i;
                this.products.set(index, updatedProduct);
            }
        }
    }

    public void addNewAdmin(String username, String password, String emailAddress){
        Admin newAdmin = new Admin(username, password, emailAddress);
        this.adminsOfShop.add(newAdmin);
    }
}
