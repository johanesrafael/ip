package Task.DukeHelper;

import Task.Exception.OtherException;
import Task.Exception.ToDoException;
import Task.TaskType.Deadline;
import Task.TaskType.Event;
import Task.TaskType.ToDo;

import java.io.IOException;
import java.util.Scanner;

public class Parser {

    // insert user input to the list
    public static void insertToList(String userInput) throws OtherException, IOException {
        String userInputFirstWord = userInput.split(" ")[0];

        switch(userInputFirstWord) {
        case "todo":
            try {
                TaskList.insertToDo(userInput);
            } catch (ToDoException e) {
                Ui.viewEmptyToDoMessage();
            }
            break;
        case "deadline":
            TaskList.insertDeadline(userInput);
            break;
        case "event":
            TaskList.insertEvent(userInput);
            break;
        default:
            throw new OtherException();
        }
        // update file
        Storage.createFile();
    }

    public static void handleCommand() throws IOException {
        Scanner echo = new Scanner(System.in);
        // scan user input
        String userInput = echo.nextLine();

        // execute command of the user input until "bye" is entered
        while (!userInput.equals("Bye")){
            // display list
            if(userInput.equals("list")){
                TaskList.getList();
            }
            else if(userInput.startsWith("done")){
                TaskList.setDone(userInput);
                // update the file
                Storage.createFile();
            }
            else if(userInput.startsWith("delete")){
                TaskList.delete(userInput);
                // update the file
                Storage.createFile();
            }
            else{
                // insert into list
                try {
                    insertToList(userInput);
                } catch (OtherException | IOException e){
                    Ui.viewInvalidCommandMessage();
                }
                // print newly added task
                TaskList.printAddedTask();
            }
            // ask user input
            userInput = echo.nextLine();
        }
    }

    static Event parseEvent(String userInput) {
        // take the description by finding the start and end index
        int descriptionStartIndex = userInput.indexOf(" ");
        int descriptionEndIndex = userInput.indexOf("/at");
        String description = userInput.substring(descriptionStartIndex, descriptionEndIndex);
        // take the event time
        String at = userInput.substring(descriptionEndIndex + 3);
        // create event task to be passed over to the actual task array
        return new Event(description, at);
    }

    static Deadline parseDeadline(String userInput) {
        // take the description by finding the start and end index
        int descriptionStartIndex = userInput.indexOf(" ");
        int descriptionEndIndex = userInput.indexOf("/by");
        String description = userInput.substring(descriptionStartIndex, descriptionEndIndex);
        // take the deadline
        String by = userInput.substring(descriptionEndIndex + 3);
        // create deadline task to be passed over to the actual task array
        return new Deadline(description, by);
    }

    static ToDo parseToDo(String userInput) throws ToDoException {
        // take the description by finding the start index
        int descriptionStartIndex = userInput.indexOf(" ");
        String description = userInput.substring(descriptionStartIndex);
        if(descriptionStartIndex != 4 || description.isEmpty()){
            throw new ToDoException();
        }
        // create To-Do task for passing over the user input to the actual task array
        return new ToDo(description);
    }

    static ToDo parseExistingToDo(String userData) {
        // take out the description
        String description = userData.split("]")[2];

        // create To-Do task for passing over the user input to the actual task array
        return new ToDo(description);
    }

    static Deadline parseExistingDeadline(String userData) {
        // take indexes
        int descriptionStartIndex = userData.indexOf(" ");
        int descriptionEndIndex = userData.indexOf("by:");
        String description = userData.substring(descriptionStartIndex, descriptionEndIndex);
        // take the event time
        String by = userData.substring(descriptionEndIndex + 3);
        // create event task to be passed over to the actual task array
        return new Deadline(description, by);
    }

    static String stripBrackets(String userData) {
        // erase brackets
        userData = userData.replace("(", "");
        userData = userData.replace(")", "");
        return userData;
    }

    static Event parseExistingEvent(String userData) {
        // take indexes
        int descriptionStartIndex = userData.indexOf(" ");
        int descriptionEndIndex = userData.indexOf("at:");
        String description = userData.substring(descriptionStartIndex, descriptionEndIndex);
        // take the event time
        String at = userData.substring(descriptionEndIndex + 3);
        // create event task to be passed over to the actual task array
        return new Event(description, at);
    }
}
