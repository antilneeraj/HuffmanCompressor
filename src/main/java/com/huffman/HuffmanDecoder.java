package com.huffman;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class HuffmanDecoder {
    private HuffmanNode root;

    private void buildTreeFromCodes(Map<Character, String> codes) {
        root = new HuffmanNode('\0', 0);
        for (var entry : codes.entrySet()) {
            char ch = entry.getKey();
            String code = entry.getValue();
            HuffmanNode current = root;
            for (char bit : code.toCharArray()) {
                if (bit == '0') {
                    if (current.left == null) current.left = new HuffmanNode('\0', 0);
                    current = current.left;
                } else {
                    if (current.right == null) current.right = new HuffmanNode('\0', 0);
                    current = current.right;
                }
            }
            current.character = ch;
        }
    }

    public void decompressFile(String inputFile, String outputFile) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(inputFile);
             ObjectInputStream ois = new ObjectInputStream(fis);
             BitInputStream bitIn = new BitInputStream(fis)) {

            Map<Character, String> codes = (Map<Character, String>) ois.readObject();
            buildTreeFromCodes(codes);

            StringBuilder decoded = new StringBuilder();
            HuffmanNode current = root;
            int bit;
            while ((bit = bitIn.readBit()) != -1) {
                if (bit == 0) current = current.left;
                else current = current.right;
                if (current.isLeaf()) {
                    decoded.append(current.character);
                    current = root;
                }
            }

            Files.writeString(Path.of(outputFile), decoded.toString());
        }
    }
}
