package me.alextzamalis.file;

import me.alextzamalis.util.Constants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class FileWriter {

    Constants constants = new Constants();

    public FileWriter() {

        try {
            BufferedWriter userCredentialsWriter = new BufferedWriter(new java.io.FileWriter(constants.USER_CREDENTIALS_FILE));
            BufferedWriter userDataWriter = new BufferedWriter(new java.io.FileWriter(constants.USER_DATA_FILE));
        } catch (IOException e)  {
            e.printStackTrace();
        }

    }
}
