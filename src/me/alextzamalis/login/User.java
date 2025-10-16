package me.alextzamalis.login;

public class User {
    private String username;
    private String password;
    private String email;
    private String secretQuestion;
    private String secretAnswer;

    private String passwordHashed;

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
        this.passwordHashed = password;
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

    /*
     Update user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    //public
}
