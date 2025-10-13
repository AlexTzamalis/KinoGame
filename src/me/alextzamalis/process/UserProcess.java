package me.alextzamalis.process;

import me.alextzamalis.encryption.Encryptor;
import me.alextzamalis.file.FileWriters;
import me.alextzamalis.scanner.UserInput;
import me.alextzamalis.util.MessageUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class UserProcess {

    private final Encryptor encryptor = new Encryptor();
    private final MessageUtil messageUtil;
    private final UserInput userInput;
    private final FileWriters fileWriters;

    private boolean userHasAccount = false;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private int userAge;
    private String userPassword;
    private String hashedPassword;

    private String[] userCredentialsArray = new String[5];

    public UserProcess(UserInput userInput, MessageUtil messageUtil, FileWriters fileWriters) {
        this.userInput = userInput;
        this.messageUtil = messageUtil;
        this.fileWriters = fileWriters;
    }


    public void signUpProcess() {
        //messageUtil.signUpStageMessages();
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
        this.userFirstName = firstName;
        registerUserFirstName(userFirstName);
    }

    public String getUserFirstName() {
        return this.userFirstName;
    }

    public void registerUserFirstName(String firstName) {
        userCredentialsArray[0] = firstName;

    }

    public void setUserLastName(String lastName) {
        this.userLastName = lastName;
        registerUserLastName(userLastName);
    }

    public String getUserLastName(String lastName) {
        return lastName;
    }

    public void registerUserLastName(String lastName) {
        userCredentialsArray[1] = lastName;
    }

    public void setUserAge(int age) {
        this.userAge = age;
        registerUserAge(String.valueOf(userAge));
    }

    public int getUserAge(int age) {
        return age;
    }

    public void registerUserAge(String age) {
        userCredentialsArray[2] = age;

    }

    public void setUserEmail(String email) {
        this.userEmail = email;
        registerUserEmail(userEmail);
    }

    public String getUserEmail(String email) {
        return email;
    }

    public void registerUserEmail(String email) {
        userCredentialsArray[3] = email;
    }

    public void setUserPassword(String password) throws NoSuchAlgorithmException {
        userPassword = password;
        // DEBUG System.out.println("DEBUG >> password = " + userPassword);
        setCurrentPasswordToHashed(password);
    }

    public void setCurrentPasswordToHashed(String password) throws NoSuchAlgorithmException {
        // DEBUG System.out.println("password = " + password + " hashedPassword = " + hashedPassword);
        hashedPassword = encryptor.encryptString(password);

        // current passwords reset in here and we only store the hashed password
        userPassword = null;
        password = null;

        registerUserHashedPassword(hashedPassword);
    }


    public String getUserHashedPassword() throws NoSuchAlgorithmException {
        return this.hashedPassword;
    }

    public void registerUserHashedPassword(String hashedPassword) {
        userCredentialsArray[4] = hashedPassword;
    }

    private void passwordChecker() throws NoSuchAlgorithmException {
        if (encryptor.encryptString(userPassword).equals(getUserHashedPassword())) {
            System.out.println("Correct");
        }
    }


    public void collectUserCredentials() throws IOException {
        System.out.println("DEBUG >> Entered collectUserCredentials!");
        for (int i = 0; i < userCredentialsArray.length; i++) {
            System.out.println(userCredentialsArray[i]);
        }

        fileWriters.writeUserCredentialsAsString(userCredentialsArray);
    }
}
