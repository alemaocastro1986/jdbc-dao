package utils;

import java.util.Locale;

public class TestConsole {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";


    public static void it(String description){
        System.out.println(ANSI_YELLOW + "##" + description + ANSI_RESET);
    }
    public static void describe(String description){
        System.out.println(ANSI_CYAN + " == " + description.toUpperCase() + "==" + ANSI_RESET);
    }
    public static void printResult(String description){
        System.out.println(ANSI_GREEN + "\t✓ " + description + ANSI_RESET);
    }

}
