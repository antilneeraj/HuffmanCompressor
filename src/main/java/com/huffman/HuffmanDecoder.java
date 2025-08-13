package com.huffman;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HuffmanDecoder {

    private HuffmanNode root;

    private void buildTreeFromCodes(Map<Character, String> codes) {
        root = new HuffmanNode('\0', 0);

        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            char ch = entry.getKey();
            String code = entry.getValue();
            HuffmanNode current = root;

            for (char bit : code.toCharArray()) {
                if (bit == '0') {
                    if (current.left == null) {
                        current.left = new HuffmanNode('\0', 0);
                    }
                    current = current.left;
                } else if (bit == '1') {
                    if (current.right == null) {
                        current.right = new HuffmanNode('\0', 0);
                    }
                    current = current.right;
                }
            }
            current.character = ch;
        }
    }

    public String decompress(String inputFile) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(inputFile);
             ObjectInputStream ois = new ObjectInputStream(fis);
             BitInputStream bitIn = new BitInputStream(fis)) {

            // Read Huffman code map
            Map<Character, String> huffmanCodes = (Map<Character, String>) ois.readObject();

            // Rebuild tree
            buildTreeFromCodes(huffmanCodes);

            // Decode bits
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
            return decoded.toString();
        }
    }
}
