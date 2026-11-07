package de.hft_stuttgart.swp_maven;

import org.apache.commons.codec.digest.Crypt;

import java.security.SecureRandom;

public class HashGenerator {
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    /**
     * Characters allowed in a crypt salt. This is the "crypt base64" alphabet
     * ('.' '/' 0-9 A-Z a-z).
     */
    private static final String SALT_CHARS =
            "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static String SALT = "";

    /**
     * Creates a new generator and creates a new random salt.
     */
    public HashGenerator() {
        SALT = generateSalt();
    }

    /**
     * Hashes the given message with the chosen algorithm.
     *
     * @param message   the message to hash
     * @param algorithm md5, sha256 or sha512
     * @return The full hashed message in the form of {@code $id$salt$hash}
     * @throws IllegalArgumentException if the algorithm is not supported
     */
    public String generateHash(String message, String algorithm) {
        String prefix = switch (algorithm.toLowerCase()) {
            case "md5" -> "$1$"; // Only has an 8 character salt so half of the generated is ignored
            case "sha256" -> "$5$";
            case "sha512" -> "$6$";
            default -> throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
        };
        return Crypt.crypt(message, prefix + SALT);
    }

    /**
     * Generates a random 16-character salt from {@link #SALT_CHARS}.
     *
     * @return the random salt
     */
    private String generateSalt() {
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            sb.append(SALT_CHARS.charAt(SECURE_RANDOM.nextInt(SALT_CHARS.length())));
        }
        return sb.toString();
    }
}
