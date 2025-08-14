package com.huffman;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HuffmanEncoderTest {

    @Test
    void testBuildTreeAndGenerateCodes() {
        Map<Character, Integer> freqMap = new HashMap<>();
        freqMap.put('a', 5);
        freqMap.put('b', 9);
        freqMap.put('c', 12);
        freqMap.put('d', 13);

        HuffmanEncoder encoder = new HuffmanEncoder();
        encoder.buildTree(freqMap);

        Map<Character, String> codes = encoder.getHuffmanCodes();

        assertEquals(4, codes.size());
        assertTrue(codes.containsKey('a'));
        assertTrue(codes.containsKey('b'));

        // Codes should be prefix-free — no code should be prefix of another
        for (String code1 : codes.values()) {
            for (String code2 : codes.values()) {
                if (code1 != code2) {
                    assertFalse(code2.startsWith(code1));
                }
            }
        }
    }

    @Test
    void testSingleCharEdgeCase() {
        Map<Character, Integer> freqMap = new HashMap<>();
        freqMap.put('x', 10);

        HuffmanEncoder encoder = new HuffmanEncoder();
        encoder.buildTree(freqMap);

        Map<Character, String> codes = encoder.getHuffmanCodes();

        assertEquals(1, codes.size());
        assertEquals("0", codes.get('x'));
    }
}
