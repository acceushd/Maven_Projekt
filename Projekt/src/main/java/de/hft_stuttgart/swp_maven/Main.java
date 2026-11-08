package de.hft_stuttgart.swp_maven;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    static void main(String[] args) {
        HashGenerator hashGenerator = new HashGenerator();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Algorithm you wanna use (md5, sha256, sha512):");
        String algorithm = scanner.nextLine();
        System.out.println("Enter the Message you wanna hash:");
        String message = args.length != 0 ? String.join("", args) : scanner.nextLine();
        String hashedMessage = hashGenerator.generateHash(message, algorithm);
        System.out.printf("Here is the Message %s.\nHere is the algorithm %s.\nAnd here is the hashed Message %s.\n",
                message, algorithm, hashedMessage);

        System.out.println("Properties of the app");
        Properties props = new Properties();
        try (InputStream in = Main.class.getResourceAsStream("/app.properties")) {
            assert in != null;
            Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
            props.load(reader);
        } catch (IOException e) {
            System.err.println("No such file");
        }
        System.out.println("Name of the App: " + props.getProperty("app.name"));
        System.out.println("Description of the App: " + props.getProperty("app.description"));
        System.out.println("Version of the App:" + props.getProperty("app.version"));
        System.out.println("Main class of the App:" + props.getProperty("app.mainClass"));
        System.out.println("Build Timestamp: " + props.getProperty("app.buildTime"));
        System.out.println("Which Java Release is used: " + props.getProperty("app.javaRelease"));
        System.out.println("Which OS is used: " + props.getProperty("app.os"));
        System.out.println("Which OS Version is used: " + props.getProperty("app.osVersion"));
        System.out.println("Developers:");
        for (int i = 1; ; i++) {
            String name = props.getProperty("app.developer." + i);
            if (name == null) break;
            System.out.println(" - " + name);
        }
    }
}