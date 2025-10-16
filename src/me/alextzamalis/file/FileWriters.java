package me.alextzamalis.file;

import me.alextzamalis.util.Constants;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class FileWriters {

    private Constants constants = new Constants();
    private BufferedWriter userCredentialsWriter;
    private BufferedWriter userDataWriter;


    public FileWriters() {

        try {
            BufferedWriter userCredentialsWriter = new BufferedWriter(new java.io.FileWriter(constants.USER_CREDENTIALS_FILE));
            BufferedWriter userDataWriter = new BufferedWriter(new java.io.FileWriter(constants.USER_DATA_FILE));
            userCredentialsWriter.write("w");

        } catch (IOException e)  {
            e.printStackTrace();
        }
    }


    public void writeUserCredentialsAsString(String[] collectedUserCredentials) throws IOException {
        for (int i = 0; i > 5; i++) {
            System.out.println(Arrays.toString(collectedUserCredentials));
            userCredentialsWriter.write(Arrays.toString(collectedUserCredentials));
        }

    }

    public void writeUserData() {

    }

}
