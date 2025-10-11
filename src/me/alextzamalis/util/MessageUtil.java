package me.alextzamalis.util;

import me.alextzamalis.scanner.UserInput;

import java.util.Date;

public class MessageUtil {

    Date date = new Date();
    UserInput userInput = new UserInput();

    public MessageUtil() {
        currentDate();
        welcomeMesasge();
        signInsignUpMessage();

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
        userInput.userSignInSignUp();

    }

    public void signInStageMessages() {

    }

    public void signUpStageMessages() {

    }




}
