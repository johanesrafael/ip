package Task.DukeHelper;

import Task.Exception.OtherException;
import Task.Exception.ToDoException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Parser {

    // insert user input to the list
    public static void insertToList(String userInput) throws OtherException, IOException {
        String userInputFirstWord = userInput.split(" ")[0];

        switch(userInputFirstWord) {
        case "todo":
            try {
                TaskList.insertToDo(userInput);
            } catch (ToDoException e) {
                TaskList.viewEmptyToDoMessage();
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
        createFile();
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
                createFile();
            }
            else if(userInput.startsWith("delete")){
                TaskList.delete(userInput);
                // update the file
                createFile();
            }
            else{
                // insert into list
                try {
                    insertToList(userInput);
                } catch (OtherException | IOException e){
                    TaskList.viewInvalidCommandMessage();
                }
                // print newly added task
                TaskList.printAddedTask();
            }
            // ask user input
            userInput = echo.nextLine();
        }
    }

}
