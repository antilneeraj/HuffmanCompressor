# HuffmanCompressor
```
HuffmanCompressor/
│
├── build.gradle.kts       # Gradle Kotlin DSL build configuration
├── settings.gradle.kts    # Gradle project settings (rootProject.name, etc.)
├── README.md              # Project documentation
├── .gitignore             # Git ignore rules
├── out/                   # <-- Gradle build outputs (ignored by git)
│   ├── classes/
│   │   ├── java/
│   │   │   ├── main/              # Compiled main source .class files
│   │   │   └── test/              # Compiled test .class files
│   ├── libs/                      # Built JAR files
│   ├── reports/                   # Test + build reports
│   └── ...
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── huffman/
│   │   │           ├── Main.java               # Entry point class
│   │   │           ├── HuffmanNode.java        # Tree node class
│   │   │           ├── HuffmanEncoder.java     # Compression engine
│   │   │           ├── HuffmanDecoder.java     # Decompression engine
│   │   │           ├── FileHandler.java        # File I/O utilities
│   │   │           └── BitWriterReader.java    # Bit-level write/read helper
│   │   └── resources/
│   │       ├── sample.txt           # Test input file 
│   │
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── huffman/
│       │           ├── HuffmanEncoderTest.java
│       │           ├── HuffmanDecoderTest.java
│       │           └── FileHandlerTest.java
│       └── resources/
│           └── test_sample.txt
│
└── gradle/                 # Gradle wrapper files
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
└── ...
```