package com.huffman;

import java.io.IOException;
import java.io.InputStream;

public class BitInputStream implements AutoCloseable {
    private InputStream in;
    private int currentByte;
    private int numBitsRemaining;

    public BitInputStream(InputStream in) {
        this.in = in;
        this.numBitsRemaining = 0;
    }

    public int readBit() throws IOException {
        if (currentByte == -1) return -1; // EOF

        if (numBitsRemaining == 0) {
            currentByte = in.read();
            if (currentByte == -1) return -1; // EOF
            numBitsRemaining = 8;
        }
        numBitsRemaining--;
        return (currentByte >>> numBitsRemaining) & 1;
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}
