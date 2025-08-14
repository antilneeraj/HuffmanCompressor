package com.huffman;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {

    @Test
    void testCompressDecompressText() throws Exception {
        String input = "huffman coding test string";
        String inputPath = "src/test/resources/test_sample.txt";
        String compressedPath = "out/test_compressed.bin";
        String decompressedPath = "out/test_decompressed.txt";

        FileHandler.writeTextFile(inputPath, input);

        HuffmanEncoder encoder = new HuffmanEncoder();
        encoder.compressFile(inputPath, compressedPath);

        HuffmanDecoder decoder = new HuffmanDecoder();
        decoder.decompressFile(compressedPath, decompressedPath);

        String output = FileHandler.readTextFile(decompressedPath);

        assertEquals(input, output);

        Files.deleteIfExists(Path.of(inputPath));
        Files.deleteIfExists(Path.of(compressedPath));
        Files.deleteIfExists(Path.of(decompressedPath));
    }
}
