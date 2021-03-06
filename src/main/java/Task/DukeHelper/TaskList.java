/**
 * TaskList class that provides methods to access list of task
 */
package Task.DukeHelper;

import Task.Exception.ToDoException;
import Task.TaskType.Deadline;
import Task.TaskType.Event;
import Task.TaskType.Task;
import Task.TaskType.ToDo;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class TaskList {
    // create task class for user's To-Do-List (max 100 items)
    protected static ArrayList<Task> tasks = new ArrayList<>(Constants.MAX_LIST_SIZE);

    /**
     *  makes sense of user input then insert to task list
     * @param userInput User Input to insert
     */
    public static void insertEvent(String userInput) {
        Event task = Parser.parseEvent(userInput);
        // assign task into actual task and increment listCounter
        tasks.add(task);
    }

    /**
     *  makes sense of user input then insert to task list
     * @param userInput User Input to insert
     */
    public static void insertDeadline(String userInput) {
        Deadline task = Parser.parseDeadline(userInput);
        // assign task into actual task and increment listCounter
        tasks.add(task);
    }

    /**
     * makes sense of user input then insert to task list
     * @param userInput User Input to insert
     * @throws ToDoException if todo task is empty
     */
    public static void insertToDo(String userInput) throws ToDoException {
        ToDo task = Parser.parseToDo(userInput);
        // assign task into actual task and increment listCounter
        tasks.add(task);
    }

    public static void printAddedTask() {
        Ui.getAddedTask();
    }

    /**
     * shows the task list
     */
    public static void getList(){
        Ui.getListHeader();
        for(int i = 0; i < tasks.size(); i++){
            System.out.println("   " + (i + 1) + ". " + tasks.get(i));
        }
        Ui.lineSeparator();
    }

    /**
     * mark the selected task as done
     * @param userInput User Input to set as done
     */
    public static void setDone(String userInput) {
        // take out the word after "done"
        String indexString = userInput.split(" ")[1];
        // change the word into integer
        Integer indexTask = Integer.parseInt(indexString);
        // mark task as done
        tasks.get(indexTask-1).markAsDone();
    }

    /**
     * delete selected task
     * @param userInput User Input to delete
     */
    public static void delete(String userInput){
        // take out the word after "delete"
        String indexString = userInput.split(" ")[1];
        // change the word into integer
        Integer indexTask = Integer.parseInt(indexString);
        // view delete message first
        tasks.get(indexTask-1).deleteTaskMessage();
        // erase the selected task from the array
        tasks.remove(indexTask-1);
    }

    /**
     * makes new filtered list using the passed keyword
     * @param tasksData Task Data to filter
     * @param filterString Filter String as keyword
     * @return
     */
    public static ArrayList<Task> filterByString(ArrayList<Task> tasksData, String filterString) {
        ArrayList<Task> filteredTaskList = (ArrayList<Task>) tasksData.stream()
                .filter((s) -> s.getDescription().contains(filterString))
                .collect(toList());
        return filteredTaskList;
    }

    /**
     * prints the filtered list
     * @param userInput User Input to do filter
     */
    public static void filteredList(String userInput){
        Ui.showFilteredListHeader();
        ArrayList<Task> filteredTasks = filterByString(tasks, userInput);
        for(int i=0; i < filteredTasks.size(); i++){
            System.out.println("    " +  (i+1) + ". " + filteredTasks.get(i));
        }
        Ui.lineSeparator();
    }
}
