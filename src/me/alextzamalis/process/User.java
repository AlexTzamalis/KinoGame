package me.alextzamalis.process;

import me.alextzamalis.encryption.Encryptor;

import java.security.NoSuchAlgorithmException;

public class User {

    Encryptor encryptor = new Encryptor();

    private String userName;
    private String userLastName;
    private String userEmail;
    private String userPassword;
    private int userAge;

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

    void setUserPassword() {

    }

    String getUserPassword(String password) throws NoSuchAlgorithmException {

        return encryptor.encryptString(this.userPassword);
    }



}
