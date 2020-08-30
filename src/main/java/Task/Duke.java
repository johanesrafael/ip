package Task;
import java.util.Scanner;

public class Duke {
    // create task class for user's To-Do-List (max 100 items)
    private static Task[] tasks = new Task[100];
    // create counter for tracking each inserted user input
    private static int listCounter = 0;

    // insert user input to the list
    public static void insertToList(String userInput) {
        if(userInput.startsWith("todo")) {
            // take the description
            String description = userInput.split("todo")[1];
            // create To-Do task for passing over the user input to the actual task array
            Todo task = new Todo(description);
            // assign task into actual task and increment listCounter
            tasks[listCounter++] = task;
        }
        else if(userInput.startsWith("deadline")){
            // create array to store words split by "/by"
            String[] by = userInput.split("/by");
            // take the description
            String description = by[0].split("deadline")[1];
            // create deadline task to be passed over to the actual task array
            Deadline task = new Deadline(description,by[1]);
            // assign task into actual task and increment listCounter
            tasks[listCounter++] = task;
        }
        else if(userInput.startsWith("event")){
            // create array to store words split by "/at"
            String[] at = userInput.split("/at");
            // take the description
            String description = at[0].split("event")[1];
            // create event task to be passed over to the actual task array
            Event task = new Event(description,at[1]);
            // assign task into actual task and increment listCounter
            tasks[listCounter++] = task;
        }
        // print the newly added task
        printAddedTask();
    }

    private static void printAddedTask() {
        if (listCounter < 2) {
            System.out.println("   Got it. I've added this task:\n\t"
                    + tasks[listCounter-1] + "\n   Now you have "
                    + listCounter + " task in the list.\n");
        } else {
            System.out.println("   Got it. I've added this task:\n\t"
                    + tasks[listCounter-1] + "\n   Now you have "
                    + listCounter + " tasks in the list.\n");
        }
    }

    // view tasks
    public static void getList(){
        System.out.println("   Here are the tasks in your list:\n");
        for(int i = 0; i < listCounter; i++){
            System.out.println("   " + (i + 1) + ". " + tasks[i]);
        }
    }

    private static void createLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    private static void greet() {
        String greet = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        // print greet
        System.out.println(greet);
    }

    private static void createByeMessage() {
        String exit = "\nBye. Hope to see you again soon!\n";
        System.out.println("   " + exit);
    }

    private static void setDone(String userInput) {
        // take out the word after "done"
        String indexString = userInput.split(" ")[1];
        // change the word into integer
        Integer indexTask = Integer.parseInt(indexString);
        // mark task as done
        tasks[indexTask-1].markAsDone();
    }

    public static void main(String[] args) {
        // create logo
        createLogo();
        // greet
        greet();
        Scanner echo = new Scanner(System.in);
        // scan user input
        String userInput = echo.nextLine();

        // execute command of the user input until "bye" is entered
        while (!userInput.equals("Bye")){
            // display list
            if(userInput.equals("list")){
                getList();
            }
            else if(userInput.startsWith("done")){
                setDone(userInput);
            }
            else{
                // insert into list
                insertToList(userInput);
            }
            // ask user input
            userInput = echo.nextLine();
        }

        // create bye message
        createByeMessage();
    }
}
