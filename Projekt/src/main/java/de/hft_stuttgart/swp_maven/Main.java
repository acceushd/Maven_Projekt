package de.hft_stuttgart.swp_maven;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        HashGenerator hashGenerator = new HashGenerator();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Algorithm you wanna use (md5, sha256, sha512):");
        String algorithm = scanner.nextLine();
        System.out.println("Enter the Message you wanna hash:");
        String message = scanner.nextLine();
        String hashedMessage = hashGenerator.generateHash(message, algorithm);
        System.out.printf("Here is the Message %s.\n Here is the algorithm %s.\n And here is the hashed Message %s.",
                message, algorithm, hashedMessage);
    }


}