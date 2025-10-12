package me.alextzamalis;

import me.alextzamalis.process.UserProcess;
import me.alextzamalis.scanner.UserInput;
import me.alextzamalis.util.MessageUtil;

import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

public class MainClass {
    public static void main(String[] args) throws NoSuchAlgorithmException {

        Random random = new Random();                                   // Generates a random number
        UUID uuid = new UUID(random.nextLong(), random.nextLong());     // Generates random UUID with Long numbers

        UserInput userInput = new UserInput();                          // User input (might remove it from the main class
        MessageUtil messageUtil = new MessageUtil();                    // Calls few starting messages in app startup
        UserProcess userProcess = new UserProcess(userInput, messageUtil);                           // starts off for user sign up/sign in

        userInput.setUserProcess(userProcess);
        messageUtil.setUserInput(userInput);
        messageUtil.setUserProcess(userProcess);


        // APPLICATION START
        messageUtil.currentDate();
        messageUtil.welcomeMesasge();
        messageUtil.signInsignUpMessage();

        userInput.userSignInSignUp();
        userInput.userFirstNameInput(messageUtil);
        userInput.userLastNameInput(messageUtil);
        userInput.userEmailInput(messageUtil);
        userInput.userPasswordInput(messageUtil);

        userProcess.getUserHashedPassword();
        //new UserProcess();

        userInput.closeScanner();
    }

}