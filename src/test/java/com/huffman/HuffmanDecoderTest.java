package com.huffman;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HuffmanDecoderTest {

    @Test
    void testBuildTreeReconstruction() {
        Map<Character, Integer> freqMap = new HashMap<>();
        freqMap.put('a', 3);
        freqMap.put('b', 2);

        HuffmanDecoder decoder = new HuffmanDecoder();
        decoder.buildTree(freqMap);

        // The root frequency should equal sum of frequencies
        assertEquals(5, decoder.getRoot().frequency);
    }
}
