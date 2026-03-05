package mitch;
import java.util.Scanner;

public class Ui {

    private static final String LINE = "____________________________________________________________";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome(){
        System.out.println(LINE);
        System.out.println("Hello, I'm Mitch");
        System.out.println("What can I do for you");
        System.out.println(LINE);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showError(String message) {
        System.out.println(LINE);
        System.out.println(" " + message);
        System.out.println(LINE);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showGoodbye(){
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again!");
        System.out.println(LINE);
    }
}
