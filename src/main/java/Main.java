import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
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
                "   2. Charge wallet \n" +
                "   3. View shopping cart \n" +
                "   4. Send fund request \n" +
                "   5. Request tracking \n" +
                "   6. View pending requests \n" +
                "   7. View successful requests\n" +
                "   8. View rejected Requests\n " +
                "   9. Edit information \n" +
                "   10. Logout");
        try{
            short choice = in.nextShort();
            in.nextLine();
            if (1 <= choice && choice <= 8) {
                switch (choice) {
                    case 1:
                        searchMenu();
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
                        requestTracking();
                        break;
                    case 6:
                        System.out.println("Pending requests:");
                        presentRequestsList(((User) mainShop.getCurrentUser()).getInProcessRequests());
                        break;
                    case 7:
                        System.out.println("Successful requests:");
                        presentRequestsList(((User) mainShop.getCurrentUser()).getFinishedRequests());
                        break;
                    case 8:
                        System.out.println("Rejected requests:");
                        presentRequestsList(((User) mainShop.getCurrentUser()).getRejectedRequests());
                        break;
                    case 9:
                        //editInformationMenu();
                        break;
                    case 10:
                        mainShop.logout();
                        startMenu();
                        break;
                }
            } else {
                System.out.println("Enter a number in range 1 - 8");
                userMenu();
            }
        } catch (Exception e) {
            System.out.println("You just entered wrong entry. Please try again.");
            userMenu();
        }
    }

    private static void sellerMenu() {

    }

    private static void adminMenu() {


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
    private static void searchMenu(){
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
                searchMenu();
            }
        } catch (Exception e){
            in.nextLine();
            System.out.println("You just entered wrong entry. Please try again.");
            searchMenu();
        }
    }

    private static void searchByCategoryMenu(){
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            searchMenu();
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
            searchMenu();
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
                "2. Leave comment" +
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
                "2. register this order");

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

    private static void editShoppingCartMenu(){
        User currentUser = (User) mainShop.getCurrentUser();
        Product product = selectProductInCart();
        if (product != null){
            System.out.println("What do you want todo with this product?" +
                    "1. remove this item" +
                    "2. edit quantity of an item");

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
    }

    // case 5 of user menu
    private static void requestTracking(){
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


}
