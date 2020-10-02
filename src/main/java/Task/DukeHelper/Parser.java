/**
 * parser class that handles user input and makes sense of the input
 */
package Task.DukeHelper;

import Task.Exception.OtherException;
import Task.Exception.ToDoException;
import Task.TaskType.Deadline;
import Task.TaskType.Event;
import Task.TaskType.ToDo;

import java.io.IOException;
import java.util.Scanner;


public class Parser {
    /**
     * inserts to list by determining the type of task
     * @param userInput User input to determine its task type
     * @throws OtherException
     * @throws IOException
     */
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

    /**
     * handles command and executes it
     * @throws IOException if other invalid command
     */
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
            else if(userInput.startsWith("find")) {
                find(userInput);
            }else{
                // insert into list
                try {
                    insertToList(userInput);
                    // print newly added task
                    TaskList.printAddedTask();
                } catch (OtherException | IOException e){
                    Ui.viewInvalidCommandMessage();
                }
            }
            // ask user input
            userInput = echo.nextLine();
        }
    }

    /**
     * makes sense of the user input for event task
     * @param userInput User input to parse
     * @return
     */
    public static Event parseEvent(String userInput) {
        // take the description by finding the start and end index
        int descriptionStartIndex = userInput.indexOf(" ");
        int descriptionEndIndex = userInput.indexOf("/at");
        String description = userInput.substring(descriptionStartIndex, descriptionEndIndex);
        // take the event time
        String at = userInput.substring(descriptionEndIndex + 4);
        String convertedAt = parseDate(at);
        // create event task to be passed over to the actual task array
        return new Event(description, convertedAt);
    }

    /**
     * makes sense of the user input for deadline task
     * @param userInput User input to parse
     * @return
     */
    public static Deadline parseDeadline(String userInput) {
        // take the description by finding the start and end index
        int descriptionStartIndex = userInput.indexOf(" ");
        int descriptionEndIndex = userInput.indexOf("/by");
        String description = userInput.substring(descriptionStartIndex, descriptionEndIndex);
        // take the deadline
        String by = userInput.substring(descriptionEndIndex + 4);
        String convertedBy = parseDate(by);
        // create deadline task to be passed over to the actual task array
        return new Deadline(description, convertedBy);
    }

    /**
     * makes sense of the user input for to-do task
     * @param userInput User input to parse
     * @return
     * @throws ToDoException if todotask is empty
     */
    public static ToDo parseToDo(String userInput) throws ToDoException {
        // take the description by finding the start index
        int descriptionStartIndex = userInput.indexOf(" ");
        String description = userInput.substring(descriptionStartIndex);
        if(descriptionStartIndex != 4 || description.isEmpty()){
            throw new ToDoException();
        }
        // create To-Do task for passing over the user input to the actual task array
        return new ToDo(description);
    }

    /**
     * makes sense of existing todo task from saved file
     * @param userData User Data of todo from saved file to parse
     * @return
     */
    public static ToDo parseExistingToDo(String userData) {
        // take out the description
        String description = userData.split("]")[2];

        // create To-Do task for passing over the user input to the actual task array
        return new ToDo(description);
    }

    /**
     * makes sense of existing deadline task from saved file
     * @param userData User Data of deadline from saved file to parse
     * @return
     */
    public static Deadline parseExistingDeadline(String userData) {
        // take indexes
        int descriptionStartIndex = userData.indexOf(" ");
        int descriptionEndIndex = userData.indexOf("by:");
        String description = userData.substring(descriptionStartIndex, descriptionEndIndex);
        // take the event time
        String by = userData.substring(descriptionEndIndex + 3);
        // create event task to be passed over to the actual task array
        return new Deadline(description, by);
    }

    /**
     * strips unused brackets from saved file
     * @param userData User Data to strip
     * @return
     */
    public static String stripBrackets(String userData) {
        // erase brackets
        userData = userData.replace("(", "");
        userData = userData.replace(")", "");
        return userData;
    }

    /**
     * makes sense of existing event task from saved file
     * @param userData User Data of event from saved file to parse
     * @return
     */
    public static Event parseExistingEvent(String userData) {
        // take indexes
        int descriptionStartIndex = userData.indexOf(" ");
        int descriptionEndIndex = userData.indexOf("at:");
        String description = userData.substring(descriptionStartIndex, descriptionEndIndex);
        // take the event time
        String at = userData.substring(descriptionEndIndex + 3);
        // create event task to be passed over to the actual task array
        return new Event(description, at);
    }

    /**
     * matches keyword in list and prints filtered list
     * @param userInput User Input as keyword
     */
    public static void find(String userInput) {
        userInput = userInput.split(" ")[1];
        TaskList.filteredList(userInput);
    }

    /**
     * handles date format
     * @param dateAndTime Date and/ or Time of the user input to parse
     * @return
     */
    public static String parseDate(String dateAndTime) {
        String[] dateComponent;
        String date;
        String time;
        String newDate;

        if (dateAndTime.contains(" ") && countSubstring(dateAndTime) == 2) {
            date = dateAndTime.split(" ")[0];
            time = dateAndTime.split(" ")[1];
            if (date.contains("/")) {
                dateComponent = date.split("/");
                newDate = dateComponent[2] + "-" + dateComponent[1] + "-" + dateComponent[0];
                if (time.contains(":")) {
                    return DateAndTime.convertDate(newDate) + ", " + DateAndTime.convertTime(time);
                }
                return DateAndTime.convertDate(newDate);
            }
        }
        return dateAndTime;
    }

    /**
     * counts string for date component
     * @param dateAndTime Date and/or Time to be counted its substrings
     * @return
     */
    public static int countSubstring(String dateAndTime){
        String[] substring = dateAndTime.split(" ");
        int count = 0;
        for(int i=0; i < substring.length; i++){
            count++;
        }
        return count;
    }
}

