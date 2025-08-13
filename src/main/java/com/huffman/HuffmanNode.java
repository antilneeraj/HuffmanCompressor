package com.huffman;

public class HuffmanNode implements Comparable<HuffmanNode> {
    public char character;         // The character (for leaves), '\0' for internal nodes
    public int frequency;
    public HuffmanNode left, right;

    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        // Min-heap priority based on frequency
        return Integer.compare(this.frequency, other.frequency);
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }
}
