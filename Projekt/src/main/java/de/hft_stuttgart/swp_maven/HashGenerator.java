package de.hft_stuttgart.swp_maven;

import org.apache.commons.codec.digest.Crypt;

import java.security.SecureRandom;

public class HashGenerator {
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private static final String SALT_CHARS =
            "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static String SALT = "";

    public HashGenerator() {
        SALT = generateSalt();
    }

    public String generateHash(String message, String algorithm) {
        String prefix = switch (algorithm.toLowerCase()) {
            case "md5" -> "$1$";
            case "sha256" -> "$5$";
            case "sha512" -> "$6$";
            default -> throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
        };
        return Crypt.crypt(message, prefix + SALT);
    }

    private String generateSalt() {
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            sb.append(SALT_CHARS.charAt(SECURE_RANDOM.nextInt(SALT_CHARS.length())));
        }
        return sb.toString();
    }
}
