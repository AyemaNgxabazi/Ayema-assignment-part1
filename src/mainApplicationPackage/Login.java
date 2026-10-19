
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

    // Check whether the username is correctly formatted
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

    // Check South African cell phone number
   public boolean checkCellPhoneNumber() {
    return cellNumber != null
            && cellNumber.trim().matches("^\\+27\\d{9}$");
}
    // Register the user
    public String registerUser() {

        if (!checkUserName()) {
            return "Username is not correctly formatted. "
                    + "Please ensure that your username contains "
                    + "an underscore and is no more than five "
                    + "characters long.";
        }

        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted. "
                    + "Please ensure that it contains at least "
                    + "eight characters, a capital letter, "
                    + "a number, and a special character.";
        }

        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted "
                    + "or does not contain the international code.";
        }

        return "Username successfully captured.\n"
                + "Password successfully captured.\n"
                + "Cell phone number successfully added.";
    }

    // Check login details
    public boolean loginUser(String enteredUser, String enteredPass) {
        return username.equals(enteredUser)
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
