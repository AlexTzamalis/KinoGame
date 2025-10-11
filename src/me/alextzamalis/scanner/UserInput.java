package me.alextzamalis.scanner;

import me.alextzamalis.process.UserProcess;
import me.alextzamalis.util.Constants;
import java.util.Scanner;

public class UserInput{


    private UserProcess user;
    private Constants constants = new Constants();
    private final Scanner input = new Scanner(System.in);

    private String userCurrentInput;

    public UserInput() {

    }

    // setter for wiring after creation
    public void setUserProcess(UserProcess user) {
        this.user = user;
    }

    /*
       Stores users current input and checks if the user wants to sign in or sign up.
     */
    public void userSignInSignUp() {
        userCurrentInput = input.nextLine();
        while(true) {
            if (userCurrentInput.equalsIgnoreCase("in")) {
                if (user != null) user.signInProcess();
                break;
            } else if (userCurrentInput.equalsIgnoreCase("up")) {
                if (user != null) user.signUpProcess();
                break;

            } else {
                System.out.print("Wrong input! you can only choose (in / up) >> ");
                userCurrentInput = input.nextLine();
            }
        }
    }

    public void userFirstNameInput() {
        userCurrentInput = input.nextLine();
        while(userCurrentInput.length() < constants.MIN_NAME_CHAR || userCurrentInput.length() > constants.MAX_NAME_CHAR) {
            System.out.print("You must have a name that has 3-20 characters >> ");
            userCurrentInput = input.nextLine();
        }
        if (user != null) {
            // example: user.setUserName(userCurrentInput);
        }
    }

    public void userLastNameInput() {
        userCurrentInput = input.nextLine();
        if (user != null) {
            // user.setUserLastName(userCurrentInput);
        }
    }

    // close scanner on app exit if added on exit
    public void closeScanner() {
        input.close();
    }

}
