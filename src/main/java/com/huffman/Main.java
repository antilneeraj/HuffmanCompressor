package com.huffman;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        HuffmanEncoder encoder = new HuffmanEncoder();
        HuffmanDecoder decoder = new HuffmanDecoder();

        String inputPath = "src/main/resources/sample.txt";
        String compressedPath = "out/sample-compressed.bin";
        String decompressedPath = "out/sample-decompressed.txt";

        // Compress
        encoder.compressFile(inputPath, compressedPath);
        System.out.println("File compressed to: " + compressedPath);

        // Decompress
        decoder.decompressFile(compressedPath, decompressedPath);
        System.out.println("File decompressed to: " + decompressedPath);
    }
}
