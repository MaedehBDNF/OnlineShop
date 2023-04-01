public class Test {
    public static void main(String[] args){
        Shop myShop = new Shop("EasyShopping", "www.EasyShopping.com", "09129998877");
        Seller seller1 = new Seller("Ahmadi", "Ahmadi24@098", "AhmadiShop");
        myShop.addNewTV("TV Sx770", 27000000, seller1, 5, "ELECTRONICS",22.5, 1000);
        myShop.addNewCar("mazda3", 1000000000, seller1, 3, "CAR", "mazda", "automatic", "white", 5, 4);

        // test increase number of product method
        myShop.increaseNumberOfProduct(1, myShop.getProducts().get(0));
        System.out.println("new quantity: " + myShop.getProducts().get(0).getQuantity());

        // test assign requests to admins
        Admin mainAdmin = new Admin("mainAdmin", "******", "admin@gmail.com");
        Admin admin2 = new Admin("Admin2", "@@@@@@@", "Admin2@gmail.com");
        Address defaultAddress = new Address("Tehran", "Tehran", "Daneshjo", 34);
        Wallet defaultWallet = new Wallet(0);
        myShop.getAdminsOfShop().add(mainAdmin);
        myShop.getAdminsOfShop().add(admin2);
        User user1 = new User("user1", "user@@", "user1@gmail.com", "09190897766", defaultAddress, defaultWallet);
        User user2 = new User("user2", "user89@@", "user12@gmail.com", "09190897755", defaultAddress, defaultWallet);
        myShop.makeFundRequest(100, user1);
        myShop.makeFundRequest(200, user1);
        myShop.makeFundRequest(250, user2);
        System.out.println(myShop.getPendingRequests());
        System.out.println("Requests of mainAdmin = " + mainAdmin.getPendingRequests());
        System.out.println("Requests of Admin2 = " + admin2.getPendingRequests());

        // test searches
        System.out.println(myShop.searchProductsByName("TV"));
        System.out.println(myShop.searchProductsByCategory("Car"));

        // charge wallet of user
        user1.chargeWallet(500);
        System.out.println(user1.getWallet());

        //make an order
        user1.addProductToCart(myShop.getProducts().get(0), 1);
        user1.addProductToCart(myShop.getProducts().get(1),6 );
        user1.confirmOrder(myShop);

        // admin manage requests
        mainAdmin.giveFundToUser(mainAdmin.getPendingRequests().get(0));
        System.out.println("Requests of mainAdmin = " + mainAdmin.getPendingRequests());
        System.out.println("workHistory of mainAdmin" + mainAdmin.getWorkHistory());
        System.out.println("Requests of Admin2 = " + admin2.getPendingRequests());
        admin2.submitOrder(myShop, admin2.getPendingRequests().get(1));
        System.out.println("workHistory of admin2" + admin2.getWorkHistory());
        System.out.println("finished requests of user1" + user1.getFinishedRequests());
        System.out.println(user1.getWallet());


    }


}

