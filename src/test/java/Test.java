import java.io.IOException;
import java.util.Scanner;

public class Test {
    private static Scanner in;
    public static void main(String[] args) throws IOException {
        in = new Scanner(System.in);

        Shop myShop = new Shop("EasyShopping", "www.EasyShopping.com", "09129998877");
        myShop.sellerSignUp("Ahmadi", "Ahmadi24@098", "AhmadiShop");
        Seller seller1 = (Seller) myShop.getAccounts().get(0);
        myShop.addNewTV("TV Sx770", 27000000, seller1, 5, "ELECTRONICS",22.5, 1000);
        myShop.addNewCar("mazda3", 1000000000, seller1, 3, "VEHICLE", "mazda", "automatic", "white", 5, 4);

        // test increase number of product method
        myShop.increaseNumberOfProduct(1, myShop.getProducts().get(0));
        System.out.println("new quantity: " + myShop.getProducts().get(0).getQuantity());

        // test assign requests to admins
        myShop.adminSignUp("mainAdmin", "******", "admin@gmail.com");
        Admin mainAdmin = (Admin) myShop.getAccounts().get(1);
        myShop.adminSignUp("Admin2", "@@@@@@@", "Admin2@gmail.com");
        Admin admin2 = (Admin) myShop.getAccounts().get(2);
        Address defaultAddress = new Address("Tehran", "Tehran", "Daneshjo", 34);
        Wallet defaultWallet = new Wallet(0);
        String defaultProfileScreen = "H:\\Uni\\Semesters\\Semester 8\\2- Advanced Programming\\Exercises\\Assignments\\Nourouz Project\\OnlineShop\\defaultProfileScreen.jpg";
        myShop.userSignUp("user1", "user@@", "user1@gmail.com", "09190897766", defaultAddress, defaultProfileScreen, defaultWallet);
        User user1 = (User) myShop.getAccounts().get(3);
        myShop.userSignUp("user2", "user89@@", "user12@gmail.com", "09190897755", defaultAddress, defaultProfileScreen, defaultWallet);
        User user2 = (User) myShop.getAccounts().get(4);
        myShop.makeFundRequest(100, user1);
        myShop.makeFundRequest(200, user1);
        myShop.makeFundRequest(250, user2);
        System.out.println(myShop.getPendingRequests());
        System.out.println("Requests of mainAdmin = " + mainAdmin.getPendingRequests());
        System.out.println("Requests of Admin2 = " + admin2.getPendingRequests());

        // test searches
        System.out.println("tv search: " + myShop.searchProductsByName("TV"));
        System.out.println("category search: " + myShop.searchProductsByCategory("vehicle"));

        // charge wallet of user
        user1.chargeWallet(500);
        System.out.println("charge wallet: " + user1.getWallet());

        //make an order
        user1.addProductToCart(myShop, myShop.getProducts().get(0), 1);
        user1.addProductToCart(myShop, myShop.getProducts().get(1),6 );
        myShop.makeOrder(user1);

        // admin manage requests
        mainAdmin.giveFundToUser(myShop, mainAdmin.getPendingRequests().get(0));
        System.out.println("Requests of mainAdmin = " + mainAdmin.getPendingRequests());
        System.out.println("workHistory of mainAdmin" + mainAdmin.getWorkHistory());
        System.out.println("Requests of Admin2 = " + admin2.getPendingRequests());
        admin2.submitOrder(myShop, admin2.getPendingRequests().get(1));
        System.out.println("workHistory of admin2" + admin2.getWorkHistory());
        System.out.println("finished requests of user1" + user1.getFinishedRequests());
        System.out.println(user1.getWallet());

        // view profilePhoto
        mainAdmin.viewProfilePhoto(user1);
    }
}

