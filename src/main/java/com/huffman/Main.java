package com.huffman;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length != 3) {
                System.out.println("Usage:");
                System.out.println("  java -jar Huffman.jar compress <inputFile> <outputFile>");
                System.out.println("  java -jar Huffman.jar decompress <inputFile> <outputFile>");
                return;
            }

            String command = args[0].toLowerCase();
            String inputFile = args[1];
            String outputFile = args[2];

            HuffmanEncoder encoder = new HuffmanEncoder();
            HuffmanDecoder decoder = new HuffmanDecoder();

            switch (command) {
                case "compress":
                    encoder.compressFile(inputFile, outputFile);
                    System.out.println("✅ File compressed successfully to: " + outputFile);
                    break;

                case "decompress":
                    decoder.decompressFile(inputFile, outputFile);
                    System.out.println("✅ File decompressed successfully to: " + outputFile);
                    break;

                default:
                    System.out.println("❌ Unknown command: " + command);
                    System.out.println("Valid commands are: compress | decompress");
            }

        } catch (Exception e) {
            System.err.println("⚠ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
