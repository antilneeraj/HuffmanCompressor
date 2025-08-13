package com.huffman;

import java.util.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class HuffmanEncoder {

    private Map<Character, String> huffmanCodes = new HashMap<>();
    private HuffmanNode root;

    public void buildTree(Map<Character, Integer> freqMap) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();

        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Combine nodes until single tree remains
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

    // Recursive DFS to assign codes
    private void generateCodes(HuffmanNode node, String code) {
        if (node == null) return;

        if (node.isLeaf()) {
            huffmanCodes.put(node.character, code.length() > 0 ? code : "0"); // Handle single-char case
        } else {
            generateCodes(node.left, code + "0");
            generateCodes(node.right, code + "1");
        }
    }

    public void compress(String text, String outputFile) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(outputFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos);
             BitOutputStream bitOut = new BitOutputStream(fos)) {

            // Write frequency map first (so we can rebuild tree at decompression)
            oos.writeObject(huffmanCodes);

            // Encode text into bits
            for (char c : text.toCharArray()) {
                String code = huffmanCodes.get(c);
                bitOut.writeBits(code);
            }
        }
    }

    public Map<Character, String> getHuffmanCodes() {
        return huffmanCodes;
    }

    public HuffmanNode getRoot() {
        return root;
    }
}
