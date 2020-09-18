package Task.DukeHelper;

import Task.Exception.OtherException;
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
import java.util.*;

public class Storage {
    public static void readFile() throws IOException, OtherException {
        // get path
        Path path2 = Paths.get(Constants.path, Constants.fileName);
        // find file
        File file = new File(Constants.path, Constants.fileName);
        // check file and its directory
        isFolderExisted(file);
        // change the file into string
        String fileData = Files.readString(path2, StandardCharsets.UTF_8);
        // scan the file data
        Scanner data = new Scanner(fileData);
        // make read by line then insert to the corresponding task
        while(data.hasNextLine()){
            insertExistingFileDataToTasks(data.nextLine());
        }
        data.close();
    }

    private static void isFolderExisted(File file) throws IOException {
        if (!file.exists() && !file.isDirectory()) {
            // make new directory
            File dir = new File(Constants.folder);
            dir.mkdir();
            // create the file
            createFile();
        }
    }

    private static void isTaskDone(String userDataSymbol){
        if(userDataSymbol.equals("\u2713")){
            TaskList.tasks.get(TaskList.tasks.size()-1).setDone();
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
        TaskList.tasks.add(task);
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
        TaskList.tasks.add(task);
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
        TaskList.tasks.add(task);
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
            for(int i = 0; i < TaskList.tasks.size(); i++){
                newFile.write(TaskList.tasks.get(i).toString());
                newFile.newLine();
            }
            newFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
