/**
 * Ui class that provides interaction for user
 */
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

    public static void viewEmptyToDoMessage(){
        showLine();
        System.out.println("\n ~OOPS!!! The description of a todo cannot be empty.\n");
        lineSeparator();
    }

    public static void getAddedTask() {
        showLine();
        System.out.println("   Got it. I've added this task:\n\t"
                + TaskList.tasks.get(TaskList.tasks.size()-1) + "\n   Now you have "
                + TaskList.tasks.size() + " tasks in the list.\n");
        lineSeparator();
    }

    public static void viewInvalidCommandMessage() {
        showLine();
        System.out.println("\n ~OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        lineSeparator();
    }

    public static void getListHeader() {
        showLine();
        System.out.println(System.lineSeparator() + "   Here are the tasks in your list:\n");
    }

    public static void lineSeparator(){
        showLine();
        System.out.println(System.lineSeparator());
    }

    public static void showLine(){
        System.out.println("_____________________________________________________________________");
    }

    public static void noFileOrDirectoryMessage() {
        showLine();
        System.out.println("No such file or directory. Creating new directory and new file.");
        lineSeparator();
    }

    public static void showFilteredListHeader(){
        showLine();
        System.out.println("    Here are the matching tasks in your list:\n");
    }

    public static void getHelloMessage() {
        // create logo
        createLogo();
        // greet
        greet();
    }

    public static void getCannotRewriteMessage(){
        showLine();
        System.out.println("    Cannot rewrite such file.");
        showLine();
    }

    public static void getCannotCreateFileMessage(){
        showLine();
        System.out.println("    Cannot create file.");
        showLine();
    }
}
