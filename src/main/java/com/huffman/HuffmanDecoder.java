package com.huffman;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class HuffmanDecoder {
    private HuffmanNode root;

    public HuffmanNode getRoot() {
        return root;
    }

    public void buildTree(Map<Character, Integer> freqMap) {
        var pq = new java.util.PriorityQueue<HuffmanNode>();
        for (var entry : freqMap.entrySet()) {
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            pq.add(parent);
        }
        root = pq.poll();
    }

    public void decompressFile(String inputFile, String outputFile) throws IOException {
        try (FileInputStream fis = new FileInputStream(inputFile);
             DataInputStream dis = new DataInputStream(fis);
             BitInputStream bitIn = new BitInputStream(fis)) {

            // ----- Read optimized header -----
            int uniqueCharCount = dis.readInt();
            Map<Character, Integer> freqMap = new HashMap<>();
            for (int i = 0; i < uniqueCharCount; i++) {
                char ch = dis.readChar();
                int freq = dis.readInt();
                freqMap.put(ch, freq);
            }
            // ---------------------------------

            // Build Huffman tree from frequency map
            buildTree(freqMap);

            // Decode bits to characters
            StringBuilder decoded = new StringBuilder();
            HuffmanNode current = root;
            int bit;
            int totalChars = freqMap.values().stream().mapToInt(Integer::intValue).sum();
            int decodedCount = 0;

            while ((bit = bitIn.readBit()) != -1 && decodedCount < totalChars) {
                if (bit == 0) current = current.left;
                else current = current.right;
                if (current.isLeaf()) {
                    decoded.append(current.character);
                    current = root;
                    decodedCount++;
                }
            }

            Files.writeString(Path.of(outputFile), decoded.toString());
        }
    }
}
