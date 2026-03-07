package mitch;

import java.util.Scanner;

/**
 * Represents the user interface of the Mitch application.
 * Handles all interactions with the user, such as reading input and displaying messages.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs Ui instance and initialises scanner
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads next line of input command from user
     * @return The string input by user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a divider line
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the welcome message
     */
    public void showWelcome() {
        showLine();
        System.out.println(" Hello! I'm Mitch");
        System.out.println(" What can I do for you?");
        showLine();
    }

    /**
     * Displays bye message when user exits
     */
    public void showGoodbye() {
        showLine();
        System.out.println(" Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Display an error message
     * @param message The error message displayed
     */
    public void showError(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    /**
     * Displays a standard messgae
     * @param message The message to be displayed
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}