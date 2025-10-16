package me.alextzamalis.login;

public class User {
    private String username;
    private String password;
    private String email;
    private String secretQuestion;
    private String secretAnswer;

    // Constructor
    public User(String username,
                String password,
                String email,
                String secretQuestion,
                String secretAnswer) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.secretQuestion = secretQuestion;
        this.secretAnswer = secretAnswer;
    }


}
