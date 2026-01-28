import java.util.Scanner;

public class Mitch {
    private static final String LINE = "____________________________________________________________";

    public static void main(String[] args) {

        System.out.println(LINE+"\n" + "Hello, I'm Mitch\nWhat can I do for you\n" + LINE);
        Scanner sc = new Scanner(System.in);

        //Creating Lists
        String[] tasks = new String[100];
        int counter = 0;

        while (sc.hasNextLine()){
            String line = sc.nextLine().trim();
            if (line.equals("bye")){
                // Reply to bye and stop the loop
                System.out.println(LINE + "\nBye. Hope to see you again!\n"+LINE);
                break;
            }
            if (line.equals("list")){
                System.out.println(LINE);
                for(int i = 0; i < counter; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println(LINE);
                continue;
            }

            tasks[counter] = line; //add to list
            counter++;             //update list

            System.out.println(LINE +"\nadded: "+ line +"\n" + LINE);

        }
        sc.close();

    }
}
