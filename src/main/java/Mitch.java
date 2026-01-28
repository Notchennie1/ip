import java.util.Scanner;

public class Mitch {
    private static final String LINE = "____________________________________________________________";

    public static void main(String[] args) {
        System.out.println(LINE+"\n" + "Hello, I'm Mitch\nWhat can I do for you\n" + LINE);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            if (line.equals("bye")){
                // Reply to bye and stop the loop
                System.out.println(LINE + "\nBye. Hope to see you again!\n"+LINE);
                break;
            }
            else{
                // echo
                System.out.println(LINE +"\n"+ line +"\n" + LINE);
            }
        }
        sc.close();

    }
}
