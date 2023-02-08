package org.keith.core;

import java.util.Locale;
import java.util.Scanner;

public class SingletonScanner{
    private static SingletonScanner instance = null;
    private static Scanner scanner = null;

    private SingletonScanner() {}

    public static SingletonScanner getInstance() {
        if (instance == null) {
            instance = new SingletonScanner();
            scanner = new Scanner(System.in);
            scanner.useLocale(Locale.US);
        }
        return instance;
    }

    public Scanner getScanner() {
        return scanner;
    }
}