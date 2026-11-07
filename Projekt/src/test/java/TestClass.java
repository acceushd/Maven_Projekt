import de.hft_stuttgart.swp_maven.HashGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestClass {

    static HashGenerator hashGenerator;

    @BeforeAll
    static void setup() {
        System.out.println("@BeforeAll - executes once before all test methods in this class");
        hashGenerator = new HashGenerator();
    }

    @BeforeEach
    void init() {
        System.out.println("@BeforeEach - executes before each test method in this class");
    }

    @Test
    void sha512TwoSameMessages() {
        System.out.println("SHA-512 Same Message");
        String hash1 = hashGenerator.generateHash("Hello, world!", "sha512");
        String hash2 = hashGenerator.generateHash("Hello, world!", "sha512");
        assertEquals(hash1, hash2);
    }

    @Test
    void sha512TwoDifferentMessages() {
        System.out.println("SHA-512 Different Message");
        String hash1 = hashGenerator.generateHash("Hello, world!", "sha512");
        String hash2 = hashGenerator.generateHash("Hello, HFT!", "sha512");
        assertNotEquals(hash1, hash2);
    }

    // This Test fails on purpose
    @Test
    @Disabled
    void sha512TwoDifferentMessagesFalse() {
        System.out.println("SHA-512 Different Message");
        String hash1 = hashGenerator.generateHash("Hello, world!", "sha512");
        String hash2 = hashGenerator.generateHash("Hello, world!", "sha512");
        assertNotEquals(hash1, hash2);
    }

    @Test
    void sha256TwoSameMessages() {
        System.out.println("SHA-256 Same Message");
        String hash1 = hashGenerator.generateHash("Hello, world!", "sha512");
        String hash2 = hashGenerator.generateHash("Hello, world!", "sha512");
        assertEquals(hash1, hash2);
    }

    @Test
    void sha256TwoDifferentMessages() {
        System.out.println("SHA-256 Different Message");
        String hash1 = hashGenerator.generateHash("Hello, world!", "sha256");
        String hash2 = hashGenerator.generateHash("Hello, HFT!", "sha256");
        assertNotEquals(hash1, hash2);
    }
}
