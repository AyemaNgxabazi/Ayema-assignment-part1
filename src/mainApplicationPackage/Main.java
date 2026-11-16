
package mainApplicationPackage;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== QUICKCHAT REGISTRATION =====");

        System.out.print("Enter first name: ");
        String firstName = sc.nextLine();

        System.out.print("Enter last name: ");
        String lastName = sc.nextLine();

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        System.out.print("Enter cellphone number: ");
        String cellphone = sc.nextLine();

        Login app = new Login(
                firstName, lastName, username,
                password, cellphone
        );

        System.out.println(app.registerUser());

        System.out.println("\n===== QUICKCHAT LOGIN =====");

        System.out.print("Username: ");
        String loginUsername = sc.nextLine();

        System.out.print("Password: ");
        String loginPassword = sc.nextLine();

        if (!app.loginUser(loginUsername, loginPassword)) {
            System.out.println("Login failed. Please restart.");
            return;
        }

        System.out.println("Welcome to QuickChat!");

        boolean running = true;

        while (running) {

            System.out.println("\n===== QUICKCHAT MENU =====");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.println("4) Stored Messages");

            System.out.print("Choose an option: ");
            String choice = sc.nextLine();

            switch (choice) {

                case "1":
                 sendMessages(sc, username);
                    break;

                case "2":
                    MessageReport recentReport = new MessageReport();
                    recentReport.refreshMessages();

                    String[] sent = recentReport.getSentMessages();

                    if (sent.length == 0) {
                        System.out.println("No sent messages available.");
                    } else {
                        System.out.println("\n===== SENT MESSAGES =====");
                        for (String text : sent) {
                            System.out.println(text);
                        }
                    }
                    break;

                case "3":
                    System.out.println("Goodbye!");
                    running = false;
                    break;

                case "4":
                    showStoredMessages(sc);
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        sc.close();
    }

    private static void sendMessages(Scanner sc, String username) {

        System.out.print("How many messages do you want to create? ");

        int count;

        try {
            count = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return;
        }

        if (count <= 0) {
            System.out.println("Enter a number greater than zero.");
            return;
        }

        for (int i = 0; i < count; i++) {

            System.out.println("\n===== MESSAGE " + (i + 1) + " =====");

            System.out.print("Enter recipient (+27XXXXXXXXX): ");
            String recipient = sc.nextLine();

            System.out.print("Enter message: ");
            String messageText = sc.nextLine();

            Message message = new Message(
                    i, recipient, messageText
            );
            
message.setSender(username);


            String recipientResult = message.checkRecipientCell();

            if (!recipientResult.equals(
                    "Cell phone number successfully captured.")) {

                System.out.println(recipientResult);
                continue;
            }

            String lengthResult = message.checkMessageLength();

            if (!lengthResult.equals("Message ready to send.")) {
                System.out.println(lengthResult);
                continue;
            }

            System.out.println("\nChoose message action:");
            System.out.println("1) Send Message");
            System.out.println("2) Disregard Message");
            System.out.println("3) Store Message");

            System.out.print("Choose an option: ");
            String action = sc.nextLine().trim();

            if (!action.equals("1")
                    && !action.equals("2")
                    && !action.equals("3")) {

                System.out.println("Invalid message option.");
                continue;
            }

            // Set the correct message status first.
            System.out.println(message.SentMessage(action));

            // Save ALL message types to JSON.
            // The original status remains unchanged.
            String saveResult = MessageStorage.storeMessage(message);

            if (saveResult.startsWith("Error")) {
                System.out.println(saveResult);
            } else {
                System.out.println("Message record saved successfully.");
            }

            System.out.println("\n" + message.printMessages());

            System.out.println(
                    "Total messages sent: "
                    + message.returnTotalMessagess()
            );
        }
    }

    private static void showStoredMessages(Scanner sc) {

        boolean back = false;

        while (!back) {

            MessageReport report = new MessageReport();
            report.refreshMessages();

            System.out.println("\n===== STORED MESSAGES =====");
            System.out.println("1) Display stored messages");
            System.out.println("2) Display longest message");
            System.out.println("3) Search by Message ID");
            System.out.println("4) Search by recipient");
            System.out.println("5) Delete message by hash");
            System.out.println("6) Display full report");
            System.out.println("7) Return to main menu");

            System.out.print("Choose an option: ");
            String option = sc.nextLine();

            switch (option) {

                case "1":
                    System.out.println(
                            report.displaySendersAndRecipients()
                    );
                    break;

                case "2":
                    System.out.println(
                            report.displayLongestMessage()
                    );
                    break;

                case "3":
                    System.out.print("Enter Message ID: ");
                    String id = sc.nextLine().trim();

                    System.out.println(
                            report.searchByMessageID(id)
                    );
                    break;

                case "4":
                    System.out.print("Enter recipient number: ");
                    String recipient = sc.nextLine().trim();

                    System.out.println(
                            report.searchByRecipient(recipient)
                    );
                    break;

                case "5":
                    System.out.print("Enter Message Hash: ");
                    String hash = sc.nextLine().trim();

                    System.out.println(
                            MessageStorage.deleteMessageByHash(hash)
                    );
                    break;

                case "6":
                    System.out.println(
                            report.displayFullReport()
                    );
                    break;

                case "7":
                    back = true;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
