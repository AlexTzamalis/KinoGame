package me.alextzamalis.util;

import me.alextzamalis.process.UserProcess;
import me.alextzamalis.scanner.UserInput;

import java.io.IOException;
import java.util.Date;

public class MessageUtil{

    private Date date = new Date();
    private UserInput userInput;
    private UserProcess userProcess;


    public MessageUtil() {

    }

    // setters for wiring
    public void setUserInput(UserInput userInput) {
        this.userInput = userInput;
    }

    public void setUserProcess(UserProcess userProcess) {
        this.userProcess = userProcess;
    }

    public void currentDate() { System.out.println(this.date); }

    public void welcomeMesasge() { System.out.println("--- Welcome to Kino Game! ---"); System.out.println(); }

    public void signInsignUpMessage() { System.out.print("Sign In? or Sign up? (in/up) >> "); }

    public void userFirstNameInput(){
        System.out.print("Enter your First Name: ");
    }

    public void userFirstNameRetry() { System.out.print("You must have a name that has 3-20 characters >> "); }

    public void userLastNameInput() { System.out.print("Enter your Last Name: "); }

    public void userLastNameRetry() { System.out.print("You must have a last name that has 3-20 characters >> "); }

    public void userAgeInput() { System.out.print("Enter your age: "); }

    public void userAgeRetry() { System.out.print("You must be atleast 21 to sign up!: "); }

    public void userEmailInput() { System.out.print("Enter your Email: "); }

    public void userInvalidEmailExceptionRetry() {
        System.out.print("Please enter a valid Email: ");
    }

    public void userEmailExceptionRetry() {
        System.out.print("Try again: ");
    }

    public void userPasswordInput() { System.out.print("Enter your Password: "); }

    public void userPasswordRetry() { System.out.print("You must have a password that has atleast 4 or more digits: "); }

    public void successfulSignUp() {
        System.out.println("You signed up successfully!");
    }

    public void successfulSignIn(String name) { System.out.println("Welcome back " + name ); }
}
