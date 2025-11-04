package me.alextzamalis.encryption;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class for encrypting strings using cryptographic hashing.
 * 
 * <p>This class uses SHA-256 algorithm for password hashing, which is more secure
 * than MD5. SHA-256 produces a 256-bit (32-byte) hash value, making it resistant
 * to collision attacks and suitable for password storage.
 * 
 * <p><strong>Security Note:</strong> While SHA-256 is more secure than MD5, for production
 * applications, consider using bcrypt, Argon2, or PBKDF2 with salt for password hashing.
 * 
 * @author AlexTzamalis
 * @version 1.0
 */
public class Encryptor {

    /**
     * Encrypts a string using SHA-256 hashing algorithm.
     * 
     * <p>This method converts the input string to a hash value that cannot be
     * reversed. The same input will always produce the same hash, making it suitable
     * for password verification.
     * 
     * @param input the string to encrypt
     * @return the hexadecimal representation of the SHA-256 hash
     * @throws NoSuchAlgorithmException if SHA-256 algorithm is not available
     */
    public String encryptString(String input) throws NoSuchAlgorithmException {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }

        // Using SHA-256 instead of MD5 for better security
        // SHA-256 produces a 256-bit hash, making it more resistant to attacks
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Convert input string to bytes and compute hash
        byte[] messageDigest = md.digest(input.getBytes());

        // Convert byte array to BigInteger, then to hexadecimal string
        BigInteger bigInt = new BigInteger(1, messageDigest);

        // Return hexadecimal representation
        return bigInt.toString(16);
    }
}
