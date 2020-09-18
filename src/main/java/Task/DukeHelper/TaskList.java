package Task.DukeHelper;

import Task.Exception.ToDoException;
import Task.TaskType.Deadline;
import Task.TaskType.Event;
import Task.TaskType.Task;
import Task.TaskType.ToDo;

import java.util.ArrayList;

public class TaskList {
    // create task class for user's To-Do-List (max 100 items)
    public static ArrayList<Task> tasks = new ArrayList<>(Constants.MAX_LIST_SIZE);

    static void insertEvent(String userInput) {
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

    static void insertDeadline(String userInput) {
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

    static void insertToDo(String userInput) throws ToDoException {
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

    static void printAddedTask() {
        if (tasks.size() < 2) {
            Ui.getAddedTask();
        } else {
            Ui.getAddedTask();
        }
    }

    // view tasks
    public static void getList(){
        Ui.getListHeader();
        for(int i = 0; i < tasks.size(); i++){
            System.out.println("   " + (i + 1) + ". " + tasks.get(i));
        }
        Ui.lineSeparator();
    }

    static void setDone(String userInput) {
        // take out the word after "done"
        String indexString = userInput.split(" ")[1];
        // change the word into integer
        Integer indexTask = Integer.parseInt(indexString);
        // mark task as done
        tasks.get(indexTask-1).markAsDone();
    }

    static void delete(String userInput){
        // take out the word after "delete"
        String indexString = userInput.split(" ")[1];
        // change the word into integer
        Integer indexTask = Integer.parseInt(indexString);
        // view delete message first
        tasks.get(indexTask-1).deleteTaskMessage();
        // erase the selected task from the array
        tasks.remove(indexTask-1);
    }

}
