import java.util.Scanner;

public class Mitch {
    private static final String LINE = "____________________________________________________________";
    private static final int MAX_TASKS = 100;

    public static void main(String[] args) {
        System.out.println(LINE + "\n" + "Hello, I'm Mitch\nWhat can I do for you\n" + LINE);
        Scanner sc = new Scanner(System.in);

        Task[] tasks = new Task[MAX_TASKS];
        int counter = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            if (line.equals("bye")) {
                System.out.println(LINE + "\nBye. Hope to see you again!\n" + LINE);
                break;
            }

            if (line.equals("list")) {
                System.out.println(LINE);
                for (int i = 0; i < counter; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println(LINE);
                continue;
            }

            if (line.startsWith("mark ")) {
                int index = Integer.parseInt(line.substring(5).trim()) - 1;
                tasks[index].markDone();
                System.out.println(LINE);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[index]);
                System.out.println(LINE);
                continue;
            }

            if (line.startsWith("unmark ")) {
                int index = Integer.parseInt(line.substring(7).trim()) - 1;
                tasks[index].unmarkDone();
                System.out.println(LINE);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[index]);
                System.out.println(LINE);
                continue;
            }

            tasks[counter] = new Task(line);
            counter++;

            System.out.println(LINE + "\nadded: " + line + "\n" + LINE);
        }

        sc.close();
    }
}
