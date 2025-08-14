package com.huffman;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HuffmanNodeTest {

    @Test
    void testIsLeaf() {
        HuffmanNode leaf = new HuffmanNode('a', 5);
        assertTrue(leaf.isLeaf());

        HuffmanNode parent = new HuffmanNode('\0', 10);
        parent.left = leaf;
        parent.right = new HuffmanNode('b', 5);
        assertFalse(parent.isLeaf());
    }

    @Test
    void testCompareTo() {
        HuffmanNode n1 = new HuffmanNode('a', 5);
        HuffmanNode n2 = new HuffmanNode('b', 8);
        assertTrue(n1.compareTo(n2) < 0);
        assertTrue(n2.compareTo(n1) > 0);
    }
}
