public class Seller extends Account{
    private String companyName;
    private Wallet wallet;
    private boolean authorization;

    public Seller(String username, String password, String companyName) {
        super(username, password);
        this.companyName = companyName;
        this.authorization = false;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public boolean isAuthorization() {
        return authorization;
    }

    public boolean getAuthorization(){
        return this.authorization;
    }

    public void setAuthorization(boolean authorization) {
        this.authorization = authorization;
    }

    public AuthorizationRequest sendAuthorizationRequest(Shop shop){
        return shop.makeAuthorizationRequest(this);
    }

    public void addNewTV(Shop shop, String name, double price, int quantity, Seller seller, String category, double inches, int pixels){
        shop.addNewTV(name, price, seller, quantity, category, inches, pixels);
    }

    public void addNewCoat(Shop shop, String name, double price, int quantity, Seller seller, String category, double size, int pockets){
        shop.addNewCoat(name, price, seller, quantity, category, size, pockets);
    }

    public void addNewBook(Shop shop, String name, double price, Seller seller, int quantity, String category, String author, int yearOfPublish, String isbn){
        shop.addNewBook(name, price, seller, quantity, category, author, yearOfPublish, isbn);
    }

    public void addNewHummer(Shop shop, String name, double price, Seller seller, int quantity, String category, String commodity, double mass){
        shop.addNewHummer(name, price, seller, quantity, category, commodity, mass);
    }

    public void addNewSeat(Shop shop, String name, double price, Seller seller, int quantity, String category, String commodityOfStructure, String commodityOfSeat){
        shop.addNewSeat(name, price, seller, quantity, category, commodityOfStructure, commodityOfSeat);
    }

    public void addNewRing(Shop shop, String name, double price, Seller seller, int quantity, String category, double size, String commodity){
        shop.addNewRing(name, price, seller, quantity, category, size, commodity);
    }

    public void addNewPot(Shop shop, String name, double price, Seller seller, int quantity, String category, String commodity, double volume){
        shop.addNewPot(name, price, seller, quantity, category, commodity, volume);
    }

    public void addNewCar(Shop shop, String name, double price, Seller seller, int quantity, String category, String manufacture, String gear, String color, int numOfSeats, int numOfDoors){
        shop.addNewCar(name, price, seller, quantity, category, manufacture, gear, color, numOfSeats, numOfDoors);
    }

    public void addNewPen(Shop shop, String name, double price, Seller seller, int quantity, String category, String manufacture, float thickness, String color){
        shop.addNewPen(name, price, seller, quantity, category, manufacture, thickness, color);
    }

    public void addNewPuzzle(Shop shop, String name, double price, Seller seller, int quantity, String category, int numOfPieces){
        shop.addNewPuzzle(name, price, seller, quantity, category, numOfPieces);
    }

    public boolean increaseNumberOfProduct(Shop shop, int numberOfIncrease, Product product){
        return shop.increaseNumberOfProduct(numberOfIncrease, product);
    }

    @Override
    public String toString() {
        return "Seller ='" + companyName + '\'';
    }
}
