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

}
