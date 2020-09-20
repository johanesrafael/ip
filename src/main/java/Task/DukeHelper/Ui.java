package Task.DukeHelper;

public class Ui {
    public static void createLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        lineSeparator();
    }

    public static void greet() {
        String greet = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        // print greet
        System.out.println(greet);
        lineSeparator();
    }

    public static void createByeMessage() {
        showLine();
        String exit = "\nBye. Hope to see you again soon!\n";
        System.out.println("   " + exit);
        lineSeparator();
    }

    static void viewEmptyToDoMessage(){
        showLine();
        System.out.println("\n ☹ OOPS!!! The description of a todo cannot be empty.\n");
        lineSeparator();
    }

    static void getAddedTask() {
        showLine();
        System.out.println("   Got it. I've added this task:\n\t"
                + TaskList.tasks.get(TaskList.tasks.size()-1) + "\n   Now you have "
                + TaskList.tasks.size() + " tasks in the list.\n");
        lineSeparator();
    }

    static void viewInvalidCommandMessage() {
        showLine();
        System.out.println("\n ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        lineSeparator();
    }

    static void getListHeader() {
        showLine();
        System.out.println(System.lineSeparator() + "   Here are the tasks in your list:\n");
    }

    static void lineSeparator(){
        showLine();
        System.out.println(System.lineSeparator());
    }

    static void showLine(){
        System.out.println("_____________________________________________________________________");
    }

    static void noFileOrDirectoryMessage() {
        showLine();
        System.out.println("No such file or directory. Creating new directory and new file.");
        lineSeparator();
    }

    static void showFilteredListHeader(){
        showLine();
        System.out.println("    Here are the matching tasks in your list:\n");
    }
}
