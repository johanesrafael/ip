package Task;
import java.util.Scanner;

public class Duke {
    // create task class for user's To-Do-List (max 100 items)
    private static Task[] tasks = new Task[100];
    // create counter for tracking each inserted user input
    private static int listCounter = 0;

    // to insert user input to the list
    public static void insertToList(String userInput){
        // create temporary task for passing over the user input to the actual task
        Task task = new Task(userInput);
        // assign task into actual task and increment listCounter
        tasks[listCounter++] = task;
        System.out.println("   Added: "
                + task.getDescription());
    }

    // to view tasks
    public static void getList(){
        System.out.println("Here are the tasks in your list:\n");
        for(int i = 0; i < listCounter; i++){
            System.out.println("   " + (i + 1) + ".[" + tasks[i].getStatusIcon()
                    + "]" + tasks[i].getDescription());
        }
    }

    public static void main(String[] args) {
        // create logo
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        // greet
        String greet = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        // print greet
        System.out.println(greet);
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
                // take out the word after "done"
                String indexString = userInput.split(" ")[1];
                // change the word into integer
                Integer indextask = Integer.parseInt(indexString);
                // mark task as done
                tasks[indextask-1].markAsDone();
            }
            else{
                // insert into list
                insertToList(userInput);
            }
            // ask user input
            userInput = echo.nextLine();
        }

        // create bye message
        String exit = "\nBye. Hope to see you again soon!\n";
        System.out.println("   " + exit);
    }
}
