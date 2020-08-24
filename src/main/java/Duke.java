import java.util.Scanner;
import Task.Task;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greet = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(greet);
        Scanner echo = new Scanner(System.in);

        String usrin = echo.nextLine();

        if(!usrin.equals("Bye")) {
            Task t = new Task(usrin);
            System.out.println("   Added: " + usrin);

            while (!usrin.equals("Bye")) {
                usrin = echo.nextLine();

                if(usrin.equals("list")){
                    System.out.println("Here are the tasks in your list:\n");

                }

                if(!usrin.equals("list"))
                    t = new Task(usrin);
                    System.out.println("   Added: " + usrin);

            }
        }

        String exit = "\nBye. Hope to see you again soon!\n";
        System.out.println("   " + exit);
    }
}
