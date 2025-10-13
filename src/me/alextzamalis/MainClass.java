package me.alextzamalis;

import me.alextzamalis.file.FileWriters;
import me.alextzamalis.process.UserProcess;
import me.alextzamalis.scanner.UserInput;
import me.alextzamalis.util.MessageUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

public class MainClass {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        Random random = new Random();                                                       // Generates a random number
        UUID uuid = new UUID(random.nextLong(), random.nextLong());                         // Generates random UUID with Long numbers
        UserInput userInput = new UserInput();                                              // User input
        MessageUtil messageUtil = new MessageUtil();                                        // Mesasge Utility for All message prints to the console
        FileWriters fileWriters = new FileWriters();                                        // Writes text to files
        UserProcess userProcess = new UserProcess(userInput, messageUtil, fileWriters);     // starts off for user sign up/sign in

        userInput.setUserProcess(userProcess);
        messageUtil.setUserInput(userInput);
        messageUtil.setUserProcess(userProcess);

        // APPLICATION START
        messageUtil.currentDate();
        messageUtil.welcomeMesasge();
        messageUtil.signInsignUpMessage();

        // SIGN IN
        userInput.userSignInSignUp();
        if (userInput.signInSignUpCheck()) {
            userInput.userEmailInput(messageUtil);
            userInput.userPasswordInput(messageUtil);

            messageUtil.successfulSignIn(userProcess.getUserFirstName());
        }
        // SIGN UP
        else{
            userInput.userFirstNameInput(messageUtil);
            userInput.userLastNameInput(messageUtil);
            userInput.userAgeInput(messageUtil);
            userInput.userEmailInput(messageUtil);
            userInput.userPasswordInput(messageUtil);
            userProcess.collectUserCredentials();

            messageUtil.successfulSignUp();
        }

        // application termination
        userInput.closeScanner();
    }
}