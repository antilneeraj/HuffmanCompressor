package com.huffman;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class HuffmanEncoder {
    private Map<Character, String> huffmanCodes = new HashMap<>();
    private HuffmanNode root;

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
        generateCodes(root, "");
    }

    private void generateCodes(HuffmanNode node, String code) {
        if (node == null) return;
        if (node.isLeaf()) {
            huffmanCodes.put(node.character, code.length() > 0 ? code : "0");
        } else {
            generateCodes(node.left, code + "0");
            generateCodes(node.right, code + "1");
        }
    }

    // Read file, build huffman tree, compress to file
    public void compressFile(String inputFile, String outputFile) throws IOException {
        // Read file into string
        String text = Files.readString(Path.of(inputFile));

        // Build frequency map
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Build Huffman tree
        buildTree(freqMap);

        try (FileOutputStream fos = new FileOutputStream(outputFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos);
             BitOutputStream bitOut = new BitOutputStream(fos)) {

            // Write code map to header
            oos.writeObject(huffmanCodes);

            // Encode file content
            for (char c : text.toCharArray()) {
                bitOut.writeBits(huffmanCodes.get(c));
            }
        }
    }
}
