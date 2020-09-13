package Task;

import Task.Exception.OtherException;
import Task.Exception.DukeException;
import Task.Exception.ToDoException;
import Task.TaskType.Task;
import Task.TaskType.Deadline;
import Task.TaskType.Event;
import Task.TaskType.ToDo;

import java.util.*;

public class Duke {
    // introduce a constant for list size
    public static final int MAX_LIST_SIZE = 100;
    // create task class for user's To-Do-List (max 100 items)
    private static ArrayList<Task> tasks = new ArrayList<>(MAX_LIST_SIZE);
    // create counter for tracking each inserted user input
    //private static int listCounter = 0;

    // insert user input to the list
    public static void insertToList(String userInput) throws OtherException {
        String userInputFirstWord = userInput.split(" ")[0];

        switch(userInputFirstWord) {
        case "todo":
            try {
                insertToDo(userInput);
            } catch (ToDoException e) {
                viewEmptyToDoMessage();
            }
            break;
        case "deadline":
            insertDeadline(userInput);
            break;
        case "event":
            insertEvent(userInput);
            break;
        default:
            throw new OtherException();
        }
        // print the newly added task
        // printAddedTask();
    }

    private static void insertEvent(String userInput) {
        // take the description by finding the start and end index
        int descriptionStartIndex = userInput.indexOf(" ");
        int descriptionEndIndex = userInput.indexOf("/at");
        String description = userInput.substring(descriptionStartIndex + 1, descriptionEndIndex);
        // take the event time
        String at = userInput.substring(descriptionEndIndex + 3);
        // create event task to be passed over to the actual task array
        Event task = new Event(description, at);
        // assign task into actual task and increment listCounter
        tasks.add(task);
    }

    private static void insertDeadline(String userInput) {
        // take the description by finding the start and end index
        int descriptionStartIndex = userInput.indexOf(" ");
        int descriptionEndIndex = userInput.indexOf("/by");
        String description = userInput.substring(descriptionStartIndex + 1, descriptionEndIndex);
        // take the deadline
        String by = userInput.substring(descriptionEndIndex + 3);
        // create deadline task to be passed over to the actual task array
        Deadline task = new Deadline(description, by);
        // assign task into actual task and increment listCounter
        tasks.add(task);

    }

    private static void insertToDo(String userInput) throws ToDoException{
        // take the description by finding the start index
        int descriptionStartIndex = userInput.indexOf(" ");
        String description = userInput.substring(descriptionStartIndex+1);
        if(descriptionStartIndex != 4 || description.isEmpty()){
            throw new ToDoException();
        }
        // create To-Do task for passing over the user input to the actual task array
        ToDo task = new ToDo(description);
        // assign task into actual task and increment listCounter
        tasks.add(task);

    }

    private static void printAddedTask() {
        if (tasks.size() < 2) {
            System.out.println("   Got it. I've added this task:\n\t"
                    + tasks.get(0) + "\n   Now you have "
                    + tasks.size() + " task in the list.\n");
        } else {
            System.out.println("   Got it. I've added this task:\n\t"
                    + tasks.get(tasks.size()-1) + "\n   Now you have "
                    + tasks.size() + " tasks in the list.\n");
        }
    }

    // view tasks
    public static void getList(){
        System.out.println("   Here are the tasks in your list:\n");
        for(int i = 0; i < tasks.size(); i++){
            System.out.println("   " + (i + 1) + ". " + tasks.get(i));
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
        tasks.get(indexTask-1).markAsDone();
    }

    private static void delete(String userInput){
        // take out the word after "delete"
        String indexString = userInput.split(" ")[1];
        // change the word into integer
        Integer indexTask = Integer.parseInt(indexString);
        // view delete message first
        tasks.get(indexTask-1).deleteTaskMessage();
        // erase the selected task from the array
        tasks.remove(indexTask-1);
    }

    private static void handleCommand() {
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
            else if(userInput.startsWith("delete")){
                delete(userInput);
            }
            else{
                // insert into list
                try {
                    insertToList(userInput);
                } catch (OtherException e){
                    viewInvalidCommandMessage();
                }
            }
            // ask user input
            userInput = echo.nextLine();
        }
    }

    private static void viewInvalidCommandMessage() {
        System.out.println("\n ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }

    private static void viewEmptyToDoMessage(){
        System.out.println("\n ☹ OOPS!!! The description of a todo cannot be empty.\n");
    }

    public static void main(String[] args) {
        // create logo
        createLogo();
        // greet
        greet();
        handleCommand();
        // create bye message
        createByeMessage();
    }


}
