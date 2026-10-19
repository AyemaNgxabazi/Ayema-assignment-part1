
package testApplicationPackage;

import mainApplicationPackage.Login;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {

    @Test
    public void testUsernameIsCorrectlyFormatted() {
        Login obj = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");

        assertEquals(
                "Username successfully captured.\n"
                + "Password successfully captured.\n"
                + "Cell phone number successfully added.",
                obj.registerUser());
    }

    @Test
    public void testUsernameIsIncorrectlyFormatted() {
        Login obj = new Login("Kyle", "Smith", "kyle!!!!!!!",
                "Ch&&sec@ke99!", "+27838968976");

        assertEquals(
                "Username is not correctly formatted, please ensure "
                + "that your username contains an underscore and is "
                + "no more than five characters in length.",
                obj.registerUser());
    }

    @Test
    public void testPasswordMeetsComplexityRequirements() {
        Login obj = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");

        assertEquals(
                "Username successfully captured.\n"
                + "Password successfully captured.\n"
                + "Cell phone number successfully added.",
                obj.registerUser());
    }

    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {
        Login obj = new Login("Kyle", "Smith", "kyl_1",
                "password", "+27838968976");

        assertEquals(
                "Password is not correctly formatted, please ensure "
                + "that the password contains at least eight characters, "
                + "a capital letter, a number, and a special character.",
                obj.registerUser());
    }

    @Test
    public void testCellPhoneNumberCorrectlyFormatted() {
        Login obj = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");

        assertEquals(
                "Username successfully captured.\n"
                + "Password successfully captured.\n"
                + "Cell phone number successfully added.",
                obj.registerUser());
    }

    @Test
    public void testCellPhoneNumberIncorrectlyFormatted() {
        Login obj = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+278966553");

        assertEquals(
                "Cell phone number incorrectly formatted or does not "
                + "contain international code.",
                obj.registerUser());
    }

    @Test
    public void testLoginSuccessful() {
        Login obj = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");

        assertTrue(obj.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        Login obj = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");

        assertFalse(obj.loginUser("wrong", "wrong"));
    }

    @Test
    public void testUsernameCorrectlyFormattedBoolean() {
        Login obj = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");

        assertTrue(obj.checkUserName());
    }

    @Test
    public void testUsernameIncorrectlyFormattedBoolean() {
        Login obj = new Login("Kyle", "Smith", "kyle!!!!!!!",
                "Ch&&sec@ke99!", "+27838968976");

        assertFalse(obj.checkUserName());
    }

    @Test
    public void testPasswordMeetsComplexityRequirementsBoolean() {
        Login obj = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");

        assertTrue(obj.checkPasswordComplexity());
    }

    @Test
    public void testPasswordDoesNotMeetComplexityRequirementsBoolean() {
        Login obj = new Login("Kyle", "Smith", "kyl_1",
                "password", "+27838968976");

        assertFalse(obj.checkPasswordComplexity());
    }

    @Test
    public void testCellPhoneNumberCorrectlyFormattedBoolean() {
        Login obj = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "0838968976");

        assertTrue(obj.checkCellPhoneNumber());
    }

    @Test
    public void testCellPhoneNumberIncorrectlyFormattedBoolean() {
        Login obj = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "08966553");

        assertFalse(obj.checkCellPhoneNumber());
    }
}
