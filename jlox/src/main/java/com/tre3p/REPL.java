package com.tre3p;

import com.tre3p.scanner.TokenScanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class REPL {
    private static final String PROMPT = "> ";
    private static final String USAGE_PROMPT = "Usage: jlox [script]";

    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println(USAGE_PROMPT);
            System.exit(64);
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {
            runPrompt();
        }
    }

    private static void run(String inputCode) {
        new TokenScanner(inputCode).scanTokens()
                .forEach(System.out::println);
    }

    private static void runFile(String filePath) throws IOException {
        run(new String(Files.readAllBytes(Paths.get(filePath))));
        if (Lox.hadError) System.exit(65);
    }

    private static void runPrompt() throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println(PROMPT);

            var line = br.readLine();
            if (line == null) return;
            run(line);

            Lox.hadError = false;
        }
    }
}