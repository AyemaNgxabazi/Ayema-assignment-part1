
package mainApplicationPackage;

import java.util.Scanner;

/**
 * Main class for console application.
 */
public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== REGISTRATION =====");

        System.out.print("Enter first name: ");
        String first = sc.nextLine();

        System.out.print("Enter last name: ");
        String last = sc.nextLine();

        System.out.print("Enter username: ");
        String user = sc.nextLine();

        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        System.out.print("Enter South African cell phone number (include +27): ");
        String phone = sc.nextLine();

        Login app = new Login(first, last, user, pass, phone);

        String result = app.registerUser();
        System.out.println(result);

        if (app.checkUserName() && app.checkPasswordComplexity() && app.checkCellPhoneNumber()) {
            System.out.println();
            System.out.println("===== LOGIN =====");

            System.out.print("Enter username: ");
            String loginUser = sc.nextLine();

            System.out.print("Enter password: ");
            String loginPass = sc.nextLine();

            System.out.println(app.returnLoginStatus(loginUser, loginPass));
        }

        sc.close();
    }
}
