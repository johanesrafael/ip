package Task.DukeHelper;

public class Ui {
    public static void createLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + System.lineSeparator());
    }

    public static void greet() {
        String greet = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        // print greet
        System.out.println(greet + System.lineSeparator());
    }

    public static void createByeMessage() {
        String exit = "\nBye. Hope to see you again soon!\n";
        System.out.println("   " + exit + System.lineSeparator());
    }

    static void viewEmptyToDoMessage(){
        System.out.println("\n ☹ OOPS!!! The description of a todo cannot be empty.\n");
    }

    static void getAddedTask() {
        System.out.println("   Got it. I've added this task:\n\t"
                + TaskList.tasks.get(TaskList.tasks.size()-1) + "\n   Now you have "
                + TaskList.tasks.size() + " tasks in the list.\n");
    }

    static void viewInvalidCommandMessage() {
        System.out.println("\n ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }

    static void getListHeader() {
        System.out.println(System.lineSeparator() + "   Here are the tasks in your list:\n");
    }

    static void lineSeparator(){
        System.out.println(System.lineSeparator());
    }
}
