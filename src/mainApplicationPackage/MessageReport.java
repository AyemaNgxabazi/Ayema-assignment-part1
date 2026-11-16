
package mainApplicationPackage;

import java.util.ArrayList;
import java.util.List;

public class MessageReport {

    private final List<Message> messages = new ArrayList<>();

    private String[] sentMessages;
    private String[] disregardedMessages;
    private String[] storedMessages;
    private String[] messageHashes;
    private String[] messageIDs;

    public MessageReport() {
        refreshMessages();
    }

    public final void refreshMessages() {
        messages.clear();
        messages.addAll(MessageStorage.readMessages());

        List<String> sent = new ArrayList<>();
        List<String> disregarded = new ArrayList<>();
        List<String> stored = new ArrayList<>();
        List<String> hashes = new ArrayList<>();
        List<String> ids = new ArrayList<>();

        for (Message message : messages) {
            String status = message.getMessageStatus();

            if ("Sent".equalsIgnoreCase(status)) {
                sent.add(message.getMessageText());
            } else if ("Disregarded".equalsIgnoreCase(status)) {
                disregarded.add(message.getMessageText());
            } else if ("Stored".equalsIgnoreCase(status)) {
                stored.add(message.getMessageText());
            }

            hashes.add(message.getMessageHash());
            ids.add(message.getMessageID());
        }

        sentMessages = sent.toArray(new String[0]);
        disregardedMessages = disregarded.toArray(new String[0]);
        storedMessages = stored.toArray(new String[0]);
        messageHashes = hashes.toArray(new String[0]);
        messageIDs = ids.toArray(new String[0]);
    }

    public String displaySendersAndRecipients() {
        StringBuilder result = new StringBuilder();

        for (Message message : messages) {
            if ("Stored".equalsIgnoreCase(message.getMessageStatus())) {
                result.append("Sender: ")
                      .append(message.getSender() == null
                              ? "Unknown" : message.getSender())
                      .append("\nRecipient: ")
                      .append(message.getRecipient())
                      .append("\n\n");
            }
        }

        return result.length() == 0
                ? "No stored messages found."
                : result.toString();
    }

    public String displayLongestMessage() {
        Message longest = null;

        for (Message message : messages) {
            if ("Stored".equalsIgnoreCase(message.getMessageStatus())
                    && message.getMessageText() != null) {

                if (longest == null
                        || message.getMessageText().length()
                        > longest.getMessageText().length()) {
                    longest = message;
                }
            }
        }

        return longest == null
                ? "No stored messages found."
                : longest.printMessages();
    }

    public String searchByMessageID(String id) {
        for (Message message : messages) {
            if (message.getMessageID().equals(id)) {
                return "Recipient: " + message.getRecipient()
                        + "\nMessage: " + message.getMessageText();
            }
        }

        return "Message ID not found.";
    }

    public String searchByRecipient(String recipient) {
        StringBuilder result = new StringBuilder();

        for (Message message : messages) {
            if (message.getRecipient().equals(recipient)) {
                result.append(message.printMessages())
                      .append("\n\n");
            }
        }

        return result.length() == 0
                ? "No messages found for this recipient."
                : result.toString();
    }

    public String displayFullReport() {
        StringBuilder result = new StringBuilder();

        for (Message message : messages) {
            result.append("Sender: ")
                  .append(message.getSender() == null
                          ? "Unknown" : message.getSender())
                  .append("\n")
                  .append(message.printMessages())
                  .append("\nStatus: ")
                  .append(message.getMessageStatus())
                  .append("\n----------------------\n");
        }

        return result.length() == 0
                ? "No messages available."
                : result.toString();
    }

    public String[] getSentMessages() {
        return sentMessages.clone();
    }

    public String[] getDisregardedMessages() {
        return disregardedMessages.clone();
    }

    public String[] getStoredMessages() {
        return storedMessages.clone();
    }

    public String[] getMessageHashes() {
        return messageHashes.clone();
    }

    public String[] getMessageIDs() {
        return messageIDs.clone();
    }
}
