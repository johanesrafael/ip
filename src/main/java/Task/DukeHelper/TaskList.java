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
    public static ArrayList<Task> tasks = new ArrayList<>(Constants.MAX_LIST_SIZE);

    static void insertEvent(String userInput) {
        Event task = Parser.parseEvent(userInput);
        // assign task into actual task and increment listCounter
        tasks.add(task);
    }

    static void insertDeadline(String userInput) {
        Deadline task = Parser.parseDeadline(userInput);
        // assign task into actual task and increment listCounter
        tasks.add(task);
    }

    static void insertToDo(String userInput) throws ToDoException {
        ToDo task = Parser.parseToDo(userInput);
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

    public static ArrayList<Task> filterByString(ArrayList<Task> tasksData, String filterString) {
        ArrayList<Task> filteredTaskList = (ArrayList<Task>) tasksData.stream()
                .filter((s) -> s.getDescription().contains(filterString))
                .collect(toList());
        return filteredTaskList;
    }

    public static void filteredList(String userInput){
        Ui.showFilteredListHeader();
        for(Task t: filterByString(tasks, userInput)){
            System.out.println("    " +  t);
        }
        Ui.lineSeparator();
    }
}
