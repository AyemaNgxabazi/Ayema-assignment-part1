
package mainApplicationPackage;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testMessageID() {
        Message message = new Message(
                1, "+27821234567", "Hello there");

        assertNotNull(message.getMessageID());
        assertEquals(10, message.getMessageID().length());
    }

    @Test
    public void testValidRecipient() {
        Message message = new Message(
                1, "+27821234567", "Hello there");

        assertEquals(
                "Cell phone number successfully captured.",
                message.checkRecipientCell());
    }

    @Test
    public void testInvalidRecipient() {
        Message message = new Message(
                1, "0821234567", "Hello there");

        assertEquals(
                "Cell phone number is incorrectly formatted or "
                + "does not contain an international code. "
                + "Please correct the number and try again.",
                message.checkRecipientCell());
    }

    @Test
    public void testValidMessageLength() {
        Message message = new Message(
                1, "+27821234567", "Hello there");

        assertEquals(
                "Message ready to send.",
                message.checkMessageLength());
    }

    @Test
    public void testLongMessage() {
        String longText = new String(new char[251]).replace('\0', 'A');

        Message message = new Message(
                1, "+27821234567", longText);

        assertEquals(
                "Message exceeds 250 characters by 1; please reduce the size.",
                message.checkMessageLength());
    }

    @Test
    public void testMessageHash() {
        Message message = new Message(
                0,
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        String expectedHash = message.getMessageID().substring(0, 2)
                + ":0:HITONIGHT";

        assertEquals(expectedHash, message.createMessageHash());
    }

    @Test
    public void testSendMessage() {
        Message message = new Message(
                1, "+27821234567", "Hello there");

        String result = message.SentMessage("1");

        assertEquals("Message successfully sent.", result);
        assertEquals("Sent", message.getMessageStatus());
    }

    @Test
    public void testDisregardMessage() {
        Message message = new Message(
                1, "+27821234567", "Hello there");

        String result = message.SentMessage("2");

        assertEquals("Press 0 to delete the message.", result);
        assertEquals("Disregarded", message.getMessageStatus());
    }

    @Test
    public void testStoreMessage() {
        Message message = new Message(
                1, "+27821234567", "Hello there");

        String result = message.SentMessage("3");

        assertEquals("Message successfully stored.", result);
        assertEquals("Stored", message.getMessageStatus());
    }
}
