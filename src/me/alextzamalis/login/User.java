package me.alextzamalis.login;

import me.alextzamalis.encryption.Encryptor;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class User {

    private Encryptor encryptor = new Encryptor();

    private String username;
    private String password;
    private String email;
    private String secretQuestion;
    private String secretAnswer;
    private String passwordHashed;
    private UUID uuid;

    // Constructor
    public User(String username,
                String password,
                String email,
                String secretQuestion,
                String secretAnswer,
                UUID uuid) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.secretQuestion = secretQuestion;
        this.secretAnswer = secretAnswer;
        this.uuid = uuid;
    }

    /*
      Getters and Setters for the users credentials
    */
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public String getSecretAnswer() {
        return  secretAnswer;
    }

    public UUID getUUID() {
        return uuid;
    }

    /*
     Update user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }



}
