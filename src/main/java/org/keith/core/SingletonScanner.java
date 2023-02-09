package org.keith.core;

import java.util.Locale;
import java.util.Scanner;

public class SingletonScanner{
    private static SingletonScanner instance = null;
    private Scanner scanner;

    // Guarantees that no scanner object can be created outside SingletonScanner class
    private SingletonScanner() {
            scanner = new Scanner(System.in);
            scanner.useLocale(Locale.US); // makes . for decimal input possible
    }

    public static SingletonScanner getInstance() {
        if (instance == null) {
            instance = new SingletonScanner();
        }
        return instance;
    }

    public String getString(String sPrompt) {
        System.out.print(sPrompt);
        scanner.useDelimiter("\r\n"); // Setting this delimiter ensures that we capture everything up to the <Enter> key. Without this, input stops at the next whitespace (space, tab, newline etc.).
        String sInput = scanner.nextLine();
        scanner.reset(); // The preceding use of useDelimiter() changed the state of the Scanner object. reset() re-establishes the original state.
        return sInput;
    }
}