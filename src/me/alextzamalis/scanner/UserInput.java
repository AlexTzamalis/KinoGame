package me.alextzamalis.scanner;

import me.alextzamalis.process.UserProcess;
import java.util.Scanner;

public class UserInput {

    Scanner input = new Scanner(System.in);
    UserProcess user = new UserProcess();
    String userCurretInput;

    public UserInput() {

    }


    /*
       Stores users
     */
    public void userSignInSignUp() {
        userCurretInput = input.nextLine();
        while(true) {
            if (userCurretInput.equalsIgnoreCase("in")) {
                signInStage();
            } else if (userCurretInput.equalsIgnoreCase("up")) {
                signUpStage();
            } else {
                System.out.print("Wrong input! you can only choose (in / up) >> ");
                userCurretInput = input.nextLine();
            }
        }
    }

    /*
      Start sign in! process for the user in UserProcess class
     */
    public void signInStage() {
        user.signInProcess();

    }

    /*
      Start sign up! process for the user in UserProcess class
     */
    public void signUpStage() {
        user.signUpProcess();
    }

}
