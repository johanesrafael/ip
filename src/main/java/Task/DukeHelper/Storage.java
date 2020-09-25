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
import java.util.Scanner;

public class Storage {
    /**
     * reads the file from designated path
     * @throws IOException
     * @throws OtherException
     */
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

    /**
     * checks if the file is existed or not. if not, creates new file
     * @param file
     * @throws IOException
     */
    private static void isFolderExisted(File file) throws IOException {
        if (!file.exists() && !file.isDirectory()) {
            Ui.noFileOrDirectoryMessage();
            // make new directory
            File dir = new File(Constants.folder);
            dir.mkdir();
            // create the file
            createFile();
        }
    }

    private static void isTaskDone(String userDataSymbol){
        if(userDataSymbol.equals("0")){
            TaskList.tasks.get(TaskList.tasks.size()-1).setDone();
        }
    }

    private static void passToToDo(String userData){
        ToDo task = Parser.parseExistingToDo(userData);

        // take the symbol
        String isTaskDone = userData.substring(4,5);
        // assign task into actual task and increment listCounter
        TaskList.tasks.add(task);
        // check whether the task is done
        isTaskDone(isTaskDone);
    }

    private static void passToDeadline(String userData){
        userData = Parser.stripBrackets(userData);

        Deadline task = Parser.parseExistingDeadline(userData);
        // take symbol
        String isTaskDone = userData.substring(4,5);
        // assign task into actual task and increment listCounter
        TaskList.tasks.add(task);
        // check whether the task is done
        isTaskDone(isTaskDone);
    }

    private static void passToEvent(String userData){
        // erase brackets
        userData = Parser.stripBrackets(userData);

        Event task = Parser.parseExistingEvent(userData);
        // take the symbol
        String isTaskDone = userData.substring(4,5);
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

    /**
     *  creates file if the file does not exist and rewrite file whenever it updates
     * @throws IOException
     */
    public static void createFile() throws IOException {
        // find file
        File file = new File(Constants.path, Constants.fileName);
        if (!file.exists()) {
            try {
                // make new file
                file.createNewFile();
            } catch (IOException e) {
                Ui.getCannotCreateFileMessage();
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
            Ui.getCannotRewriteMessage();
        }
    }
}
