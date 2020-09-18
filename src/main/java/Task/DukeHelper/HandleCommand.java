package Task.DukeHelper;

import Task.Exception.OtherException;
import Task.Exception.ToDoException;
import Task.TaskType.*;

import java.io.*;
import java.util.*;

public class HandleCommand {

    // create task class for user's To-Do-List (max 100 items)
    public static ArrayList<Task> tasks = new ArrayList<>(Constants.MAX_LIST_SIZE);

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
        // update file
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

    public static void handleCommand() throws IOException {
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
            else if(userInput.startsWith("delete")){
                delete(userInput);
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

    public static void createFile() throws IOException {
        // find file
        File file = new File(Constants.path, Constants.fileName);
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

    private static void viewInvalidCommandMessage() {
        System.out.println("\n ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }

    private static void viewEmptyToDoMessage(){
        System.out.println("\n ☹ OOPS!!! The description of a todo cannot be empty.\n");
    }
}
