package mitch;

import java.util.Scanner;

/**
 * Handles all user interface operations.
 * This class is responsible for reading user input and printing messages to the console
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui instance and initialises the Scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     *  Reads the next line of command entered by the user.
     * @return The raw string input provided by user.
     */

    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     *  Prints a horizontal divider line for separation in the console output.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a welcome message when the Mitch chatbot starts up
     */

    public void showWelcome() {
        showLine();
        System.out.println(" Hello! I'm Mitch");
        System.out.println(" What can I do for you?");
        showLine();
    }

    /**
     * Prints a goodbye message before Mitch terminates
     */
    public void showGoodbye() {
        showLine();
        System.out.println(" Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Prints a formatted error message to the console and enclose it with divider lines
     *
     * @param message The specific error text to be displayed to the user.
     */
    public void showError(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    /**
     * Prints a standard message to the console without extra formatting
     * @param message The text emssage to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}