package com.huffman;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        String text = "this is an example for huffman encoding";

        // Frequency calculation
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Build tree & codes
        HuffmanEncoder encoder = new HuffmanEncoder();
        encoder.buildTree(freqMap);

        // Compress to file
        encoder.compress(text, "out/compressed.bin");

        System.out.println("Compression complete! File: " + new File("out/compressed.bin").getAbsolutePath());
    }
}
