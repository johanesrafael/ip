package Task.DukeHelper;

public class Ui {
    public static void createLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void greet() {
        String greet = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        // print greet
        System.out.println(greet);
    }

    public static void createByeMessage() {
        String exit = "\nBye. Hope to see you again soon!\n";
        System.out.println("   " + exit);
    }
}
