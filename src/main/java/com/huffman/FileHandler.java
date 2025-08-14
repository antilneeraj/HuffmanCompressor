package com.huffman;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHandler {

    // Read full text file into a String
    public static String readTextFile(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }

    // Write text to a file
    public static void writeTextFile(String filePath, String content) throws IOException {
        Files.writeString(Path.of(filePath), content);
    }

    // Write compressed file with frequency table + encoded bits
    public static DataOutputStream getDataOutputStream(String filePath) throws IOException {
        return new DataOutputStream(new FileOutputStream(filePath));
    }

    // Write bits to compressed file
    public static BitOutputStream getBitOutputStream(OutputStream os) {
        return new BitOutputStream(os);
    }

    // Read compressed file header + bits
    public static DataInputStream getDataInputStream(String filePath) throws IOException {
        return new DataInputStream(new FileInputStream(filePath));
    }

    public static BitInputStream getBitInputStream(InputStream is) {
        return new BitInputStream(is);
    }
}
