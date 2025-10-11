package me.alextzamalis.process;

import me.alextzamalis.encryption.Encryptor;

import java.security.NoSuchAlgorithmException;

public class UserProcess {

    Encryptor encryptor = new Encryptor();

    private boolean userHasAccount = false;
    private String userName;
    private String userLastName;
    private String userEmail;
    private int userAge;

    private String userPassword;
    private String hashedPassword;

    public UserProcess() {

    }

    public void signInProcess() {

    }

    public void signUpProcess() {

    }


    public void setUserName() {

    }

    public String getUserName(String name) {

        return name;
    }

    public void setUserLastName() {

    }

    public String getUserLastName(String lastName) {

        return lastName;
    }

    public void setUserEmail() {

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
