
package mainApplicationPackage;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents a single QuickChat message.
 */
public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String messageStatus;

    private static int totalMessagesSent = 0;

    // Create a message
    public Message(int messageNumber, String recipient,
                   String messageText) {

        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;

        // Generate a ten-digit message ID
        long randomID = ThreadLocalRandom.current()
                .nextLong(1_000_000_000L, 10_000_000_000L);

        this.messageID = String.valueOf(randomID);
        this.messageStatus = "Pending";

        this.messageHash = createMessageHash();
    }

    // Check that the message ID is no longer than ten characters
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    // Validate a South African cell number in international format
    public String checkRecipientCell() {

        if (recipient != null
                && recipient.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured.";
        }

        return "Cell phone number is incorrectly formatted or "
                + "does not contain an international code. "
                + "Please correct the number and try again.";
    }

    // Check the message length
    public String checkMessageLength() {

        if (messageText == null) {
            return "Please enter a message.";
        }

        if (messageText.length() > 250) {
            int excess = messageText.length() - 250;

            return "Message exceeds 250 characters by "
                    + excess + "; please reduce the size.";
        }

        return "Message ready to send.";
    }

    // Create the message hash
    public String createMessageHash() {

        String firstTwoDigits = messageID.substring(0, 2);
        String firstWord = "";
        String lastWord = "";

        if (messageText != null && !messageText.trim().isEmpty()) {

            String[] words = messageText.trim().split("\\s+");

            firstWord = words[0];
            lastWord = words[words.length - 1];

            // Remove punctuation from the first and last words
            firstWord = firstWord.replaceAll(
                    "^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$", "");

            lastWord = lastWord.replaceAll(
                    "^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$", "");
        }

        return (firstTwoDigits + ":" + messageNumber + ":"
                + firstWord + lastWord).toUpperCase(Locale.ROOT);
    }

    // Process the user's choice
    public String SentMessage(String action) {

        if (action == null) {
            return "Invalid message option.";
        }

        switch (action.trim().toLowerCase(Locale.ROOT)) {

            case "send":
            case "1":
                messageStatus = "Sent";
                totalMessagesSent++;
                return "Message successfully sent.";

            case "disregard":
            case "discard":
            case "2":
                messageStatus = "Disregarded";
                return "Press 0 to delete the message.";

            case "store":
            case "3":
                messageStatus = "Stored";
                return "Message successfully stored.";

            default:
                return "Invalid message option.";
        }
    }

    // Display the message details in the required order
    public String printMessages() {

        return "Message ID: " + messageID + "\n"
                + "Message Hash: " + messageHash + "\n"
                + "Recipient: " + recipient + "\n"
                + "Message: " + messageText;
    }

    // Return the number of messages successfully sent
    public int returnTotalMessagess() {
        return totalMessagesSent;
    }

    // Getters for use in Main, tests and future features
    public String getMessageID() {
        return messageID;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getMessageStatus() {
        return messageStatus;
    }
}
