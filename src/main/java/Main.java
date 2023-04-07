import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;

public class Main {
    private static Shop mainShop = new Shop("OnlineShop", "www.OnlineShop.ir","+989121234455");
    private static Scanner in;

    public static void main(String[] args){
        in = new Scanner(System.in);

        //add little data for test
        mainShop.adminSignUp("mainAdmin", "******", "admin@gmail.com");

        Address defaultAddress = new Address("Tehran", "Tehran", "Daneshjo", 34);
        Wallet defaultWallet = new Wallet(0);
        String defaultProfileScreen = "H:\\Uni\\Semesters\\Semester 8\\2- Advanced Programming\\Exercises\\Assignments\\Nourouz Project\\OnlineShop\\defaultProfileScreen.jpg";
        mainShop.userSignUp("default user", "987654321", "defaultuser@gmail.com","09123332211", defaultAddress, defaultProfileScreen, defaultWallet);

        mainShop.sellerSignUp("Ahmadi", "Ahmadi24@098", "AhmadiShop");
        Seller seller1 = (Seller) mainShop.getAccounts().get(2);

        mainShop.addNewTV("TV Sx770", 27000000, seller1, 5, "ELECTRONICS",22.5, 1000);
        mainShop.addNewCoat("Duffle Coat", 980.54, seller1, 4, "CLOTHES", 34, 2);
        mainShop.addNewBook("One Hundred Years of Solitude", 100, seller1, 10, "BOOKS", "Gabriel Garcia Marquez", 1967, "978-600-94421-1-9");
        mainShop.addNewHummer("Mini claw hand tool hummer", 550, seller1, 8, "TOOLS", "iron", 450);
        mainShop.addNewSeat("Office Chair", 2000000, seller1, 50, "FURNITURE", "steal", "leather");
        mainShop.addNewRing("engagement ring", 5000000, seller1, 7, "JEWEL", 3, "gold");
        mainShop.addNewPot("kitchen pot", 400000, seller1, 10, "KITCHEN_UTENSILS", "cast iron", 4.5);
        mainShop.addNewCar("mazda3", 1000000000, seller1, 3, "VEHICLE", "mazda", "automatic", "white", 5, 4);
        mainShop.addNewPen("wave Rollerball pen", 5000, seller1, 100, "STATIONERY", "Canco", 0.7, "Blue");
        mainShop.addNewPuzzle("jigsaw puzzle", 100000, seller1, 50, "TOYS", 1000);

        startMenu();
    }

    private static void startMenu(){
        System.out.println("Welcome to our OnlineShop! \n" +
                "   1. LogIn\n" +
                "   2. SignUp\n" +
                "   3. Exit ");
        try {
            short choice = in.nextShort();
            in.nextLine();
            switch (choice){
                case 1:
                    loginMenu();
                    break;
                case 2:
                    signUpMenu();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Enter a number in range 1 - 3 ");
                    startMenu();
        }
        } catch (Exception e) {
            in.nextLine();
            System.out.println("You just entered wrong entry. Please try again.");
            startMenu();
        }
    }

    private static void loginMenu(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            startMenu();
        }
        System.out.print("Please Enter your username: ");
        String username = in.nextLine();
        System.out.print("Please Enter your password: ");
        String password = in.nextLine();
        if (mainShop.findAccount(username) != null){
            if (mainShop.login(username, password)){
                System.out.println("You log in Successfully!");
                userMenu();
            } else {
                System.out.println("Sorry your password is wrong. Please try again.");
                loginMenu();
            }
        } else {
            System.out.println("Sorry. The username doesn't exist. Try again.");
            loginMenu();
        }
    }

    private static void signUpMenu(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            startMenu();
        }
        System.out.println("Select one of below: \n" +
                " 1. SignUp as costumer \n" +
                " 2. SignUp as seller \n" +
                " 3. SignUp as Admin ");
        try {
            short choice = in.nextShort();
            in.nextLine();
            switch (choice){
                case 1:
                    userSignUpMenu();
                    break;
                case 2:
                    sellerSignUpMenu();
                    break;
                case 3:
                    adminSignUpMenu();
                    break;
                default:
                    System.out.println("Enter number in range 1 - 3");
                    signUpMenu();
            }
        } catch (Exception e){
            in.nextLine();
            System.out.println("You just entered wrong entry. Please try again.");
            signUpMenu();
        }
    }

    private static void userSignUpMenu(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            signUpMenu();
        }

        String username = getUsername();
        String password = getPassword();
        String email = getEmailAddress();
        String phoneNumber = getPhoneNumber();
        Address address = getAddress();
        String profileScreen = getProfilePhoto();
        Wallet wallet = getWallet();

        mainShop.userSignUp(username, password, email, phoneNumber, address, profileScreen, wallet);
        System.out.println("You signed up successfully!");
        userMenu();
    }

    private static void sellerSignUpMenu(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            signUpMenu();
        }

        String username = getUsername();
        String password = getPassword();
        String companyName = getCompanyName();

        mainShop.sellerSignUp(username, password, companyName);
        System.out.println("You signed up successfully!");
        sellerMenu();
    }

    private static void adminSignUpMenu(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            signUpMenu();
        }

        String username = getUsername();
        String password = getPassword();
        String email = getEmailAddress();

        mainShop.adminSignUp(username, password, email);
        System.out.println("You signed up successfully!");
        adminMenu();
    }

    private static void userMenu() {
        System.out.println("What do you want to do? \n" +
                "   1. Search products \n" +
                "       - add to shopping cart \n" +
                "       - leave comment \n" +
                "   2. Charge wallet \n" +
                "   3. View shopping cart \n" +
                "       - edit shopping cart \n" +
                "       - submit order \n" +
                "   4. Send fund request \n" +
                "   5. Request tracking \n" +
                "   6. View pending requests \n" +
                "   7. View successful requests\n" +
                "   8. View rejected Requests\n" +
                "   9. Edit information \n" +
                "   10. Logout");
        try{
            short choice = in.nextShort();
            in.nextLine();
            if (1 <= choice && choice <= 10) {
                switch (choice) {
                    case 1:
                        searchProductMenu();
                        break;
                    case 2:
                        chargeWallet();
                        break;
                    case 3:
                        viewShoppingCartMenu();
                        break;
                    case 4:
                        sendFundRequest();
                        break;
                    case 5:
                        requestTrackingForUser();
                        break;
                    case 6:
                        System.out.println("Pending requests:");
                        presentRequestsList(((User) mainShop.getCurrentUser()).getInProcessRequests());
                        userMenu();
                        break;
                    case 7:
                        System.out.println("Successful requests:");
                        presentRequestsList(((User) mainShop.getCurrentUser()).getFinishedRequests());
                        userMenu();
                        break;
                    case 8:
                        System.out.println("Rejected requests:");
                        presentRequestsList(((User) mainShop.getCurrentUser()).getRejectedRequests());
                        userMenu();
                        break;
                    case 9:
                        editInformationMenu();
                        break;
                    case 10:
                        mainShop.logout();
                        startMenu();
                        break;
                }
            } else {
                System.out.println("Enter a number in range 1 - 10");
                userMenu();
            }
        } catch (Exception e) {
            in.nextLine();
            System.out.println("You just entered wrong entry. Please try again.");
            userMenu();
        }
    }

    private static String getUsername(){
        System.out.print("Please enter your username: ");
        String username = in.nextLine();
        if (mainShop.findAccount(username) != null){
            System.out.println("This username is taken. Enter another one.");
            return getUsername();
        } else {
            return username;
        }
    }

    private static String getPassword(){
        System.out.print("Please enter your password: ");
        return in.nextLine();
    }

    private static String getEmailAddress(){
        System.out.println("Please enter your email: ");
        String email = in.nextLine();
        Pattern pattern = Pattern.compile("[\\w]+[@][g][m][a][i][l][.][c][o][m]|[\\w]+[@][y][a][h][o][o][.][c][o][m]");
        if (Pattern.matches(pattern.toString(),email)){
            return email;
        } else {
            System.out.println("Your email address is not valid!\n" +
                    "Notice that Only gmail and yahoo mail is acceptable.");
            return getEmailAddress();
        }
    }

    private static String getPhoneNumber(){
        System.out.println("Please enter your phone number: ");
        String phoneNumber = in.nextLine();
        Pattern pattern = Pattern.compile("[0][9][0-9]{9}");
        if (Pattern.matches(pattern.toString(), phoneNumber)){
            return phoneNumber;
        } else {
            System.out.println("wrong phone number! Try again.");
            return getPhoneNumber();
        }
    }

    private  static Address getAddress(){
        String province;
        String city;
        String street;
        String alley;
        String building;
        int floor;
        int unit;
        int no;
        System.out.println("Enter your address in this fields.\n" +
                "Fields that marked with * are necessary.\n" +
                "Just press enter for leave a field empty.");
        do {
            System.out.println("Province*: ");
            province = in.nextLine();
        } while (province.equals(""));

        do {
            System.out.println("City*: ");
            city = in.nextLine();
        } while (city.equals(""));

        do {
            System.out.println("Street*: ");
            street = in.nextLine();
        } while (street.equals(""));

        System.out.println("Alley: ");
        alley = in.nextLine();

        System.out.println("Building: ");
        building = in.nextLine();

        System.out.println("Floor: ");
        floor = in.nextInt();
        in.nextLine();

        System.out.println("Unit: ");
        unit = in.nextInt();
        in.nextLine();

        do {
            System.out.println("No*: ");
            no = in.nextInt();
            in.nextLine();
        } while (Integer.toString(no).equals(""));

        if (!alley.equals("") && building.equals("")){
            return new Address(province, city, street, alley, no);
        }

        if (alley.equals("") && building.equals("")){
            return new Address(province, city, street, no);
        }

        return new Address(province, city, street, alley, building, floor, unit, no);
    }

    // body of this method is copied from https://www.geeksforgeeks.org/java-swing-jfilechooser/
    private static String getProfilePhoto(){
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        // invoke the showsOpenDialog function to show the save dialog
        int r = j.showOpenDialog(null);

        // if the user selects a file
        if (r == JFileChooser.APPROVE_OPTION) {
            // set the label to the path of the selected file
            String pathOfProfilePhoto = j.getSelectedFile().getAbsolutePath();
            return pathOfProfilePhoto;
        } else { return null; }
    }

    private static Wallet getWallet() {
        double initialCash = 0;
        short choice = 0;
        System.out.println("Do you want to charge your wallet now?\n" +
                "1. Yes\n" +
                "2. No");

        try {
            choice = in.nextShort();
            in.nextLine();
        } catch (Exception e) {
            in.nextLine();
            System.out.println("Wrong entry. Please try again.");
            getWallet();
        }

        if (choice == 1) {
            System.out.println("amount: ");
            try {
                initialCash = in.nextDouble();
                in.nextLine();
            } catch (Exception e) {
                in.nextLine();
                System.out.println("Wrong entry. Please try again.");
                getWallet();
            }
            return new Wallet(initialCash);
        } else if (choice == 2) {
            return new Wallet(initialCash);
        } else {
            System.out.println("Enter 1 or 2.");
            getWallet();
        }
        return null;
    }

    private static String getCompanyName(){
        String companyName;
        do {
            System.out.println("Enter yor companyName or your store: ");
            companyName = in.nextLine();
        } while (companyName.equals(""));
        return companyName;
    }

    // case 1 of user menu
    private static void searchProductMenu(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            userMenu();
        }

        System.out.println("1. Search by category\n" +
                "2. Search by name of product");

        try {
            short choice = in.nextShort();
            in.nextLine();
            if (choice == 1){
                searchByCategoryMenu();
            } else if (choice == 2){
                searchByNameMenu();
            } else {
                System.out.println("Enter 1 or 2.");
                searchProductMenu();
            }
        } catch (Exception e){
            in.nextLine();
            System.out.println("You just entered wrong entry. Please try again.");
            searchProductMenu();
        }
    }

    private static void searchByCategoryMenu(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            searchProductMenu();
        }

        System.out.println("Available categories are:\n" +
                "1. ELECTRONICS includes TV,...\n" +
                "2. CLOTHES includes Coat,...\n" +
                "3. BOOKS\n" +
                "4. TOOLS includes Hummer,...\n" +
                "5. FURNITURE includes Seat,...\n" +
                "6. JEWEL includes Ring,...\n" +
                "7. KITCHEN_UTENSILS includes Pot,...\n" +
                "8. VEHICLE includes Car,...\n" +
                "9. STATIONERY includes Pen,..." +
                "10. TOYS includes Puzzle,...");

        System.out.println("Select one of above categories: ");
        try {
            short choice = in.nextShort();
            in.nextLine();
            switch (choice){
                case 1:
                    selectProduct(mainShop.searchProductsByCategory("ELECTRONICS"));
                case 2:
                    selectProduct(mainShop.searchProductsByCategory("CLOTHES"));
                case 3:
                    selectProduct(mainShop.searchProductsByCategory("BOOKS"));
                case 4:
                    selectProduct(mainShop.searchProductsByCategory("TOOLS"));
                case 5:
                    selectProduct(mainShop.searchProductsByCategory("FURNITURE"));
                case 6:
                    selectProduct(mainShop.searchProductsByCategory("JEWEL"));
                case 7:
                    selectProduct(mainShop.searchProductsByCategory("KITCHEN_UTENSILS"));
                case 8:
                    selectProduct(mainShop.searchProductsByCategory("VEHICLE"));
                case 9:
                    selectProduct(mainShop.searchProductsByCategory("STATIONERY"));
                case 10:
                    selectProduct(mainShop.searchProductsByCategory("TOYS"));
            }
        } catch (Exception e){
            in.nextLine();
            System.out.println("You just entered wrong entry. Please try again.");
            searchByCategoryMenu();
        }
    }

    private static void searchByNameMenu(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            searchProductMenu();
        }

        System.out.println("Enter the name of product: ");
        String name = in.nextLine();
        ArrayList<Product> foundProducts = mainShop.searchProductsByName(name);

        if (foundProducts.isEmpty()){
            System.out.println("Sorry this product doesn't exist!");
            searchByNameMenu();
        } else {
            selectProduct(foundProducts);
        }
    }

    private static void selectProduct(ArrayList<Product> products){
        System.out.println("\"Found Products\"\n");
        for (int i = 0; i < products.size(); i++){
            System.out.println(i + 1 + ") " + products.get(i) + "\n\n");
        }

        System.out.println("Select one of above products or 0 for back to menu. Just enter its number: ");
        try{
            int choice = in.nextInt();
            in.nextLine();
            if (choice == 0){
                userMenu();
            } else if (1 <= choice && choice <= products.size()){
                productMenu(products.get(choice - 1));
            } else {
                System.out.println("Number was not in range!");
                selectProduct(products);
            }
        } catch (Exception e){
            in.nextLine();
            System.out.println("You just entered wrong entry. Please try again.");
            selectProduct(products);
        }
    }

    private static void productMenu(Product product){
        System.out.println("Selected Product:\n" +
                product);
        System.out.println("What do you want to do?\n" +
                "1. Add this product to your shopping cart\n" +
                "2. Leave comment \n" +
                "0. Back to menu");
        try {
            short choice = in.nextShort();
            in.nextLine();
            if (choice == 0){
                userMenu();
            } else if (choice == 1) {
                ((User) mainShop.getCurrentUser()).addProductToCart(mainShop, product,1);
                System.out.println("Product just added to your cart successfully.");
            } else if (choice == 2) {
                System.out.println("Write your comment:");
                String comment = in.nextLine();
                ((User) mainShop.getCurrentUser()).leaveComment(mainShop, product, comment);
            } else {
                System.out.println("Enter a number in range 0 - 2");
                productMenu(product);
            }
        } catch (Exception e) {
            in.nextLine();
            System.out.println("You just entered wrong entry. Please try again.");
            productMenu(product);
        }
        userMenu();
    }

    // case 2 of user menu
    private static void chargeWallet(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            userMenu();
        }

        System.out.println("How much do you want to charge your wallet? ");
        try{
            double credit = in.nextDouble();
            in.nextLine();
            ((User) mainShop.getCurrentUser()).chargeWallet(credit);
        } catch (Exception e){
            in.nextLine();
            System.out.println("You just entered wrong entry. Please try again.");
            chargeWallet();
        }
        userMenu();
    }

    // case 3 of user menu
    private static void viewShoppingCartMenu(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            userMenu();
        }

        Map<Product, Integer> shoppingCart = ((User) mainShop.getCurrentUser()).getShoppingCart();

        System.out.println("Shopping Cart:");
        int counter = 1;
        for (Map.Entry<Product, Integer> entry: shoppingCart.entrySet()){
            System.out.println(counter + ") " + "Product: " + entry.getKey().getName() + "quantity: " + entry.getValue() + "\n");
            counter++;
        }

        System.out.println("What do you want to do?\n" +
                "1. edit shopping cart" +
                "2. submit this order");

        try {
            short choice = in.nextShort();
            in.nextLine();
            if (choice == 1){
                editShoppingCartMenu();
            } else if (choice == 2){
                Order order = mainShop.makeOrder((User) mainShop.getCurrentUser());
                if (order != null){
                    System.out.println("Your order confirmed successfully.\n" +
                            "Save this code for order tracking: " + order.getId());
                } else {
                    System.out.println("Your shopping cart was empty.");
                    userMenu();
                }
            } else {
                System.out.println("Enter 1 or 2");
            }
        } catch (Exception e){
            in.nextLine();
            System.out.println("You just entered wrong entry. Please try again.");
            viewShoppingCartMenu();
        }
    }

    private static Product selectProductInCart(){
        System.out.println("For edit cart first select that item.\n" +
                "Enter number of product in above list: ");

        int number = in.nextInt();

        Map<Product, Integer> shoppingCart = ((User) mainShop.getCurrentUser()).getShoppingCart();

        System.out.println("Shopping Cart:");
        int counter = 1;
        for (Map.Entry<Product, Integer> entry: shoppingCart.entrySet()){
            if (counter == number){
                return entry.getKey();
            }
            counter++;
        }
        return null;
    }

    private static void editShoppingCartMenu(){
        User currentUser = (User) mainShop.getCurrentUser();
        Product product = selectProductInCart();
        if (product != null){
            System.out.println("What do you want todo with this product?" +
                    "1. remove this item" +
                    "2. edit quantity of item");

            try{
                short choice = in.nextShort();
                in.nextLine();
                if (choice == 1){
                    currentUser.removeItemFromShoppingCart(mainShop,product);
                    System.out.println("Item deleted successfully.");
                } else if (choice == 2){
                    System.out.println("Enter new quantity: ");
                    int newQuantity = in.nextInt();
                    in.nextLine();
                    if (currentUser.editNumOfProduct(mainShop, newQuantity, product)){
                        System.out.println("Quantity edited successfully.");
                    } else {
                        System.out.println("There is no enough of this product in store.");
                        System.out.println("There is just "+ product.getQuantity()+" of this product.");
                        System.out.println("Try again if you want.");
                        editShoppingCartMenu();
                    }
                }
            } catch (Exception e) {
                in.nextLine();
                System.out.println("You just entered wrong entry. Please try again.");
                editShoppingCartMenu();
            }
        } else {
            System.out.println("Number was not in range. Please try again.");
            editShoppingCartMenu();
        }
        userMenu();
    }

    // case 4 of user menu
    private static void sendFundRequest(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            userMenu();
        }

        System.out.println("How much fund do you want?");
        try {
            double fund = in.nextDouble();
            in.nextLine();
            FundRequest fundRequest = mainShop.makeFundRequest(fund, (User) mainShop.getCurrentUser());
            System.out.println("Your request sent successfully.\n" +
                    "Save this code for request tracking: " + fundRequest.getId());

        } catch (Exception e){
            in.nextLine();
            System.out.println("You just entered wrong entry. Please try again.");
            sendFundRequest();
        }
        userMenu();
    }

    // case 5 of user menu
    private static void requestTrackingForUser(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            userMenu();
        }

        User currentUser = (User) mainShop.getCurrentUser();
        System.out.println("Enter the code of request: ");
        UUID id = UUID.fromString(in.nextLine());

        Request request;
        request = searchRequest(currentUser.getInProcessRequests(), id);
        if (request != null){
            System.out.println(request);
            System.out.println("This request doesn't checked yet." );
        }
        request = searchRequest(currentUser.getFinishedRequests(), id);
        if (request != null){
            System.out.println(request);
            System.out.println("This request has finished.");
        }
        request = searchRequest(currentUser.getRejectedRequests(), id);
        if (request != null){
            System.out.println(request);
            if (request.getClass().getSimpleName().equals("Order")){
                System.out.println("This request was rejected because your wallet didn't had enough money.");
            }
            if (request.getClass().getSimpleName().equals("FundRequest")){
                System.out.println("This request was rejected.");
            }
        }
        userMenu();
    }

    private static Request searchRequest(ArrayList<Request> listOfRequests, UUID id){
        for (Request req: listOfRequests){
            if (id.equals(req.getId())){
                return req;
            }
        }
        return null;
    }

    // case 6,7 and 8 of user menu
    private static void presentRequestsList(ArrayList<Request> requests){
        int counter = 1;
        for (Request request: requests){
            System.out.println(counter +") " + request);
            counter++;
        }
    }

    // case 9 of user menu
    private static void editInformationMenu(){
        System.out.println("What do you want to do? \n" +
                "   1. Edit username \n" +
                "   2. Edit password \n" +
                "   3. Edit email \n" +
                "   4. Edit phone number \n" +
                "   5. Edit Address \n" +
                "   6. Edit profile screen");

        try{
            short choice = in.nextShort();
            in.nextLine();
            if (1 <= choice && choice <= 6) {
                switch (choice) {
                    case 1:
                        editUsername();
                        break;
                    case 2:
                        editPassword();
                        break;
                    case 3:
                        editEmail();
                        break;
                    case 4:
                        editPhoneNumber();
                        break;
                    case 5:
                        editAddress();
                        break;
                    case 6:
                        editProfileScreen();
                        break;
                }
            } else {
                System.out.println("Enter a number in range 1 - 6");
                sellerMenu();
            }
        } catch (Exception e) {
            in.nextLine();
            System.out.println("You just entered wrong entry. Please try again.");
            sellerMenu();}
    }

    private static void editUsername(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            userMenu();
        }

        User user = (User) mainShop.getCurrentUser();
        String newUsername = getUsername();

        if (user.editUserName(newUsername)){
            System.out.println("Username updated successfully.");
        } else {
            System.out.println("New username was same as before!");
        }
        userMenu();
    }

    private static void editPassword(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            userMenu();
        }

        User user = (User) mainShop.getCurrentUser();
        String newPassword = getPassword();

        if (user.editPassword(newPassword)){
            System.out.println("Password updated successfully.");
        } else {
            System.out.println("New password was same as before!");
        }
        userMenu();
    }

    private static void editEmail(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            userMenu();
        }

        User user = (User) mainShop.getCurrentUser();
        String newEmail = getEmailAddress();

        if (user.editEmailAddress(newEmail)){
            System.out.println("Email address updated successfully.");
        } else {
            System.out.println("New Email address was same as before!");
        }
        userMenu();
    }

    private static void editPhoneNumber(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            userMenu();
        }

        User user = (User) mainShop.getCurrentUser();
        String newPhoneNumber = getPhoneNumber();

        if (user.editPhoneNumber(newPhoneNumber)){
            System.out.println("Phone number updated successfully.");
        } else {
            System.out.println("New phone number was same as before!");
        }
        userMenu();
    }

    private static void editAddress(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            userMenu();
        }

        User user = (User) mainShop.getCurrentUser();
        Address newAddress = getAddress();

        if (user.editAddress(newAddress)){
            System.out.println("Address updated successfully.");
        } else {
            System.out.println("New Address was same as before!");
        }
        userMenu();
    }

    private static void editProfileScreen(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            userMenu();
        }

        User user = (User) mainShop.getCurrentUser();
        String newProfileScreen = getProfilePhoto();

        if (user.editProfileScreen(newProfileScreen)){
            System.out.println("Profile screen updated successfully.");
        } else {
            System.out.println("New profile screen was same as before!");
        }
        userMenu();
    }

    private static void sellerMenu() {
        System.out.println("What do you want to do? \n" +
                "   1. Send Authorization request  \n" +
                "   2. Request tracking \n" +
                "   3. Add a new product\n" +
                "   4. Search in your own products \n" +
                "       - increase quantity of one product \n" +
                "   5. View wallet \n" +
                "   6. Logout");

        try{
            short choice = in.nextShort();
            in.nextLine();
            if (1 <= choice && choice <= 6) {
                switch (choice) {
                    case 1:
                        sendAuthorizationRequest();
                        break;
                    case 2:
                        requestTrackingForSeller();
                        break;
                    case 3:
                        if (checkPermission()){
                            addNewProductMenu();
                        } else {
                            System.out.println("You should first get permission from an admin.");
                            sellerMenu();
                        }
                        break;
                    case 4:
                        if (checkPermission()){
                            searchSellersProducts();
                        } else {
                            System.out.println("You should first get permission from an admin.");
                            sellerMenu();
                        }
                        break;
                    case 5:
                        if (checkPermission()){
                            System.out.println(((Seller) mainShop.getCurrentUser()).getWallet());
                            sellerMenu();
                        } else {
                            System.out.println("You should first get permission from an admin.");
                            sellerMenu();
                        }
                        break;
                    case 6:
                        mainShop.logout();
                        startMenu();
                        break;
                }
            } else {
                System.out.println("Enter a number in range 1 - 6");
                sellerMenu();
            }
        } catch (Exception e) {
            in.nextLine();
            System.out.println("You just entered wrong entry. Please try again.");
            sellerMenu();}
    }

    private static boolean checkPermission(){
        return ((Seller) mainShop.getCurrentUser()).getAuthorization();
    }

    // case 1 of seller menu
    private static void sendAuthorizationRequest(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            sellerMenu();
        }

        Seller seller = (Seller) mainShop.getCurrentUser();
        AuthorizationRequest request = seller.sendAuthorizationRequest(mainShop);
        System.out.println("Your request just sent for "+ mainShop.getName() + ". Wait until an admin get permission to you.\n" +
                "Save this code for tracking. code: " + request.getId());
        sellerMenu();
    }

    // case 2 of seller menu
    private static void requestTrackingForSeller(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            sellerMenu();
        }

        System.out.println("Enter the code of request: ");
        UUID id = UUID.fromString(in.nextLine());

        for (Admin admin: mainShop.getAdminsOfShop()){
            for (Request req: admin.getPendingRequests()){
                if (req.getId().equals(id)){
                    System.out.println("Your request has not checked yet!");
                }
            }
        }

        for (Admin admin: mainShop.getAdminsOfShop()){
            for (Request req: admin.getWorkHistory()){
                if (req.getId().equals(id)){
                    if (req.isSubmitted()){
                        System.out.println("Congratulations! Your request was accepted. You are now one of our shop family.");
                    } else {
                        System.out.println("Sorry! Your request was not accepted.");
                    }
                }
            }
        }
        sellerMenu();
    }

    // case 3 of seller menu
    private static void addNewProductMenu(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            sellerMenu();
        }

        System.out.println("Available products to add are:\n" +
                "1. TV in electronics category \n" +
                "2. Coat in clothes category \n" +
                "3. Book in book category \n" +
                "4. Hummer in tools category \n" +
                "5. Seat in furniture category \n" +
                "6. Ring in jewel category \n" +
                "7. Pot in kitchen utensils category \n" +
                "8. Car in vehicle category \n" +
                "9. Pen in stationery category \n" +
                "10. puzzle in toys category");
        System.out.println("Enter the number of one of this products if you want: ");

        try{
            short choice = in.nextShort();
            in.nextLine();
            if (1 <= choice && choice <= 10) {
                switch (choice) {
                    case 1:
                        addNewTV();
                        break;
                    case 2:
                        addNewCoat();
                        break;
                    case 3:
                        addNewBook();
                        break;
                    case 4:
                        addNewHummer();
                        break;
                    case 5:
                        addNewSeat();
                        break;
                    case 6:
                        addNewRing();
                        break;
                    case 7:
                        addNewPot();
                        break;
                    case 8:
                        addNewCar();
                        break;
                    case 9:
                        addNewPen();
                        break;
                    case 10:
                        addNewPuzzle();
                        break;
                }
            } else {
                System.out.println("Enter a number in range 1 - 6");
                addNewProductMenu();
            }
        } catch (Exception e) {
            in.nextLine();
            System.out.println("You just entered wrong entry. Please try again.");
            addNewProductMenu();
        }
    }

    private static void addNewTV(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            addNewProductMenu();
        }

        String name = getNameOfProduct();
        double price = getPriceOfProduct();
        int quantity = getQuantityOfProduct();
        double inches = getInchesOfTV();
        int pixels = getPixelsOfTV();

        Seller seller = ((Seller) mainShop.getCurrentUser());
        seller.addNewTV(mainShop, name, price, quantity, seller, "ELECTRONICS", inches, pixels);
        System.out.println("Product just added successfully.");
        sellerMenu();
    }

    private static String getNameOfProduct(){
        System.out.println("Enter whole name of product: ");
        return in.nextLine();
    }

    private static double getPriceOfProduct(){
        try {
            System.out.println("Price: ");
            double price = in.nextDouble();
            in.nextLine();
            return price;
        } catch (Exception e) {
            in.nextLine();
            System.out.println("Wrong entry. Please try again.");
        }
        return getPriceOfProduct();
    }

    private static int getQuantityOfProduct(){
        try {
            System.out.println("Quantity: ");
            int quantity = in.nextInt();
            in.nextLine();
            return quantity;
        } catch (Exception e) {
            in.nextLine();
            System.out.println("Wrong entry. Please try again.");
        }
        return getQuantityOfProduct();
    }

    private static double getInchesOfTV(){
        try {
            System.out.println("Inches: ");
            double inches = in.nextDouble();
            in.nextLine();
            return inches;
        } catch (Exception e) {
            in.nextLine();
            System.out.println("Wrong entry. Please try again.");
        }
        return getInchesOfTV();
    }

    private static int getPixelsOfTV(){
        try {
            System.out.println("Pixels: ");
            int pixels = in.nextInt();
            in.nextLine();
            return pixels;
        } catch (Exception e) {
            in.nextLine();
            System.out.println("Wrong entry. Please try again.");
        }
        return getPixelsOfTV();
    }

    private static void addNewCoat(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            addNewProductMenu();
        }

        String name = getNameOfProduct();
        double price = getPriceOfProduct();
        int quantity = getQuantityOfProduct();
        double size = getSize();
        int pockets = getNumOfPocketsOfCoat();

        Seller seller = ((Seller) mainShop.getCurrentUser());
        seller.addNewCoat(mainShop, name, price, quantity, seller, "CLOTHES", size, pockets);
        System.out.println("Product just added successfully.");
        sellerMenu();
    }

    private static double getSize(){
        try {
            System.out.println("Size: ");
            double size = in.nextDouble();
            in.nextLine();
            return size;
        } catch (Exception e) {
            in.nextLine();
            System.out.println("Wrong entry. Please try again.");
        }
        return getSize();
    }

    private static int getNumOfPocketsOfCoat(){
        try {
            System.out.println("Number of pockets: ");
            int pockets = in.nextInt();
            in.nextLine();
            return pockets;
        } catch (Exception e) {
            in.nextLine();
            System.out.println("Wrong entry. Please try again.");
        }
        return getNumOfPocketsOfCoat();
    }

    private static void addNewBook(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            addNewProductMenu();
        }

        String name = getNameOfProduct();
        double price = getPriceOfProduct();
        int quantity = getQuantityOfProduct();
        System.out.println("Author: ");
        String author = in.nextLine();
        int publishYear = getPublishYearOfBook();
        String isbn = getISBNOfBook();

        Seller seller = ((Seller) mainShop.getCurrentUser());
        seller.addNewBook(mainShop, name, price, seller, quantity, "BOOKS", author, publishYear, isbn);
        System.out.println("Product just added successfully.");
        sellerMenu();
    }

    private static int getPublishYearOfBook(){
        try {
            System.out.println("Year of Publication: ");
            int publishYear = in.nextInt();
            in.nextLine();
            return publishYear;
        } catch (Exception e) {
            in.nextLine();
            System.out.println("Wrong entry. Please try again.");
        }
        return getPublishYearOfBook();
    }

    private static String getISBNOfBook(){
        System.out.println("ISBN: ");
        String isbn = in.nextLine();
        Pattern pattern = Pattern.compile("[0-9]{3}[-][0-9]{3}[-][0-9]{5}[-][0-9][-][0-9]");
        if (Pattern.matches(pattern.toString(), isbn)){
            return isbn;
        } else {
            System.out.println("Wrong entry! Try again.");
            return getISBNOfBook();
        }
    }

    private static void addNewHummer(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            addNewProductMenu();
        }

        String name = getNameOfProduct();
        double price = getPriceOfProduct();
        int quantity = getQuantityOfProduct();
        System.out.println("Commodity: ");
        String commodity = in.nextLine();
        double mass = getMassOfHummer();

        Seller seller = ((Seller) mainShop.getCurrentUser());
        seller.addNewHummer(mainShop, name, price, seller, quantity, "TOOLS", commodity, mass);
        System.out.println("Product just added successfully.");
        sellerMenu();
    }


    private static double getMassOfHummer(){
        try {
            System.out.println("Mass in grams: ");
            double mass = in.nextDouble();
            in.nextLine();
            return mass;
        } catch (Exception e) {
            in.nextLine();
            System.out.println("Wrong entry. Please try again.");
        }
        return getMassOfHummer();
    }

    private static void addNewSeat(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            addNewProductMenu();
        }

        String name = getNameOfProduct();
        double price = getPriceOfProduct();
        int quantity = getQuantityOfProduct();
        System.out.println("Commodity of structure: ");
        String commodityOfStructure = in.nextLine();
        System.out.println("Commodity of seat: ");
        String commodityOfSeat = in.nextLine();

        Seller seller = ((Seller) mainShop.getCurrentUser());
        seller.addNewSeat(mainShop, name, price, seller, quantity, "FURNITURE", commodityOfStructure, commodityOfSeat);
        System.out.println("Product just added successfully.");
        sellerMenu();
    }

    private static void addNewRing(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            addNewProductMenu();
        }

        String name = getNameOfProduct();
        double price = getPriceOfProduct();
        int quantity = getQuantityOfProduct();
        double size = getSize();
        System.out.println("Commodity: ");
        String commodity = in.nextLine();

        Seller seller = ((Seller) mainShop.getCurrentUser());
        seller.addNewRing(mainShop, name, price, seller, quantity, "JEWEL", size, commodity);
        System.out.println("Product just added successfully.");
        sellerMenu();
    }

    private static void addNewPot(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            addNewProductMenu();
        }

        String name = getNameOfProduct();
        double price = getPriceOfProduct();
        int quantity = getQuantityOfProduct();
        System.out.println("Commodity: ");
        String commodity = in.nextLine();
        double volume = getVolumeOfPot();

        Seller seller = ((Seller) mainShop.getCurrentUser());
        seller.addNewPot(mainShop, name, price, seller, quantity, "KITCHEN_UTENSILS", commodity, volume);
        System.out.println("Product just added successfully.");
        sellerMenu();
    }

    private static double getVolumeOfPot(){
        try {
            System.out.println("Volume in litre: ");
            double volume = in.nextDouble();
            in.nextLine();
            return volume;
        } catch (Exception e) {
            in.nextLine();
            System.out.println("Wrong entry. Please try again.");
        }
        return getVolumeOfPot();
    }

    private static void addNewCar(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            addNewProductMenu();
        }

        String name = getNameOfProduct();
        double price = getPriceOfProduct();
        int quantity = getQuantityOfProduct();
        System.out.println("Manufacture: ");
        String manufacture = in.nextLine();
        System.out.println("Type of gear: ");
        String gear = in.nextLine();
        System.out.println("Color:");
        String color = in.nextLine();
        int numOfSeats = getNumOfSeatsOfCar();
        int numOfDoors = getNumOfDoorsOfCar();

        Seller seller = ((Seller) mainShop.getCurrentUser());
        seller.addNewCar(mainShop, name, price, seller, quantity, "VEHICLE", manufacture, gear, color, numOfSeats ,numOfDoors);
        System.out.println("Product just added successfully.");
        sellerMenu();
    }

    private static int getNumOfSeatsOfCar(){
        try {
            System.out.println("Number of seats: ");
            int numOfSeats = in.nextInt();
            in.nextLine();
            return numOfSeats;
        } catch (Exception e) {
            in.nextLine();
            System.out.println("Wrong entry. Please try again.");
        }
        return getNumOfSeatsOfCar();
    }

    private static int getNumOfDoorsOfCar(){
        try {
            System.out.println("Number of doors: ");
            int numOfDoors = in.nextInt();
            in.nextLine();
            return numOfDoors;
        } catch (Exception e) {
            in.nextLine();
            System.out.println("Wrong entry. Please try again.");
        }
        return getNumOfDoorsOfCar();
    }

    private static void addNewPen(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            addNewProductMenu();
        }

        String name = getNameOfProduct();
        double price = getPriceOfProduct();
        int quantity = getQuantityOfProduct();
        System.out.println("Manufacture: ");
        String manufacture = in.nextLine();
        double thickness = getThicknessOfPen();
        System.out.println("Color:");
        String color = in.nextLine();

        Seller seller = ((Seller) mainShop.getCurrentUser());
        seller.addNewPen(mainShop, name, price, seller, quantity, "STATIONERY", manufacture, thickness, color);
        System.out.println("Product just added successfully.");
        sellerMenu();
    }

    private static double getThicknessOfPen(){
        try {
            System.out.println("Thickness: ");
            double thickness = in.nextDouble();
            in.nextLine();
            return thickness;
        } catch (Exception e) {
            in.nextLine();
            System.out.println("Wrong entry. Please try again.");
        }
        return getThicknessOfPen();
    }

    private static void addNewPuzzle(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            addNewProductMenu();
        }

        String name = getNameOfProduct();
        double price = getPriceOfProduct();
        int quantity = getQuantityOfProduct();
        int numOfPieces = getNumOfPiecesOfPuzzle();

        Seller seller = ((Seller) mainShop.getCurrentUser());
        seller.addNewPuzzle(mainShop, name, price, seller, quantity, "TOYS", numOfPieces);
        System.out.println("Product just added successfully.");
        sellerMenu();
    }

    private static int getNumOfPiecesOfPuzzle(){
        try {
            System.out.println("Number of pieces: ");
            int numOfPieces = in.nextInt();
            in.nextLine();
            return numOfPieces;
        } catch (Exception e) {
            in.nextLine();
            System.out.println("Wrong entry. Please try again.");
        }
        return getNumOfPiecesOfPuzzle();
    }

    // case 4 of seller menu
    private static void searchSellersProducts(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            sellerMenu();
        }

        Seller seller = (Seller) mainShop.getCurrentUser();
        ArrayList<Product> sellersProducts = mainShop.searchProductsBySeller(seller);
        selectProductForSeller(sellersProducts);
    }

    private static void selectProductForSeller(ArrayList<Product> products){
        System.out.println("\"Found Products\"\n");
        for (int i = 0; i < products.size(); i++){
            System.out.println(i + 1 + ") " + products.get(i) + "\n\n");
        }

        System.out.println("Select one of above products to increase its quantity or 0 for back to menu. Just enter its number: ");
        try{
            int choice = in.nextInt();
            in.nextLine();
            if (choice == 0){
                sellerMenu();
            } else if (1 <= choice && choice <= products.size()){
                increaseQuantityOfProduct(products.get(choice - 1));
            } else {
                System.out.println("Number was not in range!");
                selectProductForSeller(products);
            }
        } catch (Exception e){
            in.nextLine();
            System.out.println("You just entered wrong entry. Please try again.");
            selectProductForSeller(products);
        }
    }

    private static void increaseQuantityOfProduct(Product product){
        System.out.println("Selected Product:\n" +
                product);
        System.out.println("Number of increase: ");
        try {
            int numOfIncrease = in.nextInt();
            in.nextLine();
            Seller seller = (Seller) mainShop.getCurrentUser();
            seller.increaseNumberOfProduct(mainShop, numOfIncrease, product);
            System.out.println("Quantity just increased. new quantity is " + product.getQuantity());
            sellerMenu();
        } catch (Exception e) {
            in.nextLine();
            System.out.println("You just entered wrong entry. Please try again.");
            increaseQuantityOfProduct(product);
        }
        sellerMenu();
    }

    private static void adminMenu() {
        System.out.println("What do you want to do? \n" +
                "   1. View pending requests \n" +
                "       - submit or reject a request \n" +
                "   2. View work History \n " +
                "   3. View profile screen of a user \n" +
                "   4. Give fund to one user \n" +
                "   5. add new admin \n" +
                "   6. Logout");

        try{
            short choice = in.nextShort();
            in.nextLine();
            if (1 <= choice && choice <= 6) {
                switch (choice) {
                    case 1:
                        viewPendingRequests();
                        break;
                    case 2:
                        viewHistory();
                        break;
                    case 3:
                        viewProfilePhotoOfUser();
                        break;
                    case 4:
                        giveFundToUser();
                        break;
                    case 5:
                        addNewAdmin();
                        break;
                    case 6:
                        mainShop.logout();
                        startMenu();
                        break;
                }
            } else {
                System.out.println("Enter a number in range 1 - 4");
                sellerMenu();
            }
        } catch (Exception e) {
            in.nextLine();
            System.out.println("You just entered wrong entry. Please try again.");
            adminMenu();
        }
    }

    // case 1 of admin menu
    private static void viewPendingRequests(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            adminMenu();
        }

        Admin admin = (Admin) mainShop.getCurrentUser();
        ArrayList<Request> pendingRequests = admin.getPendingRequests();
        selectRequest(pendingRequests);
    }

    private static void selectRequest(ArrayList<Request> requests){
        System.out.println("\"Found Requests\"\n");
        for (int i = 0; i < requests.size(); i++){
            System.out.println(i + 1 + ") " + requests.get(i) + "\n\n");
        }

        System.out.println("Select one of above requests for submit or reject it or enter 0 for back to menu. Just enter a number: ");
        try{
            int choice = in.nextInt();
            in.nextLine();
            if (choice == 0){
                adminMenu();
            } else if (1 <= choice && choice <= requests.size()){
                Request request = requests.get(choice - 1);
                if (request.getClass().getSimpleName().equals("Order")){
                    orderMenu((Order) request);
                }
                if (request.getClass().getSimpleName().equals("FundRequest")){
                    fundRequestMenu((FundRequest) request);
                }
                if (request.getClass().getSimpleName().equals("AuthorizationRequest")){
                    authorizationRequestMenu((AuthorizationRequest) request);
                }
            } else {
                System.out.println("Number was not in range!");
                selectRequest(requests);
            }
        } catch (Exception e){
            in.nextLine();
            System.out.println("You just entered wrong entry. Please try again.");
            selectRequest(requests);
        }
    }

    private static void orderMenu(Order order){
        System.out.println("Selected Request:\n" +
                order);
        System.out.println("What do you want to do?\n" +
                "1. Submit request \n" +
                "2. Reject request \n" +
                "0. Back to menu");

        Admin admin = (Admin) mainShop.getCurrentUser();

        try {
            short choice = in.nextShort();
            in.nextLine();
            if (choice == 0){
                adminMenu();
            } else if (choice == 1) {
                admin.submitOrder(mainShop, order);
            } else if (choice == 2) {
                User buyer = order.getBuyer();
                buyer.getAdminsResponse(order);
            } else {
                System.out.println("Enter a number in range 0 - 2");
                orderMenu(order);
            }
        } catch (Exception e) {
            in.nextLine();
            System.out.println("You just entered wrong entry. Please try again.");
            orderMenu(order);
        }
        adminMenu();
    }

    private static void fundRequestMenu(FundRequest fundRequest){
        System.out.println("Selected Request:\n" +
                fundRequest);
        System.out.println("What do you want to do?\n" +
                "1. Submit request \n" +
                "2. Reject request \n" +
                "0. Back to menu");

        Admin admin = (Admin) mainShop.getCurrentUser();

        try {
            short choice = in.nextShort();
            in.nextLine();
            if (choice == 0){
                adminMenu();
            } else if (choice == 1) {
                admin.giveFundToUser(mainShop, fundRequest);
            } else if (choice == 2) {
                User requester = fundRequest.getRequester();
                requester.getAdminsResponse(fundRequest);
            } else {
                System.out.println("Enter a number in range 0 - 2");
                fundRequestMenu(fundRequest);
            }
        } catch (Exception e) {
            in.nextLine();
            System.out.println("You just entered wrong entry. Please try again.");
            fundRequestMenu(fundRequest);
        }
        adminMenu();
    }

    private static void authorizationRequestMenu(AuthorizationRequest authorizationRequest){
        System.out.println("Selected Request:\n" +
                authorizationRequest);
        System.out.println("What do you want to do?\n" +
                "1. Submit request \n" +
                "2. Reject request \n" +
                "0. Back to menu");

        Admin admin = (Admin) mainShop.getCurrentUser();

        try {
            short choice = in.nextShort();
            in.nextLine();
            if (choice == 0){
                adminMenu();
            } else if (choice == 1) {
                admin.acceptSeller(mainShop, authorizationRequest);
            } else if (choice == 2) {
                admin.rejectSeller(mainShop, authorizationRequest);
            } else {
                System.out.println("Enter a number in range 0 - 2");
                authorizationRequestMenu(authorizationRequest);
            }
        } catch (Exception e) {
            in.nextLine();
            System.out.println("You just entered wrong entry. Please try again.");
            authorizationRequestMenu(authorizationRequest);
        }
        adminMenu();
    }

    // case 2 of admin menu
    private static void viewHistory(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            adminMenu();
        }

        Admin admin = (Admin) mainShop.getCurrentUser();
        ArrayList<Request> workHistory = admin.getWorkHistory();

        for (int i = 0; i < workHistory.size(); i++){
            System.out.println(i + 1 + ") " + workHistory.get(i) + "\n\n");
        }
        adminMenu();
    }

    // case 3 of admin menu
    private static void viewProfilePhotoOfUser() throws IOException {
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            adminMenu();
        }

        System.out.println("Enter the username of the user: ");
        String username = in.nextLine();
        User user = searchInUsers(username);
        if (user != null){
            Admin admin = (Admin) mainShop.getCurrentUser();
            admin.viewProfilePhoto(user);
            adminMenu();
        } else {
            System.out.println("Invalid username! Please try again.");
            viewProfilePhotoOfUser();
        }
    }

    private static User searchInUsers(String username){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            adminMenu();
        }

        for (Account account: mainShop.getAccounts()){
            if (account.getUsername().equals(username)){
                return (User) account;
            }
        }
        return null;
    }

    // case 4 of admin menu
    private static void giveFundToUser() throws IOException {
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            adminMenu();
        }

        System.out.println("Enter the username of the user: ");
        String username = in.nextLine();
        User user = searchInUsers(username);
        if (user != null){
            Admin admin = (Admin) mainShop.getCurrentUser();
            double fund = getFundValue();
            admin.giveFundToUser(mainShop, user, fund);
            System.out.println("Fund added.");
            adminMenu();
        } else {
            System.out.println("Invalid username! Please try again.");
            viewProfilePhotoOfUser();
        }
    }

    private static double getFundValue(){
        try {
            System.out.println("Fund value: ");
            double fund = in.nextDouble();
            in.nextLine();
            return fund;
        } catch (Exception e) {
            in.nextLine();
            System.out.println("Wrong entry. Please try again.");
        }
        return getFundValue();
    }

    // case 5 of admin menu
    private static void addNewAdmin(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            adminMenu();
        }

        System.out.println("Fill based admins information: ");
        String username = getUsername();
        String password = getPassword();
        String email = getEmailAddress();

        Admin admin = (Admin) mainShop.getCurrentUser();
        admin.addNewAdmin(mainShop, username, password, email);

        adminMenu();
    }
}