
package mainApplicationPackage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MessageStorage {

    private static final String FILE_NAME = "messages.json";

    private static final Gson gson =
            new GsonBuilder().setPrettyPrinting().create();

    // Save a message to the JSON file
    public static String storeMessage(Message message) {

        List<Message> messages = readMessages();
        messages.add(message);

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(messages, writer);
            return "Message successfully stored.";
        } catch (IOException e) {
            return "Error saving message: " + e.getMessage();
        }
    }

    // Read messages already saved in the JSON file
    public static List<Message> readMessages() {

        Type messageListType =
                new TypeToken<ArrayList<Message>>() {}.getType();

        try (FileReader reader = new FileReader(FILE_NAME)) {
            List<Message> messages =
                    gson.fromJson(reader, messageListType);

            if (messages == null) {
                return new ArrayList<>();
            }

            return messages;

        } catch (IOException e) {
            // Return an empty list if the file does not exist yet
            return new ArrayList<>();
        }
    }
}
