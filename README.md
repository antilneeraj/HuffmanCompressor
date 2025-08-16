# Text File Compression and Decompression Using Huffman Coding

[![Java](https://img.shields.io/badge/Java-21+-orange.svg)](https://www.oracle.com/java/)
[![Gradle](https://img.shields.io/badge/Gradle-8.14-green.svg)](https://gradle.org/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](#license)

<img width="1232" height="670" alt="image" src="https://github.com/user-attachments/assets/de7d0e24-55fa-48dc-820a-90ba08309fc4" />

## Table of Contents

- [Introduction](#introduction)
- [Highlights](#highlights)
- [Objective](#objective)
- [Methodology](#methodology)
- [Software and Libraries Used](#software-and-libraries-used)
- [Directory Structure](#directory-structure)
- [Project Flow Diagram](#project-flow-diagram)
- [Usage](#usage)
- [Setup](#setup)
    - [Prerequisites](#prerequisites)
    - [Building the Project](#building-the-project)
    - [Setting up Aliases (Windows)](#setting-up-aliases-windows)
- [Examples](#examples)
- [Future Work](#future-work)
- [Contributing](#contributing)
- [License](#license)

---

## Introduction

This project implements a **lossless text file compression and decompression tool** using the **Huffman Coding algorithm
**. The tool can efficiently compress text files by assigning variable-length binary codes to characters based on their
frequency of occurrence, achieving compression ratios of typically 40-50% for text files.

Huffman Coding is a greedy algorithm that builds an optimal prefix code tree, ensuring that no code is a prefix of
another, making the compressed data uniquely decodable without data loss.

---

## Highlights

✅ **Lossless Compression** - Perfect reconstruction of original files  
✅ **Variable-Length Encoding** - More frequent characters get shorter codes  
✅ **Optimal Compression** - Uses Huffman's greedy algorithm for optimal prefix codes  
✅ **Cross-Platform** - Works on Windows, macOS, and Linux  
✅ **CLI Interface** - Easy-to-use command-line interface  
✅ **Modular Design** - Clean separation of concerns with dedicated classes  
✅ **Comprehensive Tests** - Unit and integration tests for reliability  
✅ **Optimized File Format** - Minimal header overhead for better compression ratios

---

## Objective

The primary objectives of this project are to:

1. **Demonstrate the practical implementation** of Huffman Coding algorithm in Java
2. **Achieve significant compression** of text files while maintaining data integrity
3. **Provide a user-friendly CLI tool** for file compression and decompression
4. **Showcase software engineering best practices** including modular design, testing, and documentation
5. **Create an educational resource** for understanding lossless compression algorithms

---

## Methodology

The compression process follows these key steps:

### Compression

1. **Frequency Analysis** - Calculate frequency of each character in the input file
2. **Huffman Tree Construction** - Build optimal binary tree using a min-heap (priority queue)
3. **Code Generation** - Assign binary codes: '0' for left branches, '1' for right branches
4. **File Header Creation** - Store character frequencies for decompression
5. **Bit-Level Encoding** - Replace characters with their Huffman codes and pack into bytes

### Decompression

1. **Header Parsing** - Read character frequencies from compressed file
2. **Tree Reconstruction** - Rebuild the Huffman tree using stored frequencies
3. **Bit-by-Bit Decoding** - Traverse tree according to bits until leaf nodes are reached
4. **Character Output** - Write decoded characters to output file

### Key Algorithms Used

- **Min-Heap (Priority Queue)** - For efficient tree construction
- **Greedy Algorithm** - Huffman's approach for optimal code assignment
- **Binary Tree Traversal** - For code generation and decoding
- **Bit Manipulation** - For efficient binary I/O operations

---
## Project Flow Diagram
[![](https://mermaid.ink/img/pako:eNqVVN1u0zAUfhXLF2xIacl_2jB6MZcxLtDQVoREwoWbnLaREqc49lipersH4BF5Ek7idTIS2sAXPv75zt93jr2nRVsCTela8u2GLOa5IDg6vTQHcyjaZiuh66pWkI-yLXBpMP2Ye1lOz8rqlnRqV8ObEwV3avQdVdN-en0yuwZekkucQJLTCwnfNIhiRxZ8WcPLs1eoOsvpV8uin13DUlc1aunVquGCLCSAjQiywehb0YdekvNKdUoCb2xMmC0kvwXZwaBOXgyZlDCgbWD0XAKfZaXA4gE9LhBDVEuutNpqZSVhE0NGoxlmY0RgRGhEZHAgyqPKI9_sKbaZZzJ_L9CtieKiqm1umJ8xXhe65hgz23BJjpRXYKfNguz8KY5Z-Bwt70CA7L0cLTBktyOng9Nf9z97osmNkpVY_63M7B95_4_OYXFmWsIw86lD139GZ4OTzDiwu6iv6QOhNulD0ZipJDOVZKaSLDIiNiKx6kod2oBseFXi49r3FzlVG2ggpykuBWgleZ3TXBwQyrVqb3aioKmSGhwqW73e0HTF6w53elsi0_OKY480j6dbLr60bXNUwS1N9_SOpp7rjoM4mnpR4vmu5we-Q3c0DeNx4vthnCRT18fhHRz6YzDgjifxxI9DbzoJ3ACvIgf_gz7yh2gwI5Cs1UKhdQQ5FMpKtfKD-TuGL-TwG5zoULs?type=png)](https://mermaid.live/edit#pako:eNqVVN1u0zAUfhXLF2xIacl_2jB6MZcxLtDQVoREwoWbnLaREqc49lipersH4BF5Ek7idTIS2sAXPv75zt93jr2nRVsCTela8u2GLOa5IDg6vTQHcyjaZiuh66pWkI-yLXBpMP2Ye1lOz8rqlnRqV8ObEwV3avQdVdN-en0yuwZekkucQJLTCwnfNIhiRxZ8WcPLs1eoOsvpV8uin13DUlc1aunVquGCLCSAjQiywehb0YdekvNKdUoCb2xMmC0kvwXZwaBOXgyZlDCgbWD0XAKfZaXA4gE9LhBDVEuutNpqZSVhE0NGoxlmY0RgRGhEZHAgyqPKI9_sKbaZZzJ_L9CtieKiqm1umJ8xXhe65hgz23BJjpRXYKfNguz8KY5Z-Bwt70CA7L0cLTBktyOng9Nf9z97osmNkpVY_63M7B95_4_OYXFmWsIw86lD139GZ4OTzDiwu6iv6QOhNulD0ZipJDOVZKaSLDIiNiKx6kod2oBseFXi49r3FzlVG2ggpykuBWgleZ3TXBwQyrVqb3aioKmSGhwqW73e0HTF6w53elsi0_OKY480j6dbLr60bXNUwS1N9_SOpp7rjoM4mnpR4vmu5we-Q3c0DeNx4vthnCRT18fhHRz6YzDgjifxxI9DbzoJ3ACvIgf_gz7yh2gwI5Cs1UKhdQQ5FMpKtfKD-TuGL-TwG5zoULs)

---

## Software and Libraries Used

| Technology        | Version  | Purpose                                    |
|-------------------|----------|--------------------------------------------|
| **Java**          | 21.0.5   | Core programming language                  |
| **Gradle**        | 8.14     | Build automation and dependency management |
| **JUnit 5**       | 5.10.0   | Unit testing framework                     |
| **IntelliJ IDEA** | 2024.2.6 | Integrated Development Environment         |

### Core Java Libraries

- `java.util.PriorityQueue` - Min-heap for Huffman tree construction
- `java.util.HashMap` - Frequency counting and code mapping
- `java.io.*` - File I/O operations
- `java.nio.file.*` - Modern file handling

---

## Directory Structure

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
---
## Usage

### Command Line Interface

#### Compress a file:

```bash
java -jar out/libs/HuffmanCompressor-1.0-SNAPSHOT.jar compress input.txt compressed.bin
```

#### Decompress a file:

```bash
java -jar out/libs/HuffmanCompressor-1.0-SNAPSHOT.jar decompress compressed.bin restored.txt
```

### Using Gradle Tasks (Development)

### Compress

```bash
./gradlew runHuffman --args="compress input.txt output.bin"
```

### Decompress

```bash
./gradlew runHuffman --args="decompress output.bin restored.txt"
```

---

## Setup

### Prerequisites

- **Java Development Kit (JDK) 17 or higher**
- **Git** (for cloning the repository)
- **Windows PowerShell** or **Command Prompt** (Windows users)

### Building the Project

1. **Clone the repository:**
```bash
git clone https://github.com/antilneeraj/HuffmanCompressor.git
cd HuffmanCompressor
```

2. **Build the project:**
```bash
./gradlew clean build
```

3. **Run tests:**
```bash
./gradlew test 
```

4. **The executable JAR will be generated at:** `out/libs/HuffmanCompressor-1.0-SNAPSHOT.jar`

### Setting up Aliases (Windows)

For convenient usage, set up PowerShell aliases:

1. **Open PowerShell as Administrator**

2. **Create/Edit your PowerShell profile:**
```bash
notepad $profile
```

3. **Add these functions to the profile:**
```bash
function huffc {
param($inputFile, $outputFile)
java -jar "C:\path\to\your\project\out\libs\HuffmanCompressor-1.0-SNAPSHOT.jar" compress $inputFile $outputFile
}

function huffd {
param($inputFile, $outputFile)
java -jar "C:\path\to\your\project\out\libs\HuffmanCompressor-1.0-SNAPSHOT.jar" decompress $inputFile $outputFile
}
```

*Replace `C:\path\to\your\project\` with your actual project path*

4. **Save and reload your profile:**
```bash
. $profile
```

5. **Now you can use short commands:**
```bash
huffc input.txt compressed.bin
huffd compressed.bin restored.txt
```

---

## Examples

### Basic Compression Example

### 1. Create a Sample Text File

```bash
echo "This is a sample text file for Huffman compression testing." > sample.txt
```
### Compress the file
```bash
java -jar out/libs/HuffmanCompressor-1.0-SNAPSHOT.jar compress sample.txt sample.bin
```
### Check compression ratio
`Original: ~62 bytes, Compressed: ~35-40 bytes (typical 35-45% reduction)`

### Decompress back
```bash
java -jar out/libs/HuffmanCompressor-1.0-SNAPSHOT.jar decompress sample.bin recovered.txt
```
### Verify integrity
```bash
fc sample.txt recovered.txt # Windows
diff sample.txt recovered.txt # Linux/macOS
```

### Performance Characteristics
- **Compression Ratio:** 35-50% size reduction for typical text files
- **Time Complexity:** O(n log k) where n = file size, k = unique characters
- **Space Complexity:** O(k) for storing the Huffman tree
- **File Types:** Works best with `.txt`, `.java`, `.html`, `.css`, `.js`, etc.

---

## Future Work
### Planned Enhancements
- [ ] **GUI Interface** - Develop a user-friendly graphical interface
- [ ] **Batch Processing** - Support for compressing multiple files at once
- [ ] **Directory Compression** - Compress entire folders recursively
- [ ] **Compression Statistics** - Detailed reports on compression ratios and performance
- [ ] **Advanced Algorithms** - Implement LZ77, LZW, or hybrid compression methods
- [ ] **Binary File Support** - Extend support to non-text file types
- [ ] **Progress Indicators** - Show compression/decompression progress for large files
- [ ] **Compression Level Options** - Different optimization levels for speed vs. ratio trade-offs

### Algorithmic Improvements
- [ ] **Adaptive Huffman Coding** - Update tree dynamically as file is processed
- [ ] **Canonical Huffman Codes** - More efficient code representation
- [ ] **Multi-threading** - Parallel processing for large files
- [ ] **Memory Optimization** - Streaming compression for files larger than available RAM

---

## Contributing

Contributions are welcome! Please feel free to submit issues, suggestions, or pull requests.

### How to Contribute
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Development Guidelines
- Follow Java coding conventions
- Write unit tests for new features
- Update documentation as needed
- Ensure all tests pass before submitting

---

## License

This project is licensed under the [MIT License](LICENSE)

---

## Acknowledgments

- **David A. Huffman** - For the Huffman Coding algorithm (1952)
- **GeeksforGeeks** - For algorithmic reference and inspiration
- **Java Community** - For excellent documentation and libraries

---

*⭐ If you found this project helpful, please consider giving it a star!*
