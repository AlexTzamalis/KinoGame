package me.alextzamalis.util;

import me.alextzamalis.process.User;
import java.util.Date;

public class MessageUtil {

    Date date = new Date();

    public MessageUtil() {
        currentDate();
        welcomeMesasge();
        signInsignUpMessage();

    }

    public void welcomeMesasge() {
        System.out.println("--- Welcome to Kino Game! ---");
        System.out.println();
    }

    public void signInsignUpMessage() {
        System.out.print("Sign In? or Sign up? (in/up)");
    }

    public void currentDate() {
        System.out.println(this.date);
    }
}
