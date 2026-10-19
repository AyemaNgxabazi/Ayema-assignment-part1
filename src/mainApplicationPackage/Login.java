
package mainApplicationPackage;

public class Login {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellNumber;

    public Login(String firstName, String lastName,
                 String username, String password,
                 String cellNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellNumber = cellNumber;
    }

    // Check username format
    public boolean checkUserName() {
        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }

    // Check password complexity
    public boolean checkPasswordComplexity() {

        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) {
                hasCapital = true;
            }

            if (Character.isDigit(ch)) {
                hasNumber = true;
            }

            if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
        }

        return hasCapital && hasNumber && hasSpecial;
    }

    // Check phone number format
    public boolean checkCellPhoneNumber() {

        if (cellNumber == null) {
            return false;
        }

        String number = cellNumber.trim();

        return number.matches("^\\+27[0-9]{9}$")
                || number.matches("^0[0-9]{9}$");
    }

    // Registration requires an international phone number
    private boolean cellNumberIsInternationalAndValid() {
        return cellNumber != null
                && cellNumber.trim().matches("^\\+27[0-9]{9}$");
    }

    // Register the user
    public String registerUser() {

        if (!checkUserName()) {
            return "Username is not correctly formatted, please ensure "
                    + "that your username contains an underscore and is "
                    + "no more than five characters in length.";
        }

        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted, please ensure "
                    + "that the password contains at least eight characters, "
                    + "a capital letter, a number, and a special character.";
        }

        if (!cellNumberIsInternationalAndValid()) {
            return "Cell phone number incorrectly formatted or does not "
                    + "contain international code.";
        }

        return "Username successfully captured.\n"
                + "Password successfully captured.\n"
                + "Cell phone number successfully added.";
    }

    // Check login details
    public boolean loginUser(String enteredUser, String enteredPass) {
        return username != null
                && password != null
                && username.equals(enteredUser)
                && password.equals(enteredPass);
    }

    // Return login status
    public String returnLoginStatus(String enteredUser,
                                    String enteredPass) {

        if (loginUser(enteredUser, enteredPass)) {
            return "Welcome " + firstName + ", " + lastName
                    + " it is great to see you again.";
        }

        return "Username or password incorrect, please try again.";
    }
}
