package me.alextzamalis.encription;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {

    public String encryptString(String input) throws NoSuchAlgorithmException {

        // MessageDigest works with MD2, MD5, SHA-1, SHA-224, SHA-256
        // SHA-384 and SHA-512
        MessageDigest md = MessageDigest.getInstance("MD5");

        return null;
    }


}
