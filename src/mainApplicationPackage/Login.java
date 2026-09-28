
package mainApplicationPackage;

/**
 * Login class - User registration and authentication implementation.
 * All methods follow assignment specifications precisely.
 */
public class Login {
    
    // User account fields
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellNumber;

    /**
     * Constructor sets up Login with user registration data.
     * 
     * @param firstName User first name
     * @param lastName User last name
     * @param username Desired username
     * @param password Desired password
     * @param cellNumber Cell phone number
     */
    public Login(String firstName, String lastName, String username, String password, String cellNumber) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
    this.cellNumber = cellNumber;

  
}
  /**
 * Checks if the username is correctly formatted.
 * The username must contain an underscore and must not
 * be more than five characters long.
 *
 * @return true if the username is valid, otherwise false
 */
    public boolean checkUserName() {
        boolean underscoreExists = username.contains("_");
        boolean lengthValid = username.length() <= 5;
        return underscoreExists && lengthValid;
    }

    /**
     * Check password complexity rules.
     * Rules: 8+ chars, uppercase, digit, special character.
     * 
     * @return boolean result
     */
    public boolean checkPasswordComplexity() {
        boolean upperCaseFound = false;
        boolean digitFound = false;
        boolean specialFound = false;

        if (password.length() < 8) {
            return false;
        }

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            
            if (Character.isUpperCase(ch)) upperCaseFound = true;
            if (Character.isDigit(ch)) digitFound = true;
            if (!Character.isLetterOrDigit(ch)) specialFound = true;
        }

        return upperCaseFound && digitFound && specialFound;
    }

   /**
 * Checks if the cell phone number is correctly formatted.
 * The number must contain the South African international code (+27)
 * followed by 9 digits.
 *
 * Regular expression used to validate the cell phone number.
 *
 * @return true if the cell phone number is correctly formatted,
 * otherwise returns false
 */
public boolean checkCellPhoneNumber() {
    return cellNumber.matches("^\\+27\\d{9}$");
}

    /**
     * Register user with validation.
     * 
     * @return String message
     */
    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }

    /**
     * Verify login credentials.
     * 
     * @param enteredUser Username entered
     * @param enteredPass Password entered
     * @return boolean result
     */
    public boolean loginUser(String enteredUser, String enteredPass) {
        return username.equals(enteredUser) && password.equals(enteredPass);
    }

    /**
     * Get login status message.
     * 
     * @param enteredUser Username entered
     * @param enteredPass Password entered
     * @return String message
     */
    public String returnLoginStatus(String enteredUser, String enteredPass) {
        if (loginUser(enteredUser, enteredPass)) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }
}
