package me.alextzamalis.process;

import me.alextzamalis.encryption.Encryptor;

import java.security.NoSuchAlgorithmException;

public class User {

    Encryptor encryptor = new Encryptor();

    private String userName;
    private String userLastName;
    private String userEmail;
    private int userAge;

    private String userPassword;
    private String hashedPassword;

    public User() {

    }


    void setUserName() {

    }

    String getUserName(String name) {

        return name;
    }

    void setUserLastName() {

    }

    String getUseRLastName(String lastName) {

        return lastName;
    }

    void setUserEmail() {

    }

    String getUserEmail(String email) {

        return email;
    }

    void setUserPassword() throws NoSuchAlgorithmException {
        hashedPassword = encryptor.encryptString(this.userPassword);

    }

    String getUserHashedPassword() throws NoSuchAlgorithmException {

        return this.hashedPassword;
    }

    void passwordChecker() throws NoSuchAlgorithmException {
        if(encryptor.encryptString(userPassword).equals(getUserHashedPassword())) {
            System.out.println("Correct");
        }
    }



}
