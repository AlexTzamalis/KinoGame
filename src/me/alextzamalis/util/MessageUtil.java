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
        //this.userProcess = userProcess;
    }

    public void currentDate() {
        System.out.println(this.date);
    }

    public void welcomeMesasge() {
        System.out.println("--- Welcome to Kino Game! ---");
        System.out.println();
    }

    public void signInsignUpMessage() {
        System.out.print("Sign In? or Sign up? (in/up) >> ");
        //userInput.userSignInSignUp();

    }

    public void userFirstNameInput(){
        System.out.print("Enter your First Name: ");
        //userInput.userFirstNameInput();
    }

    public void userLastNameInput() {
        System.out.print("Enter your Last Name: ");
        //userInput.userLastNameInput();
    }

    public void signUpStageMessages() {
        System.out.print("Give your name: ");

    }
}
