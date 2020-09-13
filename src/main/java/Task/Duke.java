package Task;

import Task.Exception.OtherException;
import Task.Exception.DukeException;
import Task.Exception.ToDoException;
import Task.TaskType.Task;
import Task.TaskType.Deadline;
import Task.TaskType.Event;
import Task.TaskType.ToDo;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

public class Duke {
    // introduce a constant for list size
    public static final int MAX_LIST_SIZE = 100;
    // create task class for user's To-Do-List (max 100 items)
    private static ArrayList<Task> tasks = new ArrayList<>(MAX_LIST_SIZE);
    // create counter for tracking each inserted user input
    // private static int listCounter = 0;

    // insert user input to the list
    public static void insertToList(String userInput) throws OtherException, IOException {
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
        createFile();
        // print the newly added task
        // printAddedTask();
    }

    private static void insertEvent(String userInput) {
        // take the description by finding the start and end index
        int descriptionStartIndex = userInput.indexOf(" ");
        int descriptionEndIndex = userInput.indexOf("/at");
        String description = userInput.substring(descriptionStartIndex, descriptionEndIndex);
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
        String description = userInput.substring(descriptionStartIndex, descriptionEndIndex);
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
        String description = userInput.substring(descriptionStartIndex);
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
        tasks.get(indexTask - 1).markAsDone();
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
            else{
                // insert into list
                try {
                    insertToList(userInput);
                } catch (OtherException | IOException e){
                    viewInvalidCommandMessage();
                }
            }
            // ask user input
            userInput = echo.nextLine();
        }
    }

    private static void createFile() throws IOException {
        String path ="D:\\CEG\\SEMESTER 3\\CS2113\\Individual Project\\Saved Files";
        File file = new File(path, "progress.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter newFile = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
            for(int i = 0; i < tasks.size(); i++){
                newFile.write(tasks.get(i).toString());
                newFile.newLine();
            }
            newFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFile() throws IOException, OtherException {
        Path path = Paths.get("D:\\CEG\\SEMESTER 3\\CS2113\\Individual Project\\Saved Files\\progress.txt");
        String file = Files.readString(path, StandardCharsets.UTF_8);
        Scanner data = new Scanner(file);
        // make read by line then insert to the corresponding task type
        while(data.hasNextLine()){
            insertExistingFileDataToTasks(data.nextLine());
        }
        data.close();
    }

    private static void isDone(String userDataSymbol){
        if(userDataSymbol.equals("\u2713")){
            tasks.get(tasks.size()-1).setDone();
        }
    }

    private static void passToToDo(String userData){
        String description = userData.split("]")[2];
        // create To-Do task for passing over the user input to the actual task array
        ToDo task = new ToDo(description);
        // assign task into actual task and increment listCounter
        tasks.add(task);
    }

    private static void passToDeadline(String userData){
        userData = userData.replace("(","");
        userData = userData.replace(")","");
        String isDone = userData.substring(3,5);
        int descriptionStartIndex = userData.indexOf(" ");
        int descriptionEndIndex = userData.indexOf("by:");
        String description = userData.substring(descriptionStartIndex, descriptionEndIndex);
        // take the event time
        String by = userData.substring(descriptionEndIndex + 3);
        // create event task to be passed over to the actual task array
        Deadline task = new Deadline(description, by);
        // assign task into actual task and increment listCounter
        tasks.add(task);
        //isDone(isDone);
    }

    private static void passToEvent(String userData){
        userData = userData.replace("(","");
        userData = userData.replace(")","");
        int descriptionStartIndex = userData.indexOf(" ");
        int descriptionEndIndex = userData.indexOf("at:");
        String description = userData.substring(descriptionStartIndex, descriptionEndIndex);
        // take the event time
        String at = userData.substring(descriptionEndIndex + 3);
        // create event task to be passed over to the actual task array
        Event task = new Event(description, at);
        // assign task into actual task and increment listCounter
        tasks.add(task);
    }

    private static void insertExistingFileDataToTasks(String userData) throws OtherException {
        char taskType = userData.charAt(1);
        switch (taskType){
        case 'T':
            passToToDo(userData);
            break;
        case 'D':
            passToDeadline(userData);
            break;
        case 'E':
            passToEvent(userData);
            break;
        default:
            throw new OtherException();
        }
    }


    private static void viewInvalidCommandMessage() {
        System.out.println("\n ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }

    private static void viewEmptyToDoMessage(){
        System.out.println("\n ☹ OOPS!!! The description of a todo cannot be empty.\n");
    }

    public static void main(String[] args) throws IOException, OtherException {
        // create logo
        createLogo();
        // greet
        greet();
        // createFile();
        readFile();
        handleCommand();
        // create bye message
        createByeMessage();
    }
}
