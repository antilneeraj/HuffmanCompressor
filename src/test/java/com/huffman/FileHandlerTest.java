package com.huffman;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    @Test
    void testReadWriteFile() throws IOException {
        String tempFile = "out/testfile.txt";
        String content = "Hello Huffman!";

        FileHandler.writeTextFile(tempFile, content);
        String readContent = FileHandler.readTextFile(tempFile);

        assertEquals(content, readContent);

        Files.deleteIfExists(Path.of(tempFile));
    }
}
