package com.huffman;

import java.io.*;
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

    public void compressFile(String inputFile, String outputFile) throws IOException {
        String text = FileHandler.readTextFile(inputFile);

        // Build frequency map
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Build Huffman tree
        buildTree(freqMap);

        try (DataOutputStream dos = FileHandler.getDataOutputStream(outputFile)){

            // ----- Optimized header -----
            dos.writeInt(freqMap.size()); // Number of unique chars
            for (var entry : freqMap.entrySet()) {
                dos.writeChar(entry.getKey()); // Char (2 bytes in Java char storage)
                dos.writeInt(entry.getValue()); // Frequency (4 bytes)
            }
            // ----------------------------

            // write bitstream (encoded file content)
            try (BitOutputStream bitOut = FileHandler.getBitOutputStream(dos)) {
                for (char c : text.toCharArray()) {
                    bitOut.writeBits(huffmanCodes.get(c));
                }
            }
        }
    }

    public Map<Character, String> getHuffmanCodes() {
        return huffmanCodes;
    }
}
