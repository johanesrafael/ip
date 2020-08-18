import java.util.Scanner;

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
            while (!usrin.equals("Bye")) {
                usrin = echo.nextLine();
                System.out.println("   " + usrin);
            }
        }

        String exit = "\nBye. Hope to see you again soon!\n";
        System.out.println("   " + exit);
    }
}
