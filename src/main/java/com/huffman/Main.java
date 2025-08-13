package com.huffman;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        String text = "this is an example for huffman encoding";

        // ---- Compression ----
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        HuffmanEncoder encoder = new HuffmanEncoder();
        encoder.buildTree(freqMap);
        encoder.compress(text, "out/compressed.bin");
        System.out.println("Compression complete!");

        // ---- Decompression ----
        HuffmanDecoder decoder = new HuffmanDecoder();
        String result = decoder.decompress("out/compressed.bin");

        System.out.println("Decompressed text: " + result);
        System.out.println("Matches original? " + text.equals(result));
    }
}
