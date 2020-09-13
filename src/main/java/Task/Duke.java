package Task;

import Task.Exception.OtherException;
import Task.Exception.DukeException;
import Task.Exception.ToDoException;
import Task.TaskType.Task;
import Task.TaskType.Deadline;
import Task.TaskType.Event;
import Task.TaskType.ToDo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    // introduce a constant for list size
    public static final int MAX_LIST_SIZE = 100;
    // create task class for user's To-Do-List (max 100 items)
    private static ArrayList<Task> tasks = new ArrayList<>(MAX_LIST_SIZE);
    // create counter for tracking each inserted user input
    private static String path ="D:\\CEG\\SEMESTER 3\\CS2113\\Individual Project\\Saved Files";
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

    private static void handleCommand() throws IOException {
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
                // update the file
                createFile();
            }
            else{
                // insert into list
                try {
                    insertToList(userInput);
                } catch (OtherException | IOException e){
                    viewInvalidCommandMessage();
                }
                // print newly added task
                printAddedTask();
            }
            // ask user input
            userInput = echo.nextLine();
        }
    }

    private static void createFile() throws IOException {
        // find file
        File file = new File(path, "progress.txt");
        if (!file.exists()) {
            try {
                // make new file
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            // rewrite file
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
        // get path
        Path path2 = Paths.get(path, "progress.txt");
        String file = Files.readString(path2, StandardCharsets.UTF_8);
        Scanner data = new Scanner(file);
        // make read by line then insert to the corresponding task
        while(data.hasNextLine()){
            insertExistingFileDataToTasks(data.nextLine());
        }
        data.close();
    }

    private static void isTaskDone(String userDataSymbol){
        if(userDataSymbol.equals("\u2713")){
            tasks.get(tasks.size()-1).setDone();
        }
    }

    private static void passToToDo(String userData){
        // take out the description
        String description = userData.split("]")[2];
        // take the symbol
        String isTaskDone = userData.substring(4,5);
        // create To-Do task for passing over the user input to the actual task array
        ToDo task = new ToDo(description);
        // assign task into actual task and increment listCounter
        tasks.add(task);
        // check whether the task is done
        isTaskDone(isTaskDone);
    }

    private static void passToDeadline(String userData){
        // erase brackets
        userData = userData.replace("(","");
        userData = userData.replace(")","");
        // take symbol
        String isTaskDone = userData.substring(4,5);
        // take indexes
        int descriptionStartIndex = userData.indexOf(" ");
        int descriptionEndIndex = userData.indexOf("by:");
        String description = userData.substring(descriptionStartIndex, descriptionEndIndex);
        // take the event time
        String by = userData.substring(descriptionEndIndex + 3);
        // create event task to be passed over to the actual task array
        Deadline task = new Deadline(description, by);
        // assign task into actual task and increment listCounter
        tasks.add(task);
        // check whether the task is done
        isTaskDone(isTaskDone);
    }

    private static void passToEvent(String userData){
        // erase brackets
        userData = userData.replace("(","");
        userData = userData.replace(")","");
        // take the symbol
        String isTaskDone = userData.substring(4,5);
        // take indexes
        int descriptionStartIndex = userData.indexOf(" ");
        int descriptionEndIndex = userData.indexOf("at:");
        String description = userData.substring(descriptionStartIndex, descriptionEndIndex);
        // take the event time
        String at = userData.substring(descriptionEndIndex + 3);
        // create event task to be passed over to the actual task array
        Event task = new Event(description, at);
        // assign task into actual task and increment listCounter
        tasks.add(task);
        // check whether the task is done
        isTaskDone(isTaskDone);
    }

    private static void insertExistingFileDataToTasks(String userData) throws OtherException {
        // take task type
        char taskType = userData.charAt(1);
        // insert the task based on task type
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
        // read file first
        readFile();
        handleCommand();
        // create bye message
        createByeMessage();
    }
}
