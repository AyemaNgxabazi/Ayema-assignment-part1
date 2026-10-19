
package mainApplicationPackage;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // REGISTRATION
        System.out.println("===== REGISTRATION =====");

        System.out.print("Enter first name: ");
        String firstName = sc.nextLine();

        System.out.print("Enter last name: ");
        String lastName = sc.nextLine();

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        System.out.print(
                "Enter South African cell phone number (include +27): ");
        String cellNumber = sc.nextLine().trim();

        Login app = new Login(
                firstName,
                lastName,
                username,
                password,
                cellNumber
        );

        System.out.println(app.registerUser());

        // Continue only if registration details are valid
        if (app.checkUserName()
                && app.checkPasswordComplexity()
                && app.checkCellPhoneNumber()) {

            // LOGIN
            System.out.println();
            System.out.println("===== LOGIN =====");

            System.out.print("Enter your registered username: ");
            String loginUser = sc.nextLine().trim();

            System.out.print("Enter your registered password: ");
            String loginPass = sc.nextLine();

            if (app.loginUser(loginUser, loginPass)) {

                System.out.println("Welcome to QuickChat.");

                // QUICKCHAT MENU
                boolean running = true;

                while (running) {

                    System.out.println();
                    System.out.println("===== QUICKCHAT MENU =====");
                    System.out.println("1) Send Messages");
                    System.out.println(
                            "2) Show recently sent messages");
                    System.out.println("3) Quit");

                    System.out.print("Choose an option: ");
                    String option = sc.nextLine().trim();

                    switch (option) {

                        case "1":

                            System.out.print(
                                    "How many messages would you like to send? ");

                            String countInput = sc.nextLine().trim();

                            try {
                                int numberOfMessages =
                                        Integer.parseInt(countInput);

                                if (numberOfMessages <= 0) {
                                    System.out.println(
                                            "Please enter a number greater than zero.");
                                    break;
                                }

                                // Process the requested number of messages
                                for (int i = 0;
                                        i < numberOfMessages; i++) {

                                    System.out.println();
                                    System.out.println(
                                            "Preparing message "
                                            + (i + 1) + " of "
                                            + numberOfMessages);

                                    System.out.print(
                                            "Enter recipient number (+27...): ");
                                    String recipient =
                                            sc.nextLine().trim();

                                    System.out.print(
                                            "Enter your message: ");
                                    String messageText =
                                            sc.nextLine();

                                    Message message = new Message(
                                            i,
                                            recipient,
                                            messageText
                                    );

                                    // Validate recipient
                                    String recipientStatus =
                                            message.checkRecipientCell();

                                    System.out.println(recipientStatus);

                                    if (!recipientStatus.equals(
                                            "Cell phone number successfully captured.")) {
                                        System.out.println(
                                                "Message was not processed.");
                                        continue;
                                    }

                                    // Validate message length
                                    String lengthStatus =
                                            message.checkMessageLength();

                                    System.out.println(lengthStatus);

                                    if (!lengthStatus.equals(
                                            "Message ready to send.")) {
                                        System.out.println(
                                                "Message was not processed.");
                                        continue;
                                    }

                                    // Choose an action
                                    System.out.println();
                                    System.out.println("1) Send Message");
                                    System.out.println("2) Disregard Message");
                                    System.out.println(
                                            "3) Store Message to send later");

                                    System.out.print("Choose an option: ");
                                    String action =
                                            sc.nextLine().trim();

                                    if (action.equals("3")) {
                                        System.out.println(
                                                MessageStorage.storeMessage(message));
                                    } else {
                                        System.out.println(
                                                message.SentMessage(action));
                                    }

                                    // Display message details
                                    System.out.println();
                                    System.out.println(
                                            message.printMessages());
                                }

                                // Display total after all messages
                                Message summary = new Message(
                                        0, "+27821234567", "Summary");

                                System.out.println();
                                System.out.println(
                                        "Total messages sent: "
                                        + summary.returnTotalMessagess());

                            } catch (NumberFormatException e) {
                                System.out.println(
                                        "Please enter a valid number.");
                            }

                            break;

                        case "2":
                            System.out.println("Coming Soon.");
                            break;

                        case "3":
                            running = false;
                            System.out.println("Goodbye.");
                            break;

                        default:
                            System.out.println(
                                    "Invalid option. Please choose 1, 2 or 3.");
                    }
                }

            } else {
                System.out.println(
                        app.returnLoginStatus(loginUser, loginPass));
            }
        }

        sc.close();
    }
}
