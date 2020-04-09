package app.util;

public class Logger {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    private Logger() { }

    public static void red_write(String s) {
        System.out.println(ANSI_RED + s + ANSI_RESET);
    }

    public static void green_write(String s) {
        System.out.println(ANSI_GREEN + s + ANSI_RESET);
    }
}
