import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.util.Scanner;
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
}
