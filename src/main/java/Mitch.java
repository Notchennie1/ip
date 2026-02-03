import java.util.Scanner;

public class Mitch {
    private static final String LINE = "____________________________________________________________";
    private static final int MAX_TASKS = 100;

    public static void main(String[] args) {
        System.out.println(LINE);
        System.out.println("Hello, I'm Mitch");
        System.out.println("What can I do for you");
        System.out.println(LINE);

        Scanner scanner = new Scanner(System.in); //changed sc to scanner

        Task[] tasks = new Task[MAX_TASKS];
        int taskCount = 0;

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();

            if (input.equals("bye")) {
                System.out.println(LINE);
                System.out.println("Bye. Hope to see you again!");
                System.out.println(LINE);
                break;
            }

            if (input.equals("list")) {
                System.out.println(LINE);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println(LINE);
                continue;
            }

            if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5).trim()) - 1;
                tasks[index].markDone();

                System.out.println(LINE);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[index]);
                System.out.println(LINE);
                continue;
            }

            if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7).trim()) - 1;
                tasks[index].unmarkDone();

                System.out.println(LINE);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[index]);
                System.out.println(LINE);
                continue;
            }

            if (input.startsWith("todo ")) {
                tasks[taskCount] = new ToDo(input);
                taskCount++;
                System.out.println(LINE);
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                System.out.println(LINE);
            }

            if (input.startsWith("deadline ")) {
                tasks[taskCount] = new Deadlines(input);
                taskCount++;
                System.out.println(LINE);
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                System.out.println(LINE);
            }

            if (input.startsWith("event ")) {
                tasks[taskCount] = new Events(input);
                taskCount++;
                System.out.println(LINE);
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                System.out.println(LINE);
            }


        }

        scanner.close();
    }
}
