package org.keith.menu;

import org.keith.core.LoanInput;
import org.keith.core.SingletonScanner;
import org.keith.menu.enums.MenuCommand;
import java.util.Scanner;

public class Menu {
    private final String MENU = """
                OPTIONS:
                mortgage    Start calculating the mortgage loan
                car         Start calculating the car loan
                help        List the command options
                exit        exit the program
                """;

    public void LOGO() {
        System.out.println("""
                                                              \s
                     _     ___    _    _   _    ____ _     ___\s
                    | |   / _ \\  / \\  | \\ | |  / ___| |   |_ _|
                    | |  | | | |/ _ \\ |  \\| | | |   | |    | |\s
                    | |__| |_| / ___ \\| |\\  | | |___| |___ | |\s
                    |_____\\___/_/   \\_|_| \\_|  \\____|_____|___|
                """);
    }
    public final String WELCOME_MSG = """
    LOAN CLI v1.0.2
    © Keith Atmodimedjo
    Type "help" to list command options.
    """;
    private final int isEmpty = 0;
    private final String exit = "EXIT",
                         help = "HELP",
                         mortgage = "MORTGAGE",
                         car = "CAR";
    private final String cmdLine = "user> ";
    private final int maxMortgageTerms = 30,
                      maxVehicleTerms = 5;
    private boolean isRunning = true;
    String input;
    LoanInput mi = new LoanInput();

    public void getMenuInput(){
        while(isRunning){
            try {
                getInput();

                // if input is empty continue to print the option number message above
                if (ifInputIsEmpty()) {
                    continue;
                }

                // Determine the input regardless of it's written in upper case or lower case
                MenuCommand mc = checkCommand(input.toUpperCase());
                switch (mc) {
                    case MORTGAGE -> mi.getLoanInfo(maxMortgageTerms);
                    case CAR -> mi.getLoanInfo(maxVehicleTerms);
                    case HELP -> showMenu();
                    case EXIT -> isRunning = false;
                    default -> getInput();
                }

            } catch(Exception e){
                System.out.println("Error: " + e.getMessage() + " caused by " + e.getCause());
            }
        }
    }

    private boolean ifInputIsEmpty(){
        return input.length() == isEmpty;
    }

    private MenuCommand checkCommand(String expr) {
        return switch (expr) {
            case mortgage -> MenuCommand.MORTGAGE;
            case car -> MenuCommand.CAR;
            case exit -> MenuCommand.EXIT;
            case help -> MenuCommand.HELP;
            default -> MenuCommand.EVALUATE_COMMAND;
        };
    }

    private void showMenu(){
        System.out.print(MENU);
    }

    public void getInput(){
        SingletonScanner singletonScanner = SingletonScanner.getInstance();
        Scanner scanner = singletonScanner.getScanner();
        System.out.print(cmdLine);
        input = scanner.nextLine();
    }
}