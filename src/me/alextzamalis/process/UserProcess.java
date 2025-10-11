package me.alextzamalis.process;

import me.alextzamalis.encryption.Encryptor;
import me.alextzamalis.scanner.UserInput;
import me.alextzamalis.util.MessageUtil;

import java.security.NoSuchAlgorithmException;

public class UserProcess {

    private final Encryptor encryptor = new Encryptor();
    private final MessageUtil messageUtil;
    private final UserInput userInput;

    private boolean userHasAccount = false;
    private String userName;
    private String userLastName;
    private String userEmail;
    private int userAge;
    private String userPassword;
    private String hashedPassword;

    public UserProcess(UserInput userInput, MessageUtil messageUtil) {
        this.userInput = userInput;
        this.messageUtil = messageUtil;
    }

    public void signUpProcess() {
        messageUtil.signUpStageMessages();
        // call userInput methods to collect data:
        messageUtil.userFirstNameInput();
        messageUtil.userLastNameInput();
        userHasAccount = true;
    }

    public void signInProcess() {
        userHasAccount = true;
        messageUtil.signInsignUpMessage();

    }

    public void setUserFirstName(String firstName) {
        this.userName = firstName;
    }

    public String getUserFirstName(String firstName) {
        return firstName;
    }

    public void setUserLastName(String lastName) {
        this.userLastName = lastName;
    }

    public String getUserLastName(String lastName) {
        return lastName;
    }

    public void setUserEmail(String email) {
        this.userEmail = email;
    }

    public String getUserEmail(String email) {
        return email;
    }

    public void setUserPassword() throws NoSuchAlgorithmException {
        hashedPassword = encryptor.encryptString(this.userPassword);
    }

    public String getUserHashedPassword() throws NoSuchAlgorithmException {
        return this.hashedPassword;
    }

    void passwordChecker() throws NoSuchAlgorithmException {
        if(encryptor.encryptString(userPassword).equals(getUserHashedPassword())) {
            System.out.println("Correct");
        }
    }
}
